package com.yumyap.dto;

import com.yumyap.beans.Recipe;
import java.util.List;

public class RecipesDto {

private List<Recipe> recipes;
	
	
	public RecipesDto() {}
	
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
