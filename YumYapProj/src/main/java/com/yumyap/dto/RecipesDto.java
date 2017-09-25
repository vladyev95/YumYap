package com.yumyap.dto;

<<<<<<< HEAD
import java.util.List;

import com.yumyap.beans.Recipe;

public class RecipesDto {

	private List<Recipe> recipes;

	public RecipesDto() {
	}

=======
import com.yumyap.beans.Recipe;
import java.util.List;

public class RecipesDto {

private List<Recipe> recipes;
	
	
	public RecipesDto() {}
	
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
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
<<<<<<< HEAD

=======
	
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
}
