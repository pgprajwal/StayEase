package com.crio.stayEase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.stayEase.entities.User;

public interface HotelRepository extends JpaRepository<User, Integer> {
    
}
