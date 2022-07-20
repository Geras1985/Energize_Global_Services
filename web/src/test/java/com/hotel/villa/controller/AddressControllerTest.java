package com.hotel.villa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hotel.villa.dto.address.CreateAddressDto;
import com.hotel.villa.dto.address.UpdateAddressDto;
import com.hotel.villa.entity.Address;
import com.hotel.villa.entity.Hotel;
import com.hotel.villa.entity.User;
import com.hotel.villa.enums.Status;
import com.hotel.villa.repository.AddressRepo;
import com.hotel.villa.service.impl.AddressServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AddressControllerTest {
    /**
     * Method under test: {@link AddressController#getAddressById(Long)}
     */
    @Test
    void testGetAddressById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   See https://diff.blue/R026 to resolve this issue.

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

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setStatus(Status.ACTIVE);
        user.setUid("1234");
        user.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("janedoe");
        user.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Address address = new Address();
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setHotel(hotel);
        address.setId(123L);
        address.setPostalCode("Postal Code");
        address.setRegistrationAddress("42 Main St");
        address.setResidenceAddress("42 Main St");
        address.setStatus(Status.ACTIVE);
        address.setUid("1234");
        address.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setUser(user);
        address.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        AddressRepo addressRepo = mock(AddressRepo.class);
        when(addressRepo.findAddressById((Long) any())).thenReturn(address);
        ResponseEntity<Address> actualAddressById = (new AddressController(new AddressServiceImpl(addressRepo)))
                .getAddressById(1L);
        assertNull(actualAddressById.getBody());
        assertEquals(HttpStatus.OK, actualAddressById.getStatusCode());
        assertTrue(actualAddressById.getHeaders().isEmpty());
        verify(addressRepo).findAddressById((Long) any());
    }

    /**
     * Method under test: {@link AddressController#createAddress(CreateAddressDto)}
     */
    @Test
    void testCreateAddress() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   See https://diff.blue/R026 to resolve this issue.

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

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setStatus(Status.ACTIVE);
        user.setUid("1234");
        user.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("janedoe");
        user.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Address address = new Address();
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setHotel(hotel);
        address.setId(123L);
        address.setPostalCode("Postal Code");
        address.setRegistrationAddress("42 Main St");
        address.setResidenceAddress("42 Main St");
        address.setStatus(Status.ACTIVE);
        address.setUid("1234");
        address.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setUser(user);
        address.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        AddressRepo addressRepo = mock(AddressRepo.class);
        when(addressRepo.save((Address) any())).thenReturn(address);
        AddressController addressController = new AddressController(new AddressServiceImpl(addressRepo));

        CreateAddressDto createAddressDto = new CreateAddressDto();
        createAddressDto.setCity("Oxford");
        createAddressDto.setCountry("GB");
        createAddressDto.setPostalCode("Postal Code");
        createAddressDto.setRegistrationAddress("42 Main St");
        createAddressDto.setResidenceAddress("42 Main St");
        ResponseEntity<Address> actualCreateAddressResult = addressController.createAddress(createAddressDto);
        assertTrue(actualCreateAddressResult.hasBody());
        assertTrue(actualCreateAddressResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualCreateAddressResult.getStatusCode());
        verify(addressRepo).save((Address) any());
    }

    /**
     * Method under test: {@link AddressController#deleteAddressById(Long)}
     */
    @Test
    void testDeleteAddressById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   See https://diff.blue/R026 to resolve this issue.

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

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setStatus(Status.ACTIVE);
        user.setUid("1234");
        user.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("janedoe");
        user.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Address address = new Address();
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setHotel(hotel);
        address.setId(123L);
        address.setPostalCode("Postal Code");
        address.setRegistrationAddress("42 Main St");
        address.setResidenceAddress("42 Main St");
        address.setStatus(Status.ACTIVE);
        address.setUid("1234");
        address.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setUser(user);
        address.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Hotel hotel1 = new Hotel();
        hotel1.setAddress(new HashSet<>());
        hotel1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel1.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel1.setEmail("jane.doe@example.org");
        hotel1.setId(123L);
        hotel1.setName("Name");
        hotel1.setPhone("4105551212");
        hotel1.setStatus(Status.ACTIVE);
        hotel1.setUid("1234");
        hotel1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel1.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        User user1 = new User();
        user1.setAddresses(new ArrayList<>());
        user1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setRoles(new ArrayList<>());
        user1.setStatus(Status.ACTIVE);
        user1.setUid("1234");
        user1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUsername("janedoe");
        user1.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setCountry("GB");
        address1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        address1.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        address1.setHotel(hotel1);
        address1.setId(123L);
        address1.setPostalCode("Postal Code");
        address1.setRegistrationAddress("42 Main St");
        address1.setResidenceAddress("42 Main St");
        address1.setStatus(Status.ACTIVE);
        address1.setUid("1234");
        address1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        address1.setUser(user1);
        address1.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        AddressRepo addressRepo = mock(AddressRepo.class);
        when(addressRepo.save((Address) any())).thenReturn(address1);
        when(addressRepo.getById((Long) any())).thenReturn(address);
        ResponseEntity<String> actualDeleteAddressByIdResult = (new AddressController(new AddressServiceImpl(addressRepo)))
                .deleteAddressById(1L);
        assertEquals("Address was deleted successfully", actualDeleteAddressByIdResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteAddressByIdResult.getStatusCode());
        assertTrue(actualDeleteAddressByIdResult.getHeaders().isEmpty());
        verify(addressRepo).getById((Long) any());
        verify(addressRepo).save((Address) any());
    }

    /**
     * Method under test: {@link AddressController#editAddressById(UpdateAddressDto)}
     */
    @Test
    void testEditAddressById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   See https://diff.blue/R026 to resolve this issue.

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

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setStatus(Status.ACTIVE);
        user.setUid("1234");
        user.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("janedoe");
        user.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Address address = new Address();
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setHotel(hotel);
        address.setId(123L);
        address.setPostalCode("Postal Code");
        address.setRegistrationAddress("42 Main St");
        address.setResidenceAddress("42 Main St");
        address.setStatus(Status.ACTIVE);
        address.setUid("1234");
        address.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        address.setUser(user);
        address.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Hotel hotel1 = new Hotel();
        hotel1.setAddress(new HashSet<>());
        hotel1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel1.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel1.setEmail("jane.doe@example.org");
        hotel1.setId(123L);
        hotel1.setName("Name");
        hotel1.setPhone("4105551212");
        hotel1.setStatus(Status.ACTIVE);
        hotel1.setUid("1234");
        hotel1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        hotel1.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        User user1 = new User();
        user1.setAddresses(new ArrayList<>());
        user1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setRoles(new ArrayList<>());
        user1.setStatus(Status.ACTIVE);
        user1.setUid("1234");
        user1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUsername("janedoe");
        user1.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setCountry("GB");
        address1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        address1.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        address1.setHotel(hotel1);
        address1.setId(123L);
        address1.setPostalCode("Postal Code");
        address1.setRegistrationAddress("42 Main St");
        address1.setResidenceAddress("42 Main St");
        address1.setStatus(Status.ACTIVE);
        address1.setUid("1234");
        address1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        address1.setUser(user1);
        address1.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        AddressRepo addressRepo = mock(AddressRepo.class);
        when(addressRepo.save((Address) any())).thenReturn(address1);
        when(addressRepo.findAddressById((Long) any())).thenReturn(address);
        AddressController addressController = new AddressController(new AddressServiceImpl(addressRepo));

        UpdateAddressDto updateAddressDto = new UpdateAddressDto();
        updateAddressDto.setCity("Oxford");
        updateAddressDto.setCountry("GB");
        updateAddressDto.setId(123L);
        updateAddressDto.setPostalCode("Postal Code");
        updateAddressDto.setRegistrationAddress("42 Main St");
        updateAddressDto.setResidenceAddress("42 Main St");
        ResponseEntity<Address> actualEditAddressByIdResult = addressController.editAddressById(updateAddressDto);
        assertTrue(actualEditAddressByIdResult.hasBody());
        assertTrue(actualEditAddressByIdResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.ACCEPTED, actualEditAddressByIdResult.getStatusCode());
        Address body = actualEditAddressByIdResult.getBody();
        assertEquals("42 Main St", body.getResidenceAddress());
        assertEquals("42 Main St", body.getRegistrationAddress());
        assertEquals("Postal Code", body.getPostalCode());
        assertEquals("Oxford", body.getCity());
        assertEquals("GB", body.getCountry());
        verify(addressRepo).findAddressById((Long) any());
        verify(addressRepo).save((Address) any());
    }
}

