package com.roomservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roomservice.model.Room;
@Repository
public interface RoomRepository extends MongoRepository<Room,Long> {
	
	

	List<Room> findByRoomType(String roomName);

}
