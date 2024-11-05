package org.jisu.e_store_user_service.controller;

import java.util.Map;

import org.jisu.e_store_user_service.request.OtpVerifyRequest;
import org.jisu.e_store_user_service.request.PasswordChangeRequest;
import org.jisu.e_store_user_service.request.UserLoginRequest;
import org.jisu.e_store_user_service.request.UserRegisterRequest;
import org.jisu.e_store_user_service.service.AuthService;
import org.jisu.e_store_user_service.service.ImageUploadService;
import org.jisu.e_store_user_service.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    public final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final ImageUploadService imageUploadService;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegisterRequest userRegisterRequest) {

        return new ResponseEntity<>(authService.registerUser(userRegisterRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return new ResponseEntity<>(authService.loginUser(userLoginRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> map) {
        return new ResponseEntity<>(refreshTokenService.generateAccessToken(map.get("token")), HttpStatus.OK);
    }

    @PostMapping("/send-password-reset-otp")
    public ResponseEntity<?> sendPasswordResetOtp(
            @RequestParam("email") String email) {
        return new ResponseEntity<>(authService.sendResetPasswordOtp(email), HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerifyRequest otpVerifyRequest) {
        return new ResponseEntity<>(authService.verifyOtp(otpVerifyRequest), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody PasswordChangeRequest passwordChangeRequest) {
        return new ResponseEntity<>(authService.changePassword(passwordChangeRequest), HttpStatus.OK);
    }


}
