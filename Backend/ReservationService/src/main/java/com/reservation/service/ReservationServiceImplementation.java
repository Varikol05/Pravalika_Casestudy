package com.reservation.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation;
import com.reservation.repository.ReservationRepository;

@Service
public class ReservationServiceImplementation implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepo;

	Logger log = LoggerFactory.getLogger(ReservationServiceImplementation.class);

	@Override
	public List<Reservation> showAllReservationDetails() throws ReservationNotFoundException {
		log.info("Show All Reservations Method Started");
		List<Reservation> reservation = reservationRepo.findAll();
		log.debug("Reservations are {} ", reservation);
		log.info("Show All Reservations Method Ended");
		return reservation;
	}

	@Override
	public Reservation showReservationById(long id) throws ReservationNotFoundException {
		log.info("Show Reservation By Id Method Started");
		return reservationRepo.findById(id)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation with the id " + id + " Doesn't Exists"));
	}

	@Override
	public Reservation addReservation(Reservation reservation) throws ReservationNotFoundException {
		log.info("Add Reservation Method Started");
		Optional<Reservation> r = reservationRepo.findById(reservation.getReservationCode());
		if (!r.isPresent()) {
			log.info("Add Reservation Method Ended");
			return reservationRepo.insert(reservation);
		} else {
			return r.orElseThrow(() -> new ReservationNotFoundException("Reservation Already Exists"));
		}
	}

	@Override
	public Reservation updateReservation(Reservation reservation) throws ReservationNotFoundException {
		log.info("Update Reservation Method Started");
		Optional<Reservation> r = reservationRepo.findById(reservation.getReservationCode());
		if (!r.isPresent())
			return r.orElseThrow(() -> new ReservationNotFoundException(
					"Reservation with the id " + reservation.getReservationCode() + " Doesn't Exists"));
		log.info("Update Reservation Method Ended");
		return reservationRepo.save(reservation);
	}

	@Override
	public String deleteReservation(long id) throws ReservationNotFoundException {
		log.info("Delete Reservation Method Started");
		Optional<Reservation> reservation = reservationRepo.findById(id);
		if (!reservation.isPresent()) {
			reservation.orElseThrow(
					() -> new ReservationNotFoundException("Reservation with the id " + id + " Doesn't Exists"));
		} else {
			reservationRepo.deleteById(id);
			log.debug("Deleted SuccessFully {}", reservation);
			log.info("Delete Reservation Method Ended");
		}
		return "Reservation with the id " + id + " Deleted Successfully!";
	}

}
