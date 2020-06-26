package com.demo.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hotelmanagement.dto.HotelResponseDto;
import com.demo.hotelmanagement.service.HotelService;

@RestController
public class HotelController {
	@Autowired
	HotelService hotelService;
	@GetMapping("/hotels")
	public ResponseEntity<HotelResponseDto> getHotelsByLocation(@RequestParam(value="location") String location,@RequestParam(value="date") String date)
	{
		return new ResponseEntity<>(hotelService.getHotelsByLocation(location,date),HttpStatus.OK);
		
	}
	
	

}
