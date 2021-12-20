package com.bgc.backgroundVerification.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bgc.backgroundVerification.adaptor.CallLoginUserDetails;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;

@Service
public class CandidateLoginService implements UserDetailsService{ 
	
	@Autowired
	CallLoginUserDetails service;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		CandidatesInfo userInfo = service.getCandidateInfo(username.trim());

		/*
		 * String pass = AES.decrypt(userInfo.getPassword(), secretKey); String
		 * passEncode = bCryptPasswordEncoder.encode(pass);
		 * System.out.println(userInfo.getDefaultUserName());
		 */
		return User.builder().username(userInfo.getDefaultUserName()).password(userInfo.getDefaultPassword()).roles("Candidate").disabled(userInfo.getDisabled()).build();
		/*
		 * return new LoginDetails(userInfo.getDefaultUserName(),
		 * userInfo.getDefaultPassword(), userInfo.getDisabled(),"Candidate", true);
		 */
	}
	

}
