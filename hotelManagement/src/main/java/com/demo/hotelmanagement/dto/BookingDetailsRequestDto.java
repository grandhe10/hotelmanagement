package com.demo.hotelmanagement.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BookingDetailsRequestDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookingDetailsRequestDto {
	
	int guests;
	
	int roomsRequired;
	
	Long hotelId;
	
	public int getGuests() {
		return guests;
	}
	public void setGuests(int guests) {
		this.guests = guests;
	}
	public int getRoomsRequired() {
		return roomsRequired;
	}
	public void setRoomsRequired(int roomsRequired) {
		this.roomsRequired = roomsRequired;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	
	

}
