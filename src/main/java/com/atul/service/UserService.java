package com.atul.service;

import com.atul.dto.UserDTO;
import com.atul.exception.DestinationException;

public interface UserService {

	public UserDTO authenticateUser(String contactNumber, String password) throws DestinationException;

	public Integer registerUser(UserDTO user) throws DestinationException;
	
	
	
}
