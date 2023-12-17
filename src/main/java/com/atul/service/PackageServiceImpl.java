package com.atul.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atul.dto.DestinationDTO;
import com.atul.dto.DetailsDTO;
import com.atul.dto.ItineraryDTO;
import com.atul.entity.Destination;
import com.atul.exception.WanderLustException;
import com.atul.repository.PackageRepository;

@Service(value="packageService")
@Transactional
public class PackageServiceImpl implements PackageService{
	
	@Autowired
	private PackageRepository packageRepo;
	
	
	@Override
	public List<DestinationDTO> getPackagesSearch(String continent) throws WanderLustException{
		List<Destination> optional=packageRepo.findByContinent(continent);
		if(optional.isEmpty())
			throw new WanderLustException("PackageService.PACKAGE_UNAVAILABLE");
		List<DestinationDTO> list=new ArrayList<DestinationDTO>();
		for(Destination d:optional) {
			DestinationDTO d1=new DestinationDTO();
			DetailsDTO d2=new DetailsDTO();
			d2.setAbout(d.getDetails().getAbout());
			d2.setDetailsId(d.getDetails().getDetailsId());
			d2.setHighlights(d.getDetails().getHighlights());
			d2.setPace(d.getDetails().getPace());
			d2.setPackageInclusion(d.getDetails().getPackageInclusion());
			
			ItineraryDTO i=new ItineraryDTO();
			i.setFirstDay(d.getDetails().getItinerary().getFirstDay());
			i.setItineraryId(d.getDetails().getItinerary().getItineraryId());
			i.setLastDay(d.getDetails().getItinerary().getLastDay());
			i.setRestOfDays(d.getDetails().getItinerary().getRestOfDays());
			
			d1.setAvailability(d.getAvailability());
			d1.setChargePerPerson(d.getChargePerPerson());
			d1.setContinent(d.getContinent());
			d1.setDestinationId(d.getDestinationId());
			d1.setDestinationName(d.getDestinationName());
			
			d1.setDiscount(d.getDiscount());
			d1.setFlightCharge(d.getFlightCharge());
			d1.setImageUrl(d.getImageUrl());
			d1.setNoOfNights(d.getNoOfNights());
			d2.setItinerary(i);
			d1.setDetails(d2);
			list.add(d1);
			
		
	}
		return list;
		
	}
	
	public DestinationDTO getItinerary(String destinationId) throws WanderLustException{
		
		Optional<Destination> optional=packageRepo.findById(destinationId);
		Destination d=optional.orElseThrow(()->new WanderLustException("PackageService.ITINERARY_UNAVAILABLE"));
		
		DestinationDTO d1=new DestinationDTO();
		DetailsDTO d2=new DetailsDTO();
		d2.setAbout(d.getDetails().getAbout());
		d2.setDetailsId(d.getDetails().getDetailsId());
		d2.setHighlights(d.getDetails().getHighlights());
		d2.setPace(d.getDetails().getPace());
		d2.setPackageInclusion(d.getDetails().getPackageInclusion());
		
		ItineraryDTO i=new ItineraryDTO();
		i.setFirstDay(d.getDetails().getItinerary().getFirstDay());
		i.setItineraryId(d.getDetails().getItinerary().getItineraryId());
		i.setLastDay(d.getDetails().getItinerary().getLastDay());
		i.setRestOfDays(d.getDetails().getItinerary().getRestOfDays());
		
		d1.setAvailability(d.getAvailability());
		d1.setChargePerPerson(d.getChargePerPerson());
		d1.setContinent(d.getContinent());
		d1.setDestinationId(d.getDestinationId());
		d1.setDestinationName(d.getDestinationName());
		
		d1.setDiscount(d.getDiscount());
		d1.setFlightCharge(d.getFlightCharge());
		d1.setImageUrl(d.getImageUrl());
		d1.setNoOfNights(d.getNoOfNights());
		d2.setItinerary(i);
		d1.setDetails(d2);
		
		return d1;
	}
	
	@Override
	public List<DestinationDTO> getPackages() throws WanderLustException {
		Iterable<Destination> destination = packageRepo.findAll();
		List<DestinationDTO> dest=new ArrayList<>();
		destination.forEach(d->{
			ItineraryDTO it=new ItineraryDTO();
		it.setFirstDay(d.getDetails().getItinerary().getFirstDay());
		it.setItineraryId(d.getDetails().getItinerary().getItineraryId());
		it.setLastDay(d.getDetails().getItinerary().getLastDay());
		it.setRestOfDays(d.getDetails().getItinerary().getRestOfDays());
		
		DetailsDTO dt= new DetailsDTO();
		dt.setAbout(d.getDetails().getAbout());
		dt.setDetailsId(d.getDetails().getDetailsId());
		dt.setHighlights(d.getDetails().getHighlights());
		dt.setItinerary(it);
		dt.setPace(d.getDetails().getPace());
		dt.setPackageInclusion(d.getDetails().getPackageInclusion());
		
		DestinationDTO d1=new DestinationDTO();
		
		d1.setAvailability(d.getAvailability());
		d1.setChargePerPerson(d.getChargePerPerson());
		d1.setContinent(d.getContinent());
		d1.setDestinationId(d.getDestinationId());
		d1.setDestinationName(d.getDestinationName());
		
		d1.setDiscount(d.getDiscount());
		d1.setFlightCharge(d.getFlightCharge());
		d1.setImageUrl(d.getImageUrl());
		d1.setNoOfNights(d.getNoOfNights());
		d1.setDetails(d1.getDetails());
		dest.add(d1);
		});
		if(dest.isEmpty()) throw new WanderLustException("PackageService.PACKAGE_UNAVAILABLE");
		return dest;
	}
	
}
