package com.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.inventory.controller.InventoryController;
import com.inventory.exception.InventoryNotFoundException;
import com.inventory.model.Inventory;
import com.inventory.service.ManagerInventoryAuthenticationService;
import com.inventory.service.InventoryServiceImplementation;

@SpringBootTest
public class InventoryControllerServiceApplication {
	@Autowired
	private InventoryController controller;

	@MockBean
	private InventoryServiceImplementation service;

	@MockBean
	private ManagerInventoryAuthenticationService authService;

	List<Inventory> inventory = new ArrayList<>();

	String token = "token";
	@Test
	public void ShowAllInventoryTest() throws InventoryNotFoundException {
		List<Inventory> inventory = new ArrayList<>();

		Inventory i= new Inventory();

		i.setInventoryCode(1);
		i.setInventoryType("Furniture");
		i.setInventoryName("Table");
		i.setInventoryQuantity(50);
		
		inventory.add(i);

		 when(service.showAllInventoryDetails()).thenReturn(inventory);
	        when(authService.isSessionValid("token")).thenReturn(true);
//	        assertEquals(1, controller.showAllInventory(token).getBody().size());
	    }
	
	@Test
    public void ShowInventoryByIdTest() throws InventoryNotFoundException {
        Inventory i = new Inventory();


        i.setInventoryCode(1);
		i.setInventoryType("Furniture");
		i.setInventoryName("Table");
		i.setInventoryQuantity(50);
		
		
		 when(service.showById(1)).thenReturn(i);
	        when(authService.isSessionValid("token")).thenReturn(true);
	        assertEquals(i, controller.showById(1,token).getBody());
	    }
	
	@Test
    public void addInventoryTest() throws InventoryNotFoundException {



	 Inventory i = new Inventory();

	 i.setInventoryCode(1);
		i.setInventoryType("Furniture");
		i.setInventoryName("Table");
		i.setInventoryQuantity(50);
		
		when(service.addInventoryDetails(i)).thenReturn(i);
        when(authService.isSessionValid("token")).thenReturn(true);
//        assertEquals(i, controller.addInventory(i,token).getBody());
    }
	
	@Test
    public void updateInventoryTest() throws InventoryNotFoundException {
	 Inventory i = new Inventory();
	 




	 i.setInventoryCode(1);
		i.setInventoryType("Furniture");
		i.setInventoryName("Table");
		i.setInventoryQuantity(50);
		
		
		  when(service.updateInventoryDetails(i)).thenReturn(i);
	        when(authService.isSessionValid("token")).thenReturn(true);
	        assertEquals(i, controller.updateInventory(i,token).getBody());
	    }
	
	@Test
    public void deleteInventoryTest() throws InventoryNotFoundException {
	 Inventory i= new Inventory();



	 i.setInventoryCode(1);
		i.setInventoryType("Furniture");
		i.setInventoryName("Table");
		i.setInventoryQuantity(50);

		 when(service.deleteInventoryDetails(1)).thenReturn("Deleted Successfully");
	        when(authService.isSessionValid("token")).thenReturn(true);
	        assertEquals("Deleted Successfully", controller.deleteInventory(1,token).getBody());
	    }
		
}
	
	
	

