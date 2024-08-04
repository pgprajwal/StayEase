package com.crio.stayEase.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.crio.stayEase.dto.BookingDto;
import com.crio.stayEase.exchanges.BookRoomsRequest;

public interface BookingService {
    
    BookingDto bookRooms(int hotelId, BookRoomsRequest bookRoomsRequest, UserDetails userDetails);

    BookingDto checkIn(int bookingId);

    BookingDto checkOut(int bookingId);

    BookingDto cancelBooking(int bookingId);
}
