package com.roomservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.roomservice.model.AuthenticationResponse;



@FeignClient(name="Authentication", url= "http://localhost:9999/auth")

public interface RoomAuthenticationClient {
	@GetMapping("/validate")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token) ;
	
	}

