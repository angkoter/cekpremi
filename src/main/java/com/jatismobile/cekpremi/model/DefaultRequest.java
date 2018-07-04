package com.jatismobile.cekpremi.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class DefaultRequest {
	@NotBlank
	String merchantId;
	@NotBlank
	String orderId;
	@NotBlank
	String custName;
	@NotBlank
	String custAddr;
	@NotBlank
	String vehicleBrand;
	@NotBlank
	String vehicleType;
	@NotBlank
	String transmission;
	@NotBlank
	String vehColor;
	@NotBlank
	String vehiclePlatNo;
	@NotBlank
	String vehicleYear;
	@NotBlank
	String imgVehRegNo;
	@NotBlank
	String imgCustId;
	@NotBlank
	String imgBodyNo;
	@NotBlank
	String imgVehPic;
	@NotBlank
	String productCode;
	@NotNull
	double totalAmount;
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@NotBlank
	String checksumHash;
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAddr() {
		return custAddr;
	}
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getVehColor() {
		return vehColor;
	}
	public void setVehColor(String vehColor) {
		this.vehColor = vehColor;
	}
	public String getVehiclePlatNo() {
		return vehiclePlatNo;
	}
	public void setVehiclePlatNo(String vehiclePlatNo) {
		this.vehiclePlatNo = vehiclePlatNo;
	}
	public String getVehicleYear() {
		return vehicleYear;
	}
	public void setVehicleYear(String vehicleYear) {
		this.vehicleYear = vehicleYear;
	}
	public String getImgVehRegNo() {
		return imgVehRegNo;
	}
	public void setImgVehRegNo(String imgVehRegNo) {
		this.imgVehRegNo = imgVehRegNo;
	}
	public String getImgCustId() {
		return imgCustId;
	}
	public void setImgCustId(String imgCustId) {
		this.imgCustId = imgCustId;
	}
	public String getImgBodyNo() {
		return imgBodyNo;
	}
	public void setImgBodyNo(String imgBodyNo) {
		this.imgBodyNo = imgBodyNo;
	}
	public String getImgVehPic() {
		return imgVehPic;
	}
	public void setImgVehPic(String imgVehPic) {
		this.imgVehPic = imgVehPic;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getChecksumHash() {
		return checksumHash;
	}
	public void setChecksumHash(String checksumHash) {
		this.checksumHash = checksumHash;
	}
	
	
}
