package com.example.darelo.RH.repository;

import com.example.darelo.RH.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByEmail(String email);
}
