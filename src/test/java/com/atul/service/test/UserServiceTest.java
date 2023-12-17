package com.atul.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.atul.dto.UserDTO;
import com.atul.entity.User;
import com.atul.exception.DestinationException;
import com.atul.repository.UserRepository;
import com.atul.service.UserService;
import com.atul.service.UserServiceImpl;
import com.atul.utility.HashingUtility;

@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	 private UserService userServiceImpl = new UserServiceImpl();

	@Test
	public void invalidLoginInvalidUserTest() throws Exception {
		User user = new User();

		String contactNo = "1234567890";
		String password = "abcd";

		user.setPassword("xyz");

		Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
		DestinationException exception = Assertions.assertThrows(DestinationException.class,
				() -> userServiceImpl.authenticateUser(contactNo, password));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

	}
	@Test
	public void validAuthenticateUser() throws Exception{
		UserDTO userdto=new UserDTO();
		userdto.setContactNumber("9876543210");
		userdto.setEmailId("Ram@exm.com");
		userdto.setPassword("Ram#12345");
		userdto.setUserName("Ram123");
		userdto.setUserId(99);
		
		User user=new User();
		user.setContactNumber(userdto.getContactNumber());
		user.setEmailId(user.getEmailId());
		String hashCode=HashingUtility.getHashValue("Ram#12345");
		user.setPassword(hashCode);
		user.setUserName(userdto.getUserName());
		user.setUserId(userdto.getUserId());
		Mockito.when(userRepository.findByContactNumber(userdto.getContactNumber())).thenReturn(user);
		UserDTO userdto2=userServiceImpl.authenticateUser(userdto.getContactNumber(), userdto.getPassword());
		Assertions.assertEquals(userdto.getUserId(),userdto2.getUserId());
		
				
	}
	@Test
	public void noUserFoundAuthenticateTest() throws Exception{
		Mockito.when(userRepository.findByContactNumber("703208765")).thenReturn(null);
		DestinationException wle=Assertions.assertThrows(DestinationException.class,()->userServiceImpl.authenticateUser("703208765","Qwert@123"));
		
				Assertions.assertEquals("UserService.INVALID_CREDENTIALS",wle.getMessage());
	}
	@Test
	public void nullUserPassword() throws Exception{
		UserDTO userdto=new UserDTO();
		userdto.setContactNumber("9876543210");
		userdto.setEmailId("Ram@exm.com");
		userdto.setPassword("Ram#12345");
		userdto.setUserName("Ram123");
		userdto.setUserId(99);
		User user=new User();
		user.setContactNumber(userdto.getContactNumber());
		user.setEmailId(userdto.getEmailId());
		user.setPassword(null);
		user.setUserName(userdto.getUserName());
		user.setUserId(userdto.getUserId());
		Mockito.when(userRepository.findByContactNumber(userdto.getContactNumber())).thenReturn(user);
		DestinationException excp= Assertions.assertThrows(DestinationException.class,()->userServiceImpl.authenticateUser(userdto.getContactNumber(), null));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", excp.getMessage());		
	}
	
	
	
	
	
	
	
	@Test
	public void validRegisterUser() throws Exception{
		UserDTO user1=new UserDTO();
		user1.setContactNumber("9876543210");
		user1.setEmailId("Shyam@gmail.com");
		user1.setPassword("Shyam@1234");
		user1.setUserName("Shyam");
		User user=new User();
		user.setContactNumber(user1.getContactNumber());
		user.setEmailId(user1.getEmailId());
		user.setPassword(HashingUtility.getHashValue(user1.getPassword()));
		user.setUserName("Shyam");
		
		Mockito.when(userRepository.findByContactNumber(Mockito.anyString())).thenReturn(null);

		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		Integer id=userServiceImpl.registerUser(user1);
		Assertions.assertEquals(user.getUserId(),id);
				
		
	}
	@Test
	public void userAlreadyExistsTest() throws Exception{
		UserDTO userdto=new UserDTO();
		userdto.setContactNumber("9876543210");
		userdto.setEmailId("Shyam@gmail.com");
		userdto.setPassword("Shyam@1234");
		userdto.setUserName("Shyam");
		User user=new User();
		user.setContactNumber(userdto.getContactNumber());
		user.setEmailId(userdto.getEmailId());
		user.setPassword(HashingUtility.getHashValue(userdto.getPassword()));
		user.setUserName(userdto.getUserName());
		
		Mockito.when(userRepository.findByContactNumber(userdto.getContactNumber())).thenReturn(user);
		DestinationException excp=Assertions.assertThrows(DestinationException.class,()->userServiceImpl.registerUser(userdto));
		Assertions.assertEquals("UserService.CONTACT_NUMBER_ALREADY_EXISTS",excp.getMessage());
		
	}
	

	

	

	

}

