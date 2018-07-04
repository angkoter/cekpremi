package com.jatismobile.cekpremi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransactionsData {
	@Id
	String trxId;
	String merchantId;
	String orderId;
	String jatisProductCode;
	String billerProductCode;
	String type;
	String billerStatusCode;
	String billerDescriptions;
	@Column(columnDefinition = "TEXT")
	String billerRealResponse;
	String billerRefNum;
	String createdDate;
	String modifiedDate;
	double totalAmount;
	@ManyToOne
    @JoinColumn(name = "idVehicle")
	VehiclesData vehiclesData;
	
	public VehiclesData getVehiclesData() {
		return vehiclesData;
	}
	public void setVehiclesData(VehiclesData vehiclesData) {
		this.vehiclesData = vehiclesData;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getJatisProductCode() {
		return jatisProductCode;
	}
	public void setJatisProductCode(String jatisProductCode) {
		this.jatisProductCode = jatisProductCode;
	}
	public String getBillerProductCode() {
		return billerProductCode;
	}
	public void setBillerProductCode(String billerProductCode) {
		this.billerProductCode = billerProductCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getBillerStatusCode() {
		return billerStatusCode;
	}
	public void setBillerStatusCode(String billerStatusCode) {
		this.billerStatusCode = billerStatusCode;
	}
	public String getBillerDescriptions() {
		return billerDescriptions;
	}
	public void setBillerDescriptions(String billerDescriptions) {
		this.billerDescriptions = billerDescriptions;
	}
	public String getBillerRealResponse() {
		return billerRealResponse;
	}
	public void setBillerRealResponse(String billerRealResponse) {
		this.billerRealResponse = billerRealResponse;
	}
	public String getBillerRefNum() {
		return billerRefNum;
	}
	public void setBillerRefNum(String billerRefNum) {
		this.billerRefNum = billerRefNum;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
