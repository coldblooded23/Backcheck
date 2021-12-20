package com.bgc.backgroundVerification.model;

public class SsnInfo {

	private String Email;

	private Long candidateId;

	private String sSN;

	public SsnInfo() {
		// TODO Auto-generated constructor stub
	}

	public SsnInfo(String sSN) {
		super();
		this.sSN = sSN;
	}
	
	

	public SsnInfo(String email, Long candidateId, String sSN) {
		super();
		Email = email;
		this.candidateId = candidateId;
		this.sSN = sSN;
	}

	public String getsSN() {
		return sSN;
	}

	public void setsSN(String sSN) {
		this.sSN = sSN;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	

}
