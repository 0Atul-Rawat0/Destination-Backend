package com.atul.service;

import java.util.List;

import com.atul.dto.DestinationDTO;
import com.atul.exception.DestinationException;

public interface PackageService {
	
	public List<DestinationDTO> getPackagesSearch(String continent) throws DestinationException;
	public DestinationDTO getItinerary(String destinationId) throws DestinationException;
	public List<DestinationDTO> getPackages() throws DestinationException;

}
