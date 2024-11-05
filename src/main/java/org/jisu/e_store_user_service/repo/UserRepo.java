package org.jisu.e_store_user_service.repo;

import org.jisu.e_store_user_service.entities.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEO,Integer>{
    UserEO findByEmail(String email);
}
