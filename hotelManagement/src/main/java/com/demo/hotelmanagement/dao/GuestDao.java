package com.demo.hotelmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotelmanagement.model.Guest;
@Repository
public interface GuestDao extends CrudRepository<Guest, Long>{
	
	Optional<List<Guest>> findAllByBookingId(Long bookingId) ;

}
