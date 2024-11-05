package org.jisu.e_store_user_service.entities;

import java.time.LocalDateTime;
import java.util.*;
import org.hibernate.annotations.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEO> roles=new ArrayList<>();
    private String profilePhoto;

    private Boolean otpVerified;
    private LocalDateTime otpVerifiedTime;
    
}
