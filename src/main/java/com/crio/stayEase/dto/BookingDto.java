package com.crio.stayEase.dto;

import java.time.LocalDate;

import com.crio.stayEase.entities.enums.BookingStatus;

import lombok.Data;

@Data
public class BookingDto {

    private int id;

    private UserDto user;

    private HotelDto bookedHotel;

    private int guests;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private BookingStatus bookingStatus;

    private LocalDate bookingDate;
}
