package com.picker.client;

public class Employee {			//Employee class with Getters, Setters and constructors.

	private String usernameLogin;


	private String userPasswordLogin;
	private String userID;
	private String userRights;
	
	public String getUserRights() {
		return userRights;
	}

	public void setUserRights(String userRights) {
		this.userRights = userRights;
	}


	
	public Employee(String usernameLogin, String userID) {
		this.usernameLogin = usernameLogin;
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUsernameLogin() {
		return usernameLogin;
	}
	public String getUserPasswordLogin() {
		return userPasswordLogin;
	}
	
	public void setUsernameLogin(String userNameLogin) {
		this.usernameLogin = userNameLogin;
	}
	public void setUserPasswordLogin(String userPasswordLogin) {
		this.userPasswordLogin = userPasswordLogin;
	}
	
	public Employee() {
	}


	public Employee(String userID, String userNameLogin, String userPasswordLogin) {
		this.userID = userID;
		this.usernameLogin = userNameLogin;
		this.userPasswordLogin = userPasswordLogin;
	}

	
}
