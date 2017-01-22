package org.mfaruga.MFSpringJPAMongo.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class MFSpringDataAuditHelper implements AuditorAware<String>{

	public String getCurrentAuditor() {
		return "mfaruga-editor";
	}

}
