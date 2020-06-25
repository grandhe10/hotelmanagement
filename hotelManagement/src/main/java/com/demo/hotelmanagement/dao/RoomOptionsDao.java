package com.demo.hotelmanagement.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotelmanagement.model.RoomOptions;
@Repository
public interface RoomOptionsDao extends CrudRepository<RoomOptions, Long>{
	
	Optional<RoomOptions> findByRoomId(Long roomId);
	
	Optional<RoomOptions> findByHotelId(Long hotelId);

	Optional<RoomOptions> findByRoomOptionId(Long roomId);

	
	
	
	
	
	

}
