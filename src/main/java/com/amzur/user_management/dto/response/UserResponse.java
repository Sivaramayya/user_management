package com.amzur.user_management.dto.response;

import java.util.List;

public class UserResponse {
	
	private Long userId;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private List<OrderResponse> orderItems;

	
	
	public List<OrderResponse> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderResponse> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	
}
