package com.atul.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
	
	private int bookingId;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int noOfPeople;
	private float totalCost;
	private LocalDateTime timeOfBooking;
	private UserDTO users;
	private DestinationDTO destination;
	
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	public int getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public LocalDateTime getTimeOfBooking() {
		return timeOfBooking;
	}
	public void setTimeOfBooking(LocalDateTime timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	public UserDTO getUsers() {
		return users;
	}
	public void setUsers(UserDTO users) {
		this.users = users;
	}
	public DestinationDTO getDestination() {
		return destination;
	}
	public void setDestination(DestinationDTO destination) {
		this.destination = destination;
	}
	
	
	

}
