package com.atul.service;

import java.util.List;

import com.atul.dto.BookingDTO;
import com.atul.exception.WanderLustException;

public interface BookingService {

	public Integer addBooking(BookingDTO booking,Integer userId, String destinationId) throws WanderLustException;
	public List<BookingDTO> getBooking(Integer userId) throws WanderLustException;
	public String deleteBooking(Integer bookingId) throws WanderLustException;
	
}
