package com.crio.stayEase.repositoryServices;

import java.util.List;

import com.crio.stayEase.dto.HotelDto;

public interface HotelRepositoryService {
    
    HotelDto createHotel(String hotelName, String location, String description, int availableRooms);

    List<HotelDto> getAllHotels();

    HotelDto updateHotel(int hotelId, String hotelName, String location, String description, int availableRooms);

    void deleteHotel(int hotelId);
    
}
