package org.jisu.e_store_user_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleEO {
    @Id
    private Integer id;
    private String roleName;
}
