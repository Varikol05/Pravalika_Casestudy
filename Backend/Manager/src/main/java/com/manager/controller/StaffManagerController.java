package com.manager.controller;

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
import com.manager.exception.StaffNotFoundException;
import com.manager.feignclient.StaffFeignClient;
import com.manager.model.Staff;

@RestController
@RequestMapping("/manager")
public class StaffManagerController {
	@Autowired
	private StaffFeignClient staffFeignClient;

	@GetMapping("/allstaff")
	public ResponseEntity<List<Staff>> showAllStaff(@RequestHeader("Authorization") String token) {

		return staffFeignClient.showAllstaff(token);

	}

	@GetMapping("/staff/{id}")
	public ResponseEntity<Staff> showById(@PathVariable("id") int id, @RequestHeader("Authorization") String token)
			throws StaffNotFoundException {

		return staffFeignClient.showById(id, token);

	}

	@PostMapping("/addstaff")
	public ResponseEntity<Staff> addStaff(@RequestBody Staff staffDetails, @RequestHeader("Authorization") String token)
			throws StaffNotFoundException {

		return staffFeignClient.addstaff(staffDetails, token);
	}

	@PutMapping("/updatestaff")
	public ResponseEntity<Staff> updateStaff(@RequestBody Staff staffDetails,
			@RequestHeader("Authorization") String token) throws StaffNotFoundException {

		return staffFeignClient.updatestaff(staffDetails, token);

	}

	@DeleteMapping("/deletestaff/{id}")
	public ResponseEntity<String> deleteStaff(@PathVariable("id") int id, @RequestHeader("Authorization") String token)
			throws StaffNotFoundException {

		return staffFeignClient.deletestaff(id, token);

	}
}
