package com.atul.service.test;

//comment
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import com.atul.dto.BookingDTO;
import com.atul.dto.DestinationDTO;
import com.atul.dto.DetailsDTO;
import com.atul.dto.ItineraryDTO;
import com.atul.dto.UserDTO;
import com.atul.entity.Booking;
import com.atul.entity.Destination;
import com.atul.entity.Details;
import com.atul.entity.Itinerary;

import com.atul.dto.UserDTO;
import com.atul.entity.User;
import com.atul.exception.WanderLustException;
import com.atul.repository.BookingRepository;
import com.atul.repository.PackageRepository;
import com.atul.repository.UserRepository;
import com.atul.dto.BookingDTO;
import com.atul.dto.DestinationDTO;
import com.atul.dto.DetailsDTO;
import com.atul.dto.ItineraryDTO;

import com.atul.entity.Booking;
import com.atul.entity.Destination;
import com.atul.entity.Details;
import com.atul.entity.Itinerary;


import com.atul.service.BookingServiceImpl;

@SpringBootTest
public class BookingServiceTest {


		
	@Mock
	private UserRepository userRepository;
		
	@Mock
	private	PackageRepository packageRepository;
	
	@Mock
	private	BookingRepository bookRepository;
	@InjectMocks
		 BookingServiceImpl bookServiceImpl=new BookingServiceImpl();
	
	

	
	
	

	@Test
	public void invalidBookingInvalidUserIdTest() throws Exception {
		BookingDTO booking=new BookingDTO();
		booking.setBookingId(10);
		booking.setCheckIn(LocalDate.now());
		booking.setCheckOut(LocalDate.of(2024, 1, 1));
		booking.setNoOfPeople(5);
		booking.setTimeOfBooking(LocalDateTime.now());
		booking.setTotalCost(76842);
		
		UserDTO user=new UserDTO();
		user.setUserId(3);
		
		booking.setUsers(user);
		DestinationDTO destination=new DestinationDTO();
		DetailsDTO d=new DetailsDTO();
		d.setDetailsId("D1111");
		ItineraryDTO i=new ItineraryDTO();
		i.setItineraryId("I111");
		d.setItinerary(i);
		destination.setDetails(d);
		destination.setDestinationId("D1001");
		
		booking.setDestination(destination);
		
				
		
		
		

		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> bookServiceImpl.addBooking(booking,user.getUserId(),destination.getDestinationId()));
		Assertions.assertEquals("BookingService.BOOKING_ERROR", exception.getMessage());

	}
	@Test
	public void invalidBookingInvalidDestinationIdTest() throws Exception {
		BookingDTO booking=new BookingDTO();
		booking.setBookingId(10);
		booking.setCheckIn(LocalDate.now());
		booking.setCheckOut(LocalDate.of(2024, 1, 1));
		booking.setNoOfPeople(5);
		booking.setTimeOfBooking(LocalDateTime.now());
		booking.setTotalCost(76842);
		
		UserDTO user=new UserDTO();
		user.setUserId(3);
		booking.setUsers(user);
		
		DestinationDTO destination=new DestinationDTO();
		DetailsDTO d=new DetailsDTO();
		d.setDetailsId("D1111");
		
		ItineraryDTO i=new ItineraryDTO();
		i.setItineraryId("I111");
		d.setItinerary(i);
		
		destination.setDetails(d);
		destination.setDestinationId("D1001");
		booking.setDestination(destination);
		
		User user1=new User();
		user1.setUserId(101);
		
		
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user1));
		Mockito.when( packageRepository.findById(anyString())).thenReturn(Optional.empty());

		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> bookServiceImpl.addBooking(booking,user.getUserId(),destination.getDestinationId()));
		Assertions.assertEquals("BookingService.NO_BOOKING", exception.getMessage());

	}
	
	@Test
	public void validBookingTest() throws Exception {
		BookingDTO booking=new BookingDTO();
		booking.setBookingId(10);
		booking.setCheckIn(LocalDate.now());
		booking.setCheckOut(LocalDate.of(2024, 1, 1));
		booking.setNoOfPeople(5);
		booking.setTimeOfBooking(LocalDateTime.now());
		booking.setTotalCost(76842);
		
		UserDTO user=new UserDTO();
		user.setUserId(101);
		booking.setUsers(user);
		
		DestinationDTO destination=new DestinationDTO();
		DetailsDTO d=new DetailsDTO();
		d.setDetailsId("D1111");
		
		ItineraryDTO i=new ItineraryDTO();
		i.setItineraryId("I111");
		d.setItinerary(i);
		
		destination.setDetails(d);
		destination.setDestinationId("D1001");
		booking.setDestination(destination);
		
		User user1=new User();
		user1.setUserId(101);
		
		Destination des=new Destination();
		des.setDestinationId("D1001");
		
		Booking book=new Booking();
		book.setBookingId(10);
		book.setCheckIn(LocalDate.now());
		book.setCheckOut(LocalDate.of(2024, 1, 1));
		book.setNoOfPeople(5);
		book.setTimeOfBooking(LocalDateTime.now());
		book.setTotalCost(76842);
		
		
		book.setUserEntity(user1);
		
		
		Details d1=new Details();
		d1.setDetailsId("D1111");
		
		Itinerary i1=new Itinerary();
		i1.setItineraryId("I111");
		d1.setItinerary(i1);
		
		des.setDetails(d1);
		
		book.setDestinationEntity(des);
		
		
		
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user1));
		Mockito.when( packageRepository.findById(anyString())).thenReturn(Optional.of(des));
		Mockito.when( bookRepository.save(any())).thenReturn(book);
		

		Integer id= bookServiceImpl.addBooking(booking,user.getUserId(),destination.getDestinationId());
		Assertions.assertEquals(10, id);

	}
	@Test
	public void invalidDeleteBookingInvalidIdTest() throws Exception {
		BookingDTO booking=new BookingDTO();
		booking.setBookingId(10);
		
		Mockito.when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> bookServiceImpl.deleteBooking(booking.getBookingId()));
		Assertions.assertEquals("BookingService.NO_BOOKING", exception.getMessage());

	}
	
	@Test
	public void validDeleteBookingTest() throws Exception {
		BookingDTO booking=new BookingDTO();
		booking.setBookingId(10);
		Booking book=new Booking();
		book.setBookingId(10);
		book.setCheckIn(LocalDate.now());
		book.setCheckOut(LocalDate.of(2024, 1, 1));
		book.setNoOfPeople(5);
		book.setTimeOfBooking(LocalDateTime.now());
		book.setTotalCost(76842);
		
		Mockito.when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
		String msg=bookServiceImpl.deleteBooking(booking.getBookingId());
		Assertions.assertEquals(booking.getBookingId()+"",msg);

	}
	@Test
	public void InvalidGetBookingRecordsTest() throws Exception{
		Integer userId=500;
		User a=new User();
		a.setUserId(500);
		UserDTO ad=new UserDTO();
		ad.setUserId(500);
		Mockito.when(userRepository.findById(500)).thenReturn (Optional.empty());
		WanderLustException exception=Assertions.assertThrows(WanderLustException.class, ()-> bookServiceImpl.getBooking(a.getUserId()));
		Assertions.assertEquals("BookingService.NO_BOOKING", exception.getMessage());
	}

	@Test
	public void InvalidGetBookingBookingError() throws Exception{
		Integer userId=659;
		User a=new User();
		a.setUserId(659);
		UserDTO ad=new UserDTO();
		ad.setUserId(111);
		Mockito.when(userRepository.findById(659)).thenReturn (Optional.of(a));
		Mockito.when(bookRepository.findByUserEntity(a)).thenReturn(List.of());
		WanderLustException exception=Assertions.assertThrows(WanderLustException.class, ()-> bookServiceImpl.getBooking(659));
		Assertions.assertEquals("BookingService.BOOKING_ERROR", exception.getMessage());
	}
	

	
	
	
	
	
	
@Test
public void getBookingvalid() throws WanderLustException
{
	Booking b4=new Booking();
	b4.setBookingId(110);
	b4.setNoOfPeople(2);
	b4.setCheckIn(LocalDate.now());
	b4.setCheckOut(LocalDate.now());
	
	Destination de3=new Destination();
	de3.setDestinationId("D1012");
	
	Details details=new Details();
	details.setDetailsId("DL161");
	
	Itinerary itinerary =new Itinerary();
	itinerary.setItineraryId("I1071");
	details.setItinerary(itinerary);
	de3.setDetails(details);
	
	User u2=new User();
	u2.setUserId(107);
	
	b4.setDestinationEntity(de3);
	b4.setUserEntity(u2);
	
	List<Booking> booking=new ArrayList<>();
	booking.add(b4);
	
	Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(u2));
	Mockito.when(userRepository.save(u2)).thenReturn(u2);
	Mockito.when(bookRepository.save(b4)).thenReturn(b4);
	Mockito.when(bookRepository.findByUserEntity(u2)).thenReturn(booking);
	
	List<BookingDTO> bookingDTO=bookServiceImpl.getBooking(110);
	Assertions.assertEquals(bookingDTO.get(0).getBookingId(), booking.get(0).getBookingId());



	
}
	


		

	
}