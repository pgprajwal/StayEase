package com.crio.stayEase.exchanges;

import java.util.Set;

import com.crio.stayEase.dto.BookingDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateHotelRequest {

    @NotEmpty
    private String hotelName;

    @NotEmpty
    private String location;

    @NotEmpty
    private String description;

    @Positive
    private int availableRooms;

    private Set<BookingDto> bookingDtoList;

}
