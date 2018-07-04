package com.jatismobile.cekpremi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductsData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String jatisProductCode;
	String billerProductCode;
	String billerId;
	double amount;
	
	
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
