package com.demo.hotelmanagement.service;

import com.demo.hotelmanagement.dto.HotelResponseDto;

public interface HotelService {

	HotelResponseDto getHotelsByLocation(String location, String date);

}
