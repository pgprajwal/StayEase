package com.crio.stayEase.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crio.stayEase.dto.HotelDto;
import com.crio.stayEase.exchanges.CreateHotelRequest;
import com.crio.stayEase.exchanges.GetAllHotelsResponse;
import com.crio.stayEase.exchanges.UpdateHotelRequest;
import com.crio.stayEase.repositoryServices.HotelRepositoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepositoryService hotelRepositoryService;

    @Override
    public HotelDto createHotel(CreateHotelRequest createHotelRequest) {
        String hotelName = createHotelRequest.getHotelName();
        String location = createHotelRequest.getLocation();
        String description = createHotelRequest.getDescription();
        int availableRooms = createHotelRequest.getAvailableRooms();

        HotelDto hotelDto = hotelRepositoryService.createHotel(hotelName, location, description, availableRooms);
        return hotelDto;
    }

    @Override
    public HotelDto findHotelById(int hotelId) {
        HotelDto hotelDto = hotelRepositoryService.findHotelById(hotelId);
        return hotelDto;
    }

    @Override
    public GetAllHotelsResponse findAllHotels() {
        List<HotelDto> hotelDtoList = hotelRepositoryService.findAllHotels();
        return new GetAllHotelsResponse(hotelDtoList);
    }

    @Override
    public HotelDto updateHotel(int hotelId, UpdateHotelRequest updateHotelRequest) {
        String hotelName = updateHotelRequest.getHotelName();
        String location = updateHotelRequest.getLocation();
        String description = updateHotelRequest.getDescription();
        int availableRooms = updateHotelRequest.getAvailableRooms();
        HotelDto hotelDto = hotelRepositoryService.updateHotel(hotelId, hotelName, location, description, availableRooms);
        return hotelDto;
    }

    @Override
    public HotelDto saveHotel(HotelDto hotelDto) {
        return hotelRepositoryService.saveHotel(hotelDto);
    }

    @Override
    public String deleteHotel(int hotelId) {
        String response = "Successfully deleted hotel with ID: " + String.valueOf(hotelId);
        hotelRepositoryService.deleteHotel(hotelId);
        return response;
    }

}
