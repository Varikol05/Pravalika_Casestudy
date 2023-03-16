package com.guestservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "guestdetails")
public class GuestDetails {

	@Id
	private long guestId;
	private String name;
	private long contact;
	private String gender;
	private String email;
	private String address;

}