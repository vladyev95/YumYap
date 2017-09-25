package com.yumyap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;
import com.yumyap.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/dash", 
			method = {RequestMethod.GET},
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RecipesDto> loadDashboard(@RequestBody UserDto userDto){
		System.out.println("Loading Dashboard");
		
		return new ResponseEntity<RecipesDto>(
				userService.getDashboard(userDto, 0), HttpStatus.OK);
	}
	
	@RequestMapping(value="/profile", 
			method = {RequestMethod.GET},
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ProfileDto> loadProfile(@RequestBody UserDto userDto){
		System.out.println("Loading Profile");
		
		return new ResponseEntity<ProfileDto>(
				userService.getProfile(userDto), HttpStatus.OK);
	}
	


	
}

