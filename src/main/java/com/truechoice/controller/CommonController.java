package com.truechoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truechoice.requestresponseclass.CommonDropDown;
import com.truechoice.services.CommonService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	CommonService commonService;
	
	@PostMapping("/addMotherTongue")
	public ResponseEntity<?> addMotherTongue(@RequestParam String name) throws Exception {
		return commonService.addMotherTongue(name);
	}
	
	@PostMapping("/addMaritialStatus")
	public ResponseEntity<?> addMaritialStatus(@RequestParam String name) throws Exception {
		return commonService.addMaritialStatus(name);
	}
	
	@PostMapping("/addEducationInfo")
	public ResponseEntity<?> addEducationInfo(@RequestParam String name) throws Exception {
		return commonService.addEducationInfo(name);
	}
	
	@PostMapping("/addReligion")
	public ResponseEntity<?> addReligion(@RequestParam String name) throws Exception {
		return commonService.addReligion(name);
	}
	
	@PostMapping("/addCaste")
	public ResponseEntity<?> addCaste(@RequestParam String name) throws Exception {
		return commonService.addCaste(name);
	}
	
	@PostMapping("/addOccupation")
	public ResponseEntity<?> addOccupation(@RequestParam String name) throws Exception {
		return commonService.addOccupation(name);
	}
	
	@GetMapping("/getCommonData")
	public CommonDropDown getDropDownListData() throws Exception {
		return commonService.getDropDownListData();
	}
	
	@PostMapping("/updateUserPlan")
	public ResponseEntity<?> updateUserPlan(@RequestParam long userId , @RequestParam int planId , @RequestParam(required = false) String password) throws Exception {
		return commonService.updateUserPlan(userId , planId , password);
	}
	
	@PostMapping("/addPlan")
	public ResponseEntity<?> addPlan(@RequestParam String name , @RequestParam Double amount , @RequestParam String description) throws Exception {
		return commonService.addPlan(name , amount , description);
	}
	
	@PostMapping("/updatePlan")
	public ResponseEntity<?> updatePlan( @RequestParam int planId , @RequestParam Double amount , @RequestParam String name , @RequestParam String description) throws Exception {
		return commonService.updatePlan(planId , amount ,name , description);
	}
	
	@DeleteMapping("/deletePlan")
	public ResponseEntity<?> deletePlan(@RequestParam int planId) throws Exception {
		return commonService.deletePlan(planId);
	}
	@GetMapping("/getPlan")
	public ResponseEntity<?> getPlan() throws Exception {
		return commonService.getPlan();
	}
}
