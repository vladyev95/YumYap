package com.yumyap.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yumyap.beans.Recipe;
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
		logger.trace("attemptLogin() using " + user);

		return new ResponseEntity<User>(userService.attemptLogin(user.getEmail(), user.getPassword()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/attemptRegister",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> attemptRegister(@RequestBody User user) {
		logger.trace("attemptRegister() using " + user);
		
		return new ResponseEntity<Boolean>(userService.attemptRegister(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/dash", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecipesDto> loadDashboard(@RequestBody UserDto userDto) {
		logger.trace("loadDashboard(userDto= "+userDto+")");

		return new ResponseEntity<RecipesDto>(userService.getDashboard(userDto, 0), HttpStatus.OK);
	}

	@RequestMapping(value = "/profile", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProfileDto> loadProfile(@RequestBody UserDto userDto) {
		System.out.println("Loading Profile");

		return new ResponseEntity<ProfileDto>(
				userService.getProfile(userDto), HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/favorite", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> favoriteRecipe(@RequestBody RecipeDto recipeDto, @RequestBody UserDto userDto) {
		System.out.println("favoriting this recipe");
		System.out.println(userDto);
		System.out.println(recipeDto);
		return new ResponseEntity<UserDto>(userService.favoriteRecipe(recipeDto, userDto), HttpStatus.OK);
	}

	@RequestMapping(value = "/deactivate", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> deactivateUser(@RequestBody UserDto userDto) {

		System.out.println("Deactivate user");

		return new ResponseEntity<UserDto>(userService.logoutUser(userDto), HttpStatus.OK);
	}


}
