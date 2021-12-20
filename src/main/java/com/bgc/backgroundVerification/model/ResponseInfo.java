package com.bgc.backgroundVerification.model;

public class ResponseInfo {
	
	
	
	private String alertMessage;
	
	private SetupModel setupModel;

	private String message;
	
	private String emailId;
	
	private CandidatesInfo candidatesInfo;
	
	private CompanyProfileInfo companyProfileInfo;
	
	private CustomerInfo customerInfo;
	


	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public CompanyProfileInfo getCompanyProfileInfo() {
		return companyProfileInfo;
	}

	public void setCompanyProfileInfo(CompanyProfileInfo companyProfileInfo) {
		this.companyProfileInfo = companyProfileInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SetupModel getSetupModel() {
		return setupModel;
	}

	public void setSetupModel(SetupModel setupModel) {
		this.setupModel = setupModel;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public CandidatesInfo getCandidatesInfo() {
		return candidatesInfo;
	}

	public void setCandidatesInfo(CandidatesInfo candidatesInfo) {
		this.candidatesInfo = candidatesInfo;
	}
	
	
	
	

}
