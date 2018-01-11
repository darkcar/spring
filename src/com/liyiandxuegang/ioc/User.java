package com.liyiandxuegang.ioc;

public class User {
	
	private String username; 
	public User() {}
	
	public User(String name) {
		this.username = name;
	}
	
	public void add() {
		System.out.println("Add..... method");
	}
	
	/*
	 * 
	public static void main(String[] args) {
		// Original way
		User user = new User();
		user.add();
	}
	*/
}
