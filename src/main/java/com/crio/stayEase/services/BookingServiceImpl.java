package com.crio.stayEase.services;

import java.time.LocalDate;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crio.stayEase.dto.BookingDto;
import com.crio.stayEase.dto.HotelDto;
import com.crio.stayEase.entities.enums.BookingStatus;
import com.crio.stayEase.exceptions.InvalidBookingStatusException;
import com.crio.stayEase.exceptions.InvalidGuestCountException;
import com.crio.stayEase.exceptions.RoomsNotAvailableException;
import com.crio.stayEase.exchanges.BookRoomsRequest;
import com.crio.stayEase.repositoryServices.BookingRepositoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final HotelService hotelService;

    private final BookingRepositoryService bookingRepositoryService;

    @Override
    @Transactional
    public BookingDto bookRooms(int hotelId, BookRoomsRequest bookRoomsRequest, UserDetails userDetails) {
        HotelDto hotelDto = hotelService.findHotelById(hotelId);
        int availableRooms = hotelDto.getAvailableRooms();
        int requestedRooms = bookRoomsRequest.getRoomsBooked();
        int guests = bookRoomsRequest.getGuests();
        String userEmail = userDetails.getUsername();
            
        checkRoomsAvailability(availableRooms, requestedRooms);
        validateRoomCapacity(requestedRooms, guests);

        hotelDto.setAvailableRooms(availableRooms - requestedRooms);
        hotelService.saveHotel(hotelDto);
        BookingDto bookingDto = bookingRepositoryService.createBooking(hotelId, userEmail, requestedRooms, guests, bookRoomsRequest.getCheckInDate(), bookRoomsRequest.getCheckOutDate());
        return bookingDto;
    }

    @Override
    public BookingDto checkIn(int bookingId) {
        BookingDto bookingDto = bookingRepositoryService.findBookingById(bookingId);
        BookingStatus bookingStatus = bookingDto.getBookingStatus();
        LocalDate checkInDate = bookingDto.getCheckInDate();
        LocalDate checkOutDate = bookingDto.getCheckOutDate();
        LocalDate currentDate = LocalDate.now();

        if(!bookingStatus.equals(BookingStatus.BOOKED))
            throw new InvalidBookingStatusException("Booking status must be 'BOOKED' to check in"); 

        if(currentDate.isBefore(checkInDate) || currentDate.isAfter(checkOutDate))
            throw new InvalidBookingStatusException("Current date is outside the valid check-in/check-out range");
            
        bookingDto.setBookingStatus(BookingStatus.CHECKED_IN);

        return bookingRepositoryService.updateBooking(bookingDto);
    }

    @Override
    @Transactional
    public BookingDto checkOut(int bookingId) {
        BookingDto bookingDto = bookingRepositoryService.findBookingById(bookingId);
        BookingStatus bookingStatus = bookingDto.getBookingStatus();
    
        if(!bookingStatus.equals(BookingStatus.CHECKED_IN))
            throw new InvalidBookingStatusException("Booking status must be 'CHECKED_IN' to check out"); 

        HotelDto hotelDto = hotelService.findHotelById(bookingDto.getBookedHotel().getId());
        hotelDto.setAvailableRooms(hotelDto.getAvailableRooms() + bookingDto.getRoomsBooked());
        hotelService.saveHotel(hotelDto);

        bookingDto.setBookingStatus(BookingStatus.CHECKED_OUT);
        return bookingRepositoryService.updateBooking(bookingDto);
    }

    @Override
    public BookingDto cancelBooking(int bookingId) {
        BookingDto bookingDto = bookingRepositoryService.findBookingById(bookingId);
        BookingStatus bookingStatus = bookingDto.getBookingStatus();
        LocalDate checkInDate = bookingDto.getCheckInDate();
        LocalDate currentDate = LocalDate.now();

        if(!(bookingStatus.equals(BookingStatus.BOOKED) && currentDate.isBefore(checkInDate)))
            throw new InvalidBookingStatusException("Booking status must be 'BOOKED' to cancel the booking");
        
        HotelDto hotelDto = hotelService.findHotelById(bookingDto.getBookedHotel().getId());
        hotelDto.setAvailableRooms(hotelDto.getAvailableRooms() + bookingDto.getRoomsBooked());
        hotelService.saveHotel(hotelDto);

        bookingDto.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepositoryService.updateBooking(bookingDto);
    }

    private void checkRoomsAvailability(int availableRooms, int requestedRooms) throws RoomsNotAvailableException {
        if(requestedRooms > availableRooms)
            throw new RoomsNotAvailableException("Requested rooms are not available for booking");
    }
    
    private int calculateMaxRoomCapacity(int rooms) {
        final int MAX_GUESTS_PER_ROOM = 2;
        int maxRoomCapacity = rooms * MAX_GUESTS_PER_ROOM;
        return maxRoomCapacity;
    }

    private void validateRoomCapacity(int requestedRooms, int guests) {
        int maxRoomsCapacity = calculateMaxRoomCapacity(requestedRooms);
        int minRoomsCapacity = requestedRooms;

        if(guests < minRoomsCapacity)
            throw new InvalidGuestCountException("Please provide atleast " + minRoomsCapacity + " guests to continue booking");

        if(guests > maxRoomsCapacity) 
            throw new InvalidGuestCountException("Provided number of guests exceeds the room capacity");
    }
}
