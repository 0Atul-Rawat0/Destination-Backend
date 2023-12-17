package com.atul.service;

import com.atul.dto.UserDTO;
import com.atul.exception.WanderLustException;

public interface UserService {

	public UserDTO authenticateUser(String contactNumber, String password) throws WanderLustException;

	public Integer registerUser(UserDTO user) throws WanderLustException;
	
	
	
}
