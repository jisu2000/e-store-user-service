package org.jisu.e_store_user_service.service;

import org.jisu.e_store_user_service.entities.RefreshTokenEO;
import org.jisu.e_store_user_service.response.LoginRegistrationResponse;

public interface RefreshTokenService {
    
    public RefreshTokenEO generateNewTokenForUser(Integer userId);
    public LoginRegistrationResponse generateAccessToken(String refreshToken);
}
