package org.jisu.e_store_user_service.response;

import org.springframework.http.HttpStatus;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MailResponseBack {
    private String msg;
    private HttpStatus status;
}
