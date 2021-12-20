
package com.bgc.backgroundVerification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.CallLoginUserDetails;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	CallLoginUserDetails service;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Value("${aesSecretKey}")
	private String secretKey;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetailsInfo userInfo = service.getUserDetails(username.trim());

		if (userInfo.getEmail() != null) {
			System.out.println(userInfo.getEmail()+ "uerutrrgj");
			return new LoginDetails(userInfo.getEmail(), userInfo.getPassword(), userInfo.getDisabled(),
					userInfo.getUsertype(), userInfo.getIsEmailVerified());
		} else {
			EmployeeInfo employeeinfo = service.getEmployeeInfo(username);
			System.out.println(employeeinfo.getEmail());
			if (employeeinfo.getEmail() != null) {
				return new LoginDetails(employeeinfo);
			} else {
				System.out.println("please enter correct id");
				throw new BgcUIException("Invalid user or password");
			}

		}

	}
	
	

}


