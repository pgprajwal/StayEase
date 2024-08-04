package com.crio.stayEase.repositoryServices;

import java.util.Date;

import com.crio.stayEase.dto.BookingDto;

public class BookingRepositoryServiceImpl implements BookingRepositoryService {

    @Override
    public BookingDto createBooking(int hotelId, String userEmail, int roomsBooked, int guests, Date checkInDate, Date checkOutDate) {
        
        throw new UnsupportedOperationException("Unimplemented method 'createBooking'");
    }

    @Override
    public BookingDto updateBooking(BookingDto bookingDto) {
        
        throw new UnsupportedOperationException("Unimplemented method 'updateBooking'");
    }

    @Override
    public BookingDto findBookingById(int bookingId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findBookingById'");
    }

    @Override
    public void cancelBooking(int bookingId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'cancelBooking'");
    }
    
}
