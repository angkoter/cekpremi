
package com.jatismobile.cekpremi.logic;

import java.util.LinkedHashMap;

import com.jatismobile.cekpremi.model.DefaultRequest;
import com.jatismobile.cekpremi.model.DefaultResponse;

public interface CekPremiLogic {
	String saveData(DefaultRequest dataRequest);
	String hitToBiller(String url,String request);
	String updateData(String trxId,String responseFromBiller);
	String generateRequest(String trxId);
	DefaultResponse cekValidation(DefaultRequest request);
	String checkVehiclePlatNo(LinkedHashMap<String, String> request); 
}
