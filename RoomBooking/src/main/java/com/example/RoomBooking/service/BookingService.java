package com.example.RoomBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RoomBooking.model.Booking;
import com.example.RoomBooking.repository.BookingRepository;


@Service
public class BookingService {
	
	@Autowired
private BookingRepository repo;
	
	public Booking saveUser(Booking booking){
		
		return repo.save(booking);
		
	}
	public Booking fetchUserByType(String type){
		return repo.findByType(type);
	}

	
	public void deletebooking(Integer id){
		
		repo.deleteById(id);

}}
