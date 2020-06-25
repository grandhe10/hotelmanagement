package com.demo.hotelmanagement.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	Long roomId;
	Long hotelId;
	@Enumerated(EnumType.STRING)
	RoomType roomType;
	Long price;
	LocalDate availableDate;
}
