package com.yumyap.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.yumyap.beans.User;
import com.yumyap.dao.DaoImpl;
import com.yumyap.dto.UserDto;

public class Service {
	
	@Autowired
	private DaoImpl daoImpl;

	public void setDaoImpl(DaoImpl daoImpl) {
		this.daoImpl = daoImpl;
	}

	public UserDto authenticateUser(UserDto userDto) {
		User user = daoImpl.findUserByEmail(userDto.getUser().getEmail());
		if(user != null && 
				(user.getPassword().equals(userDto.getUser().getPassword()))) {
			userDto.setAuthenticated(true);
		}else {
			return null;
		}
		return userDto;
	}

	public UserDto createUser(UserDto userDto) {
		// FIXME user validation
		// FIXME enumerate other fields in user
		User user = new User();
		user.setEmail(userDto.getUser().getEmail());
		user.setPassword(userDto.getUser().getPassword());
		user = daoImpl.addUser(user);
		userDto.getUser().setId(user.getId());
		return userDto ;
	}

}
