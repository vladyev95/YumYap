package com.yumyap.test;

import com.yumyap.beans.User;
import com.yumyap.dao.DaoImpl;

public class MainTest {

	public static void main(String[] args) {
		
		DaoImpl dao = new DaoImpl();
/*		User u = new User();
		
		u.setEmail("test@mail.com");
		u.setPassword("test");
		u.setActive(0);
		u.setFirstname("first");
		u.setLastname("last");
		
		User u1 = dao.addUser(u);
		
		//System.out.println("user: " + u);
		System.out.println("user1: " + u1 +" is added");
		
		u.setEmail("aaa@mail.com");
		u.setPassword("aaa");
		u.setActive(0);
		u.setFirstname("firstA");
		u.setLastname("lastA");
		
		User u2= dao.addUser(u);
		
		System.out.println("user2: " + u2 +" is added");
		
		u.setEmail("bbb@mail.com");
		u.setPassword("bbb");
		u.setActive(0);
		u.setFirstname("firstB");
		u.setLastname("lastB");
		
		User u3= dao.addUser(u);
		
		System.out.println("user3: " + u3 +" is added");
*/
		User u1=dao.getUser("test@mail.com");
		User u2=dao.getUser("smith@mail.com");
		
		
	}
}
