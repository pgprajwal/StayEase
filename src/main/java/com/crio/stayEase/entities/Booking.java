package com.crio.stayEase.entities;

import java.time.LocalDate;

import com.crio.stayEase.entities.enums.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel bookedHotel;

    private int roomsBooked;

    private int guests;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDate bookingDate;
}
