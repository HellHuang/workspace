package com.hsbc.study.entity;

public class Fund {
	private String name;
	private String type;
	private String code;
	private String returnRate;
	private double nav;
	private double income;
	private String cardNumber;
	private double redeemTotal;
	private String sum;
	private String amount;
	
	public Fund(String name,String code,String type,String returnRate){
		this.name=name;
		this.code=code;
		this.type=type;
		this.returnRate=returnRate;
	}
	public Fund(String name,String code,String type,String returnRate,double nav){
		this.name=name;
		this.code=code;
		this.type=type;
		this.returnRate=returnRate;
		this.nav=nav;
	}
	public Fund(String name,String code,String type,String returnRate,double income,double redeemTotal,String cardNumber,String amount,String sum){
		this.name=name;
		this.code=code;
		this.type=type;
		this.returnRate=returnRate;
		this.income=income;
		this.cardNumber=cardNumber;
		this.redeemTotal=redeemTotal;
		this.amount=amount;
		this.sum=sum;
	}
	public String getSum(){
		return sum;
	}
	public void setSum(String sum){
		this.sum=sum;
	}
	public String getAmount(){
		return amount;
	}
	public void setAmount(String amount){
		this.amount=amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getIncome() {
		return income;
	}
	public void setSum(double income) {
		this.income = income;
	}
	
	public double getredeemTotal() {
		return redeemTotal;
	}
	public void setredeemTotal(double redeemTotal) {
		this.redeemTotal = redeemTotal;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReturnRate() {
		return returnRate;
	}
	public void setReturnRate(String returnRate) {
		this.returnRate = returnRate;
	}
	public String toString(){
		return name+code+type+returnRate;
	}
	public void setNav(double nav) {
		this.nav = nav;
	}
	public double getNav(){
		return nav;
	}
}
