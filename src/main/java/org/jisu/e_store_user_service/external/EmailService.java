package org.jisu.e_store_user_service.external;

import java.util.*;

import org.jisu.e_store_user_service.response.MailResponseBack;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final RestTemplate restTemplate;
    @Value("${email.service}")
    private String emailUrl;

   public MailResponseBack sendEmail(String receiver, String sub, String body) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("recipentEmail", receiver);
        requestMap.put("subject", sub);
        requestMap.put("body", body);

            ResponseEntity<MailResponseBack> response = restTemplate.postForEntity(
                emailUrl + "/send-mail",
                requestMap,
                MailResponseBack.class
            );

            if (response.getBody().getStatus() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return MailResponseBack.
                builder()
                .msg("Failed to send Email")
                .status(HttpStatus.BAD_GATEWAY)
                .build();
            }

    }
}
