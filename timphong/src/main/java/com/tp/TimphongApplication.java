package com.tp;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tp.service.ImageService;

@SpringBootApplication
public class TimphongApplication implements CommandLineRunner{

	@Resource
    ImageService imageService;
	public static void main(String[] args) {
		SpringApplication.run(TimphongApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		imageService.init();
	}
}
