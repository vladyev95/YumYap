package com.yumyap.service;



import com.yumyap.beans.*;

public interface UserService {

	User attemptLogin(String email, String password);
	
	boolean attemptRegister(User user);
}
