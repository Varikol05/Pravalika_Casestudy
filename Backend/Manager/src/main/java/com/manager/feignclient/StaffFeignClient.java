package com.manager.feignclient;

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

import com.manager.exception.StaffNotFoundException;
import com.manager.model.Staff;

@FeignClient(name = "Staff-Service", url = "http://localhost:1003/staff")
public interface StaffFeignClient {

	@GetMapping("/all")
	public ResponseEntity<List<Staff>> showAllstaff(@RequestHeader("Authorization") String token);

	@GetMapping("/{id}")
	public ResponseEntity<Staff> showById(@PathVariable("id") int id, @RequestHeader("Authorization") String token)
			throws StaffNotFoundException;

	@PostMapping("/addstaff")
	public ResponseEntity<Staff> addstaff(@RequestBody Staff staffDetails, @RequestHeader("Authorization") String token)
			throws StaffNotFoundException;

	@PutMapping("/updatestaff")
	public ResponseEntity<Staff> updatestaff(@RequestBody Staff staff, @RequestHeader("Authorization") String token)
			throws StaffNotFoundException;

	@DeleteMapping("/deletestaff/{id}")
	public ResponseEntity<String> deletestaff(@PathVariable("id") int id, @RequestHeader("Authorization") String token)
			throws StaffNotFoundException;
}
