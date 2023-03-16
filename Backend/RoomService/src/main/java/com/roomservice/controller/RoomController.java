package com.roomservice.controller;

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


import com.roomservice.exception.RoomNotFoundException;
import com.roomservice.model.Room;
import com.roomservice.service.ManagerRoomAuthenticationService;
import com.roomservice.service.OwnerRoomAuthenticationService;
import com.roomservice.service.RecRoomAuthenticationService;
import com.roomservice.service.RoomServiceImplementation;
import com.roomservice.service.SequenceGeneratorService;

@CrossOrigin()
@RestController
@RequestMapping("/room")

public class RoomController {

	@Autowired
	private RoomServiceImplementation service;

	@Autowired
	private OwnerRoomAuthenticationService ownerRoomAuthService;

	@Autowired
	private ManagerRoomAuthenticationService managerRoomAuthService;

	@Autowired
	private RecRoomAuthenticationService recRoomAuthService;
	
	@Autowired
	private   SequenceGeneratorService seqservice;

	
	


	Logger log = LoggerFactory.getLogger(RoomController.class);

	@GetMapping("/all")
	public ResponseEntity<List<Room>> showAllRoom(
			
			@RequestHeader("Authorization") String token
			) {
		try {
			if (ownerRoomAuthService.isSessionValid(token) || managerRoomAuthService.isSessionValid(token)
					|| recRoomAuthService.isSessionValid(token)) {
				List<Room> room = service.showAllRoom();
				if (room.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Rooms are {}", room);
				return new ResponseEntity<>(room, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Room> showById(@PathVariable("id") long id
			,@RequestHeader("Authorization") String token
			)
			 throws RoomNotFoundException {
		try {       
			if (ownerRoomAuthService.isSessionValid(token) ||managerRoomAuthService.isSessionValid(token)||recRoomAuthService.isSessionValid(token)) {
	
		
		Room room = service.showById(id);
		if (room != null) {
			log.debug("Room: {}", room);
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
       throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
       
		catch (ResponseStatusException e) {
     throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
  }
		}

	@PostMapping("/addroom")

	public ResponseEntity<Room> addRoom(@RequestBody Room room
			, @RequestHeader("Authorization") String token
			)
			throws RoomNotFoundException {
		try {
			if (ownerRoomAuthService.isSessionValid(token) || managerRoomAuthService.isSessionValid(token)
				|| recRoomAuthService.isSessionValid(token)) {
		
		long seqid = seqservice.getNextSequenceId("room");
		room.setRoomNumber(seqid);
		Room rm = service.addRoom(room);
		
//				@SuppressWarnings("unused");
				
				if (room != null) {
					log.debug("Rooms: {}", room);
					return new ResponseEntity<>(room, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updateroom")

	public ResponseEntity<Room> updateRoom(@RequestBody Room room, @RequestHeader("Authorization") String token
			)
			throws RoomNotFoundException {
		try {
		if (ownerRoomAuthService.isSessionValid(token) || managerRoomAuthService.isSessionValid(token)
					|| recRoomAuthService.isSessionValid(token)) {

				@SuppressWarnings("unused")
				Room rm = service.updateRoom(room);
				if (room != null) {
					log.debug("Rooms: {}", room);
					return new ResponseEntity<>(room, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
	
	@GetMapping("/findByRoomType/{roomType}")
	public ResponseEntity<List<Room>> getRoomByType(@PathVariable("roomType")String roomType )throws RoomNotFoundException{
		log.info("Retrieving room by room type");
		return ResponseEntity.ok(service.getRoomByType(roomType));
	}
	
	
	
	

	@DeleteMapping("/deleteroom/{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable("id") long id
			, @RequestHeader("Authorization") String token
			)
			throws RoomNotFoundException {
	try {
			if (ownerRoomAuthService.isSessionValid(token) || managerRoomAuthService.isSessionValid(token)
					|| recRoomAuthService.isSessionValid(token)) {
   			service.deleteRoom(id);
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}
