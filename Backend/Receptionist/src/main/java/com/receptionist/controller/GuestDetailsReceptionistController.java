package com.receptionist.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.receptionist.exception.GuestNotFoundException;
import com.receptionist.feignclient.GuestFeignClient;
import com.receptionist.model.GuestDetails;

@RestController
@RequestMapping("/receptionist")
public class GuestDetailsReceptionistController {
	
	@Autowired
	private GuestFeignClient guestFeignClient;
	
	
	
	@GetMapping("/allguest")
	public ResponseEntity<List<GuestDetails>> showAllGuest(@RequestHeader("Authorization") String token){
		
     		return guestFeignClient.showAllGuestDetails(token);
	
	}
	
	@GetMapping("/guest/{id}")
	public ResponseEntity<GuestDetails> showGuestById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) 
			 throws GuestNotFoundException {
		
			return guestFeignClient.showGuestDetailsById(id, token);
	
		}
			
	
	@PostMapping("/addguest")
	public ResponseEntity<GuestDetails> addGuest(@RequestBody GuestDetails guestDetails,@RequestHeader("Authorization") String token) 
			throws GuestNotFoundException {
		
		return guestFeignClient.addGuestDetails(guestDetails, token);
	
}
	
	@PutMapping("/updateguest")
	public ResponseEntity<GuestDetails> updateGuest(@RequestBody GuestDetails guestDetails,@RequestHeader("Authorization") String token) 
			throws GuestNotFoundException{
		
		return guestFeignClient.updateGuestDetails(guestDetails, token);
	

}
	
	@DeleteMapping("/deleteguest/{id}")
	public ResponseEntity<String> deleteGuest(@PathVariable("id") int id,@RequestHeader("Authorization") String token) 
			throws GuestNotFoundException{
		
		return guestFeignClient.deleteGuestDetails(id, token);
	
}
}
