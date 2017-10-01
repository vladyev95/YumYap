package com.yumyap.dao;

import java.util.List;
import java.util.Set;

import com.yumyap.beans.Comment;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dto.RecipeDto;

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
	
	/**
	 * Returns the User with the corresponding id
	 * @param id The id of the user to get
	 * @return The User with the id or null if no such user
	 */
	User getUserById(int id);
	
	/**
	 * Returns the Recipe with the specified id
	 * @param id The id of the Recipe to get
	 * @return The Recipe with the id, null if no such recipe
	 */
	Recipe getRecipeById(int id);

	void updateUser(User user);
	
	/**
	 * Adds the Comment to the Recipe with the corresponding id
	 * @param id The id of the Recipe to add the comment to
	 * @param comment The Comment to add to the Recipe with the corresponding id
	 */ 
	void addCommentForRecipeById(int id, Comment comment);
	
	/**
	 * Searches for names matching the given string
	 * @param search
	 * @return list of recipes
	 */
	Set<Recipe> getRecipes(String search);
}
