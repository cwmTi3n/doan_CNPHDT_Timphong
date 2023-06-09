package com.tp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.service.ImageService;

@Controller
@RequestMapping("image/{filename}")
public class ImageController {
	@Autowired
    ImageService imageService;
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<Resource> getImage(@PathVariable String filename) {
		Resource resource = imageService.getResource(filename);
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
