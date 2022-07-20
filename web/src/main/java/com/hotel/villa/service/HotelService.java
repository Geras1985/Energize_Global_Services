package com.hotel.villa.service;

import com.hotel.villa.entity.Hotel;
import com.hotel.villa.repository.HotelRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface HotelService {

    Hotel createHotel(Hotel hotel);

    Hotel editHotelById(Long id);

    void deleteHotelById(Long id);

    List<Hotel> getAllHotel();

    Hotel getHotelById(Long id);
}
