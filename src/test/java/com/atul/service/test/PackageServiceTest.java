package com.atul.service.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.atul.dto.DestinationDTO;
import com.atul.dto.DetailsDTO;
import com.atul.dto.ItineraryDTO;
import com.atul.entity.Destination;
import com.atul.entity.Details;
import com.atul.entity.Itinerary;
import com.atul.exception.WanderLustException;
import com.atul.repository.PackageRepository;
import com.atul.service.PackageServiceImpl;

@SpringBootTest
public class PackageServiceTest {

	
	
	@Mock
	private PackageRepository packageRepo;
	@InjectMocks
	private PackageServiceImpl packageServiceImpl=new PackageServiceImpl();
	
	
    @Test
	 void GetPackagesSearchInvalidTest() throws Exception{
		List<Destination> dest=new ArrayList<>();
		Mockito.when(packageRepo.findByContinent("continent")).thenReturn(dest);
		WanderLustException excep= Assertions.assertThrows(WanderLustException.class,()->packageServiceImpl.getPackagesSearch("continent"));
		Assertions.assertEquals("PackageService.PACKAGE_UNAVAILABLE",excep.getMessage());
	
	}
    @Test
	 void GetPackagesSearchvalidTest() throws Exception{
		Destination d=new Destination();
		d.setDestinationId("D5643");
		d.setContinent("America");
		Itinerary iti=new Itinerary();
		iti.setItineraryId("I7654");
		Details dt= new Details();
		dt.setDetailsId("DL789");
		dt.setItinerary(iti);
		d.setDetails(dt);
		
		
		List<Destination> dlist=new ArrayList<Destination>();
		dlist.add(d);
		
		
		Mockito.when(packageRepo.findByContinent("America")).thenReturn(dlist);
        List<DestinationDTO> dtolist= packageServiceImpl.getPackagesSearch("America");
		Assertions.assertNotNull(dtolist);
	}

    @Test
     void GetItineraryInvalidTest() throws Exception{
    	Destination dest= new Destination();
    	Mockito.when(packageRepo.findById(dest.getDestinationId())).thenReturn(null);
		WanderLustException excep= Assertions.assertThrows(WanderLustException.class,()->packageServiceImpl.getItinerary("S52752"));
		Assertions.assertEquals("PackageService.ITINERARY_UNAVAILABLE",excep.getMessage());
    }
    @Test
     void GetItineraryvalid() throws Exception{
    	Destination d=new Destination();
    	d.setDestinationId("D5643");
		d.setContinent("America");
		Itinerary iti=new Itinerary();
		iti.setItineraryId("I7654");
		Details dt= new Details();
		dt.setDetailsId("DL789");
		dt.setItinerary(iti);
		d.setDetails(dt);
		
		Mockito.<Optional<Destination>>when(packageRepo.findById("D5643")).thenReturn(Optional.of(d));
		DestinationDTO de=packageServiceImpl.getItinerary("D5643");
		Assertions.assertNotNull(de);
    }
    @Test
     void GetPackagesvalid()throws Exception{
    	Destination d=new Destination();
    	d.setDestinationId("D5643");
		d.setContinent("America");
		Itinerary iti=new Itinerary();
		iti.setItineraryId("I7654");
		Details dt= new Details();
		dt.setDetailsId("DL789");
		dt.setItinerary(iti);
		d.setDetails(dt);
		
		DestinationDTO d1=new DestinationDTO();
    	d1.setDestinationId("D5643");
		d1.setContinent("America");
		ItineraryDTO iti1=new ItineraryDTO();
		iti1.setItineraryId("I7654");
		DetailsDTO dt1= new DetailsDTO();
		dt1.setDetailsId("DL789");
		dt1.setItinerary(iti1);
		d1.setDetails(dt1);
		
		List<Destination> dlist=new ArrayList<Destination>();
		dlist.add(d);
		
		List<DestinationDTO> dlist1=new ArrayList<DestinationDTO>();
		dlist1.add(d1);

		Mockito.<Iterable<Destination>>when(packageRepo.findAll()).thenReturn(dlist);
		
        List<DestinationDTO> dtolist= packageServiceImpl.getPackages();
		Assertions.assertEquals(dlist1.get(0).getDestinationId(), dtolist.get(0).getDestinationId());
    }
    
    @Test
     void GetPackagesInvalid()throws Exception{
    	DestinationDTO de = new DestinationDTO();
    	de.setDestinationId("D73470");
    	
    	Mockito.when(packageRepo.findAll()).thenReturn(List.of());
		WanderLustException excep= Assertions.assertThrows(WanderLustException.class,()->packageServiceImpl.getPackages());
		Assertions.assertEquals("PackageService.PACKAGE_UNAVAILABLE",excep.getMessage());
    
    }
}
