package com.crio.stayEase.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.stayEase.dto.BookingDto;
import com.crio.stayEase.dto.HotelDto;
import com.crio.stayEase.exchanges.BookRoomRequest;
import com.crio.stayEase.exchanges.CreateHotelRequest;
import com.crio.stayEase.exchanges.GetAllHotelsResponse;
import com.crio.stayEase.exchanges.UpdateHotelRequest;
import com.crio.stayEase.services.HotelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(HotelController.HOTEL_API_ENDPOINT)
@RequiredArgsConstructor
public class HotelController {
    
    public static final String HOTEL_API_ENDPOINT = "/hotels";

    private final HotelService hotelService; 

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody CreateHotelRequest createHotelRequest) {
        return ResponseEntity.ok(hotelService.createHotel(createHotelRequest));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/{hotelId}/book") 
    public ResponseEntity<BookingDto> createBooking(@PathVariable(value = "hotelId") int hotelId, @RequestBody BookRoomRequest bookRoomRequest, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(hotelService.createBooking(hotelId, bookRoomRequest, userDetails));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> findHotelById(@PathVariable( value = "hotelId") int hotelId) {
        return ResponseEntity.ok(hotelService.findHotelById(hotelId));
    }

    @GetMapping
    public ResponseEntity<GetAllHotelsResponse> findAllHotels() {
        return ResponseEntity.ok(hotelService.findAllHotels());
    }

    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable(value = "hotelId") int hotelId, UpdateHotelRequest updateHotelRequest) {
        return ResponseEntity.ok(hotelService.updateHotel(hotelId, updateHotelRequest));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{hotelId}") 
    public ResponseEntity<String> deleteHotel(@PathVariable(value = "hotelId") int hotelId) {
        return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
    }

}
