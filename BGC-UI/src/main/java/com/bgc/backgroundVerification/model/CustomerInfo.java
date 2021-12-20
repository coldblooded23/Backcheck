package com.bgc.backgroundVerification.model;

import java.util.List;

public class CustomerInfo {

	private Long customerId;

	private String companyName;

	private Boolean Disabled;
	
	private UserDetailsInfo userDetailsInfo;

	private CompanyProfileInfo companyProfileInfo;
	
	private List<EmployeeInfo> employeeInfo;

	private List<CandidatesInfo> candidatesInfo;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getDisabled() {
		return Disabled;
	}

	public void setDisabled(Boolean disabled) {
		Disabled = disabled;
	}

	
	public UserDetailsInfo getUserDetailsInfo() {
		return userDetailsInfo;
	}

	public void setUserDetailsInfo(UserDetailsInfo userDetailsInfo) {
		this.userDetailsInfo = userDetailsInfo;
	}

	public CompanyProfileInfo getCompanyProfileInfo() {
		return companyProfileInfo;
	}

	public void setCompanyProfileInfo(CompanyProfileInfo companyProfileInfo) {
		this.companyProfileInfo = companyProfileInfo;
	}

	public List<EmployeeInfo> getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(List<EmployeeInfo> employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public List<CandidatesInfo> getCandidatesInfo() {
		return candidatesInfo;
	}

	public void setCandidatesInfo(List<CandidatesInfo> candidatesInfo) {
		this.candidatesInfo = candidatesInfo;
	}

	
	
	
	
	

}
