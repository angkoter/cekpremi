package com.jatismobile.cekpremi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VehiclesData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int idVehicle;
	String custName;
	String custAddr;
	String vehicleBrand;
	String vehicleType;
	String transmission;
	String vehColor;
	String vehiclePlatNo;
	String vehicleYear;
	String imgVehRegNo;
	String imgCustId;
	String imgBodyNo;
	String imgVehPic;
	String policyNo;
	String policyUrl;
	
	
	
	public int getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}
	public String getVehiclePlatNo() {
		return vehiclePlatNo;
	}
	public void setVehiclePlatNo(String vehiclePlatNo) {
		this.vehiclePlatNo = vehiclePlatNo;
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
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyUrl() {
		return policyUrl;
	}
	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}
	
}
