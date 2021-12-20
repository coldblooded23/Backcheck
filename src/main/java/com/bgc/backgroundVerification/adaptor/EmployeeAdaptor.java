package com.bgc.backgroundVerification.adaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.util.AllUrls;
import com.bgc.backgroundVerification.util.BgcUIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class EmployeeAdaptor {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	

	
	
	public CustomerInfo getEmployeeDetails(String id,String jwtToken) {

		ResponseEntity<CustomerInfo> response=null;
		CustomerInfo customerInfo = new CustomerInfo();
		String url=AllUrls.GetAllEmployees+id;
		//String url="http://localhost:9091/getListOfEmployees/"+id;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
		
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			response=restTemplate.exchange(url, HttpMethod.GET, entity, CustomerInfo.class);
			
			if(response!=null)
			{
				customerInfo.setCustomerId(response.getBody().getCustomerId());
				List<EmployeeInfo> noOFEmployee =new ArrayList<EmployeeInfo>();
				Iterable<EmployeeInfo> info=response.getBody().getEmployeeInfo();
				for(EmployeeInfo employee:info) {
					EmployeeInfo employeeInfo = new EmployeeInfo();
					employeeInfo.setEmployeeId(employee.getEmployeeId());
					employeeInfo.setFirstName(employee.getFirstName());
					employeeInfo.setLastName(employee.getLastName() );
					employeeInfo.setEmail(employee.getEmail());
					employeeInfo.setDesignation(employee.getDesignation());
					employeeInfo.setGender(employee.getGender()); 
					String date1=employee.getdOB();
					employeeInfo.setdOB(date1.substring(0, 10));
					employeeInfo.setQualification(employee.getQualification());
					employeeInfo.setSecurityProfile(employee.getSecurityProfile());
					employeeInfo.setPhone(employee.getPhone());
					employeeInfo.setAddress(employee.getAddress());
					employeeInfo.setState(employee.getState());
					employeeInfo.setMaritalStatus(employee.getMaritalStatus());
					employeeInfo.setCountry(employee.getCountry());
					employeeInfo.setZipCode(employee.getZipCode());
					noOFEmployee.add(employeeInfo);
				}
				customerInfo.setEmployeeInfo(noOFEmployee);
			}
			
		}catch (Exception e) {
			throw new BgcUIException("exeception occered while accessing the data"+e.getMessage());
		}
		return customerInfo;
		
	}
	
	
	
	public ResponseEntity<String> addEmployee(EmployeeInfo employeeInfo,String jwtToken){
		//String url="http://localhost:9091/addEmployee";
		String url=AllUrls.AddEmployees;
		ResponseEntity<String> response=null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
			ObjectWriter objectWriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json=objectWriter.writeValueAsString(employeeInfo);
		
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		
		response=restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
			
		}catch (Exception e) {
			throw new BgcUIException("exeception occered while accessing the data"+e.getMessage());
		}
		return response;
		
	}
	
	
	
	public EmployeeInfo getemployeeDetailsforUpdate(String id,String jwtToken) {
		
		EmployeeInfo employeeInfo=new EmployeeInfo();
		String url=AllUrls.GetEmployeeDetails+id;
		//String url="http://localhost:9091/getEmployeeDetails/"+id;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Accept", "application/json");
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer "+jwtToken);
			
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<EmployeeInfo> response=restTemplate.exchange(url, HttpMethod.GET, entity, EmployeeInfo.class);
			employeeInfo.setEmployeeId(response.getBody().getEmployeeId());
			employeeInfo.setCustomerId(response.getBody().getCustomerId());
			employeeInfo.setFirstName(response.getBody().getFirstName());
			employeeInfo.setLastName(response.getBody().getLastName() );
			employeeInfo.setEmail(response.getBody().getEmail());
			employeeInfo.setDesignation(response.getBody().getDesignation());
			employeeInfo.setGender(response.getBody().getGender()); 
			String date1=response.getBody().getdOB();
			
			employeeInfo.setdOB(date1.substring(0, 10));

			employeeInfo.setMdate(date1.substring(0, 10));
			employeeInfo.setQualification(response.getBody().getQualification());
			employeeInfo.setSecurityProfile(response.getBody().getSecurityProfile());
			employeeInfo.setPhone(response.getBody().getPhone());
			employeeInfo.setAddress(response.getBody().getAddress());
			employeeInfo.setState(response.getBody().getState());
			employeeInfo.setMaritalStatus(response.getBody().getMaritalStatus());
			employeeInfo.setCountry(response.getBody().getCountry());
			employeeInfo.setZipCode(response.getBody().getZipCode());
			employeeInfo.setRolessId(response.getBody().getRolessId());
			System.out.println(response.getBody().getCustomerId());
		}catch (Exception e) {
		throw new BgcUIException("exeception occered while accessing the data"+e.getMessage());
		}
		return employeeInfo;
	
	}





public ResponseEntity<String> updateEmployee( EmployeeInfo employeeInfo,String jwtToken) {
	ResponseEntity<String> response=null;
	//String url="http://localhost:9091/updateEmployee";
	String url=AllUrls.UpdateEmployees;
	try {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer "+jwtToken);
		
		ObjectWriter ow= new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json =ow.writeValueAsString(employeeInfo);
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		response=restTemplate.exchange(url, HttpMethod.POST, entity, String.class);	
		return response;	
	}catch (Exception e) {
		throw new BgcUIException("exeception occered while accessing the data"+e.getMessage());
	
	}

}

public ResponseEntity<String> deleteEmployee( String id ,String jwtToken) {
	ResponseEntity<String> response=null;
	//System.out.println(id);
	//String url="http://localhost:9091/deleteEmployee/"+id;
	String url=AllUrls.DEleteEmployees+id;
	try {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer "+jwtToken);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		response=restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);	
		return response;	
	}catch (Exception e) {
		throw new BgcUIException("exeception occered while accessing the data"+e.getMessage());
	
	}

}


}