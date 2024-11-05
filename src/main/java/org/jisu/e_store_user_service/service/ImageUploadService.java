package org.jisu.e_store_user_service.service;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    Map<?,?> uploadImage(MultipartFile file);
}
