package com.yumyap.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.User;
import com.yumyap.dao.Dao;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private Dao dao;


	@Autowired
	public UserServiceImpl(Dao dao) {
		this.dao = dao;
	}

	@Override
	public User attemptLogin(String email, String password) {
		logger.fatal("attemptLogin(" + email + ", " + password + ") ");
		return dao.getUserByEmailAndPassword(email, password);
	}

	@Override
	public boolean attemptRegister(User user) {
		return dao.addUser(user);
	}
}
