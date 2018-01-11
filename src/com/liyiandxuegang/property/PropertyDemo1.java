package com.liyiandxuegang.property;

public class PropertyDemo1 {

	private String userName; 
	
	public PropertyDemo1(String userName) {
		this.userName = userName;
	}
	
	public void test1() {
		System.out.println("demo1--------test1()-------" + userName);
	}
}
