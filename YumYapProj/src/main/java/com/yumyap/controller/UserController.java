package com.yumyap.controller;

import java.util.List;

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
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.SimpleUserDto;
import com.yumyap.dto.UserDto;
import com.yumyap.service.UserService;

/**
 * A controller for retrieving information regarding Users
 * 
 * @author vlad
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Attempts to validate the given User
	 * 
	 * @param user
	 *            The User to validate
	 * @return The UserDto with the corresponding information upon success
	 */
	@RequestMapping(value = "/attemptLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> attemptLogin(@RequestBody User user) {
		logger.trace("attemptLogin() using " + user);
		User actualUser = userService.attemptLogin(user.getEmail(), user.getPassword());
		if (actualUser == null) {
			return new ResponseEntity<UserDto>(HttpStatus.I_AM_A_TEAPOT);
		} else {
			return new ResponseEntity<UserDto>(new UserDto(actualUser), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/attemptRegister", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> attemptRegister(@RequestBody User user) {
		logger.trace("attemptRegister() using " + user);

		return new ResponseEntity<Boolean>(userService.attemptRegister(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/dash", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecipeDto>> loadDashboard(@RequestBody UserDto userDto) {
		logger.trace("loadDashboard() by " + userDto);

		return new ResponseEntity<List<RecipeDto>>(userService.getDashboard(userDto), HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/profile", method = { RequestMethod.POST }, consumes
	 * = { MediaType.APPLICATION_JSON_VALUE }, produces = {
	 * MediaType.APPLICATION_JSON_VALUE }) public ResponseEntity<UserDto>
	 * loadProfile(@RequestBody UserDto userDto) {
	 * System.out.println("Loading Profile");
	 * 
	 * return new ResponseEntity<UserDto>(userService.getProfile(userDto),
	 * HttpStatus.OK); }
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> loadProfile(@RequestBody SimpleUserDto simpleUserDto) {
		logger.trace("loadProfile() by " + simpleUserDto);
		UserDto userDto = userService.simpleUserDtoToUserDto(simpleUserDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

	}

	/*
	 * @RequestMapping(value = "/favorite", method = { RequestMethod.POST },
	 * consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
	 * MediaType.APPLICATION_JSON_VALUE }) public ResponseEntity<UserDto>
	 * addFavoriteRecipe(@RequestBody RecipeDto recipeDto, @RequestBody UserDto
	 * userDto) { System.out.println("favoriting this recipe");
	 * System.out.println(userDto); System.out.println(recipeDto); return new
	 * ResponseEntity<UserDto>(userService.addFavoriteRecipe(recipeDto, userDto),
	 * HttpStatus.OK); }
	 */

	/*
	 * // TODO: Deactivate or logout???
	 * 
	 * @RequestMapping(value = "/deactivate", method = { RequestMethod.POST },
	 * consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
	 * MediaType.APPLICATION_JSON_VALUE }) public ResponseEntity<UserDto>
	 * deactivateUser(@RequestBody UserDto userDto) {
	 * 
	 * System.out.println("Deactivate user");
	 * 
	 * return new ResponseEntity<UserDto>(userService.logoutUser(userDto),
	 * HttpStatus.OK); }
	 */

	@RequestMapping(value = "/create", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> createRecipe(@RequestBody Recipe recipe) {

		logger.trace("creating a new recipe");
		userService.addRecipe(recipe);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/addFollower", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> addFollower(@RequestBody UserDto user, @RequestBody UserDto follower) {
		logger.trace("Adding a follower");

		if (userService.addFollower(user, follower))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else {
			logger.info("The user is already following that person");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
}
