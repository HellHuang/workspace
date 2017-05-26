package com.hsbc.study.entity;

public class Transaction {
	private String transactionId;
	private String transactionDate;
	private String transactionType;
	private String transactionAmount;
	private String cardFrom;
	public Transaction(String transactionDate,String transactionType,String transactionAmount){
		super();
		this.transactionDate=transactionDate;
		this.transactionType=transactionType;
		this.transactionAmount=transactionAmount;
		
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getCardFrom() {
		return cardFrom;
	}
	public void setCardFrom(String cardFrom) {
		this.cardFrom = cardFrom;
	}
	public String getCardTo() {
		return cardTo;
	}
	public void setCardTo(String cardTo) {
		this.cardTo = cardTo;
	}
	private String cardTo;
}
