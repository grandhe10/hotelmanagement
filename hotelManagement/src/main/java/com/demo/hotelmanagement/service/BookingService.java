package com.demo.hotelmanagement.service;

import java.util.List;

import com.demo.hotelmanagement.dto.BookingDetailsRequestDto;
import com.demo.hotelmanagement.dto.BookingRequestDto;
import com.demo.hotelmanagement.dto.ResponseDto;
import com.demo.hotelmanagement.dto.RoomDetailDto;

public interface BookingService {
	
	ResponseDto bookRoom(List<BookingRequestDto> bookingRequestDtoList,String roomType,Long roomOptionId);

	
	RoomDetailDto getCustomeHotelDetails( BookingDetailsRequestDto bookingDetailsRequestDto);

}
