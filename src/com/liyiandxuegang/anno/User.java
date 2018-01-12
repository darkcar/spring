package com.liyiandxuegang.anno;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="user") // <bean id="user" class="" />
@Scope(value="prototype")
public class User {
	public void annoAdd() {
		System.out.println("anno Add...."); 
	}
}
