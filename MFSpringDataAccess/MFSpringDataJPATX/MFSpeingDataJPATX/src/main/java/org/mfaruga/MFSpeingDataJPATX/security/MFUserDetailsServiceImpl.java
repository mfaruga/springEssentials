package org.mfaruga.MFSpeingDataJPATX.security;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;

public class MFUserDetailsServiceImpl implements MFUserDetailsService {

	private static final MFUserDetails HARD_CODED_MFARUGA = new MFUserDetails("mfaruga", "mfaruga",
			Arrays.asList(new GrantedAuthority[] { MFAuthorities.ADMIN, MFAuthorities.USER }));

	private static final MFUserDetails HARD_CODED_SFARUGA = new MFUserDetails("sfaruga", "sfaruga",
			Arrays.asList(new GrantedAuthority[] { MFAuthorities.USER }));

	public MFUserDetails loadUserByUserName(String userName) {
		if (userName.equals("mfaruga")) {
			return HARD_CODED_MFARUGA;
		} else if (userName.equals("sfaruga")) {
			return HARD_CODED_SFARUGA;
		}
		return null;
	}

}
