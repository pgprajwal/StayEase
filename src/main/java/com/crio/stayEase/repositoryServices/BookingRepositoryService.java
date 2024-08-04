package com.crio.stayEase.repositoryServices;

import java.util.Date;

import com.crio.stayEase.dto.BookingDto;

public interface BookingRepositoryService {
    
    BookingDto createBooking(int hotelId, String userEmail, int roomsBooked, int guests, Date checkInDate, Date checkOutDate);

    BookingDto updateBooking(BookingDto bookingDto);

    BookingDto findBookingById(int bookingId);

    void cancelBooking(int bookingId);
}
