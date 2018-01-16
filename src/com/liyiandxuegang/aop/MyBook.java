package com.liyiandxuegang.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyBook {
	
	public void before1() {
		//before enhance
		System.out.println("mybook before 1...........");
	}
	
	public void after() {
		System.out.println("after the method to execute.......");
	}
	
	// Around, class the method 
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Method before......");
		
		// Call the method add()
		proceedingJoinPoint.proceed();
		
		
		System.out.println("Method after..... ");
	}
}
