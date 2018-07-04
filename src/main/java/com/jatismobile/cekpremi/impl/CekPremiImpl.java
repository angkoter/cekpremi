package com.jatismobile.cekpremi.impl;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jatismobile.cekpremi.constant.ResponseCode;
import com.jatismobile.cekpremi.constant.ResponseDescription;
import com.jatismobile.cekpremi.constant.TransactionType;
import com.jatismobile.cekpremi.logic.CekPremiLogic;
import com.jatismobile.cekpremi.logic.ProductsRepo;
import com.jatismobile.cekpremi.logic.TransactionsRepo;
import com.jatismobile.cekpremi.logic.VehiclesRepo;
import com.jatismobile.cekpremi.model.DefaultRequest;
import com.jatismobile.cekpremi.model.DefaultResponse;
import com.jatismobile.cekpremi.model.ProductsData;
import com.jatismobile.cekpremi.model.TransactionsData;
import com.jatismobile.cekpremi.model.VehiclesData;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

@Service
public class CekPremiImpl implements CekPremiLogic {
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	VehiclesRepo vehiclesRepo;
	@Autowired
	TransactionsRepo transactionsRepo;
	@Autowired
	ProductsRepo productsRepo;
	@Value("${merchantId}")
	String merchantId;
	@Value("${sharedKey}")
	String sharedKey;
	@Value("${apiKey}")
	String apiKey;

	@SuppressWarnings("unchecked")
	@Override
	public String saveData(DefaultRequest dataRequest) {
		String trxid = generateId();

		TransactionsData transactionsData = new TransactionsData();
		VehiclesData vehiclesData = new VehiclesData();
		HashMap<String, String> mapRequest = mapper.convertValue(dataRequest, HashMap.class);
		HashMap<String, Object> mapTransData = mapper.convertValue(transactionsData, HashMap.class);
		HashMap<String, String> mapVechData = mapper.convertValue(vehiclesData, HashMap.class);
		for (HashMap.Entry<String, String> subEntry : mapRequest.entrySet()) {
			if (mapTransData.containsKey(subEntry.getKey())) {
				mapTransData.put(subEntry.getKey(), subEntry.getValue());
			} else if (mapVechData.containsKey(subEntry.getKey())) {
				mapVechData.put(subEntry.getKey(), subEntry.getValue());
			}else if(subEntry.getKey().equals("productCode")){
				mapTransData.put("jatisProductCode", subEntry.getValue());
			}else if(subEntry.getKey().equals("totalAmount")){
				mapTransData.put("totalAmount", subEntry.getValue());
			}
			
		}
		ProductsData productsData  = productsRepo.findByJatisProductCodeAndBillerId(dataRequest.getProductCode(), "1");
		mapTransData.put("billerProductCode", productsData.getBillerProductCode());
		mapTransData.put("type", TransactionType.REGISTER);
		transactionsData = mapper.convertValue(mapTransData, TransactionsData.class);
		vehiclesData = mapper.convertValue(mapVechData, VehiclesData.class);
		transactionsData.setVehiclesData(vehiclesData);
		transactionsData.setTrxId(trxid);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateCreated = dateFormat.format(new Date());

		transactionsData.setCreatedDate(dateCreated);
		transactionsData.setModifiedDate(dateCreated);
		vehiclesRepo.save(vehiclesData);
		transactionsRepo.save(transactionsData);
//em.out.println(transactionsData.getTotalAmount());
		return trxid;

	}

	@Override
	public String hitToBiller(String url, String request) {
		String responseBiller = null;
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}
			} };

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient okHttpClient = new OkHttpClient();
			okHttpClient.setSslSocketFactory(sslSocketFactory);
			okHttpClient.setHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					// TODO Auto-generated method stub
					return true;
				}
			});
			RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), request);
			Request r = new Request.Builder().url(url).post(body).build();;
			Response response = okHttpClient.newCall(r).execute();
			responseBiller = response.body().string();

		} catch (Exception e) {
			e.getMessage();
		}
		return responseBiller;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String updateData(String trxId, String responseFromBiller) {
		TransactionsData transactionsData = transactionsRepo.findByTrxId(trxId);
		VehiclesData vehiclesData = transactionsData.getVehiclesData();
		try {
			HashMap<String, Object> mapResponseFromBiller = mapper.readValue(responseFromBiller, HashMap.class);
			for (HashMap.Entry<String, Object> subEntry : mapResponseFromBiller.entrySet()) {
				switch (subEntry.getKey()) {
				case "errStatus":
					transactionsData.setBillerStatusCode(subEntry.getValue().toString());
					break;
				case "errMessage":
					transactionsData.setBillerDescriptions(subEntry.getValue().toString());
					break;
				case "policyNo":
					transactionsData.setBillerRefNum(subEntry.getValue().toString());
					vehiclesData.setPolicyNo(subEntry.getValue().toString());
					break;
				case "policyUrl":
					vehiclesData.setPolicyUrl(subEntry.getValue().toString());
					break;
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datemodified = dateFormat.format(new Date());
			transactionsData.setModifiedDate(datemodified);
			transactionsData.setBillerRealResponse(responseFromBiller);
			vehiclesRepo.save(vehiclesData);
			transactionsData.setVehiclesData(vehiclesData);
			transactionsRepo.save(transactionsData);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return transactionsData.toString();
	}

	public String generateId() {
		String output = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		output = dateFormat.format(new Date());
		String id = DigestUtils.md5Hex(output);
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DefaultResponse cekValidation(DefaultRequest request) {
		DefaultResponse defaultResponse = new DefaultResponse(mapper.convertValue(request, LinkedHashMap.class));
		
		 if(!request.getChecksumHash().equalsIgnoreCase(DigestUtils.sha1Hex(merchantId+sharedKey+request.getOrderId()))){
//			 System.out.println("method checksum");
				defaultResponse.setErrStatus(ResponseCode.INVALID_CHECKSUM);
				defaultResponse.setErrMessage(ResponseDescription.INVALID_CHECKSUM);
			}else{
				defaultResponse.setErrStatus(ResponseCode.SUCCESS);
			}
			return defaultResponse;	
		// TODO Auto- method stub
		
	}

	@Override
	public String generateRequest(String trxId) {
		String requestToBiller ="";
		TransactionsData transactionsData = transactionsRepo.findByTrxId(trxId);
		VehiclesData vehiclesData = transactionsData.getVehiclesData();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> mapRequestToBiller = mapper.convertValue(vehiclesData, HashMap.class);
		mapRequestToBiller.put("apiKey", apiKey);
		mapRequestToBiller.put("trxId", transactionsData.getTrxId());
		mapRequestToBiller.put("productId", transactionsData.getBillerProductCode());
		mapRequestToBiller.put("trxDate", transactionsData.getCreatedDate().split(" ")[0]);
		mapRequestToBiller.remove("idVechicle");
		mapRequestToBiller.remove("dateCreated");
		mapRequestToBiller.remove("dateModified");
		mapRequestToBiller.remove("idVechicle");
		mapRequestToBiller.values().removeIf(Objects::isNull);
		
		try {
			requestToBiller = mapper.writeValueAsString(mapRequestToBiller);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requestToBiller;
	}

	@Override
	public String checkVehiclePlatNo(LinkedHashMap<String, String> request) {
		DefaultResponse defaultResponse = new DefaultResponse(mapper.convertValue(request, LinkedHashMap.class));
//		System.out.println(request.get("vehiclePlatNo"));
//		TransactionsData transactionsData= transactionsRepo.findByVehiclePlatNo(request.get("vehiclePlatNo"));
		List<TransactionsData> transactionsDatas = transactionsRepo.findByTypeAndVehiclePlatNo(request.get("productCode"), request.get("vehiclePlatNo"));
		if(transactionsDatas.size()>0){
//			System.out.println("plat no "+ ResponseDescription.ALREADY_REGISTERED);
			defaultResponse.setErrStatus(ResponseCode.ALREADY_REGISTERED);
			defaultResponse.setErrMessage(ResponseDescription.ALREADY_REGISTERED);
			try {
				return mapper.writeValueAsString(defaultResponse);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				return "";
			}
		}else{
//			System.out.println("plat no "+ ResponseDescription.NOT_REGISTERED_YET);
			ProductsData productsData  = productsRepo.findByJatisProductCodeAndBillerId(request.get("productCode"), "1");
			LinkedHashMap<String, Object> response = new LinkedHashMap<>();
			if(productsData!=null){
				defaultResponse.setErrStatus(ResponseCode.NOT_REGISTERED_YET);
				defaultResponse.setErrMessage(ResponseDescription.NOT_REGISTERED_YET);
				response= mapper.convertValue(defaultResponse, LinkedHashMap.class);
				response.put("totalAmount", productsData.getAmount());
			}else{
				defaultResponse.setErrStatus(ResponseCode.INVALID_PRODUCT_CODE);
				defaultResponse.setErrMessage(ResponseDescription.INVALID_PRODUCT_CODE);
				response= mapper.convertValue(defaultResponse, LinkedHashMap.class);
			}
			try {
				return mapper.writeValueAsString(response);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				return "";
			}
		}
		
		
	}

}
