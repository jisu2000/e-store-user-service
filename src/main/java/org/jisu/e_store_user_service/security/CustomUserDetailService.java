package org.jisu.e_store_user_service.security;

import org.jisu.e_store_user_service.entities.UserEO;
import org.jisu.e_store_user_service.exception.ResourceNotFoundException;
import org.jisu.e_store_user_service.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEO fetchedByEmail = userRepo.findByEmail(username);

        if (fetchedByEmail == null) {
            throw new ResourceNotFoundException("USER", "EMAIL", username);
        }
        return new PrincipleUser(fetchedByEmail);
    }

}
