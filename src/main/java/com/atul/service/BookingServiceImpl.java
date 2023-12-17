package com.atul.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atul.dto.BookingDTO;
import com.atul.dto.DestinationDTO;
import com.atul.dto.DetailsDTO;
import com.atul.dto.ItineraryDTO;
import com.atul.dto.UserDTO;
import com.atul.entity.Booking;
import com.atul.entity.Destination;
import com.atul.entity.User;
import com.atul.exception.WanderLustException;
import com.atul.repository.BookingRepository;
import com.atul.repository.PackageRepository;
import com.atul.repository.UserRepository;

@Service(value = "bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	
	private UserRepository userRepo;
	@Autowired
	private PackageRepository packageRepo;

	@Override

	public Integer addBooking(BookingDTO booking, Integer userId, String destinationId) throws WanderLustException {

		Optional<User> optional = userRepo.findById(userId);
		User user = optional.orElseThrow(() -> new WanderLustException("BookingService.BOOKING_ERROR"));

		Optional<Destination> opt = packageRepo.findById(destinationId);
		Destination destination = opt.orElseThrow(() -> new WanderLustException("BookingService.NO_BOOKING"));
		Booking book = new Booking();
		book.setTimeOfBooking(booking.getTimeOfBooking());
		book.setCheckIn(booking.getCheckIn());
		book.setCheckOut(booking.getCheckOut());
		book.setDestinationEntity(destination);
		book.setTotalCost(booking.getTotalCost());
		book.setNoOfPeople(booking.getNoOfPeople());
		
		book.setUserEntity(user);
		Booking book1 =bookingRepo.save(book);
		return book1.getBookingId();
	}


	@Override
	public List<BookingDTO> getBooking(Integer userId) throws WanderLustException {
		
		
		Optional<User> optional=userRepo.findById(userId);
		User user=optional.orElseThrow(()-> new WanderLustException("BookingService.NO_BOOKING"));
		List<Booking> bookings=bookingRepo.findByUserEntity(user);
		if(bookings.isEmpty()) {
			throw new WanderLustException("BookingService.BOOKING_ERROR");
		}
		List<BookingDTO>bookingDTO=new ArrayList<>();
		bookings.forEach(a->{
			BookingDTO book=new BookingDTO();
			
			book.setBookingId(a.getBookingId());
			book.setCheckIn(a.getCheckIn());
			book.setCheckOut(a.getCheckOut());
			book.setNoOfPeople(a.getNoOfPeople());
			book.setTimeOfBooking(a.getTimeOfBooking());
			book.setTotalCost(a.getTotalCost());
			
			
			UserDTO userDTO=new UserDTO();
			userDTO.setContactNumber(a.getUserEntity().getContactNumber());
			userDTO.setEmailId(a.getUserEntity().getEmailId());
			userDTO.setPassword(a.getUserEntity().getPassword());
			userDTO.setUserId(a.getUserEntity().getUserId());
			userDTO.setUserName(a.getUserEntity().getUserName());
			
			DestinationDTO dest1=new DestinationDTO();
			dest1.setAvailability(a.getDestinationEntity().getAvailability());
			dest1.setChargePerPerson(a.getDestinationEntity().getChargePerPerson());
			dest1.setContinent(a.getDestinationEntity().getContinent());
			dest1.setDestinationId(a.getDestinationEntity().getDestinationId());
			dest1.setDestinationName(a.getDestinationEntity().getDestinationName());
			dest1.setImageUrl(a.getDestinationEntity().getImageUrl());
			dest1.setNoOfNights(a.getDestinationEntity().getNoOfNights());
			dest1.setAvailability(a.getDestinationEntity().getAvailability());
			dest1.setFlightCharge(a.getDestinationEntity().getFlightCharge());
			dest1.setDiscount(a.getDestinationEntity().getDiscount());
			
			DetailsDTO det=new DetailsDTO();
			det.setDetailsId(a.getDestinationEntity().getDetails().getDetailsId());
			det.setAbout(a.getDestinationEntity().getDetails().getAbout());
			det.setHighlights(a.getDestinationEntity().getDetails().getHighlights());
			det.setPackageInclusion(a.getDestinationEntity().getDetails().getPackageInclusion());
			det.setPace(a.getDestinationEntity().getDetails().getPace());
			
			ItineraryDTO it=new ItineraryDTO();
			it.setItineraryId(a.getDestinationEntity().getDetails().getItinerary().getItineraryId());
			it.setFirstDay(a.getDestinationEntity().getDetails().getItinerary().getFirstDay());
			it.setRestOfDays(a.getDestinationEntity().getDetails().getItinerary().getRestOfDays());
			it.setLastDay(a.getDestinationEntity().getDetails().getItinerary().getLastDay());
			
			dest1.setDetails(det);
			det.setItinerary(it);
			book.setDestination(dest1);
			bookingDTO.add(book);
			
		});
		
			return bookingDTO;
	}

	@Override
	public String deleteBooking(Integer bookingId) throws WanderLustException {
		Optional<Booking> opt = bookingRepo.findById(bookingId);
		Booking booking = opt.orElseThrow(() -> new WanderLustException("BookingService.NO_BOOKING"));
		if (booking != null) {
			booking.setUserEntity(null);
			booking.setDestinationEntity(null);

		}
		bookingRepo.delete(booking);

		return bookingId + "";

	}

}
