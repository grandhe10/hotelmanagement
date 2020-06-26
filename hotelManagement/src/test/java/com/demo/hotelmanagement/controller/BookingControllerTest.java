package com.demo.hotelmanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.demo.hotelmanagement.dto.BookingDetailsRequestDto;
import com.demo.hotelmanagement.dto.BookingRequestDto;
import com.demo.hotelmanagement.dto.GuestRequestDto;
import com.demo.hotelmanagement.dto.ResponseDto;
import com.demo.hotelmanagement.dto.RoomDetailDto;
import com.demo.hotelmanagement.model.RoomType;
import com.demo.hotelmanagement.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)

public class BookingControllerTest {

	@Mock
	BookingService bookingService;

	MockMvc mockMvc;
	ObjectMapper objectMapper;

	@InjectMocks
	BookingController bookingController;

	BookingRequestDto bookingRequestDto;
	List<BookingRequestDto> bookingRequestDtoList;
	List<GuestRequestDto> guestRequestDtoList;
	GuestRequestDto guestRequestDto;
	ResponseDto responseDto;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();

	}

	@SuppressWarnings("unchecked")
	@Test
	public void bookRoom() throws Exception {
		List<BookingRequestDto> bookingRequestDtoList = new ArrayList<>();

		bookingRequestDto = new BookingRequestDto();
		bookingRequestDto.setCheckIn("09:00:00");
		bookingRequestDto.setCheckOut("12:00:00");
		bookingRequestDto.setDate("2020-06-30");
		bookingRequestDto.setGuestRequestDtoList(guestRequestDtoList);

		GuestRequestDto guestRequestDto = new GuestRequestDto();
		guestRequestDto.setAge(23);
		guestRequestDto.setGuestName("suma");

		List<GuestRequestDto> guestRequestDtoList = new ArrayList<>();

		guestRequestDtoList.add(guestRequestDto);
		bookingRequestDtoList.add(bookingRequestDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Booking confirmed");
		responseDto.setStatusCode(201);

		when(bookingService.bookRoom(any(List.class), eq("SINGLE"), eq(1L))).thenReturn(responseDto);

		mockMvc.perform(post("/roomOptions/{roomOptionId}/bookings", 1L).contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("roomType", "SINGLE").content(objectMapper.writeValueAsString(bookingRequestDtoList)))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.any(LinkedHashMap.class)));
		verify(bookingService).bookRoom(any(List.class), eq("SINGLE"), eq(1L));
	}

	@Test
	public void getCustomHotelDetails() throws Exception {
		BookingDetailsRequestDto bookingDetailsRequestDto = new BookingDetailsRequestDto();
		bookingDetailsRequestDto.setGuests(20);
		bookingDetailsRequestDto.setHotelId(1L);
		bookingDetailsRequestDto.setRoomsRequired(5);
		RoomDetailDto roomDetailDto = new RoomDetailDto();
		roomDetailDto.setAvailable(200);
		roomDetailDto.setHotelName("testHotelName");
		roomDetailDto.setMessage("Please find details here");
		roomDetailDto.setPrice(2000);
		roomDetailDto.setRoomOptionId(1L);
		roomDetailDto.setRoomType(RoomType.VILLA);

		when(bookingService.getCustomeHotelDetails(any(BookingDetailsRequestDto.class))).thenReturn((roomDetailDto));
		
		mockMvc.perform(post("/roomOptions").contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(objectMapper.writeValueAsString(bookingDetailsRequestDto)))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.any(LinkedHashMap.class)));
		verify(bookingService).getCustomeHotelDetails(any(BookingDetailsRequestDto.class));
	}
	
}
