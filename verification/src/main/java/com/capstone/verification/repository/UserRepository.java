package com.capstone.verification.repository;

import com.capstone.verification.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "select * from users where number = ?1", nativeQuery = true)
    Optional<User> findUserByNumber(String number);
}