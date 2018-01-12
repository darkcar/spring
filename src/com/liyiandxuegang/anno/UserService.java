package com.liyiandxuegang.anno;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class UserService {
	// define the field of userDao
	@Autowired
	private UserDao userDao;
	
	// Resource second way: but this time, the value of name should be the same as the objects.
	@Resource(name="userDao2")
	private UserDao2 userDao2; 
	
	public void add() {
		System.out.println("Service.add ========");
		userDao.add();
		userDao2.add();
	}
	
	
}
