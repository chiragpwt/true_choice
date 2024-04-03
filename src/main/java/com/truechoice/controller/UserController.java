package com.truechoice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.truechoice.common.CommonMethods;
import com.truechoice.exceptionhandling.CustomException;
import com.truechoice.exceptionhandling.ResourceNotFoundException;
import com.truechoice.model.UserInfo;
import com.truechoice.repository.UserRepository;
import com.truechoice.requestresponseclass.UserRequest;
import com.truechoice.services.ImageService;
import com.truechoice.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommonMethods commonMethod;

	@Autowired
	UserService userService;

	@Autowired
	ImageService imageService;

//	@PostMapping("/register")
//	public ResponseEntity<?> registerUserDetails(@RequestBody UserRequest userDetail) throws Exception {
//		return userService.registerUserDetails(userDetail);
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws Exception {
//		return userService.loginUser(authRequest);
//	}
	
	@GetMapping("/getUserById")
	public ResponseEntity<?> getUserById(@RequestParam long userId) throws Exception {
		return userService.getUserById(userId);
	}

	@GetMapping("/sendOTP")
	public ResponseEntity<?> sendOTP(@RequestParam String email) throws Exception {
		return userService.verifyEmail(email);
	}
	
	@GetMapping("/verifyOTP")
	public ResponseEntity<?> verifyOTP(@RequestParam String otp , @RequestParam long id) throws Exception {
		return userService.verifyOTP(otp, id);
	}
	
	@PostMapping("/updatePassword")
	public  ResponseEntity<?> updatePassword( @RequestParam String newPassword, @RequestParam long id) throws CustomException {
		return userService.updatePassword(newPassword,id);
	}

	@PostMapping("/upload")
	public void uploadImage(@RequestParam("productImage") MultipartFile file) throws IOException {
		imageService.uploadImage(file);
	}

	@GetMapping("/getUserList") 
	public List<UserInfo> getUserList() {
		return userService.getUserList();
	}
	
	@DeleteMapping("/deleteUser") 
	public ResponseEntity<?> deleteUser(@RequestParam long id) {
		userService.deleteById(id);
		return new ResponseEntity("Delete Sucessfully." , HttpStatus.OK); 
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<?> updateUserDetails(@RequestBody UserRequest userDetail) throws Exception {
		if ((!commonMethod.nullBlankCheck(userDetail.getEmail()))
				&& (!commonMethod.nullBlankCheck(userDetail.getPhone()))) {
			throw new ResourceNotFoundException("Please email or mobile");
		}

		if (commonMethod.nullBlankCheck(userDetail.getEmail())) {
			userService.updateUserDetailByEmail(userDetail);
		}

		if (commonMethod.nullBlankCheck(userDetail.getPhone())) {
			userService.updateUserDetailByMobile(userDetail);
		}
		return new ResponseEntity<>("User Details Updated.", HttpStatus.CREATED);

	}
	
	@PostMapping("/checkUser") 
	public ResponseEntity<?> checkUser(@RequestParam String email , @RequestParam String mobile) throws Exception {
		if (!commonMethod.nullBlankCheck(email)) {
			throw new ResourceNotFoundException("Please enter email");
		}
		
		if (!commonMethod.nullBlankCheck(mobile)) {
			throw new ResourceNotFoundException("Please enter mobile");
		}
		
		userService.checkUser(email , mobile);
		return new ResponseEntity<>("", HttpStatus.OK);
				
	}
}
