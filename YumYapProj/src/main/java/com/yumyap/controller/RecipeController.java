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

import com.yumyap.beans.Recipe;
import com.yumyap.service.UserService;

/**
 * A Controller handling retrieval of information regarding Recipes
 * @author vlad
 */
@Controller
@RequestMapping(value = "/recipe")
public class RecipeController {
	
	private static final Logger logger = Logger.getLogger(RecipeController.class);
	
	@Autowired
	private UserService userService;
	
	public RecipeController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/create",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createRecipe(@RequestBody Recipe recipe) {
		logger.trace("createRecipe() by " + recipe);
		userService.addRecipe(recipe);		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
