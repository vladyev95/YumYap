package com.yumyap.dto;

import java.util.List;

public class RecipesDto {

private List<RecipeDto> recipes;
	
	
	public RecipesDto() {}
	
	public RecipesDto(List<RecipeDto> recipeDtos) {
		super();
		this.recipes = recipeDtos;
	}

	public List<RecipeDto> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeDto> recipeDtos) {
		this.recipes = recipeDtos;
	}
}
