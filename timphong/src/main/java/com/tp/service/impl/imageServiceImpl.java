package com.tp.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tp.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	private final Path root = Paths.get("uploads");
	@Override
	public void init() {
		try {
		      Files.createDirectories(root);
		    } 
		catch (IOException e) {
		      throw new RuntimeException("Could not initialize folder for upload!");
		    }
		
	}

	@Override
	public String save(MultipartFile file) {
		
	    try {
			String originalFileName = file.getOriginalFilename();
			if(originalFileName == null) {
				return null;
			}
			int index = originalFileName.lastIndexOf(".");
			String ext = originalFileName.substring(index+1);
			String newFileName = UUID.randomUUID().toString() + "." + ext;
	        Files.copy(file.getInputStream(), this.root.resolve(newFileName));
			return newFileName;
	      } 
	    catch (Exception e) {
	        if (e instanceof FileAlreadyExistsException) {
	          throw new RuntimeException("A file of that name already exists.");
	        }

	        throw new RuntimeException(e.getMessage());
	      }
		
	}

	@Override
	public Resource getResource(String filename) {
		try {
		      Path file = root.resolve(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } 
		      else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } 
		catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
	}

	@Override
	public boolean delete(String imageName) {
		try {
			  if(imageName != null) {
				  Path file = root.resolve(imageName);
				  return Files.deleteIfExists(file);
			  }
			  else {
				  return true;
			  }
		    } 
		catch (IOException e) {
		      throw new RuntimeException("Failed to delete file: " + imageName);
		    }
	}
}
