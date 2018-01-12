package com.liyiandxuegang.xmlanno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookTest {

	@Test
	public void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean3.xml");
		BookService bookService = (BookService)applicationContext.getBean("bookService");
		bookService.add();
	}
}
