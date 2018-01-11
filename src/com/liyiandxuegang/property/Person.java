package com.liyiandxuegang.property;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {

	private String pname;
	
	public void setPname(String pname) {
		this.pname = pname;
	}
	//1 define the value
	private String[] arraysStrings;
	private List<String> list;
	private Map<String, String> map;
	private Properties properties;
	
	
	// 2 set mehtod 
	public String[] getArraysStrings() {
		return arraysStrings;
	}


	public void setArraysStrings(String[] arraysStrings) {
		this.arraysStrings = arraysStrings;
	}


	public List<String> getList() {
		return list;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	public Map<String, String> getMap() {
		return map;
	}


	public void setMap(Map<String, String> map) {
		this.map = map;
	}


	public Properties getProperties() {
		return properties;
	}


	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public void testprint() {
		System.out.println("person....." + pname);
	}
	
	// 3 test method
	public void testComplicated() {
		System.out.println("string[]....." + arraysStrings);
		System.out.println("list...." + list); 
		System.out.println("map....." + map); 
		System.out.println("properties...." + properties);
	}
	
}
