package com.atul.service;

import java.util.List;

import com.atul.dto.DestinationDTO;
import com.atul.exception.WanderLustException;

public interface PackageService {
	
	public List<DestinationDTO> getPackagesSearch(String continent) throws WanderLustException;
	public DestinationDTO getItinerary(String destinationId) throws WanderLustException;
	public List<DestinationDTO> getPackages() throws WanderLustException;

}
