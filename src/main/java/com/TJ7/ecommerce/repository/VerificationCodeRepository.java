package com.TJ7.ecommerce.repository;

import com.TJ7.ecommerce.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    VerificationCode findByEmail(String email);

    VerificationCode findByOtp(String otp);

}