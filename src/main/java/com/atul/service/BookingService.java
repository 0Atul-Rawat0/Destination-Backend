package com.atul.service;

import java.util.List;

import com.atul.dto.BookingDTO;
import com.atul.exception.DestinationException;

public interface BookingService {

	public Integer addBooking(BookingDTO booking,Integer userId, String destinationId) throws DestinationException;
	public List<BookingDTO> getBooking(Integer userId) throws DestinationException;
	public String deleteBooking(Integer bookingId) throws DestinationException;
	
}
