package org.jisu.e_store_user_service.security;

import java.io.IOException;
import java.util.Arrays;

import org.jisu.e_store_user_service.response.ApiResponse;
import org.jisu.e_store_user_service.response.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        if (response.getHeader("ex")==null) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(401)
                    .error("Validation Falils")
                    .suberrors(Arrays.asList("Request has been blocked due to Unauthenticated Source"))
                    .build();

            ApiResponse<Void> apiResponse = new ApiResponse<>(errorResponse);
            apiResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(apiResponse);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(jsonResponse);
        }
    }

}
