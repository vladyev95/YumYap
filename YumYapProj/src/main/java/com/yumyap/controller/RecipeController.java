package com.yumyap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.UserDto;
import com.yumyap.service.RecipeService;
import com.yumyap.service.UserService;

@Controller
@RequestMapping(value="/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;


	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}


	@RequestMapping(value = "/create", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipe) {

		System.out.println("creating a new recipe");
		
		return new ResponseEntity<RecipeDto>(recipeService.addRecipe(recipe), HttpStatus.OK);
	}

}
