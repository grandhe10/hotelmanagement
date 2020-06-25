package com.demo.hotelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hotelmanagement.dto.BookingDetailsRequestDto;
import com.demo.hotelmanagement.dto.BookingRequestDto;
import com.demo.hotelmanagement.dto.ResponseDto;
import com.demo.hotelmanagement.dto.RoomDetailDto;
import com.demo.hotelmanagement.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	@PostMapping("roomOptions/{roomOptionId}/bookings")
	public ResponseEntity<ResponseDto> bookRoom(@Valid @RequestBody List<BookingRequestDto> bookingRequestDtoList,@RequestParam(value="roomType") String roomType,@PathVariable("roomOptionId") Long roomOptionId)
	{
		return new ResponseEntity<>(bookingService.bookRoom(bookingRequestDtoList,roomType,roomOptionId),HttpStatus.OK);
		
	}
	
	@PostMapping("/roomOptions")
	public ResponseEntity<RoomDetailDto> getCustomHotelDetails(@Valid @RequestBody BookingDetailsRequestDto bookingDetailsRequestDto)
	{
		
		return new ResponseEntity<RoomDetailDto>(bookingService.getCustomeHotelDetails(bookingDetailsRequestDto),HttpStatus.OK);
		
		
	}
	
}
