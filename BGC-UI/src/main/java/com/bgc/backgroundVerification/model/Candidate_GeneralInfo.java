package com.bgc.backgroundVerification.model;



public class Candidate_GeneralInfo {

	private Long generalInfoId;

	private Long candidateId;

	private String gender;

	private String maritalStatus;

	private String address;

	private String city;

	private String state;

	private String country;

	private String pincode;
	
	private String ssn;

	private CandidatesInfo candidatesInfo;

	public Long getGeneralInfoId() {
		return generalInfoId;
	}

	public void setGeneralInfoId(Long generalInfoId) {
		this.generalInfoId = generalInfoId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public CandidatesInfo getCandidatesInfo() {
		return candidatesInfo;
	}

	public void setCandidatesInfo(CandidatesInfo candidatesInfo) {
		this.candidatesInfo = candidatesInfo;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	
	

 }
