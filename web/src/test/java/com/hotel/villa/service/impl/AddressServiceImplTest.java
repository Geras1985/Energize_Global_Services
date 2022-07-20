package com.hotel.villa.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hotel.villa.dto.address.CreateAddressDto;
import com.hotel.villa.dto.address.UpdateAddressDto;
import com.hotel.villa.entity.Address;
import com.hotel.villa.entity.Hotel;
import com.hotel.villa.entity.User;
import com.hotel.villa.enums.Status;
import com.hotel.villa.repository.AddressRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {
    @MockBean
    private AddressRepo addressRepo;

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    /**
     * Method under test: {@link AddressServiceImpl#createAddress(CreateAddressDto)}
     */
    @Test
    void testCreateAddress() {

        Address mockAddress = createMockAddress();
        when(this.addressRepo.save(any())).thenReturn(mockAddress);

        CreateAddressDto createAddressDto = new CreateAddressDto();
        createAddressDto.setCity("Oxford");
        createAddressDto.setCountry("GB");
        createAddressDto.setPostalCode("Postal Code");
        createAddressDto.setRegistrationAddress("42 Main St");
        createAddressDto.setResidenceAddress("42 Main St");
        assertSame(mockAddress, this.addressServiceImpl.createAddress(createAddressDto));
        verify(this.addressRepo).save(any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#deleteAddressById(Long)}
     */
    @Test
    void testDeleteAddressById() {
        Address address = createMockAddress();

        Address address1 = createMockAddress();
        when(this.addressRepo.save(any())).thenReturn(address1);
        when(this.addressRepo.getById(any())).thenReturn(address);
        this.addressServiceImpl.deleteAddressById(123L);
        verify(this.addressRepo).getById(any());
        verify(this.addressRepo).save(any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#editAddressById(UpdateAddressDto)}
     */
    @Test
    void testEditAddressById() {

        Address address = createMockAddress();

        Address address1 = createMockAddress();

        when(this.addressRepo.save(any())).thenReturn(address1);
        when(this.addressRepo.findAddressById(any())).thenReturn(address);

        UpdateAddressDto updateAddressDto = new UpdateAddressDto();
        updateAddressDto.setCity("Oxford");
        updateAddressDto.setCountry("GB");
        updateAddressDto.setId(123L);
        updateAddressDto.setPostalCode("Postal Code");
        updateAddressDto.setRegistrationAddress("42 Main St");
        updateAddressDto.setResidenceAddress("42 Main St");
        Address actualEditAddressByIdResult = this.addressServiceImpl.editAddressById(updateAddressDto);
        assertSame(address, actualEditAddressByIdResult);
        assertEquals("Oxford", actualEditAddressByIdResult.getCity());
        assertEquals("GB", actualEditAddressByIdResult.getCountry());
        assertEquals("42 Main St", actualEditAddressByIdResult.getRegistrationAddress());
        assertEquals("42 Main St", actualEditAddressByIdResult.getResidenceAddress());
        assertEquals("Postal Code", actualEditAddressByIdResult.getPostalCode());
        verify(this.addressRepo).findAddressById(any());
        verify(this.addressRepo).save(any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#getAddressById(Long)}
     */
    @Test
    void testGetAddressById() {

        Address address = createMockAddress();

        when(this.addressRepo.findAddressById(any())).thenReturn(address);
        assertNull(this.addressServiceImpl.getAddressById(123L));
        verify(this.addressRepo).findAddressById(any());
    }

    private Hotel createMockHotel(){
        Hotel hotel = new Hotel();
        hotel.setAddress(new HashSet<>());
        hotel.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel.setEmail("jane.doe@example.org");
        hotel.setId(123L);
        hotel.setName("Name");
        hotel.setPhone("4105551212");
        hotel.setStatus(Status.ACTIVE);
        hotel.setUid("1234");
        hotel.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        return hotel;
    }

    private User createMockUser(){
        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("I Love You");
        user.setRoles(new ArrayList<>());
        user.setStatus(Status.ACTIVE);
        user.setUid("1234");
        user.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("jane");
        user.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        return user;
    }

    private Address createMockAddress(){
        Address address = new Address();
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setHotel(createMockHotel());
        address.setId(123L);
        address.setPostalCode("Postal Code");
        address.setRegistrationAddress("42 Main St");
        address.setResidenceAddress("42 Main St");
        address.setStatus(Status.ACTIVE);
        address.setUid("1234");
        address.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setUser(createMockUser());
        address.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        return address;
    }
}

