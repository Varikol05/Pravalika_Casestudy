package com.inventory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.inventory.exception.InventoryNotFoundException;
import com.inventory.model.Inventory;
import com.inventory.service.ManagerInventoryAuthenticationService;
import com.inventory.service.OwnerInventoryAuthenticationService;
import com.inventory.service.SequenceGeneratorService;
import com.inventory.service.InventoryServiceImplementation;
@CrossOrigin()
@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryServiceImplementation service;
	
	@Autowired
	private OwnerInventoryAuthenticationService ownerInventoryAuthService;


	@Autowired
	private ManagerInventoryAuthenticationService managerInventoryAuthService;
	
	@Autowired
	private SequenceGeneratorService seqservice;


	Logger log = LoggerFactory.getLogger(InventoryController.class);

	@GetMapping("/all")
	public ResponseEntity<List<Inventory>> showAllInventory(
//			@RequestHeader("Authorization") String token
			) {
//		try {
//			if (ownerInventoryAuthService.isSessionValid(token)||managerInventoryAuthService.isSessionValid(token)) {
				List<Inventory> inventory = service.showAllInventoryDetails();
				if (inventory.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Inventory are {}", inventory);
				return new ResponseEntity<>(inventory, HttpStatus.OK);
//			}
//			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
//		} catch (ResponseStatusException e) {
//			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
//		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Inventory> showById(@PathVariable("id") long id
			, @RequestHeader("Authorization") String token
			)
			throws InventoryNotFoundException {
		try {
			if (ownerInventoryAuthService.isSessionValid(token)||managerInventoryAuthService.isSessionValid(token)) {
				Inventory inventoryDetails = service.showById(id);
				if (inventoryDetails != null) {
					log.debug("Inventory Details: {}", inventoryDetails);
					return new ResponseEntity<>(inventoryDetails, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PostMapping("/addinventory")
	public ResponseEntity<Inventory> addInventory(
			@RequestBody Inventory inventoryDetails
			
,@RequestHeader("Authorization") String token
			) throws InventoryNotFoundException {
		try {
			if (ownerInventoryAuthService.isSessionValid(token)||managerInventoryAuthService.isSessionValid(token)) {
		     
		long seqid = seqservice.getNextSequenceId("inventory");
        inventoryDetails.setInventoryCode(seqid);
				Inventory inventory = service.addInventoryDetails(inventoryDetails);
				
				
				if (inventory != null) {
					log.debug("Inventory Details: {}", inventory);
					return new ResponseEntity<>(inventory, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updateinventory")
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventoryDetails
			,@RequestHeader("Authorization") String token
			) throws InventoryNotFoundException {
	try {
		if (ownerInventoryAuthService.isSessionValid(token)||managerInventoryAuthService.isSessionValid(token)) {
				Inventory inventory = service.updateInventoryDetails(inventoryDetails);

				if (inventory != null) {
					log.debug("Inventory Details: {}", inventory);
					return new ResponseEntity<>(inventory, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteInventory(@PathVariable("id") long id
			,@RequestHeader("Authorization") String token
			) throws InventoryNotFoundException {
		try {
			if (ownerInventoryAuthService.isSessionValid(token)||managerInventoryAuthService.isSessionValid(token)) {
				service.deleteInventoryDetails(id);
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}