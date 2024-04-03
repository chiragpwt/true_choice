package com.truechoice.requestresponseclass;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.truechoice.model.Caste;
import com.truechoice.model.EducationModel;
import com.truechoice.model.MaritialStatusModel;
import com.truechoice.model.MotherTongueModel;
import com.truechoice.model.OccupationModel;
import com.truechoice.model.PlanInfo;
import com.truechoice.model.ReligionModel;

public class NewMatchesResponse {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private LocalDate dob;
	private MotherTongueModel motherTongueInfo;
	private String height;
	private String weight;
	private MaritialStatusModel maritialStatus;
	private String workingAt;
	private String annualIncome;
	private EducationModel education;
	private ReligionModel religion;
	private Caste caste;
	private String subCaste;
	private String aboutYourself;
	private String expectation;
	private String fatherName;
	private OccupationModel fatherOccupation;
	private String motherName;
	private OccupationModel motherOccupation;
	private String contactEmail;
	private String contactMobile;
	private String country;
	private String state;
	private String city;
	private String address;
	private LocalDateTime createdAt;
	private Integer age;
	private String gender;
	private PlanInfo planInfo;
	private Boolean isIgnore;
	private Boolean isFavorite;
	private Boolean isReuest;
	private Boolean isAccepted;
	
	public NewMatchesResponse() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public MotherTongueModel getMotherTongueInfo() {
		return motherTongueInfo;
	}
	public void setMotherTongueInfo(MotherTongueModel motherTongueInfo) {
		this.motherTongueInfo = motherTongueInfo;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public MaritialStatusModel getMaritialStatus() {
		return maritialStatus;
	}
	public void setMaritialStatus(MaritialStatusModel maritialStatus) {
		this.maritialStatus = maritialStatus;
	}
	public String getWorkingAt() {
		return workingAt;
	}
	public void setWorkingAt(String workingAt) {
		this.workingAt = workingAt;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public EducationModel getEducation() {
		return education;
	}
	public void setEducation(EducationModel education) {
		this.education = education;
	}
	public ReligionModel getReligion() {
		return religion;
	}
	public void setReligion(ReligionModel religion) {
		this.religion = religion;
	}
	public Caste getCaste() {
		return caste;
	}
	public void setCaste(Caste caste) {
		this.caste = caste;
	}
	public String getSubCaste() {
		return subCaste;
	}
	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}
	public String getAboutYourself() {
		return aboutYourself;
	}
	public void setAboutYourself(String aboutYourself) {
		this.aboutYourself = aboutYourself;
	}
	public String getExpectation() {
		return expectation;
	}
	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public OccupationModel getFatherOccupation() {
		return fatherOccupation;
	}
	public void setFatherOccupation(OccupationModel fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public OccupationModel getMotherOccupation() {
		return motherOccupation;
	}
	public void setMotherOccupation(OccupationModel motherOccupation) {
		this.motherOccupation = motherOccupation;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public PlanInfo getPlanInfo() {
		return planInfo;
	}
	public void setPlanInfo(PlanInfo planInfo) {
		this.planInfo = planInfo;
	}

	public Boolean getIsIgnore() {
		return isIgnore;
	}

	public void setIsIgnore(Boolean isIgnore) {
		this.isIgnore = isIgnore;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Boolean getIsReuest() {
		return isReuest;
	}

	public void setIsReuest(Boolean isReuest) {
		this.isReuest = isReuest;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
}
