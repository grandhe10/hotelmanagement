package com.demo.hotelmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotelmanagement.model.Hotel;
@Repository
public interface HotelDao extends CrudRepository<Hotel, Long>{
	Optional<Hotel> findByHotelId(Long hotelId);
	
	Optional<List<Hotel>> findAllByLocationContaining(String location);

	Optional<List<Hotel>> findByLocation(String location);

	Optional<List<Hotel>> findByLocationContains(String location);

	//Optional<List<Hotel>> findByLocationContaining(String location);
	
	

}

