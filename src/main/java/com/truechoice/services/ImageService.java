package com.truechoice.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.truechoice.model.ImageDetails;
import com.truechoice.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
    private ImageRepository imageRepo;
	
	private final String PATH = "D:\\DummyImage\\";
	
	public ImageDetails uploadImage(MultipartFile file) throws IOException {
		String fullPath = PATH+file.getOriginalFilename();
		ImageDetails image = new ImageDetails();
		image.setProfileImageName(file.getOriginalFilename());
		image.setProfileImageType(file.getContentType());
		image.setProfileImagePath(fullPath);
		
		
		file.transferTo(new File(fullPath));
		return imageRepo.save(image);
	}
	
	public byte[] downloadImage(String fileName) throws IOException{
		return null;
//        Optional<ImageDetails> imageObject = imageRepo.findByProfileImageName(fileName);
//        String fullPath = imageObject.get().getProfileImagePath();
//        return Files.readAllBytes(new File(fullPath).toPath());
    }
	
}
