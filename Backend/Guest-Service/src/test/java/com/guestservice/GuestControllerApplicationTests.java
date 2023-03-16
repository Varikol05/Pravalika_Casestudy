package com.guestservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.guestservice.Controller.GuestDetailsController;
import com.guestservice.exception.GuestNotFoundException;
import com.guestservice.model.GuestDetails;
import com.guestservice.service.RecGuestAuthenticationService;
import com.guestservice.service.GuestDetailsServiceImplementation;



@SpringBootTest

public class GuestControllerApplicationTests {
	
	@Autowired
	private GuestDetailsController controller;

	@MockBean
	private GuestDetailsServiceImplementation service;

	@MockBean
	private RecGuestAuthenticationService authService;

	List<GuestDetails> guest = new ArrayList<>();

	String token = "token";


@Test
public void ShowGuestByIdControllerTest() throws GuestNotFoundException {
    GuestDetails g = new GuestDetails();


    g.setGuestId(1);
	g.setName("Pravalika");
	g.setContact(97826735);
	g.setGender("female");
	g.setEmail("pravalika@gmail.com");
	g.setAddress("Waranagl");
     
	 

	 

	when(service.showGuestById(1)).thenReturn(g);
    when(authService.isSessionValid("token")).thenReturn(true);
    assertEquals(g, controller.showGuestDetailsById(1,token).getBody());
}

@Test
public void addGuestTest() throws GuestNotFoundException {



   GuestDetails g = new GuestDetails();

   g.setGuestId(1);
	g.setName("Pravalika");
	g.setContact(97826735);
	g.setGender("female");
	g.setEmail("pravalika@gmail.com");
	g.setAddress("Waranagl");
     
	when(service.addGuestDetails(g)).thenReturn(g);
    when(authService.isSessionValid("token")).thenReturn(true);
    assertEquals(g, controller.addGuestDetails(g,token).getBody());
}

@Test
public void updateFarmerTest() throws GuestNotFoundException {
    GuestDetails g = new GuestDetails();
    


    g.setGuestId(1);
	g.setName("Pravalika");
	g.setContact(97826735);
	g.setGender("female");
	g.setEmail("pravalika@gmail.com");
	g.setAddress("Waranagl");
	
	when(service.updateGuestDetails(g)).thenReturn(g);
    when(authService.isSessionValid("token")).thenReturn(true);
    assertEquals(g, controller.updateGuestDetails(g,token).getBody());
}

@Test
public void deleteGuestTest() throws GuestNotFoundException {
    GuestDetails g = new GuestDetails();



    g.setGuestId(1);
	g.setName("Pravalika");
	g.setContact(97826735);
	g.setGender("female");
	g.setEmail("pravalika@gmail.com");
	g.setAddress("Waranagl");
    
	
	when(service.deleteGuestDetails(1)).thenReturn("Deleted Successfully");
    when(authService.isSessionValid("token")).thenReturn(true);
    assertEquals("Deleted Successfully", controller.deleteGuestDetails(1,token).getBody());
}

@Test
public void ShowGuestByIdTest() throws GuestNotFoundException {
    GuestDetails g = new GuestDetails();


    g.setGuestId(1);
	g.setName("Pravalika");
	g.setContact(97826735);
	g.setGender("female");
	g.setEmail("pravalika@gmail.com");
	g.setAddress("Waranagl");

	 guest.add(g);

	 

     when(service.showAllGuestDetails()).thenReturn(guest);
     when(authService.isSessionValid("token")).thenReturn(true);
     assertEquals(1, controller.showAllGuestDetails(token).getBody().size());
 }}



    
