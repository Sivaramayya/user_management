package com.amzur.user_management.dto.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class UserRequest {
	private Long userId;
	 @NotNull(message = "Please provide name")
	    @NotEmpty(message = "name should not be empty")
	private String name;
	 @NotNull(message = "Please provide Phone Number")
	    @NotEmpty(message = "Phone Number should not be empty")
	private String phoneNumber;
	 @NotNull(message = "Please provide Email")
	    @NotEmpty(message = "Email should not be empty")
	private String email;
	 @NotNull(message = "Please provide Password")
	    @NotEmpty(message = "Password should not be empty")
	private String password;
	 @NotNull(message = "Please provide Address")
	    @NotEmpty(message = "Address should not be empty")
	private String address;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	
}
