# spring

## Spring concept

Open-source framework, starts from 2003, light-weight, full-stack. 

## Features core content

* AOP: 面向切面，扩展功能不是修改源代码实现。

* IOC (控制反转)， 比如有一个类，在类里面有方法（不是静态方法），调用类里面的方法，使用对象调用方法，创建类对象过程，需要new出来对象
	把对象的创间不是通过new方法实现，而是交给spring配置创建类对象。

## spring是一站式框架

* spring, provides all layers solutions. 

- Web layer: springMVC

- service layer: IoC of Spring

- dao layer: jdbcTemplate of Spring

## spring version 4.x

 # How to use IoC in Spring
 
 ## 把对象的创建交给spring进行管理
 
 ## ioc操作包括两部分
 
 *ioc的配置文件
 
 *ioc的注解方式
 
 ## IoC 原理
 
 使用技术：1. xml配置文件； 2. dom4j解析xml； 3. 工厂的设计模式； 4. 反射技术

Code 原理

```java

public class User{
	public void add() {
		.....
	}
}

// Call method - old way 
/*
	  在servlet中调用User类里面的内容，如果类名和方法发生改变，需要改变的东西太多。
	  耦合度太高了。
*/
User user = new User();
user.add();
```
## 使用工厂模式解决耦合操作

```java
public class UserService {
	public void add() {
	}
}

public class UserServlet{
	UserService userService = UserFactory.getService();
	userService.add();
}

// Solution: create factory class. 
public class UserFactory{
	// provide one method returns UserService object. 
	public static UserService getService() {
		return new UserService();
	}
}

```

思想： 高内聚，低耦合

## IoC concept (*)

```java
	public class UserService{
	
	}
	
	public class UserServlet {
		// get object UserService
		UserFactory.getService();
	}
	
	/**
		Step 1. Create xml file, configure to class you want to create. 
			<bean id="userService" class="com.liyiandxuegang.package.UserService" />
		Step 2. Create one Factory class, use dom4j to parse configuration file + reflection tech.
			public class UserFactory{
				// return UserService 
				public static UserService getService() {
					// 1. Use dom4j parse xml file. 
					// According to id value, get the class property value responding to the id.
					String classValue = "from xml class property value";
					// 2. Use reflection create object
					Class clazz = Class.forName(classValue);
					// 3. Use reflection create object
					UserService userService = clazz.newInstance();
					return userService; 
				}
			}
		
	*/
```

# IoC (Inversion of Control) basic Example 

* Step 1. Import jar files
	
	(1) Release.jar, javadoc.jar, sources.jar
	
	(2) Basic jars: Beans, Core, Context, SpEL (Core container)
	
	(3) Import logging.jar 
* Step 2. Create class and method inside of class.

* Step 3. create spring xml file, configure the class
	
	- Spring core configuration name and location is not fixed. But better under /src.
	
	- 引入约束 schema
	
	- Configure the class 
* Step 4. Create test.

```java
@Test
	public void testUser() {
		//1. load configuration file
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		//2. Get object
		User user = (User) applicationContext.getBean("user");
		//3. Test
		user.add();
	}
```
# Manage bean in spring (xml)

* Bean 实例化方式

1. 在spring里面通过配置文件创建对象

2. bean实例化三种方式：

	- 使用类的无参构造函数
	
	- 使用静态工厂创建
	
		在类中创建静态方法，返回类对象
		
		```java
		<bean id="bean2" class="com.liyiandxuegang.bean.Bean2Factory" factory-method="getBean2" />
		
		public class Bean2 {
	
			public void add() {
				System.out.println("Bean 2 ------ Add");
			}
		}
		
		public class Bean2Factory {
			//  static method
			public static Bean2 getBean2() {
				return new Bean2();
			}
		}
		
		@Test
		public void testStaticFactory() {
			// load 
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
			
			Bean2 bean2 = (Bean2) applicationContext.getBean("bean2");
			
			bean2.add();
		}
		
		```
	- 使用实例工厂创建()
	
		创建一个不是静态的方法，，返回类的对象
		
		```java
		<!-- Create factory object -->
    	<bean id="bean3Factory" class="com.liyiandxuegang.bean.Bean3Factory" />
    	<bean id="bean3" factory-bean="bean3Factory" factory-method="getBean3" />
		```
	
	## Bean tags property
	
	* id (name can be anything) 
	
	* class: class qualified path
	
	* name: same as id, but id can't have special chars. 
	
	* scope: singleton(default), prototype, request, session, globalSession: (ssl)
	
	## 属性注入：设置类的成员变量值
	
	### Basic 方式：
	
	1. 有参构造函数
	
	```java
	public class User{
		private String name;
		public User(String name) {
			this.name = name;
		}
	}
	
	User user = new User("abcde");
	
	```
	2. 使用set方法注入
	
	```java
	public class User{
		private String name;
		public void setName(String name) {
			this.name = name;
		}
	}
	User user = new User();
	user.setName("abcde");
	```

	3. 使用接口的方法
	
	```java
	public interface Dao {
		public void assignVal(String name);
	}
	
	public class DaoImpl implements Dao {
		private String name;
		public void assignVal(String name) {
			this.name = name;
		}
	}
	``` 
	
	 ### Sping method: 
	 
	 1. set method(*)
	 
	 ```xml
	 <!-- Use set method to inject property value  -->
    <bean id="book" class="com.liyiandxuegang.property.Book">
    	<!-- set value -->
    	<property name="bookName" value="Test Value 2" />
    </bean>
	 ```
	 
	 2. Constructor method
	
	```java
	// bean.xml 
	 <!-- Use constructor to inject property value -->
    <bean id="propertyDemo1" class="com.liyiandxuegang.property.PropertyDemo1">
    	<!-- Inject value -->
    	<constructor-arg name="userName" value="Test Value" />
    </bean>
    
    // PropertyDemo1.java
    public class PropertyDemo1 {

		private String userName; 
		
		public PropertyDemo1(String userName) {
			this.userName = userName;
		}
		
		public void test1() {
			System.out.println("demo1--------test1()-------" + userName);
		}
	}
	
	// TestIoC.java
	@Test
	public void testDemo1() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		
		PropertyDemo1 propertyDemo1 = (PropertyDemo1) applicationContext.getBean("propertyDemo1");
		
		propertyDemo1.test1();
	}
	
	```
	###注入对象类型的属性
	
	1. Create class (Servcie and Dao)
	
	* 在Service里面把dao作为类型属性；
	
	* 生成dao类型属性的set方法
	
	```java
	//1.  Define a UserDao field
	private UserDao userDao; 
	
	//2. generate setmethod
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	```
	* 在配置文件中完成这个关系
	
	```xml
	 <!-- 注入对象类型的属性 -->
    <!-- 1. 配置Service和Dao的对象 -->
    <bean id="userDao" class="com.liyiandxuegang.ioc.UserDao" />
    <bean id="userService" class="com.liyiandxuegang.ioc.UserService">
    	<!-- Inject dao obj 
    		name: service类里面属性的名称
    		ref: dao配置bean标签的id值
    	-->
    	<property name="userDao" ref="userDao"></property>
    </bean>
	```
	* Test
	
	```java
	@Test
	public void testUserService() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		
		UserService userService = (UserService) applicationContext.getBean("userService");
		
		userService.addService();
	}
	```
	
	
	
	
	
	











