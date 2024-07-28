package com.crio.stayEase.dto;

import java.util.Set;

import lombok.Data;

@Data
public class HotelDto {
    
    private int id;

    private String name;

    private String location;

    private String description;

    private int availableRooms;

    private Set<BookingDto> bookings;
}
