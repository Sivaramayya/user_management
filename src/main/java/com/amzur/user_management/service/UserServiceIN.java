
package com.amzur.user_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amzur.user_management.constants.ApplicationConstants;
import com.amzur.user_management.dto.request.UserRequest;
import com.amzur.user_management.dto.response.OrderResponse;
import com.amzur.user_management.dto.response.UserResponse;
import com.amzur.user_management.entities.UserEntity;
import com.amzur.user_management.handlers.ResourceNotAvailable;
import com.amzur.user_management.handlers.UserAlreadyExist;
import com.amzur.user_management.repository.UserRepository;
@Profile("in")
@Service
public class UserServiceIN implements UserService{

	
	private UserRepository userRepository;
	private final RestTemplate restTemplate;
	
	

     @Autowired                                                              
	public UserServiceIN(UserRepository userRepository, RestTemplate restTemplate) {
		super();
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
	}

	@Override
	public UserResponse save(UserRequest userRequest) {
		 Optional<UserEntity> existingUser = userRepository.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
		 
		 if(existingUser.isPresent()) {
			 throw new UserAlreadyExist(ApplicationConstants.USER_ALREADY_EXIST);
		 }else {
			 UserEntity userEntity=new UserEntity();
				BeanUtils.copyProperties(userRequest, userEntity);
				userEntity=userRepository.save(userEntity);
				 return convertEntityToResponse(userEntity);
		 }
		
		
//		UserResponse userResponse=new UserResponse();
//		BeanUtils.copyProperties(userEntity, userResponse);
	 
		
	}

	@Override
	public UserResponse findByEmail(String email,String password) {
		UserEntity userEntity=userRepository.findByEmailAndPassword(email,password).orElseThrow(()->new ResourceNotAvailable(ApplicationConstants.USER_NOT_FOUND));
				
		return convertEntityToResponse(userEntity);
		
		//UserResponse userResponse=new UserResponse();
		
		
		// by using setters and getters method
//		userResponse.setUserId(user.getUserId());
//		userResponse.setName(user.getName());
//		userResponse.setPhoneNumber(user.getPhoneNumber());
//		userResponse.setAddress(user.getAddress());
//		userResponse.setEmail(user.getEmail());
		
		
		//BeanUtils.copyProperties(userEntity, userResponse);
	
	}

	@Override
	public List<UserResponse> findAll() {
//		List<UserEntity> users=userRepository.findAll();
//		List<UserResponse>userResponses=new ArrayList<UserResponse>();
//		for(UserEntity userEntity:users) {
////			UserResponse userReponse=new UserResponse();
////			BeanUtils.copyProperties(userEntity, userReponse);
//			userResponses.add(convertEntityToResponse(userEntity));
//		}
		return userRepository.findAll().stream().map(this::convertEntityToResponse).collect(Collectors.toList()
				);
	}

	@Override
	public void deleteById(long userId) {
		  UserResponse userResponse=findById(userId);
		
		userRepository.deleteById(userResponse.getUserId());

	}
	@Override
	public UserResponse findById(Long id) {
		UserEntity userEntity=userRepository.findById(id).orElseThrow(()->new  ResourceNotAvailable(ApplicationConstants.RESOURCE_NOT_FOUND));
//		if(userEntityopt.isPresent()) {
//			userEntity=userEntityopt.get();
//			return convertEntityToResponse(userEntity);
//		}else {
//			throw new ResourceNotAvailable("User Not Found");
//		}
		return convertEntityToResponse(userEntity);
	
	}
	public void updateUser(UserRequest userRequest) {
		UserEntity userEntity=userRepository.findById(userRequest.getUserId()).orElseThrow(()->new  ResourceNotAvailable(ApplicationConstants.RESOURCE_NOT_FOUND));
		BeanUtils.copyProperties(userRequest, userEntity);
		 userRepository.save(userEntity);
		
	}
	public String getUserEmail(long userId) {
		UserEntity userEntity=userRepository.findById(userId).orElseThrow(()->new  ResourceNotAvailable(ApplicationConstants.RESOURCE_NOT_FOUND));
		return userEntity.getEmail();
	}
	public UserResponse getUserOrders(String email, String password) {
        UserResponse userResponse = findByEmail(email, password);
        
        	
            String url = "http://localhost:9191/order-management/orders/userId/" + userResponse.getUserId();
            
            ResponseEntity<List<OrderResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderResponse>>() {}
            );
            userResponse.setOrderItems(response.getBody()); ; 
            return userResponse;
        
    }
	

	public UserResponse convertEntityToResponse(UserEntity userEntity) {
		UserResponse userReponse=new UserResponse();
		BeanUtils.copyProperties(userEntity, userReponse);
		return userReponse;
	}

	

	

	

	
	

	
	
}
