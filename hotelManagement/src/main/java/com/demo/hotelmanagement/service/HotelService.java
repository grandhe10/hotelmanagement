package com.demo.hotelmanagement.service;

import com.demo.hotelmanagement.dto.HotelResponseDto;

/**
 * @author 91970
 *
 */
public interface HotelService {

	/**
	 * This method is used to get hotelDetailsByLocation
	 * @param location
	 * @param date
	 * @return
	 */
	HotelResponseDto getHotelsByLocation(String location, String date);

}
