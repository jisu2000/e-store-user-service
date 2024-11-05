package org.jisu.e_store_user_service.service.impl;

import java.time.LocalDateTime;

import org.jisu.e_store_user_service.constants.EmailTemplateConstants;
import org.jisu.e_store_user_service.constants.RoleConstant;
import org.jisu.e_store_user_service.entities.*;
import org.jisu.e_store_user_service.exception.*;
import org.jisu.e_store_user_service.external.EmailService;
import org.jisu.e_store_user_service.jwt.JwtUtils;
import org.jisu.e_store_user_service.repo.*;
import org.jisu.e_store_user_service.request.*;
import org.jisu.e_store_user_service.response.*;
import org.jisu.e_store_user_service.security.*;
import org.jisu.e_store_user_service.service.*;
import org.jisu.e_store_user_service.utils.OtpUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final EmailOTPRepo emailOTPRepo;
    private final EmailService emailService;

    @Override
    public LoginRegistrationResponse registerUser(UserRegisterRequest registerRequest) {

        UserEO newUser = modelMapper.map(registerRequest, UserEO.class);
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.getRoles().add(RoleConstant.getUserRole());

        UserEO saved = userRepo.save(newUser);

        String jwtToken = jwtUtils.generateToken(new PrincipleUser(saved));

        String refreshToken = refreshTokenService.generateNewTokenForUser(saved.getId()).getToken();

        return LoginRegistrationResponse.builder()
                .msg("Registration Successful")
                .refreshToken(refreshToken)
                .accessToken(jwtToken).build();
    }

    @Override
    public LoginRegistrationResponse loginUser(UserLoginRequest userLoginRequest) {

        boolean authenticated = authenticationService.doAuthenticate(
                userLoginRequest.getEmail(),
                userLoginRequest.getPassword());

        if (!authenticated) {
            throw new InvalidCredientialsException();
        }

        UserEO fetched = userRepo.findByEmail(userLoginRequest.getEmail());

        String jwtToken = jwtUtils.generateToken(new PrincipleUser(fetched));

        String refreshToken = refreshTokenService.generateNewTokenForUser(fetched.getId()).getToken();

        return LoginRegistrationResponse.builder()
                .msg("Login Successful")
                .refreshToken(refreshToken)
                .accessToken(jwtToken).build();

    }

    @Override
    public ApiResponse<String> sendResetPasswordOtp(String email) {

        UserEO fetchedUser = userRepo.findByEmail(email);

        if (fetchedUser == null) {
            throw new ResourceNotFoundException("USER", "EMAIL", email);
        }

        EmailOtpEO previous = emailOTPRepo.findByEmail(email);

        boolean previousFound = previous != null;

        boolean waitBound = previousFound &&
                previous.getCreatedAt().plusMinutes(1).isAfter(LocalDateTime.now());

        if (waitBound) {
            throw new InvalidOtpException("Please wait until 1 Minute");
        }

        if (previousFound && !waitBound) {
            emailOTPRepo.delete(previous);
        }

        String newOtp = OtpUtil.generateOtp(6);

        EmailOtpEO newEmailOtp = new EmailOtpEO();
        newEmailOtp.setEmail(email);
        newEmailOtp.setOtp(newOtp);

        emailOTPRepo.save(newEmailOtp);
        emailService.sendEmail(email, "Forgot Password OTP",
                EmailTemplateConstants.generateOtpEmail(newOtp, fetchedUser.getName()));
        return new ApiResponse<String>("OTP has been sent to " + email);

    }

    @Override
    public ApiResponse<String> verifyOtp(OtpVerifyRequest otpVerifyRequest) {

        UserEO fetchedUser = userRepo.findByEmail(otpVerifyRequest.getEmail());

        if (fetchedUser == null) {
            throw new ResourceNotFoundException("USER", "EMAIL", otpVerifyRequest.getEmail());
        }

        EmailOtpEO fetched = emailOTPRepo.findByEmailAndOtp(
                otpVerifyRequest.getEmail(), otpVerifyRequest.getOtp());

        if (fetched == null) {
            throw new InvalidOtpException("Invalid OTP");
        }

        if (fetched.getCreatedAt().plusMinutes(2).isBefore(LocalDateTime.now())) {
            throw new InvalidOtpException("OTP expired");
        }

        emailOTPRepo.delete(fetched);

        fetchedUser.setOtpVerified(true);
        fetchedUser.setOtpVerifiedTime(LocalDateTime.now());
        userRepo.save(fetchedUser);

        return new ApiResponse<String>("Otp verified for Changing password");

    }

    @Override
    public ApiResponse<String> changePassword(PasswordChangeRequest passwordChangeRequest) {
        UserEO fetchedUser = userRepo.findByEmail(passwordChangeRequest.getEmail());

        if (fetchedUser == null) {
            throw new ResourceNotFoundException("USER", "EMAIL", passwordChangeRequest.getEmail());
        }

        if (fetchedUser.getOtpVerified() == null || !fetchedUser.getOtpVerified()) {
            throw new InvalidOtpException("Verify OTP first");
        }

        if (fetchedUser.getOtpVerifiedTime() == null ||
                fetchedUser.getOtpVerifiedTime().plusMinutes(1).isBefore(LocalDateTime.now())) {

            throw new InvalidOtpException("Verify OTP first");

        }

        fetchedUser.setOtpVerified(false);
        fetchedUser.setPassword(passwordEncoder.encode(passwordChangeRequest.getPassword()));
        userRepo.save(fetchedUser);
        return new ApiResponse<String>("Password Changed");

    }

    @Override
    public UserResponse getCurrentUser() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof PrincipleUser) {

            UserEO user = ((PrincipleUser) principal).getUserEO();
            return modelMapper.map(user, UserResponse.class);
        }
    }
    return null;
    }

    @Override
    public ApiResponse<String> logoutUser(String authHeader) {
        String token = authHeader.substring(7);
        jwtUtils.blacklistToken(token);
        SecurityContextHolder.clearContext();
     return new ApiResponse<String>("User logged out  at "+LocalDateTime.now());

    }

    @Override
    public UserEO getCurrentUserObject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof PrincipleUser) {
    
                UserEO user = ((PrincipleUser) principal).getUserEO();
                return user;
            }
        }
        return null;
    }

}
