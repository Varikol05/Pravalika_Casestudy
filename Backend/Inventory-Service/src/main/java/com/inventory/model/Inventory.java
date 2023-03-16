package com.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventorydetails")
public class Inventory {

	@Id
	private long inventoryCode;
	private String inventoryType;
	private String inventoryName;
	private int inventoryQuantity;

}