package com.bgc.backgroundVerification.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.RolesAdaptor;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.PrivilegesInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.model.RolesInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class RolesService {

	@Autowired
	RolesAdaptor rolesAdaptor;

	public UserDetailsInfo getRoleByEmail(String email, String jwtToken) {
		UserDetailsInfo userInfo = null;
		try {
			System.out.println(email);
			userInfo = rolesAdaptor.getRolesByEmail(email, jwtToken);

		} catch (Exception e) {
			new BgcUIException("exception occered while accesing the data" + e.getMessage());
		}
		return userInfo;

	}

	public ResponseInfo addARole(RolesInfo rolesInfo, String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
			RolesInfo roles = new RolesInfo();
			roles.setCustomerId(rolesInfo.getCustomerId());
			roles.setRoleName(rolesInfo.getRoleName());
			Set<PrivilegesInfo> info = new HashSet<PrivilegesInfo>();
			List<String> priv = rolesInfo.getPrivaleges();
			for (String privs : priv) {
				PrivilegesInfo privilegesInfo = new PrivilegesInfo(privs);
				info.add(privilegesInfo);
			}
			roles.setPrivilegesInfos(info);
			ResponseEntity<String> response = rolesAdaptor.addARoleWithPrivilages(roles, jwtToken);

			/*
			 * "responseCode": "200", "responseMessage": "Success",
			 * "response":"Authentication Success"
			 */

			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");

			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);

			return responseInfo;
		} catch (Exception e) {
			new BgcUIException("exception occered while accesing the data" + e.getMessage());
		}
		return responseInfo;

	}

	public ResponseInfo updateARole(RolesInfo rolesInfo, String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {
			RolesInfo roles = new RolesInfo();
			roles.setRolesId(rolesInfo.getRolesId());
			roles.setCustomerId(rolesInfo.getCustomerId());
			roles.setRoleName(rolesInfo.getRoleName());
			Set<PrivilegesInfo> info = new HashSet<PrivilegesInfo>();
			List<String> priv = rolesInfo.getPrivaleges();
			for (String privs : priv) {
				PrivilegesInfo privilegesInfo = new PrivilegesInfo(privs);
				info.add(privilegesInfo);
			}
			roles.setPrivilegesInfos(info);
			ResponseEntity<String> response = rolesAdaptor.updateRoleWithPrivilages(roles, jwtToken);

			/*
			 * "responseCode": "200", "responseMessage": "Success",
			 * "response":"Authentication Success"
			 */

			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");

			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);

			return responseInfo;
		} catch (Exception e) {
			new BgcUIException("exception occered while accesing the data" + e.getMessage());
		}
		return responseInfo;

	}

	public ResponseInfo deleteRole(String id,String jwtToken) {
		JSONObject jsonObject = null;
		ResponseInfo responseInfo = new ResponseInfo();
		try {

//			String jwtToken=getJwtTokenAdapter.getJwtToken(email);
			ResponseEntity<String> response = rolesAdaptor.deleteRole(id, jwtToken);
			jsonObject = new JSONObject(response.getBody());
			String responsecode = (String) jsonObject.get("responseCode");
			String responseMessage = (String) jsonObject.get("responseMessage");
			String message = (String) jsonObject.get("response");
			responseInfo.setAlertMessage(message);
			responseInfo.setMessage(responseMessage);

		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}
		return responseInfo;

	}

	public List<PrivilegesInfo> listofPrivilages(String jwtToken) {
		List<PrivilegesInfo> privilages = null;
		try {
			privilages = rolesAdaptor.privilages(jwtToken);
			return privilages;
		} catch (Exception e) {
			new BgcUIException("exception occered while accesing the data" + e.getMessage());

		}
		return privilages;
	}
	
	
	
	public List<RolesInfo> listofRoles(Long id,String jwtToken) {
		List<RolesInfo> rolesInfo = null;
		try {
			rolesInfo = rolesAdaptor.getRolesByCustomerId(id, jwtToken);
			return rolesInfo;
		} catch (Exception e) {
			new BgcUIException("exception occered while accesing the data" + e.getMessage());

		}
		return rolesInfo;
	}

	public RolesInfo getRolebyId(Long id, String jwtToken) {
		RolesInfo roles = null;
		try {
			roles = rolesAdaptor.getRolesById(id, jwtToken);

			return roles;
		} catch (Exception e) {
			// TODO: handle exception
			new BgcUIException("exception occered while accesing the data" + e.getMessage());

		}

		return roles;

	}

}