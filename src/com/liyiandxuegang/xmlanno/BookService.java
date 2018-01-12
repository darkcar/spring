package com.liyiandxuegang.xmlanno;

import javax.annotation.Resource;

public class BookService {

		@Resource(name="bookDao")
		private BookDao bookDao;
		
		@Resource(name="ordersDao")
		private OrdersDao ordersDao;
		
		public void add() {
			System.out.println("bookService ..... add");
			bookDao.book();
			ordersDao.order();
		}
}
