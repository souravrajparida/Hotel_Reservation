package com.example.RoomBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.RoomBooking.service.BookingService;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.RoomBooking.model.Booking;
import com.example.RoomBooking.repository.BookingRepository;
import com.example.RoomBooking.service.BookingService;



@RestController
public class BookingController {

	
	@Autowired
	private BookingService service;
	@Autowired
	BookingRepository bookingRepository;
	
	@PostMapping("/bookuser")
	@CrossOrigin(origins = "http://localhost:4200")
	public Booking bookUser(@RequestBody Booking booking) throws Exception{
		
		
		Booking userObj=null;
		userObj=service.saveUser(booking);
		return userObj;
		
	}
	
	@GetMapping(path="getrooms")
	@CrossOrigin(origins = "http://localhost:4200")

	   
	List<Booking> getRooms1(){
	   
        return bookingRepository.findAll();
	}

	@DeleteMapping("/deletebooking")
	@CrossOrigin(origins = "http://localhost:4200")

	public void deletebooking(@RequestParam Integer id){
		
		service.deletebooking(id);
		
	}


}
