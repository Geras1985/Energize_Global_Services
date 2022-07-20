package com.hotel.villa.controller;

import com.hotel.villa.dto.auth.AuthRequestDto;
import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.entity.User;
import com.hotel.villa.exceptions.BadRequest;
import com.hotel.villa.security.jwt.JwtTokenProvider;
import com.hotel.villa.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
@EnableWebMvc
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserServiceImpl userServiceImpl;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          UserServiceImpl userServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody AuthRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new BadRequest();
        }

        User user = userServiceImpl.findByUsername(requestDto.getUsername());
        if (user == null){
            throw new UsernameNotFoundException("User with username: " + requestDto.getUsername() + " not found");
        }

        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));


            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "/registration")
    @ApiOperation("Create User")
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) {
        User user = userServiceImpl.createUser(userCreateDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


}
