package com.amzur.user_management.service;

import java.util.List;

import com.amzur.user_management.dto.request.UserRequest;
import com.amzur.user_management.dto.response.OrderResponse;
import com.amzur.user_management.dto.response.UserResponse;


public interface UserService {
	
	public UserResponse save(UserRequest userRequest);
	
	 public List<UserResponse> findAll();
	 
	public UserResponse findByEmail(String email,String password);

	public void deleteById(long userId);
	
	public UserResponse findById(Long id);

	public List<OrderResponse> getUserOrders(String email, String password);

	public String getUserEmail(long userId);

}
