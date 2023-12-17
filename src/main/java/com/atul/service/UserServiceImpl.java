package com.atul.service;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atul.dto.UserDTO;
import com.atul.entity.User;
import com.atul.exception.WanderLustException;
import com.atul.repository.UserRepository;
import com.atul.utility.HashingUtility;


@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDTO authenticateUser(String contactNumber, String password) throws WanderLustException {

		User optionalUser = userRepository.findByContactNumber(contactNumber);
		if (optionalUser == null) {
			throw new WanderLustException("UserService.INVALID_CREDENTIALS");
		}

		String passwordFromDB = optionalUser.getPassword();

		if (passwordFromDB != null) {
			try {
				String hashedPassword = HashingUtility.getHashValue(password);
				if (hashedPassword.equals(passwordFromDB)) {
					UserDTO userObject = new UserDTO();
					userObject.setContactNumber(optionalUser.getContactNumber());
					userObject.setEmailId(optionalUser.getEmailId());
					userObject.setUserId(optionalUser.getUserId());
					userObject.setUserName(optionalUser.getUserName());
					return userObject;
				} else
					throw new WanderLustException("UserService.INVALID_CREDENTIALS");
			} catch (NoSuchAlgorithmException e) {
				throw new WanderLustException("UserService.HASH_FUNCTION_EXCEPTION");
			}

		} else
			throw new WanderLustException("UserService.INVALID_CREDENTIALS");

	}

	@Override
	public Integer registerUser(UserDTO user) throws WanderLustException {
User usr=userRepository.findByContactNumber(user.getContactNumber());
		if(usr!=null) {
			throw new WanderLustException("UserService.CONTACT_NUMBER_ALREADY_EXISTS");
		}
		User user1=new User();
		user1.setUserId(user.getUserId());
		user1.setUserName(user.getUserName());
		user1.setEmailId(user.getEmailId());
		user1.setContactNumber(user.getContactNumber());
		user1.setPassword(user.getPassword());
		if(user1.getPassword()==null) {
			throw new WanderLustException("UserService.INVALID_CREDENTIALS");		
		}
		else {
		String passwrd="";
		try {
			passwrd=HashingUtility.getHashValue(user.getPassword());
		}catch (NoSuchAlgorithmException e) {
			throw new WanderLustException("UserService.HASH_FUNCTION_EXCEPTION");
		}
		user1.setPassword(passwrd);
		userRepository.save(user1);
		}
     return user1.getUserId();
	}

}
