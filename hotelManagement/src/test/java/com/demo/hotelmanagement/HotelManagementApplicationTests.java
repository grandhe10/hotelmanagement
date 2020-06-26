package com.demo.hotelmanagement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.hotelmanagement.controller.BookingController;
import com.demo.hotelmanagement.controller.HotelController;

@SpringBootTest
class HotelManagementApplicationTests {

	@Autowired
	HotelController hotelController;
	@Autowired
	BookingController bookingController;
	@Test
	public void contexLoads() throws Exception {
		assertThat(hotelController).isNotNull();
	}

	@Test
	public void contexLoads1() throws Exception {
		assertThat(bookingController).isNotNull();
	}
}


	