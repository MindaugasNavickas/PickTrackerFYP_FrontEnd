package com.picker.client;

public class Order {
	private String orderID;
	private String orderType;
	private String itemID;
	private String customerID;
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public Order() {
		
	}
	
	
	public Order(String orderID, String orderType, String itemID, String customerID) {
		this.orderID = orderID;
		this.orderType = orderType;
		this.itemID = itemID;
		this.customerID = customerID;
	}


	
}
