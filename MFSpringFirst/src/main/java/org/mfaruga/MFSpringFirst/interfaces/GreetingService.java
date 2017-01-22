package org.mfaruga.MFSpringFirst.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface GreetingService {
		
	void greet(String message);
}
