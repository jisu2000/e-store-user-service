package org.jisu.e_store_user_service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jisu.e_store_user_service.entities.RefreshTokenEO;
import org.jisu.e_store_user_service.entities.UserEO;
import org.jisu.e_store_user_service.exception.RefreshTokenException;
import org.jisu.e_store_user_service.exception.ResourceNotFoundException;
import org.jisu.e_store_user_service.jwt.JwtUtils;
import org.jisu.e_store_user_service.repo.RefreshTokenRepo;
import org.jisu.e_store_user_service.repo.UserRepo;
import org.jisu.e_store_user_service.response.LoginRegistrationResponse;
import org.jisu.e_store_user_service.security.PrincipleUser;
import org.jisu.e_store_user_service.service.RefreshTokenService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepo userRepo;
    private final JwtUtils jwtUtils;
    private final RefreshTokenRepo refershTokenRepo;

    @Override
    public RefreshTokenEO generateNewTokenForUser(Integer userId) {
        UserEO userEO = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId.toString()));

        RefreshTokenEO newToken = new RefreshTokenEO();
        newToken.setToken(UUID.randomUUID().toString());
        newToken.setUserId(userEO.getId());
        newToken.setValidUpto(LocalDateTime.now().plusDays(30));

        return refershTokenRepo.save(newToken);

    }

    @Override
    public LoginRegistrationResponse generateAccessToken(String refreshToken) {

        RefreshTokenEO refreshTokenEO = refershTokenRepo.findByToken(refreshToken);

        if (refreshTokenEO == null) {
            throw new RefreshTokenException("Invalid Token");
        }

        if (refreshTokenEO.getValidUpto().isBefore(LocalDateTime.now())) {
            throw new RefreshTokenException("Token has been expired");
        }

        UserEO userEO = userRepo.findById(refreshTokenEO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", refreshTokenEO.getUserId().toString()));

        String jwtToken = jwtUtils.generateToken(new PrincipleUser(userEO));
        return LoginRegistrationResponse.builder()
                .msg("Token Regeration Successful")
                .refreshToken(refreshToken)
                .accessToken(jwtToken).build();

    }

}
