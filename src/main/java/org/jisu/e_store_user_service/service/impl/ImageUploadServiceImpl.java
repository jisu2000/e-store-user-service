package org.jisu.e_store_user_service.service.impl;

import java.io.IOException;
import java.util.Map;

import org.jisu.e_store_user_service.entities.UserEO;
import org.jisu.e_store_user_service.repo.UserRepo;
import org.jisu.e_store_user_service.service.AuthService;
import org.jisu.e_store_user_service.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {

    private final Cloudinary cloudinary;
    private final UserRepo userRepo;
    private final AuthService authService;
    @Value("${cloudinary.profile.folder}")
    private String imageFolder;

    @Override
    public Map<?, ?> uploadImage(MultipartFile file) {

        Map<?, ?> uploadedImageMap = null;
        try {
            uploadedImageMap = cloudinary.uploader().upload(file.getBytes(), Map.of("folder",imageFolder));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        UserEO currentUser = authService.getCurrentUserObject();
        currentUser.setProfilePhoto(uploadedImageMap.get("url").toString());
        userRepo.save(currentUser);

        return uploadedImageMap;
    }

}
