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
	# Namespace Inject
	
	```xml
	<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!-- Use P namespace inject -->
    <bean id="person" class="com.liyiandxuegang.property.Person" p:pname="Name Value" />
	```
	
	# Complicated data type
	
	* Array, List, Map, Properties
	
	## See the example:
	
	```xml
	<!-- Inject complicated data value -->
    <bean id="personComplicated" class="com.liyiandxuegang.property.Person">
    	<!-- array -->
    	<property name="arraysStrings">
    		<list>
    			<value>Eric</value>
    			<value>Mike</value>
    			<value>Jim</value>
    		</list>
    	</property>
    	<property name="list">
    		<list>
    			<value>Eric List</value>
    			<value>Mike List</value>
    			<value>Jim List</value>
    		</list>
    	</property>
    	<property name="map">
    		<map>
    			<entry key="aa" value="Eric aa" />
    			<entry key="bb" value="Mike BB" />
    		</map>
    	</property>
    	<property name="properties">
    		<props>
    			<prop key="driverClass">com.mysql.jdbc.Driver</prop>
    			<prop key="username">root</prop>
    		</props>
    	</property>
   </bean>
   ```
   
   ## Inside Person.java, we have all the set method defined. 
   
   # IoC and DI (Data Injection)
   
   IOC：控制反转，把对象创建交个spring进行配置。
   
   DI：依赖注入，向类中属性，设置属性值。
   
   关系： 依赖注入不能单独存在，需要在IOC基础上完成操作。
   
   # Spring如何整合web项目
   
	- Loading spring configuration file use lots of resources.???
	
	 ```java
	 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
	 ```
	 
	 ##Solution?
	 
	 把加载配置文件和创建对象过程，在服务器启动时候完成。
	 
	 ## How (base tech)? 
	 
	 * ServletContext Object
	 
	 * Listener Object
	 
	 ### Steps to load spring objects 
	 
	- 在服务器启动时，为每个项目创建ServletContext对象，
	
	- 在ServletContext对象创建时，使用监听器可以监听到ServletContext对象在创建
	
	- 使用监听器听到ServletContext对象创建时，加载spring的配置文件，把配置文件配置对象创建。
	
	- 把创建出来的对象方法ServletContext的域对象里面
	
	- 获取对象时，到ServletContext域得到
	
# Spring的bean管理（注解）

## 注解 
	
1. 代码里特殊的标记，使用注解也可以完成一些功能。

2. 注解写法：@注解名称（属性名称＝属性值）
 	
3. 注解可以使用在类上面，方法上面和属性上面

## 注解 (Steps) － Create class

* import spring-aop.jar

* Configure xml

```xml
	<!-- define the package to scan -->
	<context:component-scan base-package="com.liyiandxuegang.anno" />
```

Then add @annotation above the class or field, or function

```java
@Component(value="user") // <bean id="user" class="" />
public class User {
	public void annoAdd() {
		System.out.println("anno Add...."); 
	}
}
```
There are four annotations in Spring:

- @Component [as component]

- @Controller ［Web］

- @Service ［Service］

- @Repository: ［Persistence］ 

功能相同，都是用来创建对象，用来将来对功能的扩展。

###但是控制类的作用范围：scope

```java
@Component(value="user") // <bean id="user" class="" />
@Scope(value="prototype")
public class User {
	public void annoAdd() {
		System.out.println("anno Add...."); 
	}
}
```

## 注解方式注入属性

* Create class obj

Create UserDao and UserService obje

```java
@Component(value="userDao")
public class UserDao {
	public void add() {
		System.out.println("dao........add");
	}
}

@Service(value="userService")
public class UserService {
	// define the field of userDao
	@Autowired //自动注入的方法，找类的名字来注入类属性 (1)
	private UserDao userDao;
	
	// Resource second way: but this time, the value of name should be the same as the objects.
	@Resource(name="userDao2")
	private UserDao2 userDao2; 	

	public void add() {
		System.out.println("Service.add ========");
		userDao.add();
	}
}
```

Use @Resource should be good. 

## Configuration + Annotation 

1. 创建对象操作使用配置文件方式实现；

2. 注入属性的操作使用注解方式实现。

Suppose, we have three classes, BookService, BookDao.class, OrdersDao.class

In xml file, 

```xml
		<context:component-scan base-package="com.liyiandxuegang.xmlanno" />
        <bean id="bookService" class="com.liyiandxuegang.xmlanno.BookService" />  <!--BookService bookService 
        <bean id="ordersDao" class="com.liyiandxuegang.xmlanno.OrdersDao" /> <!--OrdersDao ordersDao-->
        <bean id="bookDao" class="com.liyiandxuegang.xmlanno.BookDao" /> <!--BookDao bookDao-->
```

In BookService.java file

```java
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
```

Then, we can test it, use the Bean to create the class. 

```java
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean3.xml");
	BookService bookService = (BookService)applicationContext.getBean("bookService");
```

# AOP (Aspect-Oriented Programming) 面向切面编程 

## Concept: 扩展功能不修改源代码实现。采用横向抽取机制，取代了传统纵向继承体系重复性代码(实际就是类的继承).

```java
public class User{
	// Add user
	public void add(User user) {
		// code ...
	}
}

// extends the function
// add user, then add logging function.

```

纵向体系的问题：比如父类方法名称发生了变化，在子类调用的方法也需要变化。

## 横向抽取机制

底层使用：动态代理方式实现

* One way (with inteface)

```java
public interface Dao{
	public void add();
}
									[使用动态代理方式，创建接口实现代理对象]
public class DaoImpl implements Dao{
	public void add() {
		// code ....
	}
}

// 增强add方法，创建和DaoImpl类平级对象，这个对象不是真正的对象，代理对象实现和DaoImpl相同的功能。
// Use JDK dynamic proxy.
```

* Second way (without interface) 使用cglib动态代理，没有接口

```java
	// user class
	public class User{
		public void add() {
		}
	}
	
	// 动态代理实现， 创建User类的子类的代理对象
	// 在子类中也可以调用父类的方法，完成增强
```

## AOP Operation Terms

1. Pointcut

2. Advice

3. Aspect











