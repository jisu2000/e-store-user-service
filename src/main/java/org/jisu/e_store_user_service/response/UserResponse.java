package org.jisu.e_store_user_service.response;

import java.util.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private List<RoleResponse> roles;
    private String profilePhoto;
}
