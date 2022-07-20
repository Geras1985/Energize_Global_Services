package com.hotel.villa.service;

import com.hotel.villa.annotation.Init;
import com.hotel.villa.annotation.MyAnnotation;
import com.hotel.villa.dto.auth.AuthRequestDto;
import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.entity.User;
import com.hotel.villa.entity.UserKey;
import org.springframework.security.core.Authentication;

import java.util.List;

@MyAnnotation(name = "very lazy")
public interface UserService {

    @Init
    User login(AuthRequestDto auth);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void deleteUserById(Long id);

    void deleteUserByUsername(String username);

    User loggedInUser(Authentication auth);

    void generateUserKey(UserKey userKey);

    User editUserById(Long id, UserCreateDto userCreateDto);
}
