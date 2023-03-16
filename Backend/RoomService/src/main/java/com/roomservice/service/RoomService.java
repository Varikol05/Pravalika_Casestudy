package com.roomservice.service;

import java.util.List;

import com.roomservice.exception.RoomNotFoundException;
import com.roomservice.model.Room;

public interface RoomService {
	public List<Room> showAllRoom() throws RoomNotFoundException;

	public Room showById(long id) throws RoomNotFoundException;

	public Room addRoom(Room room) throws RoomNotFoundException;

	public Room updateRoom(Room room) throws RoomNotFoundException;
	
	public List<Room> getRoomByType(String roomType) throws RoomNotFoundException;

	public String deleteRoom(long id) throws RoomNotFoundException;
}