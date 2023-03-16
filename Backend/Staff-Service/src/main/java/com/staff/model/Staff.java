package com.staff.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "staffdetails")

public class Staff {
	@Id
	@NotBlank(message = "Id is required")
    @Min(value = 1, message = "Id should not be negative and zero")
	private long id;
	private int departmentId;
	private String employeeName;
	private String phoneNo;
	private String email;
	private int age;
	private String employeeAddress;
	private Double salary;

	}