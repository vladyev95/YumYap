<<<<<<< HEAD
=======
package com.yumyap.test;

import com.yumyap.beans.User;
import com.yumyap.dao.DaoImpl;

public class MainTest {

	public static void main(String[] args) {
		
		DaoImpl dao = new DaoImpl();
		User u = new User();
		
		u.setEmail("test@mail.com");
		u.setPassword("test");
		u.setActive(0);
		u.setFirstname("first");
		u.setLastname("last");
		
		User u2 = dao.addUser(u);
		
		System.out.println("user: " + u);
		System.out.println("user2: " + u2);
		
	}

}
>>>>>>> loginRegister
