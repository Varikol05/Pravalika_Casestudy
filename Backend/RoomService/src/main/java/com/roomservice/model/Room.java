package com.roomservice.model;

import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="roomdetails")
public class Room {
	
	@Id
	private long roomNumber;
	private boolean roomStatus;
	private String roomType;
	private long roomPrice;
	
}