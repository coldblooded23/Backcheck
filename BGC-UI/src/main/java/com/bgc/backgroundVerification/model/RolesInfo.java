package com.bgc.backgroundVerification.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RolesInfo {

	private Long rolesId;

	private Long customerId;

	private Long employeeId;

	private String roleName;

	private UserDetailsInfo UserDetailsInfo;

	private List<String> privaleges;

	private Set<PrivilegesInfo> privilegesInfos = new HashSet<>();

	private Set<EmployeeInfo> employeeInfos = new HashSet<EmployeeInfo>();

	public Long getRolesId() {
		return rolesId;
	}

	public void setRolesId(Long rolesId) {
		this.rolesId = rolesId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserDetailsInfo getUserDetailsInfo() {
		return UserDetailsInfo;
	}

	public void setUserDetailsInfo(UserDetailsInfo userDetailsInfo) {
		UserDetailsInfo = userDetailsInfo;
	}

	public List<String> getPrivaleges() {
		return privaleges;
	}

	public void setPrivaleges(List<String> privaleges) {
		this.privaleges = privaleges;
	}

	public Set<PrivilegesInfo> getPrivilegesInfos() {
		return privilegesInfos;
	}

	public void setPrivilegesInfos(Set<PrivilegesInfo> privilegesInfos) {
		this.privilegesInfos = privilegesInfos;
	}

}
