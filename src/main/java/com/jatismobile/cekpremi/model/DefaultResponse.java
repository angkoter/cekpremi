package com.jatismobile.cekpremi.model;

import java.util.LinkedHashMap;

import com.jatismobile.cekpremi.constant.ResponseCode;
import com.jatismobile.cekpremi.constant.ResponseDescription;

public class DefaultResponse {
	int errStatus;
	String errMessage;
	LinkedHashMap<String, Object> pushedData;

	public DefaultResponse( LinkedHashMap<String, Object> pushedData) {
		this.errStatus = ResponseCode.UNKNOWNN_ERROR;
		this.errMessage = ResponseDescription.UNKNOWNN_ERROR;
		this.pushedData = pushedData;
	}
	public int getErrStatus() {
		return errStatus;
	}
	public void setErrStatus(int errStatus) {
		this.errStatus = errStatus;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public LinkedHashMap<String, Object> getPushedData() {
		return pushedData;
	}
	public void setPushedData(LinkedHashMap<String, Object> pushedData) {
		this.pushedData = pushedData;
	}
	
	
}
