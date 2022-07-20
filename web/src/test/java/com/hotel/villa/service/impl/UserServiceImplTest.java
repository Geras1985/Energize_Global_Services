package com.hotel.villa.service.impl;

import com.hotel.villa.dto.auth.AuthRequestDto;
import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.entity.Role;
import com.hotel.villa.entity.User;
import com.hotel.villa.entity.UserKey;
import com.hotel.villa.enums.ApplyTo;
import com.hotel.villa.enums.Status;
import com.hotel.villa.exceptions.AppException;
import com.hotel.villa.repository.RoleRepo;
import com.hotel.villa.repository.UserKeyRepo;
import com.hotel.villa.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {UserServiceImpl.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private RoleRepo roleRepo;

    @MockBean
    private UserKeyRepo userKeyRepo;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#login(AuthRequestDto)}
     */
    @Test
    void testLogin() {
        User mockUser = createMockUser();
        when(this.userRepo.findByUsername(any())).thenReturn(mockUser);

        AuthRequestDto authRequestDto = new AuthRequestDto();
        authRequestDto.setPassword(mockUser.getPassword());
        authRequestDto.setUsername(mockUser.getUsername());

        assertNull(this.userServiceImpl.login(authRequestDto));
        verify(this.userRepo).findByUsername(mockUser.getUsername());
    }


    /**
     * Method under test: {@link UserServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepo.findAll()).thenReturn(userList);
        List<User> actualAll = this.userServiceImpl.getAll();
        assertSame(userList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(this.userRepo).findAll();
    }


    /**
     * Method under test: {@link UserServiceImpl#findByUsername(String)}
     */
    @Test
    void testFindByUsername() {
        User mockUser = createMockUser();
        when(this.userRepo.findByUsername(any())).thenReturn(mockUser);
        assertSame(mockUser, this.userServiceImpl.findByUsername(mockUser.getUsername()));
        verify(this.userRepo).findByUsername(any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
        User user = createMockUser();
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepo.findById(any())).thenReturn(ofResult);
        assertSame(user, this.userServiceImpl.findById(123L));
        verify(this.userRepo).findById(any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUserById(Long)}
     */
    @Test
    void testDeleteUserById() {
        doNothing().when(this.userRepo).deleteById(any());
        this.userServiceImpl.deleteUserById(123L);
        verify(this.userRepo).deleteById(any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUserByUsername(String)}
     */
    @Test
    void testDeleteUserByUsername() {
        User mockUser = createMockUser();
        when(this.userRepo.findByUsername(any())).thenReturn(mockUser);
        doNothing().when(this.userRepo).delete(mockUser);
        this.userServiceImpl.deleteUserByUsername(mockUser.getUsername());
        verify(this.userRepo).findByUsername(any());
        verify(this.userRepo).delete(mockUser);
    }

    /**
     * Method under test: {@link UserServiceImpl#loggedInUser(org.springframework.security.core.Authentication)}
     */
    @Test
    void testLoggedInUser() {
        User user = createMockUser();
        when(this.userRepo.findByUsername(any())).thenReturn(user);
        assertSame(user,
                this.userServiceImpl.loggedInUser(new TestingAuthenticationToken(
                        new org.springframework.security.core.userdetails.User("jane", "I Love You",
                                new ArrayList<>()),
                        "Credentials")));
        verify(this.userRepo).findByUsername("jane");
    }

    /**
     * Method under test: {@link UserServiceImpl#generateUserKey(UserKey)}
     */
    @Test
    void testGenerateUserKey() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        role.setUsers(new ArrayList<>());

        UserKey userKey = new UserKey();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userKey.setExpiresAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userKey.setId(123L);
        userKey.setRefreshToken("ABC123");
        userKey.setRole(role);
        userKey.setToken("ABC123");
        userKey.setTokenType("ABC123");
        userKey.setUserId(123L);
        when(this.userKeyRepo.save(any())).thenReturn(userKey);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setName("Name");
        role1.setUsers(new ArrayList<>());

        UserKey userKey1 = new UserKey();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        userKey1.setExpiresAt(fromResult);
        userKey1.setId(123L);
        userKey1.setRefreshToken("ABC123");
        userKey1.setRole(role1);
        userKey1.setToken("ABC123");
        userKey1.setTokenType("ABC123");
        userKey1.setUserId(123L);
        this.userServiceImpl.generateUserKey(userKey1);
        verify(this.userKeyRepo).save(any());

        assertSame(fromResult, userKey1.getExpiresAt());
        assertEquals(123L, userKey1.getUserId().longValue());
        assertEquals("ABC123", userKey1.getTokenType());
        assertEquals("ABC123", userKey1.getToken());
        assertEquals(role, userKey1.getRole());
        assertEquals("ABC123", userKey1.getRefreshToken());
        assertEquals(123L, userKey1.getId().longValue());
    }

    /**
     * Method under test: {@link UserServiceImpl#editUserById(Long, UserCreateDto)}
     */
    @Test
    void testEditUserById() {
        User mockUser = createMockUser();

        User mockUser1 = createMockUser();
        Optional<User> ofResult = Optional.of(mockUser);

        when(this.userRepo.save(any())).thenReturn(mockUser1);
        when(this.userRepo.findUserById(any())).thenReturn(ofResult);

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setApplyTo(ApplyTo.Mr);
        userCreateDto.setEmail("jane.doe@example.org");
        userCreateDto.setFirstName("Jane");
        userCreateDto.setLastName("Doe");
        userCreateDto.setPassword("I Love You");
        userCreateDto.setUid("1234");
        userCreateDto.setUsername("jane");
        User actualEditUserByIdResult = this.userServiceImpl.editUserById(123L, userCreateDto);
        assertSame(mockUser, actualEditUserByIdResult);
        assertEquals("jane", actualEditUserByIdResult.getUsername());
        assertEquals("I Love You", actualEditUserByIdResult.getPassword());
        assertEquals("Doe", actualEditUserByIdResult.getLastName());
        assertEquals("Jane", actualEditUserByIdResult.getFirstName());
        assertEquals("jane.doe@example.org", actualEditUserByIdResult.getEmail());
        verify(this.userRepo).save(any());
        verify(this.userRepo).findUserById(any());
    }

    /**
     * Method under test: {@link UserServiceImpl#createUser(UserCreateDto)}
     */
    @Test
    void testCreateUser() {
        User mockUser = createMockUser();

        User mockUser1 = createMockUser();

        when(this.userRepo.findByUsername(any())).thenReturn(mockUser);
        when(this.userRepo.save(any())).thenReturn(mockUser1);

        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        role.setUsers(new ArrayList<>());

        Role role1 = new Role();
        role1.setId(123L);
        role1.setName("Name");
        role1.setUsers(new ArrayList<>());
        when(this.roleRepo.findByName(any())).thenReturn(role);
        when(this.roleRepo.save(any())).thenReturn(role1);

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setApplyTo(ApplyTo.Mr);
        userCreateDto.setEmail("jane.doe@example.org");
        userCreateDto.setFirstName("Jane");
        userCreateDto.setLastName("Doe");
        userCreateDto.setPassword("I Love You");
        userCreateDto.setUid("1234");
        userCreateDto.setUsername("jane");
        assertThrows(AppException.class, () -> this.userServiceImpl.createUser(userCreateDto));
        verify(this.userRepo).findByUsername(any());
        verify(this.roleRepo, atLeast(1)).findByName(any());
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
        user.setValidDate(LocalDateTime.of(1, 1, 1, 1, 1));
        return user;
    }
}

