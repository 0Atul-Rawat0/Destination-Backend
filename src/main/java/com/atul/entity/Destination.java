package com.atul.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Destination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String destinationId;
	private String continent;
	private String destinationName;	
	private String imageUrl;
	private int noOfNights;
	private int availability;
	private float flightCharge;
	private float chargePerPerson;
	private float discount;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="details_id", unique=true)
	private Details details;

	public String getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}
	
	public int getNoOfNights() {
		return noOfNights;
	}

	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public float getFlightCharge() {
		return flightCharge;
	}

	public void setFlightCharge(float flightCharge) {
		this.flightCharge = flightCharge;
	}
	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public Float getChargePerPerson() {
		return chargePerPerson;
	}

	public void setChargePerPerson(Float chargePerPerson) {
		this.chargePerPerson =chargePerPerson;
	}

}
