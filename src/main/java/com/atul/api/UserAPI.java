package com.atul.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atul.dto.UserDTO;
import com.atul.exception.DestinationException;
import com.atul.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("userAPI")
public class UserAPI {

	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;

	@PostMapping(value = "/userLogin")
	public ResponseEntity<UserDTO> authenticateUser( @RequestBody UserDTO user) throws DestinationException {

			UserDTO userFromDB = userService.authenticateUser(user.getContactNumber(), user.getPassword());
			return new ResponseEntity<UserDTO>(userFromDB, HttpStatus.OK);
	}

	@PostMapping(value = "/userRegister")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO user) throws DestinationException {		
		
		userService.registerUser(user);
		String mess=environment.getProperty("UserAPI.REGISTER_USER_SUCCESS2");
		return new ResponseEntity<>(mess, HttpStatus.CREATED);

	
	}

}
