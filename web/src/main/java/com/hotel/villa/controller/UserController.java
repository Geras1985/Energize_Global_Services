package com.hotel.villa.controller;

import com.hotel.villa.dto.auth.AuthRequestDto;
import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.dto.utils.Mapper;
import com.hotel.villa.entity.User;
import com.hotel.villa.images.JImageJunk;
import com.hotel.villa.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
@EnableWebMvc
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("{id}")
    @ApiOperation("Get User By ID")
    public ResponseEntity<UserCreateDto> getUserById(@PathVariable Long id) {
        User user = userServiceImpl.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserCreateDto result = Mapper.toDTO(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation("User Login")
    public ResponseEntity<User> userLogin(@RequestBody AuthRequestDto authRequestDto) {
        User user = userServiceImpl.login(authRequestDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation("Create User")
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) {
        User user = userServiceImpl.createUser(userCreateDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Edit User By ID")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody UserCreateDto userCreateDto) {
        User user = userServiceImpl.editUserById(id, userCreateDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete User By ID")
    public ResponseEntity<String> deleteUserById(@PathVariable Optional<Long> id) {
        id.ifPresent(userServiceImpl::deleteUserById);
        return new ResponseEntity<>("The user was deleted. ", HttpStatus.OK);
    }

    @DeleteMapping("/")
    @ApiOperation("Delete User By Username")
    public ResponseEntity<Void> deleteUserByUsername(@RequestBody String username) {
        userServiceImpl.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addFile")
    @ApiOperation("Add file")
    public ResponseEntity<String> addFIleForUser(@RequestParam("file") MultipartFile file) {
        String url = JImageJunk.imageIoWrite(file.getResource().getFilename(), file.getName());
        assert url != null;
        File file1 = new File(url);
        System.out.println(file1);
        return new ResponseEntity<>("File was uploaded successfully", HttpStatus.OK);
    }
}
