package com.truechoice.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.truechoice.common.CommonMethods;
import com.truechoice.dto.LoginResponseDTO;
import com.truechoice.dto.SignUpDTO;
import com.truechoice.exceptionhandling.CustomException;
import com.truechoice.exceptionhandling.ResourceNotFoundException;
import com.truechoice.model.AuthRequest;
import com.truechoice.model.UserInfo;
import com.truechoice.repository.UserRepository;
import com.truechoice.requestresponseclass.UserRequest;

import jakarta.mail.MessagingException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommonMethods commonMethod;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MailService mailService;

	@Autowired
	private Environment env;

	public ResponseEntity<?> updatePassword(String newPassword,long id) throws CustomException {
		
		if (!commonMethod.isValidPassword(newPassword)) {
			throw new ResourceNotFoundException("Enter valid new password");
		}
		
		if (!commonMethod.isValidPassword(newPassword)) {
			throw new ResourceNotFoundException("Enter valid new password");
		}
		
		Optional<UserInfo> userInfo = userRepository.findById(id);
		if (userInfo.isPresent()) {
			UserInfo userData = userInfo.get();
			userData.setPassword(encoder.encode(newPassword));
			userRepository.save(userData);
			
			return new ResponseEntity("Password updated successfully." , HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
	}

	public void updateUserDetailByEmail(UserRequest userDetail) {
		Optional<UserInfo> userByMail = userRepository.findByEmail(userDetail.getEmail());
		if (userByMail.isPresent()) {
			UserInfo setUserDetail = userByMail.get();
			setUserDetail.setFirstName(userDetail.getFirstName());
			setUserDetail.setPhone(userDetail.getPhone());
			setUserDetail.setLastName(userDetail.getLastName());
			setUserDetail.setPassword(userDetail.getPassword());
			setUserDetail.setDob(userDetail.getDob());
			setUserDetail.setMotherTongueInfo(commonService.getMotherTongue(userDetail.getMotherTongue()));
			setUserDetail.setHeight(userDetail.getHeight());
			setUserDetail.setWeight(userDetail.getWeight());
			setUserDetail.setMaritialStatus(commonService.getMaritialStatus(userDetail.getMaritialStatus()));
			setUserDetail.setWorkingAt(userDetail.getWorkingAt());
			setUserDetail.setAnnualIncome(userDetail.getAnnualIncome());
			setUserDetail.setEducation(commonService.getEducation(userDetail.getEducation()));
			setUserDetail.setReligion(commonService.getReligion(userDetail.getReligion()));
			setUserDetail.setCaste(commonService.getCaste(userDetail.getCaste()));
			setUserDetail.setSubCaste(userDetail.getSubCaste());
			setUserDetail.setAboutYourself(userDetail.getAboutYourself());
			setUserDetail.setExpectation(userDetail.getExpectation());
			setUserDetail.setFatherName(userDetail.getFatherName());
			setUserDetail.setFatherOccupation(commonService.getOccupation(userDetail.getFatherOccupation()));
			setUserDetail.setMotherName(userDetail.getMotherName());
			setUserDetail.setMotherOccupation(commonService.getOccupation(userDetail.getMotherOccupation()));
			setUserDetail.setContactEmail(userDetail.getContactEmail());
			setUserDetail.setContactMobile(userDetail.getContactMobile());
			setUserDetail.setCountry(userDetail.getCountry());
			setUserDetail.setState(userDetail.getState());
			setUserDetail.setCity(userDetail.getCity());
			setUserDetail.setAddress(userDetail.getAddress());

			userRepository.save(setUserDetail);
		}
	}

	public void updateUserDetailByMobile(UserRequest userDetail) throws Exception {
		Optional<UserInfo> userByMobile = userRepository.findByPhone(userDetail.getPhone());
		if (userByMobile.isPresent()) {
			UserInfo setUserDetail = userByMobile.get();
			setUserDetail.setFirstName(userDetail.getFirstName());
			setUserDetail.setEmail(userDetail.getEmail());
			setUserDetail.setLastName(userDetail.getLastName());
			setUserDetail.setPassword(userDetail.getPassword());
			setUserDetail.setDob(userDetail.getDob());
			setUserDetail.setMotherTongueInfo(commonService.getMotherTongue(userDetail.getMotherTongue()));
			setUserDetail.setHeight(userDetail.getHeight());
			setUserDetail.setWeight(userDetail.getWeight());
			setUserDetail.setMaritialStatus(commonService.getMaritialStatus(userDetail.getMaritialStatus()));
			setUserDetail.setWorkingAt(userDetail.getWorkingAt());
			setUserDetail.setAnnualIncome(userDetail.getAnnualIncome());
			setUserDetail.setEducation(commonService.getEducation(userDetail.getEducation()));
			setUserDetail.setReligion(commonService.getReligion(userDetail.getReligion()));
			setUserDetail.setCaste(commonService.getCaste(userDetail.getCaste()));
			setUserDetail.setSubCaste(userDetail.getSubCaste());
			setUserDetail.setAboutYourself(userDetail.getAboutYourself());
			setUserDetail.setExpectation(userDetail.getExpectation());
			setUserDetail.setFatherName(userDetail.getFatherName());
			setUserDetail.setFatherOccupation(commonService.getOccupation(userDetail.getFatherOccupation()));
			setUserDetail.setMotherName(userDetail.getMotherName());
			setUserDetail.setMotherOccupation(commonService.getOccupation(userDetail.getMotherOccupation()));
			setUserDetail.setContactEmail(userDetail.getContactEmail());
			setUserDetail.setContactMobile(userDetail.getContactMobile());
			setUserDetail.setCountry(userDetail.getCountry());
			setUserDetail.setState(userDetail.getState());
			setUserDetail.setCity(userDetail.getCity());
			setUserDetail.setAddress(userDetail.getAddress());

			userRepository.save(setUserDetail);
		} else {
			throw new Exception("Please enter registerd email or mobile number");
		}
	}

	public ResponseEntity<?> registerUserDetails(UserRequest userDetail) throws Exception {
		if (!commonMethod.nullBlankCheck(userDetail.getEmail())) {
			throw new ResourceNotFoundException("Please Enter Email");
		}

		if (!commonMethod.nullBlankCheck(userDetail.getPhone())) {
			throw new ResourceNotFoundException("Please Enter Phone");
		}

		if (!commonMethod.nullBlankCheck(userDetail.getPassword())) {
			throw new ResourceNotFoundException("Please Enter Password");
		}

		if (commonMethod.nullBlankCheck(userDetail.getEmail())) {
			Optional<UserInfo> userByMail = userRepository.findByEmail(userDetail.getEmail());
			if (userByMail.isPresent()) {
				throw new ResourceNotFoundException("Email Already Exist");
			}
		}
		String regex = "\\d{10}";
		boolean result = userDetail.getPhone().matches(regex);
		if (result) {
			Optional<UserInfo> userByMobile = userRepository.findByPhone(userDetail.getPhone());
			if (userByMobile.isPresent()) {
				throw new ResourceNotFoundException("Mobile Already Exist");
			}
		}

		if (commonMethod.isValidPassword(userDetail.getPassword())) {
			userDetail.setPassword(encoder.encode(userDetail.getPassword()));
		} else {
			throw new Exception("Invalid Password");
		}

		UserInfo userInfo = saveUser(userDetail);
		SignUpDTO userRes = setResponse(userInfo);
		return new ResponseEntity<SignUpDTO>(userRes, HttpStatus.OK);
	}

	private UserInfo saveUser(UserRequest userData) {
		UserInfo response = new UserInfo();

		response.setFirstName(userData.getFirstName());
		response.setEmail(userData.getEmail());
		response.setPhone(userData.getPhone());
		response.setPassword(userData.getPassword());
		response.setLastName(userData.getLastName());
		response.setDob(userData.getDob());
		response.setMotherTongueInfo(commonService.getMotherTongue(userData.getMotherTongue()));
		response.setHeight(userData.getHeight());
		response.setWeight(userData.getWeight());
		response.setMaritialStatus(commonService.getMaritialStatus(userData.getMaritialStatus()));
		response.setWorkingAt(userData.getWorkingAt());
		response.setAnnualIncome(userData.getAnnualIncome());
		response.setEducation(commonService.getEducation(userData.getEducation()));
		response.setReligion(commonService.getReligion(userData.getReligion()));
		response.setCaste(commonService.getCaste(userData.getCaste()));
		response.setSubCaste(userData.getSubCaste());
		response.setAboutYourself(userData.getAboutYourself());
		response.setExpectation(userData.getExpectation());
		response.setFatherName(userData.getFatherName());
		response.setFatherOccupation(commonService.getOccupation(userData.getFatherOccupation()));
		response.setMotherName(userData.getMotherName());
		response.setMotherOccupation(commonService.getOccupation(userData.getMotherOccupation()));
		response.setContactEmail(userData.getContactEmail());
		response.setContactMobile(userData.getContactMobile());
		response.setCountry(userData.getCountry());
		response.setState(userData.getState());
		response.setCity(userData.getCity());
		response.setAddress(userData.getAddress());
		response.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
		response.setGender(userData.getGender());
		LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		response.setAge(Period.between(userData.getDob(), currentDate).getYears());
		userRepository.save(response);
		return response;
	}

	private SignUpDTO setResponse(UserInfo userData) {
		SignUpDTO response = new SignUpDTO();
		response.setId(userData.getId());
		response.setFirstName(userData.getFirstName());
		response.setEmail(userData.getEmail().toLowerCase());
		response.setPhone(userData.getPhone());
		response.setLastName(userData.getLastName());
		response.setDob(userData.getDob());
		response.setMotherTongueInfo(userData.getMotherTongueInfo());
		response.setHeight(userData.getHeight());
		response.setWeight(userData.getWeight());
		response.setMaritialStatus(userData.getMaritialStatus());
		response.setWorkingAt(userData.getWorkingAt());
		response.setAnnualIncome(userData.getAnnualIncome());
		response.setEducation(userData.getEducation());
		response.setReligion(userData.getReligion());
		response.setCaste(userData.getCaste());
		response.setSubCaste(userData.getSubCaste());
		response.setAboutYourself(userData.getAboutYourself());
		response.setExpectation(userData.getExpectation());
		response.setFatherName(userData.getFatherName());
		response.setFatherOccupation(userData.getFatherOccupation());
		response.setMotherName(userData.getMotherName());
		response.setMotherOccupation(userData.getMotherOccupation());
		response.setContactEmail(userData.getContactEmail().toLowerCase());
		response.setContactMobile(userData.getContactMobile());
		response.setCountry(userData.getCountry());
		response.setState(userData.getState());
		response.setCity(userData.getCity());
		response.setAddress(userData.getAddress());
		response.setCreatedAt(userData.getCreatedAt());
		response.setAge(userData.getAge());
		response.setGender(userData.getGender());
		response.setPlanInfo(userData.getPlanInfo());
		return response;
	}

	public ResponseEntity<?> loginUser(AuthRequest authRequest) throws Exception {

		if (!commonMethod.nullBlankCheck(authRequest.getUsername())) {
			throw new ResourceNotFoundException("Please enter username");
		}

		if (!commonMethod.nullBlankCheck(authRequest.getPassword())) {
			throw new ResourceNotFoundException("Please enter password");
		}
		LoginResponseDTO loginResponse = new LoginResponseDTO();

		if (authRequest.getUsername().contains("@")) {
			Optional<UserInfo> userByMail = userRepository.findByEmail(authRequest.getUsername().toLowerCase());
			if (userByMail.isPresent()) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getUsername().toLowerCase(), authRequest.getPassword()));
				if (authentication.isAuthenticated()) {
					String token = jwtService.generateToken(authRequest.getUsername().toLowerCase());
					UserInfo userInfo = userRepository.findByEmail(authRequest.getUsername().toLowerCase()).get();
					loginResponse.setId(userInfo.getId());
					loginResponse.setEmail(userInfo.getEmail());
					loginResponse.setPhone(userInfo.getPhone());
					loginResponse.setToken(token);
				} else {
					throw new UsernameNotFoundException("invalid user request !");
				}
			} else {
				throw new Exception("Email not exist.");
			}
		} else {
			String regex = "\\d{10}";
			boolean result = authRequest.getUsername().matches(regex);
			if (result) {
				Optional<UserInfo> userByMobile = userRepository.findByPhone(authRequest.getUsername());
				if (userByMobile.isPresent()) {
					Authentication authentication = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(userByMobile.get().getEmail(),
									authRequest.getPassword()));
					if (authentication.isAuthenticated()) {
						String token = jwtService.generateToken(userByMobile.get().getEmail().toLowerCase());
						UserInfo userInfo = userRepository.findByEmail(userByMobile.get().getEmail().toLowerCase()).get();
						loginResponse.setId(userInfo.getId());
						loginResponse.setEmail(userInfo.getEmail());
						loginResponse.setPhone(userInfo.getPhone());
						loginResponse.setToken(token);
					} else {
						throw new UsernameNotFoundException("invalid user request !");
					}
				} else {
					throw new Exception("Mobile not exist.");
				}
			} else {
				throw new ResourceNotFoundException("Enter valid mobile number.");
			}

		}
		return new ResponseEntity<LoginResponseDTO>(loginResponse, HttpStatus.OK);
	}

	public ResponseEntity<?> getUserById(long userId) throws Exception {
		Optional<UserInfo> userDetails = userRepository.findById(userId);
		if (userDetails.isPresent()) {
			UserInfo userInfo = userDetails.get();
			SignUpDTO userRes = setResponse(userInfo);
			return new ResponseEntity<SignUpDTO>(userRes, HttpStatus.OK);
		} else {
			throw new Exception("User not exist.");
		}
	}

	public ResponseEntity<?> verifyEmail(String email) throws MessagingException {
		if (!commonMethod.nullBlankCheck(email)) {
			throw new ResourceNotFoundException("Please enter email.");
		}

		Optional<UserInfo> userInfo = userRepository.findByEmail(email);
		if (userInfo.isPresent()) {
			char[] otp = commonMethod.generateOTP();
			mailService.sendTextMail(env.getProperty("spring.mail.username"), email, "Truechoice Registration OTP",
					"Your OTP for registration is : " + String.valueOf(otp), env.getProperty("rs.mail.cc.user"), null);

			UserInfo userData = userInfo.get();
			userData.setOtp(String.valueOf(otp));
			userRepository.save(userData);
			return new ResponseEntity("OTP mail send successfully", HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Email not found.");
		}
	}

	public ResponseEntity<?> verifyOTP(String otp , long id) {
		
		Optional<UserInfo> userInfo = userRepository.findById(id);
		if(userInfo.isPresent()) {
			UserInfo userDetail = userInfo.get();
			if(otp.equals(userDetail.getOtp())) {
				return new ResponseEntity("OTP match successfully", HttpStatus.OK);
			}else {
				throw new ResourceNotFoundException("Otp is not match.");
			}
		}else {
			throw new ResourceNotFoundException("User not exist.");
		}
	}

	public List<UserInfo> getUserList() {
		return userRepository.findAll();
	}

	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

	public void checkUser(String email, String mobile) throws Exception {
		List<UserInfo> isUserAvailable= userRepository.checkUser(email, mobile);
		if(isUserAvailable.size() >= 1) {
			throw new Exception("Email and Mobile already registerd in our system. Try with another email and mobile number.");
		}
		
		Optional<UserInfo> userByMail = userRepository.findByEmail(email);
		if (userByMail.isPresent()) {
			throw new Exception("Email already registerd in our system. Try with another email.");
		}

		Optional<UserInfo> userByMobile = userRepository.findByPhone(mobile);
		if (userByMobile.isPresent()) {
			throw new Exception("Mobile already registerd in our system. Try with another mobile number.");
		}
	}
}
