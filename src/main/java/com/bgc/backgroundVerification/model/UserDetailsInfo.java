package com.bgc.backgroundVerification.model;

import java.util.Date;
import java.util.List;


public class UserDetailsInfo {

	private Long UserId;

	private Long CustomerId;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String Contact;

	private String vCode;

	private Boolean IsEmailVerified;

	private String Usertype;

	private Boolean Disabled;

	private Date CreatedOn;

	private String ModifiedBy;

	private Date ModifiedOn;
//Extras	
	private String oldPassword;

	private CustomerInfo customerInfo;
	
	
	private List<RolesInfo> rolesInfos;

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
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
	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}


	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	public Boolean getIsEmailVerified() {
		return IsEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		IsEmailVerified = isEmailVerified;
	}

	public String getUsertype() {
		return Usertype;
	}

	public void setUsertype(String usertype) {
		Usertype = usertype;
	}

	public Boolean getDisabled() {
		return Disabled;
	}

	public void setDisabled(Boolean disabled) {
		Disabled = disabled;
	}

	public Date getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}

	public String getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return ModifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		ModifiedOn = modifiedOn;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}


	//Extras
	
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public List<RolesInfo> getRolesInfos() {
		return rolesInfos;
	}

	public void setRolesInfos(List<RolesInfo> rolesInfos) {
		this.rolesInfos = rolesInfos;
	}	
	
	
	

}
