package com.truechoice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.truechoice.exceptionhandling.ResourceNotFoundException;
import com.truechoice.model.FollowInfo;
import com.truechoice.model.FollowersId;
import com.truechoice.model.UserInfo;
import com.truechoice.repository.FollowRepository;
import com.truechoice.repository.HomeRepository;
import com.truechoice.repository.UserRepository;
import com.truechoice.requestresponseclass.AcceptData;
import com.truechoice.requestresponseclass.FavoriteData;
import com.truechoice.requestresponseclass.IgnoreData;
import com.truechoice.requestresponseclass.MyMatchData;
import com.truechoice.requestresponseclass.NewMatchesData;
import com.truechoice.requestresponseclass.NewMatchesResponse;
import com.truechoice.requestresponseclass.PremimumMatchesData;
import com.truechoice.requestresponseclass.RejectData;
import com.truechoice.requestresponseclass.RequestedListData;
import com.truechoice.dto.ResponseMessageDTO;
import com.truechoice.dto.MessageDTO;

@Service
public class HomeService {

	@Autowired
	HomeRepository homeRepo;
	
	@Autowired
	FollowRepository followRepo;
	
	@Autowired
	UserRepository userRepo;

	public ResponseEntity<?> getMyMatches(long id) {
		
		Optional<UserInfo> userData = homeRepo.findById(id);
		if(userData.isPresent()) {
			String genderValue= userData.get().getGender();
			String gender;
			if(genderValue.equals("Male")) {
				gender = "Female";
			}else {
				gender = "Male";
			}
			List<UserInfo> getMyMatches= homeRepo.myMatchesList(id,gender);
			MyMatchData myMatches = new MyMatchData();
			List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
			getMyMatches.stream().forEach(matchedData -> {
				NewMatchesResponse newMatchdata = new NewMatchesResponse();
				FollowInfo dbfollowDetail= new FollowInfo();
				FollowersId followId = new FollowersId();
				followId.setUserId(id);
				followId.setFollowId(matchedData.getId());
				//Optional<FollowInfo> followIdInfo = followRepo.removeRejectandIgnore(id);
				Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
				if(followIdInfo.isPresent()) {
					dbfollowDetail = followIdInfo.get();
					newMatchdata.setIsFavorite(dbfollowDetail.isFavorite());
					newMatchdata.setIsIgnore(dbfollowDetail.isIgnored());
					newMatchdata.setIsReuest(dbfollowDetail.isRequested());
					newMatchdata.setIsAccepted(dbfollowDetail.isAccept());
				}else {
					newMatchdata.setIsFavorite(false);
					newMatchdata.setIsIgnore(false);
					newMatchdata.setIsReuest(false);
					newMatchdata.setIsAccepted(false);
				}
				newMatchdata.setId(matchedData.getId());
				newMatchdata.setFirstName(matchedData.getFirstName());
				newMatchdata.setEmail(matchedData.getEmail());
				newMatchdata.setPhone(matchedData.getPhone());
				newMatchdata.setLastName(matchedData.getLastName());
				newMatchdata.setDob(matchedData.getDob());
				newMatchdata.setMotherTongueInfo(matchedData.getMotherTongueInfo());
				newMatchdata.setHeight(matchedData.getHeight());
				newMatchdata.setWeight(matchedData.getWeight());
				newMatchdata.setMaritialStatus(matchedData.getMaritialStatus());
				newMatchdata.setWorkingAt(matchedData.getWorkingAt());
				newMatchdata.setAnnualIncome(matchedData.getAnnualIncome());
				newMatchdata.setEducation(matchedData.getEducation());
				newMatchdata.setReligion(matchedData.getReligion());
				newMatchdata.setCaste(matchedData.getCaste());
				newMatchdata.setSubCaste(matchedData.getSubCaste());
				newMatchdata.setAboutYourself(matchedData.getAboutYourself());
				newMatchdata.setExpectation(matchedData.getExpectation());
				newMatchdata.setFatherName(matchedData.getFatherName());
				newMatchdata.setFatherOccupation(matchedData.getFatherOccupation());
				newMatchdata.setMotherName(matchedData.getMotherName());
				newMatchdata.setMotherOccupation(matchedData.getMotherOccupation());
				newMatchdata.setContactEmail(matchedData.getContactEmail());
				newMatchdata.setContactMobile(matchedData.getContactMobile());
				newMatchdata.setCountry(matchedData.getCountry());
				newMatchdata.setState(matchedData.getState());
				newMatchdata.setCity(matchedData.getCity());
				newMatchdata.setAddress(matchedData.getAddress());
				newMatchdata.setCreatedAt(matchedData.getCreatedAt());
				newMatchdata.setAge(matchedData.getAge());
				newMatchdata.setGender(matchedData.getGender());
				newMatchdata.setPlanInfo(matchedData.getPlanInfo());
				if(!dbfollowDetail.isIgnored() && !dbfollowDetail.isReject()) {
					if(newMatchesResponse.size() != 20) {
						newMatchesResponse.add(newMatchdata);
					}
				}
				
			});

			myMatches.setMyMatches(newMatchesResponse);
			return new ResponseEntity(myMatches , HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
	}

	public ResponseEntity<?> getNewMatches(long id, Integer age, Long motherTongue, String height, String weight,
			Long education, Long religion, Long caste) {
		
		Optional<UserInfo> userData = homeRepo.findById(id);
		if(userData.isPresent()) {
			String genderValue= userData.get().getGender();
			String gender;
			if(genderValue.equals("Male")) {
				gender = "Female";
			}else {
				gender = "Male";
			}
			List<UserInfo> getNewMatches= homeRepo.getNewMatches(id ,gender ,age , motherTongue , height , weight , education , religion , caste);
			NewMatchesData newMatches = new NewMatchesData();
			List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
			getNewMatches.stream().forEach(matchedData -> {
				NewMatchesResponse newMatchdata = new NewMatchesResponse();
				FollowersId followId = new FollowersId();
				followId.setUserId(id);
				followId.setFollowId(matchedData.getId());
				Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
				if(followIdInfo.isPresent()) {
					FollowInfo dbfollowDetail= followIdInfo.get();
					newMatchdata.setIsFavorite(dbfollowDetail.isFavorite());
					newMatchdata.setIsIgnore(dbfollowDetail.isIgnored());
					newMatchdata.setIsReuest(dbfollowDetail.isRequested());
					newMatchdata.setIsAccepted(dbfollowDetail.isAccept());
				}else {
					newMatchdata.setIsFavorite(false);
					newMatchdata.setIsIgnore(false);
					newMatchdata.setIsReuest(false);
					newMatchdata.setIsAccepted(false);
				}
				newMatchdata.setId(matchedData.getId());
				newMatchdata.setFirstName(matchedData.getFirstName());
				newMatchdata.setEmail(matchedData.getEmail());
				newMatchdata.setPhone(matchedData.getPhone());
				newMatchdata.setLastName(matchedData.getLastName());
				newMatchdata.setDob(matchedData.getDob());
				newMatchdata.setMotherTongueInfo(matchedData.getMotherTongueInfo());
				newMatchdata.setHeight(matchedData.getHeight());
				newMatchdata.setWeight(matchedData.getWeight());
				newMatchdata.setMaritialStatus(matchedData.getMaritialStatus());
				newMatchdata.setWorkingAt(matchedData.getWorkingAt());
				newMatchdata.setAnnualIncome(matchedData.getAnnualIncome());
				newMatchdata.setEducation(matchedData.getEducation());
				newMatchdata.setReligion(matchedData.getReligion());
				newMatchdata.setCaste(matchedData.getCaste());
				newMatchdata.setSubCaste(matchedData.getSubCaste());
				newMatchdata.setAboutYourself(matchedData.getAboutYourself());
				newMatchdata.setExpectation(matchedData.getExpectation());
				newMatchdata.setFatherName(matchedData.getFatherName());
				newMatchdata.setFatherOccupation(matchedData.getFatherOccupation());
				newMatchdata.setMotherName(matchedData.getMotherName());
				newMatchdata.setMotherOccupation(matchedData.getMotherOccupation());
				newMatchdata.setContactEmail(matchedData.getContactEmail());
				newMatchdata.setContactMobile(matchedData.getContactMobile());
				newMatchdata.setCountry(matchedData.getCountry());
				newMatchdata.setState(matchedData.getState());
				newMatchdata.setCity(matchedData.getCity());
				newMatchdata.setAddress(matchedData.getAddress());
				newMatchdata.setCreatedAt(matchedData.getCreatedAt());
				newMatchdata.setAge(matchedData.getAge());
				newMatchdata.setGender(matchedData.getGender());
				newMatchdata.setPlanInfo(matchedData.getPlanInfo());
				newMatchesResponse.add(newMatchdata);
			});

			newMatches.setNewMatches(newMatchesResponse);
			return new ResponseEntity(newMatches , HttpStatus.OK);

		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
	}
	
	public ResponseEntity<?> getPremiumMatches(long id, Integer age, Long motherTongue, String height, String weight,
			Long education, Long religion, Long caste) {
		
		Optional<UserInfo> userData = homeRepo.findById(id);
		if(userData.isPresent()) {
			String genderValue= userData.get().getGender();
			String gender;
			if(genderValue.equals("Male")) {
				gender = "Female";
			}else {
				gender = "Male";
			}
			
			int planId = userData.get().getPlanInfo().getId();
			
			List<UserInfo> getPremiumMatches= homeRepo.getPremiumMatches(id ,planId ,gender ,age , motherTongue , height , weight , education , religion , caste);
			PremimumMatchesData premimumMatches = new PremimumMatchesData();
			List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
			getPremiumMatches.stream().forEach(matchedData -> {
				NewMatchesResponse newMatchdata = new NewMatchesResponse();
				FollowersId followId = new FollowersId();
				followId.setUserId(id);
				followId.setFollowId(matchedData.getId());
				Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
				if(followIdInfo.isPresent()) {
					FollowInfo dbfollowDetail= followIdInfo.get();
					newMatchdata.setIsFavorite(dbfollowDetail.isFavorite());
					newMatchdata.setIsIgnore(dbfollowDetail.isIgnored());
					newMatchdata.setIsReuest(dbfollowDetail.isRequested());
					newMatchdata.setIsAccepted(dbfollowDetail.isAccept());
				}else {
					newMatchdata.setIsFavorite(false);
					newMatchdata.setIsIgnore(false);
					newMatchdata.setIsReuest(false);
					newMatchdata.setIsAccepted(false);
				}
				newMatchdata.setId(matchedData.getId());
				newMatchdata.setFirstName(matchedData.getFirstName());
				newMatchdata.setEmail(matchedData.getEmail());
				newMatchdata.setPhone(matchedData.getPhone());
				newMatchdata.setLastName(matchedData.getLastName());
				newMatchdata.setDob(matchedData.getDob());
				newMatchdata.setMotherTongueInfo(matchedData.getMotherTongueInfo());
				newMatchdata.setHeight(matchedData.getHeight());
				newMatchdata.setWeight(matchedData.getWeight());
				newMatchdata.setMaritialStatus(matchedData.getMaritialStatus());
				newMatchdata.setWorkingAt(matchedData.getWorkingAt());
				newMatchdata.setAnnualIncome(matchedData.getAnnualIncome());
				newMatchdata.setEducation(matchedData.getEducation());
				newMatchdata.setReligion(matchedData.getReligion());
				newMatchdata.setCaste(matchedData.getCaste());
				newMatchdata.setSubCaste(matchedData.getSubCaste());
				newMatchdata.setAboutYourself(matchedData.getAboutYourself());
				newMatchdata.setExpectation(matchedData.getExpectation());
				newMatchdata.setFatherName(matchedData.getFatherName());
				newMatchdata.setFatherOccupation(matchedData.getFatherOccupation());
				newMatchdata.setMotherName(matchedData.getMotherName());
				newMatchdata.setMotherOccupation(matchedData.getMotherOccupation());
				newMatchdata.setContactEmail(matchedData.getContactEmail());
				newMatchdata.setContactMobile(matchedData.getContactMobile());
				newMatchdata.setCountry(matchedData.getCountry());
				newMatchdata.setState(matchedData.getState());
				newMatchdata.setCity(matchedData.getCity());
				newMatchdata.setAddress(matchedData.getAddress());
				newMatchdata.setCreatedAt(matchedData.getCreatedAt());
				newMatchdata.setAge(matchedData.getAge());
				newMatchdata.setGender(matchedData.getGender());
				newMatchdata.setPlanInfo(matchedData.getPlanInfo());
				newMatchesResponse.add(newMatchdata);
			});

			premimumMatches.setPremimumMatches(newMatchesResponse);
			return new ResponseEntity(premimumMatches , HttpStatus.OK);


		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
	}

//	public ResponseMessageDTO sendRequest(long userId, long followerId, boolean isFavorite,
//			boolean isIgnored, boolean isRequested) {
//
//		Optional<UserInfo> userInfo= userRepo.findById(userId);
//		Optional<UserInfo> followerInfo= userRepo.findById(followerId);
//
//		UserInfo userDetail = new UserInfo();
//		UserInfo followerDetail = new UserInfo();
//
//		if(userInfo.isPresent()) {
//			userDetail = userInfo.get();
//		}else {
//			throw new ResourceNotFoundException("User not exist.");
//		}
//		
//		if(followerInfo.isPresent()) {
//			followerDetail = followerInfo.get();
//		}else {
//			throw new ResourceNotFoundException("Follow user not exist.");
//		}
//		FollowInfo followInfo = new FollowInfo();
//
//		FollowersId followId = new FollowersId();
//		followId.setUserId(userId);
//		followId.setFollowId(followerId);
//		
//		Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
//		if(followIdInfo.isPresent()) {
//			FollowInfo dbfollowDetail= followIdInfo.get();
//			followInfo.setId(dbfollowDetail.getId());
//			followInfo.setRequested(isRequested);
//			followInfo.setFavorite(isFavorite);
//			followInfo.setIgnored(isIgnored);
//			followInfo.setAccept(dbfollowDetail.isAccept());
//			followInfo.setReject(dbfollowDetail.isReject());
//			followRepo.save(followInfo);
//			return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
//
//			//return new ResponseEntity("Request Send Successfully." , HttpStatus.OK);
//		}else {
//			followInfo.setId(followId);
//			followInfo.setRequested(isRequested);
//			followInfo.setFavorite(isFavorite);
//			followInfo.setIgnored(isIgnored);
//			followInfo.setUserId(userDetail);
//			followInfo.setFollowId(followerDetail);
//			followRepo.save(followInfo);
//
//			return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
//		}
//	}

	public ResponseMessageDTO acceptRejectRequest(long userId, long followerId, boolean isAccept, boolean isReject) {
		Optional<UserInfo> userInfo= userRepo.findById(userId);
		Optional<UserInfo> followerInfo= userRepo.findById(followerId);

		UserInfo userDetail = new UserInfo();
		UserInfo followerDetail = new UserInfo();

		if(userInfo.isPresent()) {
			userDetail = userInfo.get();
		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
		
		if(followerInfo.isPresent()) {
			followerDetail = followerInfo.get();
		}else {
			throw new ResourceNotFoundException("Follow user not exist.");
		}
		
		FollowInfo followInfo = new FollowInfo();
		FollowersId followId = new FollowersId();
		FollowInfo secondUserFollowInfo = new FollowInfo();
		FollowersId secondUserEntry = new FollowersId();
		
		followId.setUserId(userId);
		followId.setFollowId(followerId);
		
		secondUserEntry.setUserId(followerId);
		secondUserEntry.setFollowId(userId);
		
		Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
		Optional<FollowInfo> secondUserInfo = followRepo.findById(secondUserEntry);
		
		if(followIdInfo.isPresent()) {
			FollowInfo dbfollowDetail= followIdInfo.get();
			followInfo.setId(dbfollowDetail.getId());
			followInfo.setAccept(isAccept);
			followInfo.setReject(isReject);
			followInfo.setRequested(false);
			followInfo.setFavorite(dbfollowDetail.isFavorite());
			followInfo.setIgnored(dbfollowDetail.isIgnored());
			followRepo.save(followInfo);
			followRepo.updateRequestList(followerId, userId);
			
			if(secondUserInfo.isPresent()) {
				FollowInfo secondFollowDbDetail= secondUserInfo.get();
				secondUserFollowInfo.setId(secondFollowDbDetail.getId());
				secondUserFollowInfo.setAccept(isAccept);
				secondUserFollowInfo.setReject(isReject);
				secondUserFollowInfo.setRequested(false);
				secondUserFollowInfo.setFavorite(secondFollowDbDetail.isFavorite());
				secondUserFollowInfo.setIgnored(secondFollowDbDetail.isIgnored());
				followRepo.save(secondUserFollowInfo);
			}else {
				secondUserFollowInfo.setId(secondUserEntry);
				secondUserFollowInfo.setAccept(isAccept);
				secondUserFollowInfo.setReject(isReject);
				secondUserFollowInfo.setUserId(followerDetail);
				secondUserFollowInfo.setRequested(false);
				secondUserFollowInfo.setFollowId(userDetail);
				followRepo.save(secondUserFollowInfo);
			}
			if(isAccept == true) {
				return new ResponseMessageDTO(true,new MessageDTO("Request Accept Successfully."));
			}
			
			if(isReject == true) {
				return new ResponseMessageDTO(true,new MessageDTO("Request Reject Successfully."));
			}
		}else {
			followInfo.setId(followId);
			followInfo.setAccept(isAccept);
			followInfo.setReject(isReject);
			followInfo.setUserId(userDetail);
			followInfo.setRequested(false);
			followInfo.setFollowId(followerDetail);
			followRepo.save(followInfo);
			
			if(secondUserInfo.isPresent()) {
				FollowInfo secondFollowDbDetail= secondUserInfo.get();
				secondUserFollowInfo.setId(secondFollowDbDetail.getId());
				secondUserFollowInfo.setAccept(isAccept);
				secondUserFollowInfo.setReject(isReject);
				secondUserFollowInfo.setRequested(false);
				secondUserFollowInfo.setFavorite(secondFollowDbDetail.isFavorite());
				secondUserFollowInfo.setIgnored(secondFollowDbDetail.isIgnored());
				followRepo.save(secondUserFollowInfo);
			}else {
				secondUserFollowInfo.setId(secondUserEntry);
				secondUserFollowInfo.setAccept(isAccept);
				secondUserFollowInfo.setReject(isReject);
				secondUserFollowInfo.setUserId(followerDetail);
				secondUserFollowInfo.setRequested(false);
				secondUserFollowInfo.setFollowId(userDetail);
				followRepo.save(secondUserFollowInfo);
			}
			if(isAccept == true) {
				return new ResponseMessageDTO(true,new MessageDTO("Request Accept Successfully."));
			}
			
			if(isReject == true) {
				return new ResponseMessageDTO(true,new MessageDTO("Request Reject Successfully."));
			}
		}
		return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
	}

	public ResponseEntity<?> getFavoritList(long userId) {
		FavoriteData favData = new FavoriteData();
		List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
		List<FollowInfo> favList= followRepo.getFavoritList(userId);
		for (FollowInfo followInfo : favList) {
			UserInfo followUser = followInfo.getFollowId();
			NewMatchesResponse newMatchdata = new NewMatchesResponse();
			newMatchdata.setId(followUser.getId());
			newMatchdata.setFirstName(followUser.getFirstName());
			newMatchdata.setEmail(followUser.getEmail());
			newMatchdata.setPhone(followUser.getPhone());
			newMatchdata.setLastName(followUser.getLastName());
			newMatchdata.setDob(followUser.getDob());
			newMatchdata.setMotherTongueInfo(followUser.getMotherTongueInfo());
			newMatchdata.setHeight(followUser.getHeight());
			newMatchdata.setWeight(followUser.getWeight());
			newMatchdata.setMaritialStatus(followUser.getMaritialStatus());
			newMatchdata.setWorkingAt(followUser.getWorkingAt());
			newMatchdata.setAnnualIncome(followUser.getAnnualIncome());
			newMatchdata.setEducation(followUser.getEducation());
			newMatchdata.setReligion(followUser.getReligion());
			newMatchdata.setCaste(followUser.getCaste());
			newMatchdata.setSubCaste(followUser.getSubCaste());
			newMatchdata.setAboutYourself(followUser.getAboutYourself());
			newMatchdata.setExpectation(followUser.getExpectation());
			newMatchdata.setFatherName(followUser.getFatherName());
			newMatchdata.setFatherOccupation(followUser.getFatherOccupation());
			newMatchdata.setMotherName(followUser.getMotherName());
			newMatchdata.setMotherOccupation(followUser.getMotherOccupation());
			newMatchdata.setContactEmail(followUser.getContactEmail());
			newMatchdata.setContactMobile(followUser.getContactMobile());
			newMatchdata.setCountry(followUser.getCountry());
			newMatchdata.setState(followUser.getState());
			newMatchdata.setCity(followUser.getCity());
			newMatchdata.setAddress(followUser.getAddress());
			newMatchdata.setCreatedAt(followUser.getCreatedAt());
			newMatchdata.setAge(followUser.getAge());
			newMatchdata.setGender(followUser.getGender());
			newMatchdata.setPlanInfo(followUser.getPlanInfo());
			newMatchesResponse.add(newMatchdata);
		}
		
		favData.setFavoriteList(newMatchesResponse);
		return new ResponseEntity(favData , HttpStatus.OK);
	}

	public ResponseEntity<?> getIgnoredList(long userId) {
		IgnoreData ignoreData = new IgnoreData();
		List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
		List<FollowInfo> ignoreList= followRepo.getIgnoredList(userId);
		for (FollowInfo followInfo : ignoreList) {
			UserInfo followUser = followInfo.getFollowId();
			NewMatchesResponse newMatchdata = new NewMatchesResponse();
			newMatchdata.setId(followUser.getId());
			newMatchdata.setFirstName(followUser.getFirstName());
			newMatchdata.setEmail(followUser.getEmail());
			newMatchdata.setPhone(followUser.getPhone());
			newMatchdata.setLastName(followUser.getLastName());
			newMatchdata.setDob(followUser.getDob());
			newMatchdata.setMotherTongueInfo(followUser.getMotherTongueInfo());
			newMatchdata.setHeight(followUser.getHeight());
			newMatchdata.setWeight(followUser.getWeight());
			newMatchdata.setMaritialStatus(followUser.getMaritialStatus());
			newMatchdata.setWorkingAt(followUser.getWorkingAt());
			newMatchdata.setAnnualIncome(followUser.getAnnualIncome());
			newMatchdata.setEducation(followUser.getEducation());
			newMatchdata.setReligion(followUser.getReligion());
			newMatchdata.setCaste(followUser.getCaste());
			newMatchdata.setSubCaste(followUser.getSubCaste());
			newMatchdata.setAboutYourself(followUser.getAboutYourself());
			newMatchdata.setExpectation(followUser.getExpectation());
			newMatchdata.setFatherName(followUser.getFatherName());
			newMatchdata.setFatherOccupation(followUser.getFatherOccupation());
			newMatchdata.setMotherName(followUser.getMotherName());
			newMatchdata.setMotherOccupation(followUser.getMotherOccupation());
			newMatchdata.setContactEmail(followUser.getContactEmail());
			newMatchdata.setContactMobile(followUser.getContactMobile());
			newMatchdata.setCountry(followUser.getCountry());
			newMatchdata.setState(followUser.getState());
			newMatchdata.setCity(followUser.getCity());
			newMatchdata.setAddress(followUser.getAddress());
			newMatchdata.setCreatedAt(followUser.getCreatedAt());
			newMatchdata.setAge(followUser.getAge());
			newMatchdata.setGender(followUser.getGender());
			newMatchdata.setPlanInfo(followUser.getPlanInfo());
			newMatchesResponse.add(newMatchdata);
		}
		
		ignoreData.setIgnoreList(newMatchesResponse);
		return new ResponseEntity(ignoreData , HttpStatus.OK);
	}

	public ResponseEntity<?> getAcceptList(long userId) {
		AcceptData acceptData = new AcceptData();
		List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
		List<FollowInfo> acceptList= followRepo.getAcceptList(userId);
		for (FollowInfo followInfo : acceptList) {
			UserInfo followUser = followInfo.getFollowId();
			NewMatchesResponse newMatchdata = new NewMatchesResponse();
			newMatchdata.setId(followUser.getId());
			newMatchdata.setFirstName(followUser.getFirstName());
			newMatchdata.setEmail(followUser.getEmail());
			newMatchdata.setPhone(followUser.getPhone());
			newMatchdata.setLastName(followUser.getLastName());
			newMatchdata.setDob(followUser.getDob());
			newMatchdata.setMotherTongueInfo(followUser.getMotherTongueInfo());
			newMatchdata.setHeight(followUser.getHeight());
			newMatchdata.setWeight(followUser.getWeight());
			newMatchdata.setMaritialStatus(followUser.getMaritialStatus());
			newMatchdata.setWorkingAt(followUser.getWorkingAt());
			newMatchdata.setAnnualIncome(followUser.getAnnualIncome());
			newMatchdata.setEducation(followUser.getEducation());
			newMatchdata.setReligion(followUser.getReligion());
			newMatchdata.setCaste(followUser.getCaste());
			newMatchdata.setSubCaste(followUser.getSubCaste());
			newMatchdata.setAboutYourself(followUser.getAboutYourself());
			newMatchdata.setExpectation(followUser.getExpectation());
			newMatchdata.setFatherName(followUser.getFatherName());
			newMatchdata.setFatherOccupation(followUser.getFatherOccupation());
			newMatchdata.setMotherName(followUser.getMotherName());
			newMatchdata.setMotherOccupation(followUser.getMotherOccupation());
			newMatchdata.setContactEmail(followUser.getContactEmail());
			newMatchdata.setContactMobile(followUser.getContactMobile());
			newMatchdata.setCountry(followUser.getCountry());
			newMatchdata.setState(followUser.getState());
			newMatchdata.setCity(followUser.getCity());
			newMatchdata.setAddress(followUser.getAddress());
			newMatchdata.setCreatedAt(followUser.getCreatedAt());
			newMatchdata.setAge(followUser.getAge());
			newMatchdata.setGender(followUser.getGender());
			newMatchdata.setPlanInfo(followUser.getPlanInfo());
			newMatchesResponse.add(newMatchdata);
		}
		
		acceptData.setAcceptList(newMatchesResponse);
		return new ResponseEntity(acceptData , HttpStatus.OK);
	}

	public ResponseEntity<?> getRejectList(long userId) {
		RejectData rejectData = new RejectData();
		List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
		List<FollowInfo> rejectList= followRepo.getRejectList(userId);
		for (FollowInfo followInfo : rejectList) {
			UserInfo followUser = followInfo.getFollowId();
			NewMatchesResponse newMatchdata = new NewMatchesResponse();
			newMatchdata.setId(followUser.getId());
			newMatchdata.setFirstName(followUser.getFirstName());
			newMatchdata.setEmail(followUser.getEmail());
			newMatchdata.setPhone(followUser.getPhone());
			newMatchdata.setLastName(followUser.getLastName());
			newMatchdata.setDob(followUser.getDob());
			newMatchdata.setMotherTongueInfo(followUser.getMotherTongueInfo());
			newMatchdata.setHeight(followUser.getHeight());
			newMatchdata.setWeight(followUser.getWeight());
			newMatchdata.setMaritialStatus(followUser.getMaritialStatus());
			newMatchdata.setWorkingAt(followUser.getWorkingAt());
			newMatchdata.setAnnualIncome(followUser.getAnnualIncome());
			newMatchdata.setEducation(followUser.getEducation());
			newMatchdata.setReligion(followUser.getReligion());
			newMatchdata.setCaste(followUser.getCaste());
			newMatchdata.setSubCaste(followUser.getSubCaste());
			newMatchdata.setAboutYourself(followUser.getAboutYourself());
			newMatchdata.setExpectation(followUser.getExpectation());
			newMatchdata.setFatherName(followUser.getFatherName());
			newMatchdata.setFatherOccupation(followUser.getFatherOccupation());
			newMatchdata.setMotherName(followUser.getMotherName());
			newMatchdata.setMotherOccupation(followUser.getMotherOccupation());
			newMatchdata.setContactEmail(followUser.getContactEmail());
			newMatchdata.setContactMobile(followUser.getContactMobile());
			newMatchdata.setCountry(followUser.getCountry());
			newMatchdata.setState(followUser.getState());
			newMatchdata.setCity(followUser.getCity());
			newMatchdata.setAddress(followUser.getAddress());
			newMatchdata.setCreatedAt(followUser.getCreatedAt());
			newMatchdata.setAge(followUser.getAge());
			newMatchdata.setGender(followUser.getGender());
			newMatchdata.setPlanInfo(followUser.getPlanInfo());
			newMatchesResponse.add(newMatchdata);
		}
		
		rejectData.setRejectList(newMatchesResponse);
		return new ResponseEntity(rejectData , HttpStatus.OK);
	}

	public ResponseEntity<?> getRequestedList(long userId) {
		RequestedListData requestData = new RequestedListData();
		List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
		List<FollowInfo> requestedList= followRepo.getRequestedList(userId);
		for (FollowInfo followInfo : requestedList) {
			UserInfo followUser = followInfo.getFollowId();
			NewMatchesResponse newMatchdata = new NewMatchesResponse();
			newMatchdata.setId(followUser.getId());
			newMatchdata.setFirstName(followUser.getFirstName());
			newMatchdata.setEmail(followUser.getEmail());
			newMatchdata.setPhone(followUser.getPhone());
			newMatchdata.setLastName(followUser.getLastName());
			newMatchdata.setDob(followUser.getDob());
			newMatchdata.setMotherTongueInfo(followUser.getMotherTongueInfo());
			newMatchdata.setHeight(followUser.getHeight());
			newMatchdata.setWeight(followUser.getWeight());
			newMatchdata.setMaritialStatus(followUser.getMaritialStatus());
			newMatchdata.setWorkingAt(followUser.getWorkingAt());
			newMatchdata.setAnnualIncome(followUser.getAnnualIncome());
			newMatchdata.setEducation(followUser.getEducation());
			newMatchdata.setReligion(followUser.getReligion());
			newMatchdata.setCaste(followUser.getCaste());
			newMatchdata.setSubCaste(followUser.getSubCaste());
			newMatchdata.setAboutYourself(followUser.getAboutYourself());
			newMatchdata.setExpectation(followUser.getExpectation());
			newMatchdata.setFatherName(followUser.getFatherName());
			newMatchdata.setFatherOccupation(followUser.getFatherOccupation());
			newMatchdata.setMotherName(followUser.getMotherName());
			newMatchdata.setMotherOccupation(followUser.getMotherOccupation());
			newMatchdata.setContactEmail(followUser.getContactEmail());
			newMatchdata.setContactMobile(followUser.getContactMobile());
			newMatchdata.setCountry(followUser.getCountry());
			newMatchdata.setState(followUser.getState());
			newMatchdata.setCity(followUser.getCity());
			newMatchdata.setAddress(followUser.getAddress());
			newMatchdata.setCreatedAt(followUser.getCreatedAt());
			newMatchdata.setAge(followUser.getAge());
			newMatchdata.setGender(followUser.getGender());
			newMatchdata.setPlanInfo(followUser.getPlanInfo());
			newMatchesResponse.add(newMatchdata);
		}
		
		requestData.setRequestedList(newMatchesResponse);
		return new ResponseEntity(requestData , HttpStatus.OK);
	}

	public ResponseMessageDTO favoriteUnfavoriteRequest(long userId, long followerId, boolean isFavorite) {

		Optional<UserInfo> userInfo= userRepo.findById(userId);
		Optional<UserInfo> followerInfo= userRepo.findById(followerId);

		UserInfo userDetail = new UserInfo();
		UserInfo followerDetail = new UserInfo();

		if(userInfo.isPresent()) {
			userDetail = userInfo.get();
		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
		
		if(followerInfo.isPresent()) {
			followerDetail = followerInfo.get();
		}else {
			throw new ResourceNotFoundException("Follow user not exist.");
		}
		FollowInfo followInfo = new FollowInfo();

		FollowersId followId = new FollowersId();
		followId.setUserId(userId);
		followId.setFollowId(followerId);
		
		Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
		if(followIdInfo.isPresent()) {
			FollowInfo dbfollowDetail= followIdInfo.get();
			followInfo.setFavorite(isFavorite);
			followInfo.setId(dbfollowDetail.getId());
			followInfo.setRequested(dbfollowDetail.isRequested());
			followInfo.setIgnored(dbfollowDetail.isIgnored());
			followInfo.setAccept(dbfollowDetail.isAccept());
			followInfo.setReject(dbfollowDetail.isReject());
			followRepo.save(followInfo);
			
			return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
		}else {
			followInfo.setId(followId);
			followInfo.setFavorite(isFavorite);
			followInfo.setUserId(userDetail);
			followInfo.setFollowId(followerDetail);
			followRepo.save(followInfo);

			return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
		}
	}

	public ResponseMessageDTO ignoreNotIgnoreRequest(long userId, long followerId, boolean isIgnored) {

		Optional<UserInfo> userInfo= userRepo.findById(userId);
		Optional<UserInfo> followerInfo= userRepo.findById(followerId);

		UserInfo userDetail = new UserInfo();
		UserInfo followerDetail = new UserInfo();

		if(userInfo.isPresent()) {
			userDetail = userInfo.get();
		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
		
		if(followerInfo.isPresent()) {
			followerDetail = followerInfo.get();
		}else {
			throw new ResourceNotFoundException("Follow user not exist.");
		}
		FollowInfo followInfo = new FollowInfo();

		FollowersId followId = new FollowersId();
		followId.setUserId(userId);
		followId.setFollowId(followerId);
		
		Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
		if(followIdInfo.isPresent()) {
			FollowInfo dbfollowDetail= followIdInfo.get();
			followInfo.setId(dbfollowDetail.getId());
			followInfo.setRequested(dbfollowDetail.isRequested());
			followInfo.setFavorite(dbfollowDetail.isFavorite());
			followInfo.setIgnored(isIgnored);
			followInfo.setAccept(dbfollowDetail.isAccept());
			followInfo.setReject(dbfollowDetail.isReject());
			followRepo.save(followInfo);
			return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));

		}else {
			followInfo.setId(followId);
			followInfo.setIgnored(isIgnored);
			followInfo.setUserId(userDetail);
			followInfo.setFollowId(followerDetail);
			followRepo.save(followInfo);

			return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
		}
	}

	public ResponseMessageDTO requestRejectRequest(long userId, long followerId, boolean isRequested) {

		Optional<UserInfo> userInfo= userRepo.findById(userId);
		Optional<UserInfo> followerInfo= userRepo.findById(followerId);

		UserInfo userDetail = new UserInfo();
		UserInfo followerDetail = new UserInfo();

		if(userInfo.isPresent()) {
			userDetail = userInfo.get();
		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
		
		if(followerInfo.isPresent()) {
			followerDetail = followerInfo.get();
		}else {
			throw new ResourceNotFoundException("Follow user not exist.");
		}
		FollowInfo followInfo = new FollowInfo();

		FollowersId followId = new FollowersId();
		followId.setUserId(userId);
		followId.setFollowId(followerId);
		
		Optional<FollowInfo> followIdInfo = followRepo.findById(followId);
		if(followIdInfo.isPresent()) {
			FollowInfo dbfollowDetail= followIdInfo.get();
			followInfo.setId(dbfollowDetail.getId());
			followInfo.setRequested(isRequested);
			followInfo.setFavorite(dbfollowDetail.isFavorite());
			followInfo.setIgnored(dbfollowDetail.isIgnored());
			followInfo.setAccept(dbfollowDetail.isAccept());
			followInfo.setReject(dbfollowDetail.isReject());
			followRepo.save(followInfo);
			if(isRequested == true) {
				return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
			}else {
				return new ResponseMessageDTO(true,new MessageDTO("Request Reject Successfully."));
			}
		}else {
			followInfo.setId(followId);
			followInfo.setRequested(isRequested);
			followInfo.setUserId(userDetail);
			followInfo.setFollowId(followerDetail);
			followRepo.save(followInfo);
			if(isRequested == true) {
				return new ResponseMessageDTO(true,new MessageDTO("Request Send Successfully."));
			}else {
				return new ResponseMessageDTO(true,new MessageDTO("Request Reject Successfully."));
			}
		}
	}

	public ResponseEntity<?> getReceivedRequestList(long userId) {
		RequestedListData requestData = new RequestedListData();
		List<NewMatchesResponse> newMatchesResponse = new ArrayList<>();
		List<FollowInfo> requestedList= followRepo.getReceivedRequestList(userId);
		for (FollowInfo followInfo : requestedList) {
			UserInfo followUser = followInfo.getUserId();
			NewMatchesResponse newMatchdata = new NewMatchesResponse();
			newMatchdata.setId(followUser.getId());
			newMatchdata.setFirstName(followUser.getFirstName());
			newMatchdata.setEmail(followUser.getEmail());
			newMatchdata.setPhone(followUser.getPhone());
			newMatchdata.setLastName(followUser.getLastName());
			newMatchdata.setDob(followUser.getDob());
			newMatchdata.setMotherTongueInfo(followUser.getMotherTongueInfo());
			newMatchdata.setHeight(followUser.getHeight());
			newMatchdata.setWeight(followUser.getWeight());
			newMatchdata.setMaritialStatus(followUser.getMaritialStatus());
			newMatchdata.setWorkingAt(followUser.getWorkingAt());
			newMatchdata.setAnnualIncome(followUser.getAnnualIncome());
			newMatchdata.setEducation(followUser.getEducation());
			newMatchdata.setReligion(followUser.getReligion());
			newMatchdata.setCaste(followUser.getCaste());
			newMatchdata.setSubCaste(followUser.getSubCaste());
			newMatchdata.setAboutYourself(followUser.getAboutYourself());
			newMatchdata.setExpectation(followUser.getExpectation());
			newMatchdata.setFatherName(followUser.getFatherName());
			newMatchdata.setFatherOccupation(followUser.getFatherOccupation());
			newMatchdata.setMotherName(followUser.getMotherName());
			newMatchdata.setMotherOccupation(followUser.getMotherOccupation());
			newMatchdata.setContactEmail(followUser.getContactEmail());
			newMatchdata.setContactMobile(followUser.getContactMobile());
			newMatchdata.setCountry(followUser.getCountry());
			newMatchdata.setState(followUser.getState());
			newMatchdata.setCity(followUser.getCity());
			newMatchdata.setAddress(followUser.getAddress());
			newMatchdata.setCreatedAt(followUser.getCreatedAt());
			newMatchdata.setAge(followUser.getAge());
			newMatchdata.setGender(followUser.getGender());
			newMatchdata.setPlanInfo(followUser.getPlanInfo());
			newMatchesResponse.add(newMatchdata);
		}
		
		requestData.setRequestedList(newMatchesResponse);
		return new ResponseEntity(requestData , HttpStatus.OK);
	}
}
