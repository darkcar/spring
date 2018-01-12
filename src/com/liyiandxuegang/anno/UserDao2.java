package com.liyiandxuegang.anno;

import org.springframework.stereotype.Component;

@Component("userDao2")
public class UserDao2 {
	public void add() {
		System.out.println("UserDao2....."); 
	}
}
