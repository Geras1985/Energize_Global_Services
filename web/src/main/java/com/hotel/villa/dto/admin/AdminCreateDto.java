package com.hotel.villa.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.villa.entity.User;
import com.hotel.villa.enums.Status;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminCreateDto {

    @NotNull
    @Size(min = 4, max = 40, message = "Username must be in range 4-40")
    private String username;

    @NotNull
    @Size(min = 2, max = 50, message = "First name must be in range 2-50")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50, message = "Last name must be in range 2-50")
    private String lastName;

    @NotNull
    @Size(min = 7, max = 60, message = "Email should have at 7 characters.")
    @Email(message = "Please enter a valid email")
    private String email;

    private String status;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setStatus(Status.valueOf(status));
        return user;
    }

    public static AdminCreateDto fromUser(User user) {
        AdminCreateDto adminDto = new AdminCreateDto();

        adminDto.setUsername(user.getUsername());
        adminDto.setFirstName(user.getFirstName());
        adminDto.setLastName(user.getLastName());
        adminDto.setEmail(user.getEmail());
        adminDto.setStatus(user.getStatus().name());
        return adminDto;
    }
}
