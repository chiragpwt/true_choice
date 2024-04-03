package com.truechoice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.truechoice.services.ImageService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/image")
public class ImageController {

	@Autowired
	ImageService imageService;
	
	@PostMapping("/uploadProfileImage")
	public void uploadImage(@RequestParam("profileImage")MultipartFile file) throws IOException{
		imageService.uploadImage(file);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<byte[]> downloadProfileImage(@PathVariable String fileName) throws IOException {
		byte[] image = imageService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpg")).body(image);
	}
}
