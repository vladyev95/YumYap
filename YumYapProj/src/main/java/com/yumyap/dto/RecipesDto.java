package com.yumyap.dto;

import java.util.List;

import com.yumyap.beans.Recipe;

public class RecipesDto {

	private List<Recipe> recipes;

	public RecipesDto() {
	}

	public RecipesDto(List<Recipe> recipeDtos) {
		super();
		this.recipes = recipeDtos;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipeDtos) {
		this.recipes = recipeDtos;
	}
}
