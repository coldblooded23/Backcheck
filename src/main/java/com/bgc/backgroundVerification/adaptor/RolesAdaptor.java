package com.bgc.backgroundVerification.adaptor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bgc.backgroundVerification.model.PrivilegesInfo;
import com.bgc.backgroundVerification.model.RolesInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class RolesAdaptor {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public UserDetailsInfo getRolesByEmail(String email,String jwtToken) {
		
		String url=AllUrls.url+"/roles/getRolesByEmail/"+email;
		UserDetailsInfo userDetailsInfo = new UserDetailsInfo();
		
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(headers);	
			System.out.println(email);
	ResponseEntity<UserDetailsInfo>	responce =restTemplate.exchange(url, HttpMethod.GET, entity, UserDetailsInfo.class); 
			
	userDetailsInfo.setCustomerId(responce.getBody().getCustomerId());
	System.out.println(responce.getBody().getCustomerId());
	
	System.out.println(responce.getBody().getRolesInfos());
	userDetailsInfo.setRolesInfos(responce.getBody().getRolesInfos());

	
	return userDetailsInfo;
	
		}catch (Exception e) {
		throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	
		
	}
	
	
	public ResponseEntity<String> addARoleWithPrivilages(RolesInfo roles,String jwtToken){
		String url=AllUrls.url+"/roles/addRoles";
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
		
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(roles);
			HttpEntity<String> entity = new HttpEntity<>(json, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
	}
	
	public ResponseEntity<String> updateRoleWithPrivilages(RolesInfo roles,String jwtToken){
		String url=AllUrls.url+"/roles/updateRoles";
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
		
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(roles);
			HttpEntity<String> entity = new HttpEntity<>(json, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
	}
	
	public ResponseEntity<String> deleteRole(String id,String jwtToken){
		String url=AllUrls.url+"/roles/deleteRoles/"+id;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
		
			HttpEntity<String> entity = new HttpEntity<>( headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

			return response;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
	}
	
	
	public RolesInfo getRolesById(Long id,String JwtToken) {
		String url=AllUrls.url+"/roles/getRoleById/"+id;
		RolesInfo roles= new RolesInfo();
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(headers);	
			ResponseEntity<RolesInfo> responce =restTemplate.exchange(url, HttpMethod.GET, entity,RolesInfo.class ); 
			roles.setCustomerId(responce.getBody().getCustomerId());
			roles.setRolesId(responce.getBody().getRolesId());
			roles.setRoleName(responce.getBody().getRoleName());
			System.out.println(responce.getBody().getPrivilegesInfos());
			List<String> privilInfos = new ArrayList<String>();
			Set<PrivilegesInfo> priv=responce.getBody().getPrivilegesInfos();
			for(PrivilegesInfo privileges:priv) {
				System.out.println(privileges.getPrivilegeId().toString());
				privilInfos.add(privileges.getPrivilegeId().toString());
				
			}
			
			roles.setPrivaleges(privilInfos);
			return roles;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
			
		}
		
		
		
		

		
	}
	
	
	public List<PrivilegesInfo> privilages(String jwtToken){
		String url=AllUrls.url+"/roles/getAllPrivilages";
	//	List<PrivilegesInfo> privilageInfos = new ArrayList<PrivilegesInfo>();
	
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(headers);	
			
	ResponseEntity<List<PrivilegesInfo>>	responce =restTemplate.exchange(url, HttpMethod.GET, entity,new ParameterizedTypeReference<List<PrivilegesInfo>>() {}); 
			
	List<PrivilegesInfo> privilageInfos =responce.getBody();
	
	return privilageInfos;
	
		}catch (Exception e) {
		throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	
	}
	

	public List<RolesInfo> getRolesByCustomerId(Long id,String jwtToken){
		String url=AllUrls.url+"/roles/getRolesByCustomerId/"+id;
	//	List<PrivilegesInfo> privilageInfos = new ArrayList<PrivilegesInfo>();
	
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(headers);	
			
	ResponseEntity<List<RolesInfo>>	responce =restTemplate.exchange(url, HttpMethod.GET, entity,new ParameterizedTypeReference<List<RolesInfo>>() {}); 
			
	List<RolesInfo> rolesInfo =responce.getBody();
	
	return rolesInfo;
	
		}catch (Exception e) {
		throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	
	}

}
