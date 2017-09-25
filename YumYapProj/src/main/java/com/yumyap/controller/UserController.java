package com.yumyap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;
import com.yumyap.service.Services;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private Services service;

	public void setUserServiceImpl(Services service) {
		this.service = service;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto) {

		System.out.println("authenticating user");

		return new ResponseEntity<UserDto>(service.validateUser(userDto), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> deAuthenticateUser(@RequestBody UserDto userDto) {

		System.out.println("Logging out user");

		return new ResponseEntity<UserDto>(service.logoutUser(userDto), HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		System.out.println("creating new user");
		service.createUser(userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dash", method = { RequestMethod.GET }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecipesDto> loadDashboard(@RequestBody UserDto userDto) {
		System.out.println("Loading Dashboard");

		return new ResponseEntity<RecipesDto>(service.getDashboard(userDto, 0), HttpStatus.OK);
	}

	@RequestMapping(value = "/profile", method = { RequestMethod.GET }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecipesDto> loadProfile(@RequestBody UserDto userDto) {
		System.out.println("Loading Profile");

		return new ResponseEntity<RecipesDto>(service.getProfile(userDto), HttpStatus.OK);
	}

}