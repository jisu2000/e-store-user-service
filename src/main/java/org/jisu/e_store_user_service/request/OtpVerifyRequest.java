package org.jisu.e_store_user_service.request;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OtpVerifyRequest {
    private String email;
    private String otp;
}
