package com.bgc.backgroundVerification.model;

public class Candidate_EducationDetailsInfo {

	private Long educationId;

	private Long candidateId;

	private Long customerId;

	private String degree;

	private String specialization;

	private String passingYear;

	private String universityName;

	private Boolean disabled;

	private Long createdBy;

	private Long lastChangedBy;

	private CandidatesInfo candidatesInfo;

	public Long getEducationId() {
		return educationId;
	}

	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getLastChangedBy() {
		return lastChangedBy;
	}

	public void setLastChangedBy(Long lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}

	public CandidatesInfo getCandidatesInfo() {
		return candidatesInfo;
	}

	public void setCandidatesInfo(CandidatesInfo candidatesInfo) {
		this.candidatesInfo = candidatesInfo;
	}
	
	
	

}
