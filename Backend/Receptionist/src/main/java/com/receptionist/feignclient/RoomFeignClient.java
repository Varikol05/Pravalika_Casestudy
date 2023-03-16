package com.receptionist.feignclient;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.receptionist.exception.RoomNotFoundException;
import com.receptionist.model.Room;

@FeignClient(name="Room-Service", url="http://localhost:9001/room")
public interface RoomFeignClient {
							
	@GetMapping("/all")
	public ResponseEntity<List<Room>> showAllRoom(@RequestHeader("Authorization") String token);
			
	@GetMapping("/{id}")
	public ResponseEntity<Room> showById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws RoomNotFoundException;
}
