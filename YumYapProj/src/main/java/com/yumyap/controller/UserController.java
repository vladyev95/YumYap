package com.yumyap.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.yumyap.dto.UsrRecDto;
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
	 * 			HttpStatus.UNAUTHORIZED upon failure
	 */
	@RequestMapping(value = "/attemptLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> attemptLogin(@RequestBody User user) {
		logger.trace("attemptLogin() using " + user);
		User actualUser = userService.attemptLogin(user.getEmail(), user.getPassword());
		
		if (actualUser == null) {
			logger.info("email/password is incorrect");
			return new ResponseEntity<UserDto>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<UserDto>(new UserDto(actualUser), HttpStatus.OK);
		}
	}

	/**
	 * @param user
	 * @return true upon success
	 * 			false and HttpStatus.UNAUTHORIZED, HttpStatus.NOT_ACCEPTABLE, or
	 * 			HttpStatus.CONFLICT upon failure
	 */
	@RequestMapping(value = "/attemptRegister", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> attemptRegister(@RequestBody User user) {
		logger.trace("attemptRegister() using " + user);
		
		try {
			if (userService.attemptRegister(user))
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			else {
				logger.info("User could not be registered");
				return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
			}
		} catch (DataIntegrityViolationException e) {
			logger.info("A user already exists with that email");
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
		} catch (HibernateException e) {
			logger.info(e.getMessage());
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/dash", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecipeDto>> loadDashboard(@RequestBody UserDto userDto) {
		logger.trace("loadDashboard() by " + userDto);
		
		List<RecipeDto> recipeDtos = userService.getDashboard(userDto);
		
		recipeDtos.sort((r1, r2) -> 
			{
				if (r1.getDateCreated() == null && r2.getDateCreated() == null)
					return 0;
				else if (r1.getDateCreated() == null)
					return 1;
				else if (r2.getDateCreated() == null)
					return -1;
				return -r1.getDateCreated().compareTo(r2.getDateCreated());
			});
		logger.trace("loadDashboard() recipes: " + recipeDtos);
		return new ResponseEntity<List<RecipeDto>>(recipeDtos, HttpStatus.OK);
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
	
	/**
	 * @param simpleUserDto
	 * @return The UserDto with the corresponding information upon success
	 * 			HttpStatus.NOT_ACCEPTABLE upon failure
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> loadProfile(@RequestBody SimpleUserDto simpleUserDto) {
		logger.trace("loadProfile() by " + simpleUserDto);
		
		try {
			UserDto userDto = userService.simpleUserDtoToUserDto(simpleUserDto);
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.info("NullPointer thrown");
			return new ResponseEntity<UserDto>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
	@RequestMapping(value = "/favorite", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addFavoriteRecipe(@RequestBody UsrRecDto dto) {
		System.out.println("favoriting this recipe");
		System.out.println("userDto: " + dto);

		try {
			if (userService.addFavoriteRecipe(dto))
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			else return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		} catch (NullPointerException e) {
			logger.warn("NullPointerException thrown");
			return new ResponseEntity<Boolean>(HttpStatus.NOT_ACCEPTABLE);
		} catch (DataIntegrityViolationException e) {
			logger.info("DataIntegrityViolationException");
			return new ResponseEntity<Boolean>(false, HttpStatus.EXPECTATION_FAILED);
		}
	}

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

	/**
	 * @param recipe
	 * @return HttpStatus.OK on success, HttpStatus.NOT_ACCEPTABLE upon failure
	 */
	@RequestMapping(value = "/create", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> createRecipe(@RequestBody Recipe recipe) {
		
		logger.trace("creating a new recipe");
		
		try {
			userService.addRecipe(recipe);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.info("NullPointer thrown");
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * @param user
	 * @param follower
	 * @return HttpStatus.OK on success, HttpStatus.CONFLICT or HttpStatus.NOT_ACCEPTABLE on failure
	 */
	@RequestMapping(value = "/follow", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> addFollower(@RequestBody UserDto user) {
		logger.trace("Adding a follower");

		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		if (userService.followeUser(user))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else {
			logger.info("The user is already following that person");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
}
