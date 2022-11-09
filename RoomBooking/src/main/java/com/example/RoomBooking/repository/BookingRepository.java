package com.example.RoomBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RoomBooking.model.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{
	public Booking findByType(String type);
	
	
	@Override
	List<Booking> findAll();


}
