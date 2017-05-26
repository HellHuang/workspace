package com.hsbc.study.entity;

import java.text.DecimalFormat;

public class UserTermCard {
	public String depositNumber;
	public String cardNumberFrom;
	public String userId;
	public String term;
	public String InterestRate;
	public String amount;
	public String createDate;
	public UserTermCard(String depositNumber, String cardNumberFrom, String userId, String term, String interestRate,
			String amount, String createDate) {
		super();
		this.depositNumber = depositNumber;
		this.cardNumberFrom = cardNumberFrom;
		this.userId = userId;
		this.term = term;
		this.InterestRate = interestRate;
		double b=Double.parseDouble(amount);
		String b2=new DecimalFormat(" ,###.00").format(b);
		this.amount = b2;
		this.createDate = createDate;
	}
	public String getDepositNumber() {
		return depositNumber;
	}
	public void setDepositNumber(String depositNumber) {
		this.depositNumber = depositNumber;
	}
	public String getCardNumberFrom() {
		return cardNumberFrom;
	}
	public void setCardNumberFrom(String cardNumberFrom) {
		this.cardNumberFrom = cardNumberFrom;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getInterestRate() {
		return InterestRate;
	}
	public void setInterestRate(String interestRate) {
		InterestRate = interestRate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		double b=Double.parseDouble(amount);
		String b2=new DecimalFormat(" ,###.00").format(b);
		this.amount = b2;
		this.amount = amount;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
