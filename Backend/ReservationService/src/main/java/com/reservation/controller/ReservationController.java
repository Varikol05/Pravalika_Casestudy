package com.reservation.controller;

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

import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation;
import com.reservation.service.OwnerReservationAuthenticationService;
import com.reservation.service.RecReservationAuthenticationService;
import com.reservation.service.ReservationServiceImplementation;
import com.reservation.service.SequenceGeneratorService;
@CrossOrigin()
@RestController
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationServiceImplementation service;
    
	@Autowired
	private OwnerReservationAuthenticationService ownerReservationAuthService;
	
	@Autowired
	private RecReservationAuthenticationService recReservationAuthService;
	
	@Autowired
	private   SequenceGeneratorService seqservice;

	Logger log = LoggerFactory.getLogger(ReservationController.class);

	@GetMapping("/roomprice/{roomType}")
	public String getPrice(@PathVariable("roomType") String roomType,@RequestHeader("Authorization") String token) {
		try {
			if (ownerReservationAuthService.isSessionValid(token)||recReservationAuthService.isSessionValid(token)) {
				if (roomType.equals("SingleCot"))
					return "Single Cot " + 1000.0;
				else if (roomType.equals("DoubleCot"))
					return "Double Cot " + 2000.0;
				else if (roomType.equals("Deluxe"))
					return "Deluxe " + 4000.0;
				else
					return ("Enter a valid room type");
		}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Reservation>> showAllReservationDetails(@RequestHeader("Authorization") String token)
			throws ReservationNotFoundException {
	try {		
		if (ownerReservationAuthService.isSessionValid(token)||recReservationAuthService.isSessionValid(token)) {

				List<Reservation> reservations = service.showAllReservationDetails();
				if (reservations.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Reservation are {}", reservations);
				return new ResponseEntity<>(reservations, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reservation> showReservationDetailsById(@PathVariable("id") long id,@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (ownerReservationAuthService.isSessionValid(token)||recReservationAuthService.isSessionValid(token)) {
				Reservation reservation = service.showReservationById(id);
				if (reservation != null) {
					log.debug("Reservations: {}", reservation);
					return new ResponseEntity<>(reservation, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PostMapping("/addreservation")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation,@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (ownerReservationAuthService.isSessionValid(token)||recReservationAuthService.isSessionValid(token)) {
                
				long seqid = seqservice.getNextSequenceId("reservation");
				reservation.setReservationCode(seqid);
				
				
				Reservation r = service.addReservation(reservation);
				if (reservation != null) {
					log.debug("Reservation: {}", r);
					return new ResponseEntity<>(r, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updatereservation")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation
			,@RequestHeader("Authorization") String token
			) throws ReservationNotFoundException {
		try {
			if (ownerReservationAuthService.isSessionValid(token)||recReservationAuthService.isSessionValid(token)) {

				Reservation r = service.updateReservation(reservation);
				if (r != null) {
					log.debug("Reservation: {}", r);
					return new ResponseEntity<>(r, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteReservation(@PathVariable("id") long id
			,@RequestHeader("Authorization") String token
			) throws ReservationNotFoundException {
		try {
			if (ownerReservationAuthService.isSessionValid(token)||recReservationAuthService.isSessionValid(token)) {

				service.deleteReservation(id);
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}
