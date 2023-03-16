package com.staff.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.staff.model.AuthenticationResponse;



@FeignClient(name="Authentication", url= "http://localhost:9999/auth")

public interface StaffAuthenticationClient {
	@GetMapping("/validate")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token) ;
	
	}
