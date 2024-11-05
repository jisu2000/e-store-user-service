package org.jisu.e_store_user_service.controller;

import org.jisu.e_store_user_service.service.AuthService;
import org.jisu.e_store_user_service.service.ImageUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final ImageUploadService imageUploadService;

    @GetMapping("/get-user")
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(authService.getCurrentUser(), HttpStatus.OK);
    }

    @Operation(summary = "Upload a file")
    @RequestMapping(method = RequestMethod.POST, path = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uplaodImage(@RequestPart(value = "file", required = true) MultipartFile multipartFile) {
        return new ResponseEntity<>(imageUploadService.uploadImage(multipartFile), HttpStatus.OK);
    }

    @DeleteMapping("/log-out")
    public ResponseEntity<?> dologout(
            @RequestHeader("Authorization") String authHeader) {
        return new ResponseEntity<>(authService.logoutUser(authHeader), HttpStatus.OK);
    }

}
