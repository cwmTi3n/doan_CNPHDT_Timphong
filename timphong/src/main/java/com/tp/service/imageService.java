package com.tp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	Resource getResource(String imageName);
	void init();
	String save(MultipartFile file);
	boolean delete(String imageName);
}
