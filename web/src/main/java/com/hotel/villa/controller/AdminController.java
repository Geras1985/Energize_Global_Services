package com.hotel.villa.controller;

import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.dto.utils.Mapper;
import com.hotel.villa.entity.User;
import com.hotel.villa.service.impl.UserServiceImpl;
import com.hotel.villa.util.Helper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/admin")
@EnableWebMvc
public class AdminController {

    @Lazy
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get Admin By Id")
    public ResponseEntity<UserCreateDto> getUserById(@PathVariable Long id) {
        User user = userServiceImpl.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserCreateDto result = Mapper.toDTO(user);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
