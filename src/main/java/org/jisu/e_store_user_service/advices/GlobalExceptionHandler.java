package org.jisu.e_store_user_service.advices;

import org.jisu.e_store_user_service.exception.InvalidCredientialsException;
import org.jisu.e_store_user_service.exception.InvalidOtpException;
import org.jisu.e_store_user_service.exception.RefreshTokenException;
import org.jisu.e_store_user_service.exception.ResourceNotFoundException;
import org.jisu.e_store_user_service.exception.UnauthorizeException;
import org.jisu.e_store_user_service.response.ApiResponse;
import org.jisu.e_store_user_service.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(404)
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<?> handleUnauthorizeException(UnauthorizeException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(401)
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(InvalidCredientialsException.class)
    public ResponseEntity<?> handleInvalidCredException(InvalidCredientialsException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(401)
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handleSignatureException(SignatureException ex){
        return buildResponseEntityWithApiResponse(
            ErrorResponse.builder()
                    .status(401)
                    .error(ex.getMessage())
                    .build());
    }

    @ExceptionHandler(RefreshTokenException.class)
    public ResponseEntity<?> handleRefreshTokenException(RefreshTokenException ex){
        return buildResponseEntityWithApiResponse(
            ErrorResponse.builder()
                    .status(401)
                    .error(ex.getMessage())
                    .build());
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<?> handleOtpException(InvalidOtpException ex){
        return buildResponseEntityWithApiResponse(
            ErrorResponse.builder()
                    .status(400)
                    .error(ex.getMessage())
                    .build());
    }

    

    private ResponseEntity<ApiResponse<?>> buildResponseEntityWithApiResponse(ErrorResponse errorResponse) {
        ApiResponse<ErrorResponse> errApiResponse = new ApiResponse<>(errorResponse);
        errApiResponse.setStatus(errorResponse.getStatus());
        return new ResponseEntity<>(errApiResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }

}
