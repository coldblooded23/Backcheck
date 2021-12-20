package com.bgc.backgroundVerification.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.PrivilegesInfo;
import com.bgc.backgroundVerification.model.RolesInfo;

public class LoginDetails implements UserDetails {

	private String userName;

	private String password;

	private Boolean isAccountExpired;

	private List<GrantedAuthority> authorites;

	private Boolean isEnabled;

	public LoginDetails(String userName, String password, Boolean AccountExpired, String Usertype,
			Boolean isemailverified) {
		this.userName = userName;

		this.password = password;
		if (AccountExpired == false) {
			this.isAccountExpired = true;
		} else {
			this.isAccountExpired = false;
		}
		this.authorites = adminAuthorities();
		this.isEnabled = isemailverified;
	}

	public LoginDetails(EmployeeInfo info) {
		this.userName = info.getEmail();
		this.password = info.getPassword();
		this.isAccountExpired = true;
		if(info.getDisabled()==false) {
			this.isEnabled =true ;
		}else {
			this.isEnabled =false ;
		}
		
		this.authorites=getAuthorities(info.getRolesInfos());
	}

	public LoginDetails() {
		super();
	}
	private List<GrantedAuthority> adminAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("SuperAdmin"));

		return authorities;
	}


	private List<GrantedAuthority> getAuthorities(Set<RolesInfo> rolesInfos) {
		Set<RolesInfo> roles = rolesInfos;
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RolesInfo Rinfo : roles) {
			Set<PrivilegesInfo> privalInfos = Rinfo.getPrivilegesInfos();
			for (PrivilegesInfo privileges : privalInfos) {
				authorities.add(new SimpleGrantedAuthority(privileges.getPrivilegeName()));
			}
		}

		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorites;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

}
