package org.jisu.e_store_user_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jisu.e_store_user_service.enums.AddressType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AddressEO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String locality;
    private String landmark;
    private String state;
    private String zipCode;
    private Integer userId;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private String phoneNumber;

}
