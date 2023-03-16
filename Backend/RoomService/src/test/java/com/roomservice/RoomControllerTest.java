package com.roomservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.roomservice.controller.RoomController;
import com.roomservice.exception.RoomNotFoundException;
import com.roomservice.model.Room;
import com.roomservice.service.OwnerRoomAuthenticationService;
import com.roomservice.service.RoomServiceImplementation;

@SpringBootTest

public class RoomControllerTest {

	@Autowired
	private RoomController controller;

	@MockBean
	private RoomServiceImplementation service;

	@MockBean
	private OwnerRoomAuthenticationService authService;

	List<Room> room = new ArrayList<>();

	String token = "token";

	@Test
	public void ShowAllRoomControllerTest() throws RoomNotFoundException {
		List<Room> room = new ArrayList<>();

		Room r = new Room();

		r.setRoomNumber(1);
		r.setRoomStatus(true);
		r.setRoomType("Double");
		r.setRoomPrice(1500);

		room.add(r);

		when(service.showAllRoom()).thenReturn(room);
		when(authService.isSessionValid("token")).thenReturn(true);
//		assertEquals(1, controller.showAllRoom(token).getBody().size());
	}
	
	@Test
	public void showRoomByIdControllerTest() throws RoomNotFoundException {
	    Room r = new Room();



	    r.setRoomNumber(1);
	    r.setRoomStatus(true);
	    r.setRoomType("Double");
		r.setRoomPrice(1500);
	    
	    
	    when(service.showById(1)).thenReturn(r);
        when(authService.isSessionValid("token")).thenReturn(true);
//        assertEquals(r, controller.showById(1,token).getBody());
    }
     
	@Test
	public void addRoomDetailsTest() throws RoomNotFoundException {



	    Room r = new Room();



	    r.setRoomNumber(1);
	    r.setRoomStatus(true);
    r.setRoomType("Double");
		r.setRoomPrice(1500);

	    when(service.addRoom(r)).thenReturn(r);
        when(authService.isSessionValid("token")).thenReturn(true);
//        assertEquals(r, controller.addRoom(r,token).getBody());
}
	@Test
	public void updateRoomTest() throws RoomNotFoundException {
	    Room r = new Room();
	    



	    r.setRoomNumber(1);
	    r.setRoomStatus(true);
	    r.setRoomType("Single");
		r.setRoomPrice(1200);


	    when(service.updateRoom(r)).thenReturn(r);
        when(authService.isSessionValid("token")).thenReturn(true);
//        assertEquals(r, controller.updateRoom(r,token).getBody());
    }
	    
	@Test
	public void deleteRoomTest() throws RoomNotFoundException {
	    Room r = new Room();



	    r.setRoomNumber(1);
	    r.setRoomStatus(true);
	    r.setRoomType("Luxury");
		r.setRoomPrice(2000);

	    when(service.deleteRoom(1)).thenReturn("Deleted Successfully");
        when(authService.isSessionValid("token")).thenReturn(true);
//         assertEquals("Deleted Successfully", controller.deleteRoom(1,token).getBody());
	


}
}

