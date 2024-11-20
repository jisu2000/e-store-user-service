package org.jisu.e_store_user_service.repo;

import org.jisu.e_store_user_service.entities.AddressEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<AddressEO,Integer> {
    List<AddressEO> findByUserId(Integer userId);
}
