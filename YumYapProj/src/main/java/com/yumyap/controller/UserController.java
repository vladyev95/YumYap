package com.yumyap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;
import com.yumyap.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	public void setUserServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/follow", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> followUser(@RequestBody UserDto currentUser, @RequestBody UserDto followingUser ) {

		System.out.println("authenticating user");

		return new ResponseEntity<UserDto>(userService.addFollowing(currentUser, followingUser), HttpStatus.OK);
	}

	@RequestMapping(value = "/attemptLogin", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto) {

		System.out.println("authenticating user");

		return new ResponseEntity<UserDto>(userService.validateUser(userDto), HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> deAuthenticateUser(@RequestBody UserDto userDto) {

		System.out.println("Logging out user");

		return new ResponseEntity<UserDto>(userService.logoutUser(userDto), HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		System.out.println("creating new user: " + userDto);
		
		userDto = userService.createUser(userDto);
		if (userDto == null)
			System.out.println("User creation failed");
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/dash", method = { RequestMethod.GET }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecipesDto> loadDashboard(@RequestBody UserDto userDto) {
		System.out.println("Loading Dashboard");

		return new ResponseEntity<RecipesDto>(userService.getDashboard(userDto, 0), HttpStatus.OK);
	}

	@RequestMapping(value = "/profile", method = { RequestMethod.GET }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProfileDto> loadProfile(@RequestBody String email) {
		System.out.println("Loading Profile");

		return new ResponseEntity<ProfileDto>(
				userService.getProfile(email), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/favorite", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> favoriteRecipe(@RequestBody RecipeDto recipeDto, @RequestBody UserDto userDto) {
		System.out.println("favoriting this recipe");
		System.out.println(userDto);
		System.out.println(recipeDto);
		return new ResponseEntity<UserDto>(userService.favoriteRecipe(recipeDto, userDto), HttpStatus.OK);
	}

}
