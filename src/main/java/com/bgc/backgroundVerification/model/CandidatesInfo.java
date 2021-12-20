package com.bgc.backgroundVerification.model;

import java.util.List;


public class CandidatesInfo {
	
	private Long candidateId;
	
	private Long customerId;
		
	private String firstName;
	
	private String lastName;
	
	private String dOB;
		
	private String email;
	
	private String phone;
	
	private byte[] resume;
		
	private String filetype;
		
	private String defaultUserName;
		
	private String defaultPassword;
	
	private String vCode;
	
	private String docName;
	
	private Boolean disabled;
	
	private String mdate;
	
	private String oldPassword;
	
    private CustomerInfo customerInfo;
    
    private Candidate_GeneralInfo candidate_GeneralInfo;
    
    private List<Candidate_ExperienceInfo> candidate_ExperienceInfo;
    

    private List<Candidate_EducationDetailsInfo> candidate_EducationDetailsInfo;
    
    private List<Candidate_DocumentInfo> candidate_DocumentInfo;
    
    private SSN_StatusInfo ssn_StatusInfo;

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

	public String getdOB() {
		return dOB;
	}

	public void setdOB(String dOB) {
		this.dOB = dOB;
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

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getDefaultUserName() {
		return defaultUserName;
	}

	public void setDefaultUserName(String defaultUserName) {
		this.defaultUserName = defaultUserName;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}


	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Candidate_GeneralInfo getCandidate_GeneralInfo() {
		return candidate_GeneralInfo;
	}

	public void setCandidate_GeneralInfo(Candidate_GeneralInfo candidate_GeneralInfo) {
		this.candidate_GeneralInfo = candidate_GeneralInfo;
	}

	public List<Candidate_EducationDetailsInfo> getCandidate_EducationDetailsInfo() {
		return candidate_EducationDetailsInfo;
	}

	public void setCandidate_EducationDetailsInfo(List<Candidate_EducationDetailsInfo> candidate_EducationDetailsInfo) {
		this.candidate_EducationDetailsInfo = candidate_EducationDetailsInfo;
	}

	public List<Candidate_ExperienceInfo> getCandidate_ExperienceInfo() {
		return candidate_ExperienceInfo;
	}

	public void setCandidate_ExperienceInfo(List<Candidate_ExperienceInfo> candidate_ExperienceInfo) {
		this.candidate_ExperienceInfo = candidate_ExperienceInfo;
	}

	public List<Candidate_DocumentInfo> getCandidate_DocumentInfo() {
		return candidate_DocumentInfo;
	}

	public void setCandidate_DocumentInfo(List<Candidate_DocumentInfo> candidate_DocumentInfo) {
		this.candidate_DocumentInfo = candidate_DocumentInfo;
	}

	public SSN_StatusInfo getSsn_StatusInfo() {
		return ssn_StatusInfo;
	}

	public void setSsn_StatusInfo(SSN_StatusInfo ssn_StatusInfo) {
		this.ssn_StatusInfo = ssn_StatusInfo;
	}
	
	

	
	
	
    

}
