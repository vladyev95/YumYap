package com.yumyap.dto;

import com.yumyap.beans.User;

public class UserDto {

	private User user;
	private int loggedIn;
	
	public UserDto() {}
	
	public UserDto(User user, int loggedIn) {
		super();
		this.user = user;
		this.loggedIn = loggedIn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(int loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
}
