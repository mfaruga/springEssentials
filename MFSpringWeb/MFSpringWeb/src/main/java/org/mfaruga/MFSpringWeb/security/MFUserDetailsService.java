package org.mfaruga.MFSpringWeb.security;

import org.springframework.stereotype.Component;

public interface MFUserDetailsService {
	MFUserDetails loadUserByUserName(String userName);
}
