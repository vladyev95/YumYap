package com.yumyap.test;

import com.yumyap.beans.User;
import com.yumyap.dao.DaoImpl;

public class MainTest {

	public static void main(String[] args) {
		
		DaoImpl dao = new DaoImpl();
		User u = new User();
		u.setUsername("test");
		u.setPassword("test");
		
		dao.addUser(u);
		
	}

}