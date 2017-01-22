package org.mfaruga.MFSpringFirst.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.aspectj.lang.annotation.AfterReturning;
import org.mfaruga.MFSpringFirst.interfaces.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class GreetingServiceImpl implements GreetingService {

	private static Logger logger = Logger.getLogger(GreetingServiceImpl.class.getCanonicalName());
	private Integer numberProperty;
	private Integer someIntegerProperty = null;
	private String stringProperty;
	// TODO this is not working, so I'll pass this through setter
	// @Value("${Description}")
	private String stringPropertyFromFile;	
	
	private Date reportTime;
	
	@Value("#{new java.util.Date()}")
	private Date secondDate;
	
	public Date getSecondDate() {
		return secondDate;
	}

	@Autowired
	@Value("#{new java.util.Date()}")
	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}

	private String secondText;
	
	public String getSecondText() {
		return secondText;
	}
	
	public void setSecondText(String secondText) {
		this.secondText = secondText;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getStringPropertyFromFile() {
		return stringPropertyFromFile;
	}

	public void setStringPropertyFromFile(String stringPropertyFromFile) {
		this.stringPropertyFromFile = stringPropertyFromFile;
	}

	public GreetingServiceImpl(Integer number, String text) {
		numberProperty = number;
		stringProperty = text;
	}
	
	public void greet(String message) {
		logger.info("Greetings! " + message + numberProperty + " " + stringProperty + " " + someIntegerProperty
				+ " From file: " + stringPropertyFromFile + "\n Report time:  " + reportTime + "\nSecond date: " + secondDate + "\nSecond Text: " + secondText);
	}

	public void initialization() {
		logger.info("I'm being initialized!");		
	}

	public Integer getSomeIntegerProperty() {
		return someIntegerProperty;
	}

	public void setSomeIntegerProperty(Integer someIntegerProperty) {
		this.someIntegerProperty = someIntegerProperty;
	}

	@PostConstruct
	public void calledWhenInitIsDone() {
		logger.info("I'm called because initialization of the bean is done");
	}

	@PreDestroy
	public void calledJustBeforeDestroy() {
		logger.info("I'm called because bean is being destroyed");
	}
}
