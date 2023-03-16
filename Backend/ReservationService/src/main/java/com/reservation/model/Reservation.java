package com.reservation.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservationdetails")
public class Reservation {
	@Id
	private long reservationCode;
	private String name;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
    private String gender;
	private String roomType;
	public long getReservationCode() {
		return reservationCode;
	}
	public void setReservationCode(long reservationCode) {
		this.reservationCode = reservationCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	@Override
	public String toString() {
		return "Reservation [reservationCode=" + reservationCode + ", name=" + name + ", checkIn=" + checkIn
				+ ", checkOut=" + checkOut + ", gender=" + gender + ", roomType="
				+ roomType + "]";
	}
	public Reservation(long reservationCode, String name, LocalDate checkIn, LocalDate checkOut, String phoneNumber,
			String gender, String roomType) {
		super();
		this.reservationCode = reservationCode;
		this.name = name;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
		this.gender = gender;
		this.roomType = roomType;
	}
	public Reservation() {
		super();

	}

	
	
	

}