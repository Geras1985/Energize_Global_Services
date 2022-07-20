package com.hotel.villa.dto.address;

import lombok.Data;

@Data
public class AddressViewDto {
    private Long id;
    private String residenceAddress;
    private String registrationAddress;
    private String city;
    private String country;
    private String postalCode;
}
