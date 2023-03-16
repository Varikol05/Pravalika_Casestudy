package com.inventory.service;

import java.util.List;

import com.inventory.exception.InventoryNotFoundException;
import com.inventory.model.Inventory;

public interface InventoryService {
	public List<Inventory> showAllInventoryDetails() throws InventoryNotFoundException;

	public Inventory showById(long id) throws InventoryNotFoundException;;

	public Inventory addInventoryDetails(Inventory inventory) throws InventoryNotFoundException;

	public Inventory updateInventoryDetails(Inventory inventory) throws InventoryNotFoundException;

	public String deleteInventoryDetails(long id) throws InventoryNotFoundException;
}
