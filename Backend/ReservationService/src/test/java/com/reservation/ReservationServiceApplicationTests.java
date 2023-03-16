package com.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation;
import com.reservation.repository.ReservationRepository;
import com.reservation.service.ReservationService;

@SpringBootTest
class ReservationServiceApplicationTests {

	@Autowired
	private ReservationService reservationService;

	@MockBean
	private ReservationRepository reservationRepository;

	@Test
	public void ShowAllReservationTest() throws ReservationNotFoundException {
		List<Reservation> reservation = new ArrayList<>();

		Reservation r = new Reservation();

		r.setReservationCode(1);
		
//		r.setCheckIn("2022-12-20");
//		r.setCheckOut("2022-12-22");
		
		r.setName("Pavan");
		
		
		r.setGender("Male");
		
		reservation.add(r);

		when(reservationRepository.findAll()).thenReturn(reservation);
//		assertEquals(1, reservationService.showAllReservationDetails().size());
	}

	@Test
	public void ShowReservationByIdTest() throws ReservationNotFoundException {
		Reservation r = new Reservation();

		r.setReservationCode(1);
		
//		r.setCheckIn("2022-12-20");
//		r.setCheckOut("2022-12-22");
		
		r.setName("Pavan");
		
		r.setGender("Male");
		
		Optional<Reservation> reservation = Optional.of(r);

//		when(reservationRepository.findById(1)).thenReturn(reservation);
//		assertEquals(r, reservationService.showReservationById(1));

	}

	

	@Test
	public void addReservationTest() throws ReservationNotFoundException {

		Reservation r = new Reservation();

		r.setReservationCode(1);
		
//		r.setCheckIn("2022-12-20");
//		r.setCheckOut("2022-12-22");
		
		r.setName("Pavan");
		r.setGender("Male");

		when(reservationRepository.insert(r)).thenReturn(r);
//		assertEquals(r, reservationService.addReservation(r));
	}

	@Test
	public void updateReservationTest() throws ReservationNotFoundException {
		Reservation r1 = new Reservation();
		Reservation r2 = new Reservation();

		r1.setReservationCode(1);
		
		
//		r1.setCheckIn("2022-12-20");
//		r1.setCheckOut("2022-12-22");
		
		r1.setName("Pavan");
		r1.setGender("Male");

		r2.setReservationCode(1);
		
//		r2.setCheckIn("2022-12-20");
//		r2.setCheckOut("2022-12-22");
		
		r2.setName("Pavan");
		r2.setGender("Male");

		Optional<Reservation> reservation = Optional.of(r1);

//		when(reservationRepository.findById(1)).thenReturn(reservation);
		when(reservationRepository.save(r2)).thenReturn(r2);
//		assertEquals(r2, reservationService.updateReservation(r2));
	}

	@Test
	public void deleteReservationTest() throws ReservationNotFoundException {
		Reservation r = new Reservation();

		r.setReservationCode(1);
		
//		r.setCheckIn("2022-12-20");
//		r.setCheckOut("2022-12-22");
		
		r.setName("Pavan");
		r.setGender("Male");

		Optional<Reservation> reservation = Optional.of(r);
//	when(reservationRepository.findById(1)).thenReturn(reservation);
//		assertEquals("Reservation with the id 1 Deleted Successfully!", reservationService.deleteReservation(1));
	}

}