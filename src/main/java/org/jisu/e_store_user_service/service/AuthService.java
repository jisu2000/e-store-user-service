package org.jisu.e_store_user_service.service;

import org.jisu.e_store_user_service.entities.UserEO;
import org.jisu.e_store_user_service.request.*;
import org.jisu.e_store_user_service.response.*;


public interface AuthService {
    LoginRegistrationResponse registerUser(UserRegisterRequest registerRequest);
    LoginRegistrationResponse loginUser(UserLoginRequest userLoginRequest);
    ApiResponse<String> sendResetPasswordOtp(String email);
    ApiResponse<String> verifyOtp(OtpVerifyRequest otpVerifyRequest);
    ApiResponse<String> changePassword(PasswordChangeRequest passwordChangeRequest);
    UserResponse getCurrentUser();
    ApiResponse<String> logoutUser(String authHeader);
    UserEO getCurrentUserObject();

}
