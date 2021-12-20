package com.bgc.backgroundVerification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.GetJwtTokenAdapter;
import com.bgc.backgroundVerification.adaptor.PasswordAdaptor;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

import ch.qos.logback.core.util.SystemInfo;

@Service
public class PasswordService {
	
@Autowired	
GetJwtTokenAdapter getJwtToken;


@Autowired
PasswordAdaptor passwordAdaptor;


public String changePassword(String username,UserDetailsInfo userdetails,String jwtToken) {
	JSONObject jsonObject =null;
	try {
		UserDetailsInfo info= new UserDetailsInfo();
		info.setEmail(username);
		info.setOldPassword(userdetails.getOldPassword());
		info.setPassword(userdetails.getPassword());
//		String jwtToken=getJwtToken.getJwtToken(username);
		
		ResponseEntity<String> response =passwordAdaptor.changePassword(jwtToken, info);	
		System.out.println(response.getBody());
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		if(responsecode.equals(Constants.SuccessResponseCode)) {
			if(responseMessage.equals(Constants.Success)) {
		return responseMessage;
			}else {
				return message;
			}
		}else {
			return Constants.AuthenticationFailed;
		}
		
	}catch (Exception e) {
	throw new BgcUIException("exception occered while accssing the data");
	}
	

	
}

public String sendForgetPasswordLink(String id) {
	
	JSONObject jsonObject =null;
	try {
		ResponseEntity<String> response =passwordAdaptor.sendForgetPasswordlink(id)	;
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		if(responsecode.equals(Constants.SuccessResponseCode)) {
			if(responseMessage.equals(Constants.Success)) {
		return responseMessage;
			}else {
				return message;
			}
		}else {
			return Constants.AuthenticationFailed;
		}
		
	}catch (Exception e) {
	throw new BgcUIException("exception occered while accssing the data");
	}
	
}

public String UpdateForgetPasswordLink(UserDetailsInfo userDetailsInfo) {
	
	JSONObject jsonObject =null;
	
	try {
		ResponseEntity<String> response =passwordAdaptor.updateForgetPassword(userDetailsInfo);
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		if(responsecode.equals(Constants.SuccessResponseCode)) {
			if(responseMessage.equals(Constants.Success)) {
		return responseMessage;
			}else {
				return message;
			}
		}else {
			return Constants.AuthenticationFailed;
		}
		
	}catch (Exception e) {
	throw new BgcUIException("exception occered while accssing the data");
	}
	
}

public String sendCandidateForgetPasswordLink(String id) {
	
	JSONObject jsonObject =null;
	try {
		ResponseEntity<String> response =passwordAdaptor.sendCandidateForgetPasswordlink(id);
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		if(responsecode.equals(Constants.SuccessResponseCode)) {
			if(responseMessage.equals(Constants.Success)) {
		return responseMessage;
			}else {
				return message;
			}
		}else {
			return Constants.AuthenticationFailed;
		}
		
	}catch (Exception e) {
	throw new BgcUIException("exception occered while accssing the data"+e.getMessage());
	}
	
}


public String UpdateCandidateForgetPasswordLink(UserDetailsInfo userDetailsInfo) {
	
	JSONObject jsonObject =null;
	
	try {
		CandidatesInfo candidatesInfo= new CandidatesInfo();
		candidatesInfo.setvCode(userDetailsInfo.getvCode());
		System.out.println(candidatesInfo.getvCode());
		candidatesInfo.setDefaultPassword(userDetailsInfo.getPassword());
		
		ResponseEntity<String> response =passwordAdaptor.updateCandidateForgetPassword(candidatesInfo);
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		if(responsecode.equals(Constants.SuccessResponseCode)) {
			if(responseMessage.equals(Constants.Success)) {
		return responseMessage;
			}else {
				return message;
			}
		}else {
			return Constants.AuthenticationFailed;
		}
		
	}catch (Exception e) {
	throw new BgcUIException("exception occered while accssing the data");
	}
	
}


public String changeCandidatePassword(String username,CandidatesInfo candidatesInfo,String jwtToken) {
	JSONObject jsonObject =null;
	try {
		CandidatesInfo info= new CandidatesInfo();
		info.setDefaultUserName(username);
		info.setOldPassword(candidatesInfo.getOldPassword());
		info.setDefaultPassword(candidatesInfo.getDefaultPassword());
//		String jwtToken=getJwtToken.getJwtToken(username);
		
		ResponseEntity<String> response =passwordAdaptor.changeCandidatePassword(jwtToken,info);	
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		if(responsecode.equals(Constants.SuccessResponseCode)) {
			if(responseMessage.equals(Constants.Success)) {
		return responseMessage;
			}else {
				return message;
			}
		}else {
			return Constants.AuthenticationFailed;
		}
		
	}catch (Exception e) {
	throw new BgcUIException("exception occered while accssing the data");
	}
	
}
	
public String sendUserEmailName(CandidatesInfo candidatesInfo) {

	JSONObject jsonObject = null;
	try {
		ResponseEntity<String> response = passwordAdaptor.sendForgetUserNameLink(candidatesInfo);
		System.out.println(response);
		jsonObject = new JSONObject(response.getBody());
		String responsecode = (String) jsonObject.get("responseCode");
		String responseMessage = (String) jsonObject.get("responseMessage");
		String message = (String) jsonObject.get("response");
		if (responsecode.equals(Constants.SuccessResponseCode)) {
			if (responseMessage.equals(Constants.Success)) {
				return responseMessage;
			} else {
				return message;
			}
		} else {
			return Constants.AuthenticationFailed;
		}
	} catch (Exception e) {
		throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
	}
}

}
