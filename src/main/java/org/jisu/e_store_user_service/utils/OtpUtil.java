package org.jisu.e_store_user_service.utils;

import java.security.SecureRandom;

public class OtpUtil {
     public static String generateOtp(int n) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < n; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }
}
