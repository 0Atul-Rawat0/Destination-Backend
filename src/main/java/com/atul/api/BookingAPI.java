package com.atul.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atul.dto.BookingDTO;
import com.atul.exception.DestinationException;
import com.atul.service.BookingService;



@CrossOrigin
@RestController
@RequestMapping(value="bookingAPI")
public class BookingAPI {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping(value="/bookings/{userId}/{destinationId}")
	public ResponseEntity<Integer> addBooking(@RequestBody BookingDTO booking ,
			@PathVariable Integer userId, @PathVariable String destinationId) throws DestinationException{
		Integer id=bookingService.addBooking(booking, userId, destinationId);
		
			
		return new ResponseEntity<Integer>(id,HttpStatus.CREATED);
	}
	@GetMapping(value="/{userId}")
	public ResponseEntity<List<BookingDTO>> getBookingRecords(@PathVariable Integer userId) throws Exception
	{
		List<BookingDTO> list=bookingService.getBooking(userId);
			
			return new ResponseEntity<List<BookingDTO>>(list,HttpStatus.OK);
		
		
	}
	
	@DeleteMapping(value="/bookings/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer bookingId)throws DestinationException
    {   
		bookingService.deleteBooking(bookingId);
    
        return new ResponseEntity<String>(HttpStatus.OK);
    }
	
	

}
