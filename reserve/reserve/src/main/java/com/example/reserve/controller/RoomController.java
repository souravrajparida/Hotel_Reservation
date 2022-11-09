package com.example.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reserve.model.Room;
import com.example.reserve.repository.RoomRepo;
//import com.example.reserve.service.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	RoomRepo roomRepo;
	@CrossOrigin(origins = "http://localhost:4200")

   @GetMapping(path="getdata")
   
	List<Room> getRooms(){
	   
return roomRepo.findAll();
	}

}
