package org.jisu.e_store_user_service.entities;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlackListTokenEO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String token;
    private LocalDateTime createdAt;
    private Date timeUntilDeleted;
}
