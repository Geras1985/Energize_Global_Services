package com.hotel.villa.repository;

import com.hotel.villa.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    Hotel findHotelByName(String hotelName);
}
