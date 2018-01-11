package com.liyiandxuegang.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liyiandxuegang.bean.Bean2;
import com.liyiandxuegang.bean.Bean3;
import com.liyiandxuegang.property.Book;
import com.liyiandxuegang.property.PropertyDemo1;

public class TestIoC {

	@Test
	public void testUser() {
		//1. load configuration file
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		//2. Get object
		User user = (User) applicationContext.getBean("user");
		
		User user1 = (User) applicationContext.getBean("user");
		
		//com.liyiandxuegang.ioc.User@4923ab24, com.liyiandxuegang.ioc.User@4923ab24
		// if scope="prototype", then different instance.
		// same address
		System.out.println(user + ", " + user1);
		//3. Test
		user.add();
		
	}
	
	@Test
	public void testStaticFactory() {
		// load 
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		
		Bean2 bean2 = (Bean2) applicationContext.getBean("bean2");
		
		bean2.add();
	}
	
	@Test
	public void testRealFactory() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		
		Bean3 bean3 = (Bean3) applicationContext.getBean("bean3");
		
		bean3.add();
	}
	
	@Test
	public void testDemo1() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		
		PropertyDemo1 propertyDemo1 = (PropertyDemo1) applicationContext.getBean("propertyDemo1");
		
		propertyDemo1.test1();
	}
	
	@Test
	public void testDemo2() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		
		Book book = (Book) applicationContext.getBean("book");
		
		book.printBookName();
	}
	
	@Test
	public void testUserService() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		
		UserService userService = (UserService) applicationContext.getBean("userService");
		
		userService.addService();
	}
}
