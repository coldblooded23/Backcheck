package com.bgc.backgroundVerification.adaptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;

@Component
public class CallLoginUserDetails {
	
	@Autowired
	RestTemplate restTemplate;
		
	public UserDetailsInfo getUserDetails(String email){
		ResponseEntity<UserDetailsInfo> userinfo = null;
		try {
			//String url="https://bgcwebservices.azurewebsites.net/login/userdetails/"+email;
			String url=AllUrls.GetLoginUserDetails+email;
				 userinfo=restTemplate.exchange(url, HttpMethod.GET, null, UserDetailsInfo.class);
			UserDetailsInfo userDetailsInfo = new UserDetailsInfo();
			userDetailsInfo.setEmail(userinfo.getBody().getEmail());
			userDetailsInfo.setFirstName(userinfo.getBody().getFirstName());
			userDetailsInfo.setLastName(userinfo.getBody().getLastName());
			userDetailsInfo.setPassword(userinfo.getBody().getPassword());
			userDetailsInfo.setIsEmailVerified(userinfo.getBody().getIsEmailVerified());
			userDetailsInfo.setDisabled(userinfo.getBody().getDisabled());
			userDetailsInfo.setUsertype(userinfo.getBody().getUsertype());
			return userDetailsInfo;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BgcUIException("exception occered while accessing the data");
		}
	
	}
	
	
 public CandidatesInfo getCandidateInfo(String name) {
	 ResponseEntity<CandidatesInfo> info = null;
		try {
			//String url="https://bgcwebservices.azurewebsites.net/login/userdetails/"+name;
			String url=AllUrls.GetLoginCandidateDetails+name;
			info=restTemplate.exchange(url, HttpMethod.GET, null, CandidatesInfo.class);
			CandidatesInfo candidatesInfo = new CandidatesInfo();
			candidatesInfo.setDefaultUserName(info.getBody().getDefaultUserName());
			 System.out.println(info.getBody().getDefaultUserName());
			candidatesInfo.setDefaultPassword(info.getBody().getDefaultPassword());
			candidatesInfo.setDisabled(info.getBody().getDisabled());
	return candidatesInfo;
	 
 }catch (Exception e) {
	 System.out.println(e.getMessage());
		throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
}
 }
 
 
 public EmployeeInfo getEmployeeInfo(String email) {
	 ResponseEntity<EmployeeInfo> info=null;
	 try {
		 String url=AllUrls.url+"/login/employeedetails/"+email;
		 info=restTemplate.exchange(url, HttpMethod.GET, null,EmployeeInfo.class);
		 EmployeeInfo employeeInfo = new EmployeeInfo();
		 employeeInfo.setEmail(info.getBody().getEmail());
		 employeeInfo.setPassword(info.getBody().getPassword());
		 employeeInfo.setDisabled(info.getBody().getDisabled());
		 employeeInfo.setRolesInfos(info.getBody().getRolesInfos());
		 System.out.println(info.getBody());
		 return employeeInfo;
		 
	 }catch (Exception e) {
		// TODO: handle exception
		 throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
	}

	 
 }
 
}
