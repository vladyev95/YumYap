package com.yumyap.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.DaoImpl;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

@Service
public class RecipeService implements RecipeServiceInterface{


	@Override
	public RecipeDto addRecipe(RecipeDto recipe) {
		
		return recipe;
	}

	@Override
	public String getMacronutrients(RecipeDto recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeDto addComment(RecipesDto recipeDto, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * This takes a string representing the comment, the userDto and 
	 * the recipe to which the comment should be added, creates the date based on the current time
	 */
	//	public boolean addComment() {
	//		
	//		java.util.Date now = new java.util.Date();
	//		Date today = new Date(now.getTime());
	//		Comment c = new Comment();
	//		c.setRecipeDto(recipe); new User(user), today, comment);
	//		return true;
	//	}
	//	


}
