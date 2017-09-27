package com.yumyap.dao;

import java.util.List;

import com.yumyap.beans.Comment;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.RecipeDirection;
import com.yumyap.beans.User;

public interface Dao {
	boolean addUser(User user);
	FoodItem addFoodItem(FoodItem foodItem);
	Comment addComment(Comment comment);
	Recipe addRecipe(Recipe recipe);
	RecipeDirection addRecipeDirection(RecipeDirection recipeDirection);
	
	User getUserByEmailAndPassword(String email, String password);
	
	List<Recipe> getRecipesByUser(User user);	
}
