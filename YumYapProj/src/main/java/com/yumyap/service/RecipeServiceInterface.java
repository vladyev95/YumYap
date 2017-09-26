package com.yumyap.service;

<<<<<<< HEAD
import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
=======
>>>>>>> loginRegister
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;

public interface RecipeServiceInterface {

	public RecipeDto addRecipe(RecipeDto recipe);
	public String getMacronutrients(RecipeDto recipe);
	// comment is added to recipe's list of comments
	public RecipeDto addComment(RecipesDto recipeDto, Comment comment);

}
