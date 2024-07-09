package com.amzur.user_management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amzur.user_management.dto.request.UserRequest;
import com.amzur.user_management.dto.response.OrderResponse;
import com.amzur.user_management.dto.response.UserResponse;
import com.amzur.user_management.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String register( @Valid @RequestBody UserRequest userRequest) {
		userService.save(userRequest);
		return "Successfully Registered";
	}
	
	@GetMapping
	public List<UserResponse> getAllUsers(){
		return userService.findAll();	
		
	}
	@PostMapping("/login")
	public List<OrderResponse> getUserDetails(@RequestParam("email") String email, @RequestParam("password") String password ) {
		return userService.getUserOrders(email,password);
		
	}
	
	@PutMapping
	public String updateUser(@RequestBody UserRequest userRequest) {
		userService.save(userRequest);
		return "Updated Successfully";
	
} 
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable long userId) {
	userService.deleteById(userId);
		return "Deleted Successfully";
	}
	
}
