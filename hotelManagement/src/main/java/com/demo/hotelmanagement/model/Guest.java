package com.demo.hotelmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
/**
 * Generates Guest table with guestId,
 * guestName,age,bookingId .
 */
@Entity
@Getter
@Setter
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	Long guestId;
	String guestName;
	int age;
	Long bookingId;
	public Long getGuestId() {
		return guestId;
	}
	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

}
