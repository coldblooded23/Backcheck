package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.adaptor.SetupAdaptor;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.model.SetupModel;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class SetupService {
	
	
	@Autowired
	SetupAdaptor setupAdaptor;
	
	@Autowired
	GetJwtTokenAdapter jwtTokenAdapter;
	
	
	public SetupModel getDetails(String id,String jwtToken) {
	
		try {
//		String jwtToken =jwtTokenAdapter.getJwtToken(id);
		
		SetupModel setupModel=setupAdaptor.getSetupDetails(jwtToken, id);
		return setupModel;
		
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
	}
	
	
	public ResponseInfo updateSetup(String id,SetupModel setupModel,String jwtToken) {
		ResponseEntity<String> response=null;
		JSONObject jsonObject=null;
		ResponseInfo info =new ResponseInfo();
		try {
//		String jwtToken =jwtTokenAdapter.getJwtToken(id);
		
		response=setupAdaptor.updateSetup(jwtToken, setupModel);
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		SetupModel setup=setupAdaptor.getSetupDetails(jwtToken, id);
		info.setAlertMessage(responseMessage);
		info.setMessage(message);
		info.setSetupModel(setup);
	return info;
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
		
	}
}
