package com.bgc.backgroundVerification.model;

import java.util.Date;

public class SetupModel {
	
	private Long setupId;
	

	private Long customerId;
	

	private String emailId;
	

	private String password;
	

	private String Port;
	
	
	private String host;
	

	private Date createdOn;
	

	private Date updatedOn;
	

	private CustomerInfo CustomerInfo;


	public Long getSetupId() {
		return setupId;
	}


	public void setSetupId(Long setupId) {
		this.setupId = setupId;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPort() {
		return Port;
	}


	public void setPort(String port) {
		Port = port;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Date getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}


	public CustomerInfo getCustomerInfo() {
		return CustomerInfo;
	}


	public void setCustomerInfo(CustomerInfo customerInfo) {
		CustomerInfo = customerInfo;
	}


	
	
	

}
