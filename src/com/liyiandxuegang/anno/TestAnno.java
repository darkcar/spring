package com.liyiandxuegang.anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnno {
	
	@Test
	public void test1() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean2.xml");
		User user = (User) applicationContext.getBean("user");
		System.out.println("user ----- ");
		user.annoAdd();
	}
	
	@Test
	public void test2() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean2.xml");
		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.add();
	}
}
