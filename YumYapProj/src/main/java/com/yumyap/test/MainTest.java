package com.yumyap.test;

import com.yumyap.beans.User;
import com.yumyap.dao.DaoImpl;

public class MainTest {

	public static void main(String[] args) {
		
		DaoImpl dao = new DaoImpl();
		String email = "fred@mail.com";
		
		User u = new User();
		u.setFirstname("fred");
		u.setLastname("Smith");
		u.setEmail(email);
		u.setPassword("password");
		
		dao.addUser(u);
		User fred = dao.getUser(email);
		
		System.out.println(fred);
	}

}
