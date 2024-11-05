package org.jisu.e_store_user_service.entities;

import java.time.*;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class EmailOtpEO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String otp;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
