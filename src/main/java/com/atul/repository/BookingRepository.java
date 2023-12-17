package com.atul.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.atul.entity.Booking;
import com.atul.entity.User;



public interface BookingRepository extends CrudRepository<Booking,Integer> {

	public List<Booking> findByUserEntity(User UserEntity);
	

}
