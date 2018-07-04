package com.jatismobile.cekpremi.contoller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jatismobile.cekpremi.constant.ResponseCode;
import com.jatismobile.cekpremi.constant.ResponseDescription;
import com.jatismobile.cekpremi.logic.CekPremiLogic;
import com.jatismobile.cekpremi.model.DefaultRequest;
import com.jatismobile.cekpremi.model.DefaultResponse;

//import com.jatismobile.cekpremi.impl.CekPremiImpl;
//import com.jatismobile.cekpremi.logic.DataRepository;

@RestController
@RequestMapping("/cekpremi")
public class CekPremi {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	CekPremiLogic cekpremilogic;
	@Value("${url}")
	String url;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public LinkedHashMap<String, Object> register(@Valid @RequestBody DefaultRequest dataRequest, BindingResult validations) {
		@SuppressWarnings("unchecked")
		DefaultResponse defaultResponse = new DefaultResponse(mapper.convertValue(dataRequest, LinkedHashMap.class));
		
		try {
			if (validations.hasErrors()) {
				defaultResponse.setErrMessage(ResponseDescription.BAD_REQUEST);
				defaultResponse.setErrStatus(ResponseCode.BAD_REQUEST);
				return mapper.convertValue(defaultResponse, LinkedHashMap.class);
			} else {
				defaultResponse = cekpremilogic.cekValidation(dataRequest);
				if (defaultResponse.getErrStatus() == ResponseCode.INVALID_CHECKSUM) {
					return mapper.convertValue(defaultResponse, LinkedHashMap.class);
				} else {
					String trxId = cekpremilogic.saveData(dataRequest);
					String resposeFromBiller = cekpremilogic.hitToBiller(url,cekpremilogic.generateRequest(trxId));
					cekpremilogic.updateData(trxId, resposeFromBiller);
					@SuppressWarnings("unchecked")
					LinkedHashMap<String, String> mapResponse =mapper.readValue(resposeFromBiller, LinkedHashMap.class);
					mapResponse.put("productCode", dataRequest.getProductCode());
					return mapper.convertValue(mapResponse, LinkedHashMap.class);
				}
			}

		} catch (JsonProcessingException e) {
			return null	;
		} catch (IOException e) {
			return null;
		}
	}
	
	
	@RequestMapping(value = "/CheckPlatNo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String CheckPlat(@RequestBody LinkedHashMap<String, String>dataRequest) {
		DefaultResponse defaultResponse = new DefaultResponse(mapper.convertValue(dataRequest, LinkedHashMap.class));
		try {
			for (HashMap.Entry<String, String> subEntry : dataRequest.entrySet()) {
				if(subEntry.getValue()== null){
					defaultResponse.setErrMessage(ResponseDescription.BAD_REQUEST);
					defaultResponse.setErrStatus(ResponseCode.BAD_REQUEST);
					return mapper.writeValueAsString(defaultResponse);
				}
			}
			return cekpremilogic.checkVehiclePlatNo(dataRequest);
			
		} catch (JsonProcessingException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
		
	}


}
