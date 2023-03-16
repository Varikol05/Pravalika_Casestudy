package com.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.reservation.controller.ReservationController;
import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation;
import com.reservation.service.RecReservationAuthenticationService;
import com.reservation.service.ReservationServiceImplementation;


@SpringBootTest

public class ReservationControllerApplicationTest {
	@Autowired
	private ReservationController controller;

	@MockBean
	private ReservationServiceImplementation service;

	@MockBean
	private RecReservationAuthenticationService authService;

	List<Reservation> reservation = new ArrayList<>();

	String token = "token";
	
	
     @Test
    public void ShowAllReservationControllerTest() throws ReservationNotFoundException {
	List<Reservation> reservation = new ArrayList<>();

	Reservation r = new Reservation();

	r.setReservationCode(1);
	
	r.setName("Pavan");
	
//		r.setCheckIn("2022-12-20");
// 		r.setCheckOut("2022-12-22");
//	
//	r.setPhoneNumber("9100859396");
	r.setGender("Male");
	

	reservation.add(r);
	
	

	when(service.showAllReservationDetails()).thenReturn(reservation);
	when(authService.isSessionValid("token")).thenReturn(true);
//	assertEquals(1, controller.showAllReservationDetails(token).getBody().size());
}
     @Test
 	public void ShowReservationByIdControllerTest() throws ReservationNotFoundException {
 		Reservation r = new Reservation();

 		r.setReservationCode(1);
 		
// 		r.setCheckIn("2022-12-20");
// 		r.setCheckOut("2022-12-22");
 		
 		r.setName("Pavan");
 		
// 		r.setPhoneNumber("9100859396");
 		r.setGender("Male");
 		

 		when(service.showReservationById(1)).thenReturn(r);
        when(authService.isSessionValid("token")).thenReturn(true);
//        assertEquals(r, controller.showReservationDetailsById(1,token).getBody());
    }
     
     @Test
 	public void addReservationTest() throws ReservationNotFoundException {

 		Reservation r = new Reservation();

 		r.setReservationCode(1);
 		r.setName("Pavan");
// 		r.setCheckIn("2022-12-20");
// 		r.setCheckOut("2022-12-22");
 		
 		
 		
// 		r.setPhoneNumber("9100859396");
 		

     
     when(service.addReservation(r)).thenReturn(r);
     when(authService.isSessionValid("token")).thenReturn(true);
//     assertEquals(r, controller.addReservation(r,token).getBody());
 }
     
     @Test
 	public void updateReservationTest() throws ReservationNotFoundException {
 		Reservation r = new Reservation();
 		

 		r.setReservationCode(1);
 		r.setName("Pavan");
// 		r.setCheckIn("2022-12-20");
// 		r.setCheckOut("2022-12-22");
// 		
 		
 		
// 		r.setPhoneNumber("9100859396");
 		
 		
 		 when(service.updateReservation(r)).thenReturn(r);
         when(authService.isSessionValid("token")).thenReturn(true);
//         assertEquals(r, controller.updateReservation(r,token).getBody());
    }
     
     @Test
 	public void deleteReservationTest() throws ReservationNotFoundException {
 		Reservation r = new Reservation();

 		r.setReservationCode(1);
 		r.setName("Pavan");
//		r.setCheckIn("2022-12-20");
//		r.setCheckOut("2022-12-22");
 		
 		
 	
// 		r.setPhoneNumber("9100859396");
// 		
 		when(service.deleteReservation(1)).thenReturn("Deleted Successfully");
        when(authService.isSessionValid("token")).thenReturn(true);
//        assertEquals("Deleted Successfully", controller.deleteReservation(1,token).getBody());
   }}

     

