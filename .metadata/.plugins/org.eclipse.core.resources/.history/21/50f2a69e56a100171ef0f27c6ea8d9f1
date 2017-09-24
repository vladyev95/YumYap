package com.yumyap.service;

import org.dom4j.util.UserDataDocumentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private Dao userDaoImpl;

	public void setUserDaoImpl(Dao userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}

	@Override
	public UserDto authenticateUser(UserDto userDto) {
		User user = userDaoImpl.getUser(userDto.getUser().getUsername());
		
		if(user != null && 
				(user.getPassword().equals(userDto.getUser().getPassword()))) {
			System.out.println("setting userdto to true");
			userDto.setAuthenticated(true);
		}else {
			return null;
		}
		System.out.println("---------------------returning user dto" + userDto.toString());
		return userDto;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		// FIXME need username validation
		// FIXME add the rest of the necessary fields
		User user = new User();
		user.setUsername(userDto.getUser().getUsername());
		user.setPassword(userDto.getUser().getPassword());
		user = userDaoImpl.addUser(user);
		userDto.getUser().setId(user.getId());
		return userDto ;
	}

	@Override
	public UserDto logoutUser(UserDto userDto) {
		if (!userDto.isAuthenticated()) {
			System.out.println("No user is currently logged in");
			return userDto;
		}
		System.out.println("Logging out user: " + userDto.getUser().getUsername());
		userDto.setAuthenticated(false);
		return userDto;
	}

}