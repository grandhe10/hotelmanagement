package com.demo.hotelmanagement.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotelmanagement.model.PriceDetails;
import com.demo.hotelmanagement.model.RoomType;
@Repository
public interface PriceDetailsDao extends CrudRepository<PriceDetails, Long>{
	
	Optional<PriceDetails> findByRoomOptionIdAndRoomType(Long roomOptionId,RoomType roomType);

}
