package com.atul.repository;

import org.springframework.data.repository.CrudRepository;

import com.atul.entity.User;
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByContactNumber(String contactNumber);

}