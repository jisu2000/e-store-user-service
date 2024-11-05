package org.jisu.e_store_user_service.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginRegistrationResponse {
    private String accessToken;
    private String msg;
    private String refreshToken;
}
