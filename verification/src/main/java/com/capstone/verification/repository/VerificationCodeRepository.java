package com.capstone.verification.repository;

import com.capstone.verification.model.VerificationCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface VerificationCodeRepository extends JpaRepository<VerificationCodes, UUID> {
    @Query(value = "select * from verification_codes where number = ?1", nativeQuery = true)
    Optional<VerificationCodes> findCodeByNumber(String number);
}
