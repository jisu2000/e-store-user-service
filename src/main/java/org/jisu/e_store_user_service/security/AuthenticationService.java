package org.jisu.e_store_user_service.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public boolean doAuthenticate(String userName, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,
                password);

        boolean authenticated = false;

        try {
            authenticationManager.authenticate(authentication);

            authenticated = !authenticated;

        } catch (Exception e) {
        }

        return authenticated;
    }
}
