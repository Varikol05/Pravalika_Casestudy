package com.receptionist.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.receptionist.exception.GuestNotFoundException;
import com.receptionist.model.GuestDetails;

@FeignClient(name = "GuestService", url = "http://localhost:9002/guest")
public interface GuestFeignClient {

	@GetMapping("/all")
	public ResponseEntity<List<GuestDetails>> showAllGuestDetails(@RequestHeader("Authorization") String token);

	@GetMapping("/{id}")
	public ResponseEntity<GuestDetails> showGuestDetailsById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws GuestNotFoundException;

	@PostMapping("/addguest")
	public ResponseEntity<GuestDetails> addGuestDetails(@RequestBody GuestDetails guestDetails,
			@RequestHeader("Authorization") String token) throws GuestNotFoundException;

	@PutMapping("/updateguest")
	public ResponseEntity<GuestDetails> updateGuestDetails(@RequestBody GuestDetails guestDetails,
			@RequestHeader("Authorization") String token) throws GuestNotFoundException;

	@DeleteMapping("/deleteguest/{id}")
	public ResponseEntity<String> deleteGuestDetails(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws GuestNotFoundException;

}
