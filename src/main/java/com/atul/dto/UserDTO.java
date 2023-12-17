package com.atul.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDTO {

	private Integer userId;
	@Pattern(regexp = "([A-Za-z])+(\\s[A-Za-z]+)*", message = "{invalid.customer.format}")
	private String userName;
	@NotNull(message = "{email.absent}")
	@Pattern(regexp = "[A-Za-z0-9]+@[A-Za-z]+[.]com", message = "{invalid.email.format}")
	private String emailId;
	@Pattern(regexp = "[6-9][0-9]{9}", message = "{invalid.phonenumber.format}")
	private String contactNumber;
	@NotNull(message = "{password.absent}")
	@Pattern(regexp = ".*[A-Z]+.*", message = "{invalid.password.format.uppercase}")
	@Pattern(regexp = ".*[a-z]+.*", message = "{invalid.password.format.lowercase}")
	@Pattern(regexp = ".*[0-9]+.*", message = "{invalid.password.format.number}")
	@Pattern(regexp = ".*[!@#$%^&*].*", message = "{invalid.password.format.specialcharacter}")
	private String password;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
