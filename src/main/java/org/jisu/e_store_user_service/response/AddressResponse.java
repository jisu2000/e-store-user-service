package org.jisu.e_store_user_service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponse {
    private String locality;
    private String landmark;
    private String state;
    private String zipCode;
    private String addressType;
    private Integer id;
    private String phoneNumber;

}
