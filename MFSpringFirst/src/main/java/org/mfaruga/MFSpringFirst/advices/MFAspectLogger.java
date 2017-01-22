package org.mfaruga.MFSpringFirst.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MFAspectLogger {

	//@Pointcut("execution(public org.mfaruga..*.*(..))")
	@Pointcut("execution(* org.mfaruga..*.*(..))")
	public void k(){}
	
	@Before("k()")
	public void logBefore(JoinPoint jointPoint) {
		System.out.println("logBefore() is running");
		System.out.println("Hijacked: " + jointPoint.getSignature().getName());
		System.out.println("***");
	}

	
	//@Before("execution(public * *(..))")
//	@Before("execution(public * *(..))")
//	public void logBefore(JoinPoint jointPoint) {
//		System.out.println("logBefore() is running");
//		System.out.println("Hijacked: " + jointPoint.getSignature().getName());
//		System.out.println("***");
//	}
	
}
