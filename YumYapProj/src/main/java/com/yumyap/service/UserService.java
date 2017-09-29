package com.yumyap.service;

import java.util.List;
import java.util.Set;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.SimpleUserDto;
import com.yumyap.dto.UserDto;
/**
 * The interface representing a service that deals with
 * User operations
 * Always use this interface as the variable type, not the implementation type
 * @author vlad
 */
public interface UserService {

	/**
	 * Attempts to login/retrieve a User with the given email and password
	 * @param email The email of the User to retrieve
	 * @param password The password of the User to retrieve
	 * @return User upon success, null upon failure
	 */
	User attemptLogin(String email, String password);
	
	/**
	 * Attempts to register/add a User to the persistent store
	 * Fails if a User with the email already exists
	 * @param user The User to attempt to register/add
	 * @return Whether the User was successfully registered
	 */
	boolean attemptRegister(User user);
	
	
	/**
	 * Returns all the recipes from people that the given User follows
	 * @param user The logged in User who we need to display recipes to
	 * @return A Set of all the Recipes by Users that the given User follows
	 */
	Set<Recipe> getFollowingRecipes(User user);

	void addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto);
	List<RecipeDto> getDashboard(UserDto userDto);

	void addRecipe(Recipe recipe);

	String getMacronutrients(RecipeDto recipe);

	RecipeDto addComment(List<RecipeDto> recipeDto, Comment comment);

	UserDto getAuthor(SimpleUserDto simpleDto);
}