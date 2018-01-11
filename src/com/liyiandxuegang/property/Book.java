package com.liyiandxuegang.property;

public class Book {
	private String bookName;
	
	// set book name
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void printBookName() {
		System.out.println("Book's name is " + bookName);
	}
}
