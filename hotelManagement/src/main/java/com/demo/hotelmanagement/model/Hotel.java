package com.demo.hotelmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	Long hotelId;
	String hotelName;
	String location;
	String address;
	String contactNumber;
	int roomsAvailable;
}
