package org.jisu.e_store_user_service.repo;

import org.jisu.e_store_user_service.entities.RefreshTokenEO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepo extends JpaRepository<RefreshTokenEO, Integer> {
    RefreshTokenEO findByToken(String token);
}
