package com.hotel.villa.dto.utils;

import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.entity.User;
import com.hotel.villa.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public final class Mapper {

    public static User toEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUid(UUID.randomUUID().toString());
        user.setUsername(userCreateDto.getUsername());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());
        user.setCreated(LocalDateTime.now());
        user.setStatus(Status.ACTIVE);
        user.setValidDate(LocalDateTime.now().plusYears(3));
        return user;
    }

    public static UserCreateDto toDTO(User user) {
        UserCreateDto userDto = new UserCreateDto();
        userDto.setUid(user.getUid());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private Mapper() {
        throw new UnsupportedOperationException();
    }

}
