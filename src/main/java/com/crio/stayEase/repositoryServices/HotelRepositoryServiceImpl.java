package com.crio.stayEase.repositoryServices;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.crio.stayEase.dto.BookingDto;
import com.crio.stayEase.dto.HotelDto;
import com.crio.stayEase.entities.Hotel;
import com.crio.stayEase.exceptions.HotelNotFoundException;
import com.crio.stayEase.mapper.Mapper;
import com.crio.stayEase.repositories.HotelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelRepositoryServiceImpl implements HotelRepositoryService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelDto createHotel(String hotelName, String location, String description, int availableRooms) {
        Hotel hotel = Hotel.builder()
                      .name(hotelName)
                      .location(location)
                      .description(description)
                      .availableRooms(availableRooms)
                      .build();

        Hotel savedHotel = hotelRepository.save(hotel);
        return Mapper.mapToHotelDto(savedHotel);
    }

    @Override
    public HotelDto findHotelById(int hotelId) {
        String message = "Could not find hotel with hotelId: " + hotelId;
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException(message));
        return Mapper.mapToHotelDto(hotel);
    }

    @Override
    public List<HotelDto> findAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return Mapper.mapToHotelDtoList(hotels);
    }

    @Override
    public HotelDto updateHotel(int hotelId, String hotelName, String location, String description,
            int availableRooms, Set<BookingDto> bookingDtoSet) {
        String message = "Could not find hotel with hotelId: " + hotelId;

        if(!hotelRepository.existsById(hotelId))
           throw new HotelNotFoundException(message);     

        Hotel hotel = Hotel.builder()
                      .id(hotelId)
                      .name(hotelName)
                      .location(location)
                      .description(description)
                      .availableRooms(availableRooms)
                      .bookings(Mapper.mapToBookingSet(bookingDtoSet))
                      .build();
        
        Hotel updatedHotel = hotelRepository.save(hotel);
        return Mapper.mapToHotelDto(updatedHotel);
    }

    @Override
    public HotelDto saveHotel(HotelDto hotelDto) {
        Hotel savedHotel = hotelRepository.save(Mapper.mapToHotel(hotelDto));
        return Mapper.mapToHotelDto(savedHotel);
    }

    @Override
    public void deleteHotel(int hotelId) {
        String message = "Could not find hotel with hotelId: " + hotelId;
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException(message));
        hotelRepository.delete(hotel);
    }

}
