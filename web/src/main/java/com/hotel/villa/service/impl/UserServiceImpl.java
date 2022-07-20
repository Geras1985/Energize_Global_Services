package com.hotel.villa.service.impl;

import com.hotel.villa.dto.auth.AuthRequestDto;
import com.hotel.villa.dto.user.UserCreateDto;
import com.hotel.villa.dto.utils.Mapper;
import com.hotel.villa.entity.Role;
import com.hotel.villa.entity.User;
import com.hotel.villa.entity.UserKey;
import com.hotel.villa.exceptions.AppException;
import com.hotel.villa.exceptions.InternalServerError;
import com.hotel.villa.repository.RoleRepo;
import com.hotel.villa.repository.UserKeyRepo;
import com.hotel.villa.repository.UserRepo;
import com.hotel.villa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    Role role = new Role();

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserKeyRepo userKeyRepo;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(AuthRequestDto authRequest) {

        if (authRequest.getUsername() != null && authRequest.getPassword() != null) {

            User user = userRepo.findByUsername(authRequest.getUsername());

            if (user != null) {
                boolean check = passwordEncoder.matches(authRequest.getPassword(), user.getPassword());

                if (check) {
                    log.info("IN database by username: {} successfully registered", user);
                    return user;
                }
            }
        }
        log.info("IN database by username was not found");
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepo.findAll();

        log.info("IN getAll - {} users found", result.size());

        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepo.findByUsername(username);

        log.info("IN findByUsername - user: {} found by username: {}", result, username);

        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepo.findById(id).orElse(null);

        if (result == null) {
            log.info("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id:", result);
        return result;
    }

    @Override
    public void deleteUserById(Long id) {
        if (id != null) {
            userRepo.deleteById(id);
            log.info("IN delete - user with id: {} successfully was deleted", id);
        } else log.info("IN delete - id can not be null, please enter the id");
    }

    @Override
    public void deleteUserByUsername(String username) {
        if (username != null) {
            User byUsername = userRepo.findByUsername(username);
            userRepo.delete(byUsername);
            log.info("IN delete - user with username: {} successfully was deleted", username);
        } else log.info("IN delete - username can not be null, please enter the username");
    }

    @Override
    public User loggedInUser(Authentication authentication) {
        String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        log.info("IN logged username - {} ", username);
        return userRepo.findByUsername(username);
    }

    /**
     * Method generate UserKey object.
     */
    @Override
    public void generateUserKey(UserKey userKey) {
        try {
            userKeyRepo.save(userKey);
        } catch (Exception ex) {
            throw new InternalServerError();
        }
    }

    @Override
    public User editUserById(Long id, UserCreateDto userCreateDto) {
        User byId = userRepo.findUserById(id).orElse(null);
        assert byId != null;

        byId.setUsername(userCreateDto.getUsername());
        byId.setEmail(userCreateDto.getEmail());
        byId.setFirstName(userCreateDto.getFirstName());
        byId.setLastName(userCreateDto.getLastName());
        byId.setPassword(userCreateDto.getPassword());
        byId.setUpdated(LocalDateTime.now());
        userRepo.save(byId);
        return byId;
    }

    /**
     * Method creates a user and  return it.
     */
    public User createUser(UserCreateDto userCreateDto) {

        role.setName("USER");

        if (roleRepo.findByName(role.getName()) == null) {
            role = new Role("USER");

            roleRepo.save(role);
        } else {
            role = roleRepo.findByName(role.getName());
        }
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        if (userRepo.findByUsername(userCreateDto.getUsername()) != null) {
            throw new AppException("User already exist","400");
        }

        User user = Mapper.toEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRoles(roles);

        User result = userRepo.save(user);

        log.info("IN - user: {} created by id:", result.getId());
        return result;
    }
}
