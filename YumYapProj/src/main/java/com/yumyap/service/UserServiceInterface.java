package com.yumyap.service;

import java.util.List;

import com.yumyap.beans.*;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

public interface UserServiceInterface {

	// CREATE
	public UserDto createUser(UserDto userDto);
	public boolean addFollower(User user, User follower);

	// comment is added to recipe's list of comments
	public boolean addComment(RecipesDto recipeDto, Comment comment);

	// READ
	// Return a list of user's followers
	public List<User> getFollowing(User user);

	// Returns a String representation of the macronutrients in a recipe
	// calories, carbohydrates, protein, fat
	public String getMacronutrients(RecipesDto recipeDto);

	public String getMacronutrients(FoodItem foodItem);

	// Gets user's dashboard
	public String getDashboard(User user);
	public boolean favoriteRecipe(Recipe recipe, UserDto user);

	// UPDATE
	// rating is added to recipe's rating
	public boolean favoriteRecipe(RecipesDto recipe, UserDto user);

	
	// Verify that user != follower
	// follower is added to user's list of followers
	public boolean addFollowing(User user, User follower);
	
	// READ

	//Gets user's dashboard
	public RecipesDto getDashboard(UserDto user, int page);
	
	// UPDATE
	// rating is added to recipe's rating
	public boolean favoriteRecipe(Recipe recipe, UserDto user);
	public boolean deactivateUser(int userId);

	// VALIDATION
	public UserDto validateUser(UserDto userDto);
	public boolean isEmailValid(String email);
	public boolean isEmailAvailable(String email);
	public UserDto logoutUser(UserDto userDto);

}
