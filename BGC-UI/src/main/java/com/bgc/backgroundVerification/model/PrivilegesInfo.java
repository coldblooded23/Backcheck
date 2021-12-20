package com.bgc.backgroundVerification.model;

import java.util.HashSet;
import java.util.Set;


public class PrivilegesInfo {
	
private Long privilegeId;
	
private String privilegeName;
	

public PrivilegesInfo() { }


 
public PrivilegesInfo(Long privilegeId, String privilegeName) {
    this.privilegeId = privilegeId;
    this.privilegeName = privilegeName;
}

public PrivilegesInfo(Long privilegeId) {
    this.privilegeId = privilegeId;
  
}
 


public PrivilegesInfo(String privilegeName) {
    this.privilegeName = privilegeName;
    this.privilegeId= Long.parseLong(privilegeName);
}

public Long getPrivilegeId() {
	return privilegeId;
}

public void setPrivilegeId(Long privilegeId) {
	this.privilegeId = privilegeId;
}

public String getPrivilegeName() {
	return privilegeName;
}

public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
}








}
