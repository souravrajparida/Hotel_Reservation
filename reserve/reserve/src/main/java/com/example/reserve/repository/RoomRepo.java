package com.example.reserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reserve.model.Room;


@Repository
public interface RoomRepo extends JpaRepository<Room,Integer> {
	
	@Override
	List<Room> findAll();

}
