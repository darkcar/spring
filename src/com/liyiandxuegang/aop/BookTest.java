package com.liyiandxuegang.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookTest {
		
	@Test
	public void test() {
		 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean4.xml");
		 Book book = (Book) applicationContext.getBean("bookaop");
		 book.add();
	}
}
