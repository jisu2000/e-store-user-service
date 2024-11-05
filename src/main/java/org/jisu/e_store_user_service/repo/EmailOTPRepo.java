package org.jisu.e_store_user_service.repo;

import org.jisu.e_store_user_service.entities.EmailOtpEO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailOTPRepo extends JpaRepository<EmailOtpEO, Integer> {

    EmailOtpEO findByEmail(String email);

    EmailOtpEO findByEmailAndOtp(String email, String otp);

}
