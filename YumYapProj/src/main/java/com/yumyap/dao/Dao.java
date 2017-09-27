package com.yumyap.dao;

import java.util.List;
import java.util.Set;

import com.yumyap.beans.Comment;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.RecipeDirection;
import com.yumyap.beans.User;

/**
 * Interface representing a DAO (Data Access Object)
 * Always use this interface as the variable type
 * @author vlad
 */
public interface Dao {
	
	/**
	 * Attempts to add a User to the persistent store
	 * May fail if an User with the email already exists
	 * @param user The user to attempt to add
	 * @return Whether the User was successfully added
	 */
	boolean addUser(User user);
	
	FoodItem addFoodItem(FoodItem foodItem);
	Comment addComment(Comment comment);
	Recipe addRecipe(Recipe recipe);
	RecipeDirection addRecipeDirection(RecipeDirection recipeDirection);
	
	/**
	 * Attempts to retrieve a User with the given email and password
	 * @param email The email of the User
	 * @param password The password of the User
	 * @return User upon success, null upon no such User existing
	 */
	User getUserByEmailAndPassword(String email, String password);
	
	/**
	 * Returns The recipes by people the user with the given id follows
	 * @param id The if of the User to display recipes to
	 * @return All the recipes of people that the user with the id follows
	 */
	Set<Recipe> getFollowingRecipes(User user);
	
	/**
	 * Returns the Recipes that the user with the given id has created
	 * @param id The id of the User whose recipes we are retrieving
	 * @return The Set of Recipes created by this User
	 */
	Set<Recipe> getUsersRecipes(User user);

	List<Recipe> getRecipesByUser(User user);
}
