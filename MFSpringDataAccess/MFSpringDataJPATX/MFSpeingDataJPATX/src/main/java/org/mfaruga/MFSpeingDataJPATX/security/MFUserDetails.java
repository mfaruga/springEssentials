package org.mfaruga.MFSpeingDataJPATX.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MFUserDetails implements UserDetails {

	private static final long serialVersionUID = 1993367209592960995L;
	private String userName;	
	private String password;	
	private final List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>(); 
	
	public MFUserDetails(String name, String password, Collection<GrantedAuthority> authorities) {
		this.userName = name;
		this.password = password;
		listOfAuthorities.addAll(authorities);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return Collections.unmodifiableList(listOfAuthorities);
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return userName;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {		
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {		
		return true;
	}

}
