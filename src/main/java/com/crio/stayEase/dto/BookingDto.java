package com.crio.stayEase.dto;

import java.util.Date;

import com.crio.stayEase.entities.Hotel;
import com.crio.stayEase.entities.enums.BookingStatus;

import lombok.Data;

@Data
public class BookingDto {

    private int id;

    private UserDto user;

    private Hotel bookedHotel;

    private int roomsBooked;

    private int guests;

    private Date checkInDate;

    private Date checkOutDate;

    private BookingStatus bookingStatus;

    private Date bookingDate;
}
