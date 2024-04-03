package com.truechoice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "user_details")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "dob")
	private LocalDate dob;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mother_tongue_id", nullable = true)
	private MotherTongueModel motherTongueInfo;
	
	@Column(name = "height")
	private String height;
	
	@Column(name = "weight")
	private String weight;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maritial_status_id", nullable = true)
	private MaritialStatusModel maritialStatus;

	@Column(name = "working_at")
	private String workingAt;
	
	@Column(name = "annual_income")
	private String annualIncome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "education_id", nullable = true)
	private EducationModel education;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "religion_id", nullable = true)
	private ReligionModel religion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caste_id", nullable = true)
	private Caste caste;
	
	@Column(name = "subcaste")// text
	private String subCaste;
	
	@Column(name = "about_yourself")
	private String aboutYourself;
	
	@Column(name = "expectation")
	private String expectation;
	
	@Column(name = "father_name")
	private String fatherName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father_occupation_id", nullable = true)
	private OccupationModel fatherOccupation;
	
	@Column(name = "mother_name")
	private String motherName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mother_occupation_id", nullable = true)
	private OccupationModel motherOccupation;
	
	@Column(name = "contact_email")
	private String contactEmail;
	
	@Column(name = "contact_mobile")
	private String contactMobile;
	
	@Column(name = "country")//dd
	private String country;
	
	@Column(name = "state")//dd
	private String state;
	
	@Column(name = "city")//dd
	private String city;
	
	@Column(name = "address")//state
	private String address;

	@Column(name = "created_at")//state
	private LocalDateTime createdAt;
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id", nullable = true)
	private PlanInfo planInfo;
	
	public UserInfo() {
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

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MotherTongueModel getMotherTongueInfo() {
		return motherTongueInfo;
	}

	public void setMotherTongueInfo(MotherTongueModel motherTongueInfo) {
		this.motherTongueInfo = motherTongueInfo;
	}

	public MaritialStatusModel getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(MaritialStatusModel maritialStatus) {
		this.maritialStatus = maritialStatus;
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

	public OccupationModel getFatherOccupation() {
		return fatherOccupation;
	}

	public void setFatherOccupation(OccupationModel fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}

	public OccupationModel getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(OccupationModel motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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
}
