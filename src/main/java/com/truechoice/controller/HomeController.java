package com.truechoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truechoice.dto.ResponseMessageDTO;
import com.truechoice.services.HomeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/home")
public class HomeController {

	@Autowired
	HomeService homeService;
	
	@GetMapping("/getMyMatches")
	public ResponseEntity<?> getMyMatches(@RequestParam long id) {
		return homeService.getMyMatches(id);
	}
	
	@GetMapping("/getNewMatches")
	public ResponseEntity<?> getNewMatches(@RequestParam long id , @RequestParam(required=false) Integer age , @RequestParam(required=false) Long motherTongue , @RequestParam(required=false) String height , @RequestParam(required=false) String weight ,  @RequestParam(required=false) Long education ,  @RequestParam(required=false) Long religion , @RequestParam(required=false) Long caste ) {
		return homeService.getNewMatches(id , age , motherTongue , height , weight , education , religion , caste);
	}
	
	@GetMapping("/getPremiumMatches")
	public ResponseEntity<?> getPremiumMatches(@RequestParam long id , @RequestParam(required=false) Integer age , @RequestParam(required=false) Long motherTongue , @RequestParam(required=false) String height , @RequestParam(required=false) String weight ,  @RequestParam(required=false) Long education ,  @RequestParam(required=false) Long religion , @RequestParam(required=false) Long caste ) {
		return homeService.getPremiumMatches(id , age , motherTongue , height , weight , education , religion , caste);
	}
	
	@PostMapping("/favoriteUnfavoriteRequest")
	public ResponseMessageDTO favoriteUnfavoriteRequest(@RequestParam long userId , @RequestParam long followerId ,@RequestParam(required=false) boolean isFavorite) {
		return homeService.favoriteUnfavoriteRequest(userId , followerId  , isFavorite );
	}
	
	@PostMapping("/ignoreNotIgnoreRequest")
	public ResponseMessageDTO ignoreNotIgnoreRequest(@RequestParam long userId , @RequestParam long followerId ,@RequestParam(required=false) boolean isIgnored) {
		return homeService.ignoreNotIgnoreRequest(userId , followerId  , isIgnored);
	}
	
	@PostMapping("/requestRejectRequest")
	public ResponseMessageDTO requestRejectRequest(@RequestParam long userId , @RequestParam long followerId , @RequestParam(required=false) boolean isRequested) {
		return homeService.requestRejectRequest(userId , followerId ,isRequested);
	}
	
	@PostMapping("/approveRejectRequest")
	public ResponseMessageDTO acceptRejectRequest(@RequestParam long userId , @RequestParam long followerId ,@RequestParam(required=false) boolean isAccept , @RequestParam(required=false) boolean isReject) {
		return homeService.acceptRejectRequest(userId , followerId  ,isAccept , isReject);
	}
	
	@GetMapping("/getFavoritList")
	public ResponseEntity<?> getFavoritList(@RequestParam long userId) {
		return homeService.getFavoritList(userId);
	}
	
	@GetMapping("/getIgnoredList")
	public ResponseEntity<?> getIgnoredList(@RequestParam long userId) {
		return homeService.getIgnoredList(userId);
	}
	
	@GetMapping("/getAcceptList")
	public ResponseEntity<?> getAcceptList(@RequestParam long userId) {
		return homeService.getAcceptList(userId);
	}
	
	@GetMapping("/getRejectList")
	public ResponseEntity<?> getRejectList(@RequestParam long userId) {
		return homeService.getRejectList(userId);
	}
	
	@GetMapping("/getReceivedRequestList")
	public ResponseEntity<?> getReceivedRequestList(@RequestParam long userId) {
		return homeService.getReceivedRequestList(userId);
	}
	
	@GetMapping("/getRequestedList")
	public ResponseEntity<?> getRequestedList(@RequestParam long userId) {
		return homeService.getRequestedList(userId);
	}
}
