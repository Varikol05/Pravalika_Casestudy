package com.owner.feignclient;

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

import com.owner.exception.GuestNotFoundException;
import com.owner.model.GuestDetails;


@FeignClient(name="Guest-Service", url="http://localhost:9002/guest")
public interface GuestFeignClient {
	
	
	
	@GetMapping("/all")
	public ResponseEntity<List<GuestDetails>> showAllGuestDetails(@RequestHeader("Authorization") String token);
	
	
	@GetMapping("/{id}")
	public ResponseEntity<GuestDetails> showGuestDetailsById(@PathVariable("id") int id,@RequestHeader("Authorization") String token)throws GuestNotFoundException;
	
	@PostMapping("/addguest")
	public ResponseEntity<GuestDetails> addGuestDetails(@RequestBody GuestDetails guestDetails,@RequestHeader("Authorization") String token)throws GuestNotFoundException;
	

	@PutMapping("/updateguest")
	public ResponseEntity<GuestDetails> updateGuestDetails(@RequestBody GuestDetails guestDetails,@RequestHeader("Authorization") String token)throws GuestNotFoundException;
	
	
	@DeleteMapping("/deleteguest/{id}")
	public ResponseEntity<String> deleteGuestDetails(@PathVariable("id") int id,@RequestHeader("Authorization") String token)throws GuestNotFoundException;
}
