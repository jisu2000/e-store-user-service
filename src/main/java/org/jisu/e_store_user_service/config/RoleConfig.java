package org.jisu.e_store_user_service.config;

import org.jisu.e_store_user_service.constants.RoleConstant;
import org.jisu.e_store_user_service.constants.RoleIdConstant;
import org.jisu.e_store_user_service.entities.RoleEO;
import org.jisu.e_store_user_service.repo.RoleRepo;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RoleConfig {

    private final RoleRepo roleRepo;

    @PostConstruct
    private void insertRole() {
        boolean userRoleEmpty = !roleRepo.findById(RoleIdConstant.USER_ROLE_ID).isPresent();
        boolean adminRoleEmpty = !roleRepo.findById(RoleIdConstant.ADMIN_ROLE_ID).isPresent();

        if (userRoleEmpty) {
            RoleEO userRole = RoleConstant.getUserRole();
            roleRepo.save(userRole);
        }

        if (adminRoleEmpty) {
            RoleEO adminRole = RoleConstant.getAdminRole();
            roleRepo.save(adminRole);
        }
    }
}
