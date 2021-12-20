package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.EmployeeAdaptor;
import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class EmployeeService {

	@Autowired
	EmployeeAdaptor employeeAdaptor;

	@Autowired
	GetJwtTokenAdapter getJwtTokenAdapter;

	public CustomerInfo getListOfEmployees(String Id,String jwtToken) {
		try {
//			String jwtToken = getJwtTokenAdapter.getJwtToken(Id);

			CustomerInfo customerInfo = employeeAdaptor.getEmployeeDetails(Id, jwtToken);

			return customerInfo;

		} catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data" + e.getMessage());
		}
	}

	public ResponseInfo addEmployee(EmployeeInfo employeeInfo, String Email,String jwtToken) {
	
//		 String jwtToken=getJwtTokenAdapter.getJwtToken(Email);
		ResponseInfo responseInfo = new ResponseInfo();
	
		JSONObject jsonObject = null;
		try {
			
			  ResponseEntity<String> response =employeeAdaptor.addEmployee(employeeInfo, jwtToken);
			  jsonObject = new JSONObject(response.getBody()); 
			  String responsecode = (String) jsonObject.get("responseCode"); 
			  String responseMessage = (String) jsonObject.get("responseMessage"); 
			  String message  = (String) jsonObject.get("response");
			  
			  CustomerInfo customerInfo =employeeAdaptor.getEmployeeDetails(Email, jwtToken);
			  responseInfo.setAlertMessage(message );
			  responseInfo.setMessage(responseMessage);
			  responseInfo.setCustomerInfo(customerInfo);
			 

		} catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data" + e.getMessage());
		}
		return responseInfo;

	}
	
	public EmployeeInfo getEmployeeDetailsForUpdate(String id ,String jwtToken) {
//		String jwtToken=getJwtTokenAdapter.getJwtToken(email);
		try {	
		EmployeeInfo employee=employeeAdaptor.getemployeeDetailsforUpdate(id, jwtToken);

		return employee;
		}catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data" + e.getMessage());
			
		}
	}
	
	
	

	public ResponseInfo updateEmployee(EmployeeInfo employeeInfo, String Email,String jwtToken) {
//		 String jwtToken=getJwtTokenAdapter.getJwtToken(Email);
		ResponseInfo responseInfo = new ResponseInfo();
		JSONObject jsonObject = null;
		try {
			
			  ResponseEntity<String> response =employeeAdaptor.updateEmployee(employeeInfo, jwtToken);
			  jsonObject = new JSONObject(response.getBody()); 
			  String responsecode = (String) jsonObject.get("responseCode"); 
			  String responseMessage = (String) jsonObject.get("responseMessage"); 
			  String message  = (String) jsonObject.get("response");
			  
			  System.out.println(responsecode+"    "+responseMessage+"   "+message);
			  
			  CustomerInfo customerInfo =employeeAdaptor.getEmployeeDetails(Email, jwtToken);
			  responseInfo.setAlertMessage(message );
			  responseInfo.setMessage(responseMessage);
			  responseInfo.setCustomerInfo(customerInfo);
			 

		} catch (Exception e) {
			throw new BgcUIException("Exeception occered while accessing the data" + e.getMessage());
		}
		return responseInfo;

	}
	
	
	public ResponseInfo deleteEmployee(String  id, String Email,String jwtToken) {
//		 String jwtToken=getJwtTokenAdapter.getJwtToken(Email);
		 ResponseInfo responseInfo= new ResponseInfo();
			JSONObject jsonObject = null;
			try {
				
				  ResponseEntity<String> response =employeeAdaptor.deleteEmployee(id, jwtToken);
				  jsonObject = new JSONObject(response.getBody()); 
				  String responsecode = (String) jsonObject.get("responseCode"); 
				  String responseMessage = (String) jsonObject.get("responseMessage"); 
				  String message  = (String) jsonObject.get("response");
				  
				  System.out.println(responsecode+"    "+responseMessage+"   "+message);
				  
				  CustomerInfo customerInfo =employeeAdaptor.getEmployeeDetails(Email, jwtToken);
				  responseInfo.setAlertMessage(message );
				  responseInfo.setMessage(responseMessage);
				  responseInfo.setCustomerInfo(customerInfo);
				 

			} catch (Exception e) {
				throw new BgcUIException("Exeception occered while accessing the data" + e.getMessage());
			}
			return responseInfo;
	}

}
