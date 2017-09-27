package com.yumyap.service;

import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dao.DaoImpl;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;

@Service
public class RecipeService implements RecipeServiceInterface{

	private Dao DaoImpl;

	public void setDaoImpl(Dao daoImpl) {
		DaoImpl = daoImpl;
	}

	@Override
	public RecipeDto addRecipe(RecipeDto recipe) {
		DaoImpl.addRecipe(getRecipeBean(recipe));
		return recipe;
	}

	private Recipe getRecipeBean(RecipeDto recipe) {
		return DaoImpl.getRecipeById(recipe.getId());
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



}
