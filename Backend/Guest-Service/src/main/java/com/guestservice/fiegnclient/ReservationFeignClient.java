package com.guestservice.fiegnclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Reservation-Service", url = "http://localhost:1001/reservation")
public interface ReservationFeignClient {
	
	@GetMapping("/roomprice/{roomType}")
	public String getPrice(@PathVariable("roomType") String roomType,@RequestHeader("Authentication") String token);
}