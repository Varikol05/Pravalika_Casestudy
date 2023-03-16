package com.guestservice.fiegnclient;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.guestservice.model.AuthenticationResponse;



@FeignClient(name="Auth-Service", url= "http://localhost:9999/auth")
public interface GuestAuthenticationClient {
	
	@GetMapping("/validate")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token) ;
	
	}
