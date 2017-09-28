package com.yumyap.service;

import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.dao.Dao;
import com.yumyap.dao.DaoImpl;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;

@Service
public class RecipeService implements RecipeServiceInterface{


	Dao dao = new DaoImpl();
	@Override
	public RecipeDto addRecipe(RecipeDto recipe) {
		// TODO Auto-generated method stub
		return recipe;
	}

	@Override
	public String getMacronutrients(RecipeDto recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeDto addComment(RecipeDto recipeDto, Comment comment) {
		// TODO Auto-generated method stub
		Recipe rec = new Recipe();
		rec.setId(recipeDto.getId());
		rec.setCreated(recipeDto.getCreated());
		rec.setCreator(recipeDto.getCreator());
		rec.setName(recipeDto.getName());
		rec.setDescription(recipeDto.getDescription());
		//rec.setDirections(recipeDto.getDirections());
		rec.setImage(recipeDto.getImage());
		comment.setRecipe(rec);
		dao.addComment(comment);
		return recipeDto;
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
