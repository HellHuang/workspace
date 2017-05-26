package com.hsbc.study.entity;

import java.text.DecimalFormat;

public class Bill {
	public String billNumber;
	public String billItem;
	
	public String billPeriod;
	public String billAmount;


	public Bill(String billNumber, String billItem, String billPeriod, String billAmount, String billStatus,
			String billUserId, String billUserName) {
		super();
		this.billNumber = billNumber;
		this.billItem = billItem;
		this.billPeriod = billPeriod;
		//this.billAmount = billAmount;
		double b=Double.parseDouble(billAmount);
		String b2=new DecimalFormat(" ,##0.00").format(b);
		this.billAmount=b2;
		this.billStatus = billStatus;
		this.billUserId = billUserId;
		this.billUserName = billUserName;
	}
	public Bill(String billNumber, String billItem, String billPeriod, String billAmount, String billStatus,
			String billUserId) {
		super();
		this.billNumber = billNumber;
		this.billItem = billItem;
		this.billPeriod = billPeriod;
		//this.billAmount = billAmount;
		double b=Double.parseDouble(billAmount);
		String b2=new DecimalFormat(" ,##0.00").format(b);
		this.billAmount=b2;
		this.billStatus = billStatus;
		this.billUserId = billUserId;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getBillItem() {
		return billItem;
	}
	public void setBillItem(String billItem) {
		this.billItem = billItem;
	}
	public String getBillPeriod() {
		return billPeriod;
	}
	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}
	public String getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String billAmount) {
		double b=Double.parseDouble(billAmount);
		String b2=new DecimalFormat(" ,##0.00").format(b);
		this.billAmount=b2;
		//this.billAmount = billAmount;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getBillUserId() {
		return billUserId;
	}
	public void setBillUserId(String billUserId) {
		this.billUserId = billUserId;
	}
	public String getBillUserName() {
		return billUserName;
	}
	public void setBillUserName(String billUserName) {
		this.billUserName = billUserName;
	}
	public String billStatus;
	public String billUserId;
	public String billUserName;
	
}
