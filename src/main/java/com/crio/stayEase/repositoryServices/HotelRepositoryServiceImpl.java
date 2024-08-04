package com.crio.stayEase.repositoryServices;

import java.util.List;

import com.crio.stayEase.dto.HotelDto;

public class HotelRepositoryServiceImpl implements HotelRepositoryService {

    @Override
    public HotelDto createHotel(String hotelName, String location, String description, int availableRooms) {
       
        throw new UnsupportedOperationException("Unimplemented method 'createHotel'");
    }

    @Override
    public HotelDto findHotelById(int hotelId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findHotelById'");
    }

    @Override
    public List<HotelDto> findAllHotels() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAllHotels'");
    }

    @Override
    public HotelDto updateHotel(int hotelId, String hotelName, String location, String description,
            int availableRooms) {
        
        throw new UnsupportedOperationException("Unimplemented method 'updateHotel'");
    }

    @Override
    public HotelDto saveHotel(HotelDto hotelDto) {
        
        throw new UnsupportedOperationException("Unimplemented method 'saveHotel'");
    }

    @Override
    public void deleteHotel(int hotelId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'deleteHotel'");
    }

}
