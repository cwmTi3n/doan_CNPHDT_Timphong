package com.tp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tp.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;
    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        Map<String, String> options = new HashMap<>();
        options.put("public_id", UUID.randomUUID().toString());
        Map uploadResult = cloudinary.uploader()
                .upload(multipartFile.getBytes(), options);
        return (String) uploadResult.get("url");
    }
    @Override
    public void deleteImage(String url) throws IOException {
        if(url == null) {
            return;
        }
        String publicId = url.replaceAll("^.*/([^/]+)\\.[^.]+$", "$1");
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
