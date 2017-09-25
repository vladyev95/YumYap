package com.yumyap.service;

import java.sql.Date;
import java.util.List;

import com.yumyap.beans.*;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

public interface ServiceInterface {

	// CREATE
	public User createUser(String email, String password, String firstname, String lastname);
	// food is added to the database
	public boolean addFood(Food food);
	// Verify that user != follower
	// follower is added to user's list of followers
	public boolean addFollower(User user, User follower);
	public RecipesDto addRecipe(RecipesDto recipeDto);
	// comment is added to recipe's list of comments
	public boolean addComment(RecipesDto recipeDto, Comment comment);

	
	// READ
	// Return a list of user's followers
	public List<User> getFollowing(User user);
	// Returns a String representation of the macronutrients in a recipe
	// calories, carbohydrates, protein, fat
	public String getMacronutrients(RecipesDto recipeDto);
	public String getMacronutrients(FoodItem foodItem);
	//Gets user's dashboard
	public String getDashboard(User user);

	
	// UPDATE
	// rating is added to recipe's rating
	public boolean favoriteRecipe(RecipesDto recipe, UserDto user);
	public boolean deactivateUser(int userId);

	
	// VALIDATION
	public boolean validateUser(String username, String password);
	public boolean isEmailValid(String email);
	public boolean isEmailAvailable(String email);
	public boolean isUsernameAvailable(String username);

}
