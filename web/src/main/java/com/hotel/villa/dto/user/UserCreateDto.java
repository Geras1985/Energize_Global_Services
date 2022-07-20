package com.hotel.villa.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.villa.enums.ApplyTo;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateDto {

    @NotNull
    private String uid;

    @NotNull
    @Size(min = 4, max = 50, message = "Username must be in range 4-50")
    private String username;

    @NotNull
    @Size(min = 2, max = 30, message = "First name must be in range 2-30")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 40, message = "Last name must be in range 2-40")
    private String lastName;

    @NotNull
    @Size(min = 8, message = "Password must be min 8 characters long")
    private String password;

    @NotNull
    @Size(min = 7, max = 50, message = "Email should have at 7 characters.")
    @Email
    private String email;

    private ApplyTo applyTo;

}
