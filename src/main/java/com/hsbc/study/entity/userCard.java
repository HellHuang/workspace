package com.hsbc.study.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class userCard {
	private String userId;
	private String userName;
	private String cardNumber;
	private String balance; 
	private String status;
	private String currencyInfo;
	public userCard(String cardNumber,String balance,String status,String userName,String currencyInfo){
		this.cardNumber = cardNumber;
		double b=Double.parseDouble(balance);
		String b2=new DecimalFormat(" ,##0.00").format(b);
		this.balance=b2;
		//this.balance =  balance;
		this.status = status;
		this.userName=userName;
		this.currencyInfo=currencyInfo;
	}
	public userCard(String cardNumber,String balance,String status){
		this.cardNumber = cardNumber;
		double b=Double.parseDouble(balance);
		String b2=new DecimalFormat(" ,##0.00").format(b);
		this.balance=b2;
		//this.balance =  balance;
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.cardNumber = cardNumber;
		double b=Double.parseDouble(balance);
		String b2=new DecimalFormat("  ,##0.00").format(b);
		this.balance=b2;
		//this.balance =  balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrencyInfo() {
		return currencyInfo;
	}
	public void setCurrencyInfo(String currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
}
