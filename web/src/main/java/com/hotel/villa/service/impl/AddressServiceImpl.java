package com.hotel.villa.service.impl;

import com.hotel.villa.dto.address.CreateAddressDto;
import com.hotel.villa.dto.address.UpdateAddressDto;
import com.hotel.villa.entity.Address;
import com.hotel.villa.enums.Status;
import com.hotel.villa.repository.AddressRepo;
import com.hotel.villa.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public Address createAddress(CreateAddressDto createAddressDto) {

        Address address = new Address();

        address.setCity(createAddressDto.getCity());
        address.setCountry(createAddressDto.getCountry());
        address.setUid(UUID.randomUUID().toString());
        address.setRegistrationAddress(createAddressDto.getRegistrationAddress());
        address.setResidenceAddress(createAddressDto.getResidenceAddress());
        address.setPostalCode(createAddressDto.getPostalCode());
        address.setCreated(LocalDateTime.now());
        address.setStatus(Status.ACTIVE);
        address.setValidDate(LocalDateTime.now());

        return addressRepo.save(address);
    }

    public void deleteAddressById(Long id) {

        Address byId = addressRepo.getById(id);
        byId.setStatus(Status.DELETED);
        byId.setDeleted(LocalDateTime.now());
        byId.setValidDate(null);
        addressRepo.save(byId);
    }

    @Override
    public Address editAddressById(UpdateAddressDto updateAddressDto) {

        Address byId = addressRepo.findAddressById(updateAddressDto.getId());

        if (!byId.getStatus().equals(Status.DELETED)) {
            byId.setUpdated(LocalDateTime.now());
            byId.setPostalCode(updateAddressDto.getPostalCode());
            byId.setResidenceAddress(updateAddressDto.getResidenceAddress());
            byId.setRegistrationAddress(updateAddressDto.getRegistrationAddress());
            byId.setCountry(updateAddressDto.getCountry());
            byId.setCity(updateAddressDto.getCity());
            addressRepo.save(byId);
            return byId;
        }
        return byId;
    }

    @Override
    public Address getAddressById(Long id) {
        addressRepo.findAddressById(id);
        return null;
    }
}
