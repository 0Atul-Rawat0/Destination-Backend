package com.atul.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atul.entity.Destination;


public interface PackageRepository  extends CrudRepository<Destination, String>{
	public List<Destination> findByContinent(String continent);
}
