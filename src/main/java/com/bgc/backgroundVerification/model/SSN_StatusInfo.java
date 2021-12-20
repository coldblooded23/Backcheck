package com.bgc.backgroundVerification.model;




public class SSN_StatusInfo {

	
	
	private Long statusId;
	
	private Long candidateId;
	
	private Long EmployeeId; 
	
	private Boolean Status;
	
	private CandidatesInfo candidatesInfo;

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(Long employeeId) {
		EmployeeId = employeeId;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public CandidatesInfo getCandidatesInfo() {
		return candidatesInfo;
	}

	public void setCandidatesInfo(CandidatesInfo candidatesInfo) {
		this.candidatesInfo = candidatesInfo;
	}	
	
	
	
}
