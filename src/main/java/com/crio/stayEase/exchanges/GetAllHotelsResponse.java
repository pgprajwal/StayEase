package com.crio.stayEase.exchanges;

import java.util.List;

import com.crio.stayEase.dto.HotelDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllHotelsResponse {
    private List<HotelDto> hotelDtoList;
}
