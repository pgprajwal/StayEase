package com.crio.stayEase.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.crio.stayEase.dto.BookingDto;
import com.crio.stayEase.dto.HotelDto;
import com.crio.stayEase.exchanges.BookRoomRequest;
import com.crio.stayEase.exchanges.CreateHotelRequest;
import com.crio.stayEase.exchanges.GetAllHotelsResponse;
import com.crio.stayEase.exchanges.UpdateHotelRequest;

public interface HotelService {
    
    HotelDto createHotel(CreateHotelRequest createHotelRequest);

    BookingDto createBooking(int hotelId, BookRoomRequest bookRoomsRequest, UserDetails userDetails);
    
    HotelDto findHotelById(int hotelId);

    GetAllHotelsResponse findAllHotels();

    HotelDto updateHotel(int hotelId, UpdateHotelRequest updateHotelRequest);

    HotelDto saveHotel(HotelDto hotelDto);

    String deleteHotel(int hotelId);
}
