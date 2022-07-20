package com.hotel.villa.controller;

import com.hotel.villa.dto.address.CreateAddressDto;
import com.hotel.villa.dto.address.UpdateAddressDto;
import com.hotel.villa.entity.Address;
import com.hotel.villa.service.impl.AddressServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/address")
@EnableWebMvc
public class AddressController {
    private final AddressServiceImpl addressServiceImpl;

    public AddressController(AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl = addressServiceImpl;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Addresses By Name Of City")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address addressByCity = addressServiceImpl.getAddressById(id);
        return new ResponseEntity<>(addressByCity, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation("Create A New Address")
    public ResponseEntity<Address> createAddress(@RequestBody CreateAddressDto createAddressDto) {
        Address address = addressServiceImpl.createAddress(createAddressDto);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete Address By Id")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id) {
        addressServiceImpl.deleteAddressById(id);
        return new ResponseEntity<>("Address was deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/edit")
    @ApiOperation("Edit Address By Id")
    public ResponseEntity<Address> editAddressById(@RequestBody UpdateAddressDto updateAddressDto) {
        Address address = addressServiceImpl.editAddressById(updateAddressDto);
        return new ResponseEntity<>(address, HttpStatus.ACCEPTED);
    }

}
