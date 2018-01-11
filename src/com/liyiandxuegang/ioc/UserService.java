package com.liyiandxuegang.ioc;

public class UserService {

	//1.  Define a UserDao field
	private UserDao userDao; 
	
	//2. generate setmethod
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void addService() {
		System.out.println("add Service -----");
		/** Original way
		 *
			UserDao dao = new UserDao();
			dao.addDao();
		*/
		userDao.addDao();
	}
	
}
