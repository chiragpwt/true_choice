package com.truechoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truechoice.model.AuthRequest;
import com.truechoice.requestresponseclass.UserRequest;
import com.truechoice.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class SignupController {

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUserDetails(@RequestBody UserRequest userDetail) throws Exception {
		return userService.registerUserDetails(userDetail);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws Exception {
		return userService.loginUser(authRequest);
	}
}
