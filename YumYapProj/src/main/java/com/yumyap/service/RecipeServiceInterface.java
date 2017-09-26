package com.yumyap.service;

import com.yumyap.beans.Recipe;
import com.yumyap.dto.RecipeDto;

public interface RecipeServiceInterface {
	
	public RecipeDto addRecipe(RecipeDto recipe);
	public String getMacronutrients(RecipeDto recipe);

}
