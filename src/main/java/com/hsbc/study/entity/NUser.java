package com.hsbc.study.entity;

public class NUser {
	public String userName;
	public String userPassword;
	public String email;
	public String userIdNumber;
	public NUser(String userName, String userPassword, String email, String userIdNumber) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = email;
		this.userIdNumber = userIdNumber;
	}
	public NUser(String userIdNumber,String userPassword,String userName){
		super();
		this.userPassword=userPassword;
		this.userIdNumber = userIdNumber;
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserIdNumber() {
		return userIdNumber;
	}
	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}
}
