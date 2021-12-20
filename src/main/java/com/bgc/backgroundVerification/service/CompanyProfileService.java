package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.CompanyProfileAdaptor;
import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.model.CompanyProfileInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class CompanyProfileService {
	
	
	@Autowired
	CompanyProfileAdaptor companyProfileAdaptor;
	
	@Autowired
	GetJwtTokenAdapter getJwtToken;
	
	public CompanyProfileInfo getAprofile(String id,String jwtToken) {
		try {
//		String jwtToken =getJwtToken.getJwtToken(id);
		
		CompanyProfileInfo componyInfo = companyProfileAdaptor.getAProfile(jwtToken, id);
		return componyInfo;
		}catch (Exception e) {
			throw new BgcUIException("exeception occered while accessing the data"+e.getMessage());
			}
	
	}
	
	
	public ResponseInfo updateProfile(CompanyProfileInfo companyProfileInfo,String id,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseEntity<String> response=null;
		ResponseInfo info =new ResponseInfo();
		try {
//			String jwtToken =getJwtToken.getJwtToken(id);
			response =companyProfileAdaptor.updateProfile(companyProfileInfo, jwtToken);
			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");
		
			CompanyProfileInfo componyInfo = companyProfileAdaptor.getAProfile(jwtToken, id);
			info.setAlertMessage(responseMessage);
			info.setMessage(message);
			info.setCompanyProfileInfo(componyInfo);	
		return info;	
	}catch (Exception e) {
		throw new BgcUIException("exeception occered while accessing the data"+e.getMessage());
		
	}
	}

}
