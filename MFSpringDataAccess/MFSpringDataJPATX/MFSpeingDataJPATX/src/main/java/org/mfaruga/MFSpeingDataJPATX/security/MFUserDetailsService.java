package org.mfaruga.MFSpeingDataJPATX.security;

public interface MFUserDetailsService {
	MFUserDetails loadUserByUserName(String userName);
}
