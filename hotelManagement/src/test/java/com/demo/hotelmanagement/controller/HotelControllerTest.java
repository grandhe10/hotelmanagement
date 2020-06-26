package com.demo.hotelmanagement.controller;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.hotelmanagement.dto.HotelDetailsDto;
import com.demo.hotelmanagement.dto.HotelResponseDto;
import com.demo.hotelmanagement.service.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {
	@Mock
	HotelService hotelService;
	
	MockMvc mockMvc;
	ObjectMapper objectMapper;
	
	@InjectMocks
	HotelController hotelController;

	@BeforeEach
	public void setUp()
	{
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();	
		
	}

	@Test
	public void getHotelsByLocationTest() throws Exception
	{
		HotelResponseDto hotelResponseDto = new HotelResponseDto();
		List<HotelDetailsDto> hotelDetailsDtoList = new ArrayList<>();
		HotelDetailsDto hotelDetailsDto = new HotelDetailsDto();
		hotelDetailsDto.setAddress("testAddress");
		hotelDetailsDto.setContactNumber("9475894");
		hotelDetailsDto.setDoubleRoom(25);
		hotelDetailsDto.setFamilyRoom(25);
		hotelDetailsDto.setHotelName("testHotelName");
		hotelDetailsDtoList.add(hotelDetailsDto);
		hotelResponseDto.setHotelDetailsDtoList(hotelDetailsDtoList);
		
		when(hotelService.getHotelsByLocation(eq("hyd"), eq("2020-06-30"))).thenReturn(hotelResponseDto);
		
		  mockMvc.perform(get("/hotels").
				  contentType(MediaType.APPLICATION_JSON_VALUE)
				  .param("location","hyd")
				  .param("date", "2020-06-30")
				  .accept(MediaType.APPLICATION_JSON_VALUE))
		  .andExpect(status().isOk()) 
		  .andExpect(jsonPath("$", Matchers.any(LinkedHashMap.class)));
		  verify(hotelService).getHotelsByLocation(eq("hyd"), eq("2020-06-30"));
		
		
		  
	}
	}


