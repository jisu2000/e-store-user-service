package org.jisu.e_store_user_service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
}
