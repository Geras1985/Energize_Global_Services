package com.hotel.villa.dto.address;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateAddressDto {

    @NotNull
    private Long id;

    private String residenceAddress;
    private String registrationAddress;
    @NotBlank
    private String city;
    private String country;
    private String postalCode;
}
