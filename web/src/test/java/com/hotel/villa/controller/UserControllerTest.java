package com.hotel.villa.controller;

import com.hotel.villa.dto.auth.AuthRequestDto;
import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.entity.User;
import com.hotel.villa.enums.ApplyTo;
import com.hotel.villa.enums.Status;
import com.hotel.villa.repository.RoleRepo;
import com.hotel.villa.repository.UserRepo;
import com.hotel.villa.service.impl.AddressServiceImpl;
import com.hotel.villa.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerMapping;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserController.class})
class UserControllerTest {

    /**
     * Method under test: {@link UserController#getUserById(Long)}
     */
    @Test
    void testGetUserById() {

        User mockUser = createMockUser();
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        when(userServiceImpl.findById(any())).thenReturn(mockUser);
        ResponseEntity<UserCreateDto> actualUserById = (new UserController(userServiceImpl)).getUserById(123L);
        assertTrue(actualUserById.hasBody());
        assertTrue(actualUserById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUserById.getStatusCode());
        UserCreateDto body = actualUserById.getBody();
        assert body != null;
        assertEquals("Doe", body.getLastName());
        assertEquals("Jane", body.getFirstName());
        assertEquals("jane.doe@example.org", body.getEmail());
        assertEquals("jane", body.getUsername());
        assertEquals("I Love You", body.getPassword());
        assertEquals("1234", body.getUid());
        verify(userServiceImpl).findById(any());
    }

    /**
     * Method under test: {@link UserController#userLogin(AuthRequestDto)}
     */
    @Test
    void testUserLogin() {

        User user = createMockUser();
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(any())).thenReturn(user);
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserController userController = new UserController(
                new UserServiceImpl(userRepo, roleRepo, new BCryptPasswordEncoder()));

        AuthRequestDto authRequestDto = new AuthRequestDto();
        authRequestDto.setPassword("I Love You");
        authRequestDto.setUsername("jane");
        ResponseEntity<User> actualUserLoginResult = userController.userLogin(authRequestDto);
        assertNull(actualUserLoginResult.getBody());
        assertEquals(HttpStatus.OK, actualUserLoginResult.getStatusCode());
        assertTrue(actualUserLoginResult.getHeaders().isEmpty());
        verify(userRepo).findByUsername(any());
    }


    /**
     * Method under test: {@link UserController#createUser(UserCreateDto)}
     */
    @Test
    void testCreateUser() {

        User user = createMockUser();
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        when(userServiceImpl.createUser(any())).thenReturn(user);
        UserController userController = new UserController(userServiceImpl);

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setApplyTo(ApplyTo.Mr);
        userCreateDto.setEmail("jane.doe@example.org");
        userCreateDto.setFirstName("Jane");
        userCreateDto.setLastName("Doe");
        userCreateDto.setPassword("I Love You");
        userCreateDto.setUid("1234");
        userCreateDto.setUsername("jane");
        ResponseEntity<User> actualCreateUserResult = userController.createUser(userCreateDto);
        assertTrue(actualCreateUserResult.hasBody());
        assertTrue(actualCreateUserResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateUserResult.getStatusCode());
        verify(userServiceImpl).createUser(any());
    }

    /**
     * Method under test: {@link UserController#editUser(Long, UserCreateDto)}
     */
    @Test
    void testEditUser() {

        User user = createMockUser();
        Optional<User> ofResult = Optional.of(user);

        User user1 = createMockUser();
        UserRepo userRepo = mock(UserRepo.class);

        when(userRepo.save(any())).thenReturn(user1);
        when(userRepo.findUserById(any())).thenReturn(ofResult);
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserController userController = new UserController(
                new UserServiceImpl(userRepo, roleRepo, new BCryptPasswordEncoder()));

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setApplyTo(ApplyTo.Mr);
        userCreateDto.setEmail("jane.doe@example.org");
        userCreateDto.setFirstName("Jane");
        userCreateDto.setLastName("Doe");
        userCreateDto.setPassword("I Love You");
        userCreateDto.setUid("1234");
        userCreateDto.setUsername("jane");
        ResponseEntity<User> actualEditUserResult = userController.editUser(1L, userCreateDto);
        User body = actualEditUserResult.getBody();
        assertEquals(user1, body);
        assertTrue(actualEditUserResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEditUserResult.getStatusCode());
        assert body != null;
        assertEquals("I Love You", body.getPassword());
        assertEquals("Doe", body.getLastName());
        assertEquals("Jane", body.getFirstName());
        assertEquals("jane.doe@example.org", body.getEmail());
        assertEquals("jane", body.getUsername());
        verify(userRepo).save(any());
        verify(userRepo).findUserById(any());
    }

    /**
     * Method under test: {@link UserController#deleteUserById(Optional)}
     */
    @Test
    void testDeleteUserById() {

        UserRepo userRepo = mock(UserRepo.class);
        doNothing().when(userRepo).deleteById(any());
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserController userController = new UserController(
                new UserServiceImpl(userRepo, roleRepo, new BCryptPasswordEncoder()));
        ResponseEntity<String> actualDeleteUserByIdResult = userController.deleteUserById(Optional.of(1L));
        assertEquals("The user was deleted. ", actualDeleteUserByIdResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteUserByIdResult.getStatusCode());
        assertTrue(actualDeleteUserByIdResult.getHeaders().isEmpty());
        verify(userRepo).deleteById(any());
    }

    /**
     * Method under test: {@link UserController#deleteUserByUsername(String)}
     */
    @Test
    void testDeleteUserByUsername() {

        User user = createMockUser();
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(any())).thenReturn(user);
        doNothing().when(userRepo).delete(any());
        RoleRepo roleRepo = mock(RoleRepo.class);
        ResponseEntity<Void> actualDeleteUserByUsernameResult = (new UserController(
                new UserServiceImpl(userRepo, roleRepo, new BCryptPasswordEncoder()))).deleteUserByUsername("foo");
        assertNull(actualDeleteUserByUsernameResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualDeleteUserByUsernameResult.getStatusCode());
        assertTrue(actualDeleteUserByUsernameResult.getHeaders().isEmpty());
        verify(userRepo).findByUsername(any());
        verify(userRepo).delete(any());
    }

    private User createMockUser() {
        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setDeleted(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("I Love You");
        user.setRoles(new ArrayList<>());
        user.setStatus(Status.ACTIVE);
        user.setUid("1234");
        user.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("jane");
        return user;
    }
}

