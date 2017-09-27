package com.yumyap.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yumyap.beans.User;
import com.yumyap.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@RequestMapping(value = "/attemptLogin", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE , 
			produces =  MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<User> attemptLogin(@RequestBody User user) {
		logger.fatal("attemptLogin() using " + user);

		return new ResponseEntity<User>(userService.attemptLogin(user.getEmail(), user.getPassword()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/attemptRegister",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> attemptRegister(@RequestBody User user) {
		logger.fatal("attemptRegister() using " + user);
		
		return new ResponseEntity<Boolean>(userService.attemptRegister(user), HttpStatus.OK);
	}


}
