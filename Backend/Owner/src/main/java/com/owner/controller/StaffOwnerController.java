package com.owner.controller;

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
import com.owner.exception.StaffNotFoundException;
import com.owner.feignclient.StaffFeignClient;
import com.owner.model.Staff;


@RestController
@RequestMapping("/owner")
public class StaffOwnerController {
	@Autowired
	private StaffFeignClient staffFeignClient;
	
	
	
	
	@GetMapping("/allstaff")
	public ResponseEntity<List<Staff>> showAllstaff(@RequestHeader("Authorization") String token){
		
     		return staffFeignClient.showAllStaff(token);
	
		}
	@GetMapping("/staff/{id}")
	public ResponseEntity<Staff> showById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) 
			throws StaffNotFoundException {
		
			return staffFeignClient.showById(id, token);
			
	}
	@PostMapping("/addStaff")
	public ResponseEntity<Staff> addstaff(@RequestBody Staff staffDetails,@RequestHeader("Authorization") String token) 
			throws StaffNotFoundException {
		
		return staffFeignClient.addStaff(staffDetails, token);
	}
	@PutMapping("/updateStaff")
	public ResponseEntity<Staff> updatestaff(@RequestBody Staff staffDetails,@RequestHeader("Authorization") String token) 
			throws StaffNotFoundException{
		
		return staffFeignClient.updateStaff(staffDetails, token);
			
	}
	@DeleteMapping("/deleteStaff/{id}")
	public ResponseEntity<String> deletestaff(@PathVariable("id") int id,@RequestHeader("Authorization") String token) 
			throws StaffNotFoundException{
		
		return staffFeignClient.deleteStaff(id, token);
	}
}
