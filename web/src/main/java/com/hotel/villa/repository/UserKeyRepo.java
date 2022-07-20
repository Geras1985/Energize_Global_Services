package com.hotel.villa.repository;

import com.hotel.villa.entity.UserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserKeyRepo extends JpaRepository<UserKey, Long> {
}
