package com.hotel.villa.repository;

import com.hotel.villa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    void deleteByUsername(String username);

    Optional<User> findUserById(Long id);

}
