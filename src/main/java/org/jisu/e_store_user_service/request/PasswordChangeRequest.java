package org.jisu.e_store_user_service.request;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PasswordChangeRequest {
    private String email;
    private String password;
}
