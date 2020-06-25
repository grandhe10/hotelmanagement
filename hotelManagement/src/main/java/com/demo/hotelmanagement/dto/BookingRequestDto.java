package com.demo.hotelmanagement.dto;

import java.util.List;

public class BookingRequestDto {
	
	public BookingRequestDto() {
		super();
	}
	public BookingRequestDto(String message) {
		super();
		this.message = message;
	}
	Long userId;
	String checkIn;
	String checkOut;
	List<GuestRequestDto> guestRequestDtoList;
	String message;
	String date;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public List<GuestRequestDto> getGuestRequestDtoList() {
		return guestRequestDtoList;
	}
	public void setGuestRequestDtoList(List<GuestRequestDto> guestRequestDtoList) {
		this.guestRequestDtoList = guestRequestDtoList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}
