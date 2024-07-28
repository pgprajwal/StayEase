package com.crio.stayEase.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UserDto {
    
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    private Set<BookingDto> bookings;
}
