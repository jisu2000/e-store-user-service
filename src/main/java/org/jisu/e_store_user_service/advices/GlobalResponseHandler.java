package org.jisu.e_store_user_service.advices;

import java.net.URI;

import org.jisu.e_store_user_service.response.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {

        URI requestPath = request.getURI();

        if (!requestPath.getRawPath().equals("/v3/api-docs")) {

            // Return directly if the body is already a String or ApiResponse
            if (body instanceof String || body instanceof ApiResponse<?>) {
                return body;
            }

            // Wrap the response in ApiResponse if not already wrapped
            return new ApiResponse<>(body);
        }
        return body;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
}
