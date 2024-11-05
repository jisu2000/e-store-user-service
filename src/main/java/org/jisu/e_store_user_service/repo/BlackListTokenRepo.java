package org.jisu.e_store_user_service.repo;

import org.jisu.e_store_user_service.entities.BlackListTokenEO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlackListTokenRepo extends JpaRepository<BlackListTokenEO,Integer>{
    BlackListTokenEO findByToken(String token);
}
