package com.example.website.entities;



public class Login{
	
	private long loginId;
	
	
	private String userMailId;
	
	
	private String userPassword;
	
	
	public long getLoginId() {
		return loginId;
	}
	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}
	public String getUserMailId(){
		return userMailId;
	}
	public void setUserMailId(String userMailId){
		this.userMailId = userMailId;
	}
	public String getUserPassword(){
		return userPassword;
	}
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;

	}
}