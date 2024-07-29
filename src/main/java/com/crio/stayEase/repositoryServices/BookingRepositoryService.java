package com.crio.stayEase.repositoryServices;

import java.util.Date;
import java.util.Optional;

import com.crio.stayEase.dto.BookingDto;

public interface BookingRepositoryService {
    
    BookingDto createBooking(int hotelId, int roomsBooked, int guests, Date checkInDate, Date checkOutDate);

    BookingDto updateBooking(BookingDto bookingDto);

    Optional<BookingDto> findBookingById(int bookingId);

    void cancelBooking(int bookingId);
}
