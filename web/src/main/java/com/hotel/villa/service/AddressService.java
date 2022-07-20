package com.hotel.villa.service;


import com.hotel.villa.dto.address.CreateAddressDto;
import com.hotel.villa.dto.address.UpdateAddressDto;
import com.hotel.villa.entity.Address;

public interface AddressService {

    Address createAddress(CreateAddressDto createAddressDto);

    Address editAddressById(UpdateAddressDto createAddressDto);

    Address getAddressById(Long id);
}
