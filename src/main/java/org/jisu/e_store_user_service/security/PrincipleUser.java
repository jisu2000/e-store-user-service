package org.jisu.e_store_user_service.security;

import java.util.Collection;

import org.jisu.e_store_user_service.entities.UserEO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrincipleUser implements UserDetails {

    private UserEO userEO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEO.getRoles().stream()
                .map(e -> new SimpleGrantedAuthority("ROLE_" + e.getRoleName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return userEO.getPassword();
    }

    @Override
    public String getUsername() {
        return userEO.getEmail();
    }

}
