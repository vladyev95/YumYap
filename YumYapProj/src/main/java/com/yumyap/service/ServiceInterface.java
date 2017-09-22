package com.yumyap.service;

import java.sql.Date;
import java.util.List;

import com.yumyap.beans.*;
import com.yumyap.dto.UserDto;

public interface ServiceInterface {

	public UserDto authenticateUser(UserDto userDto);
	public UserDto createUser(UserDto userDto);
	
	
	// CREATE
	public User createUser(String username, String password, String firstname, String lastname);
	// food is added to the database
	public boolean addFood(Food food);
	// Verify that user != follower
	// follower is added to user's list of followers
	public boolean addFollower(User user, User follower);
	public Recipe addRecipe(Recipe recipe);
	// comment is added to recipe's list of comments
	public boolean addComment(Recipe recipe, Comment comment);
	// log is added to user's list of logs


	
	// READ
	// Return a list of user's followers
	public List<User> getFollowers(User user);
	// Returns a String representation of the macronutrients in a recipe
	// calories, carbohydrates, protein, fat
	public String getMacronutrients(Recipe recipe);
	public String getMacronutrients(Food food);
	public String getMacronutrients(FoodItem foodItem);
	// Returns the total macronutrients consumed in date
	public String getNutrientsForDate(User user, Date date);

	
	// UPDATE
	// rating is added to recipe's rating
	public boolean rateRecipe(Recipe recipe, int rating);
	public boolean deactivateUser(int userId);

	
	// VALIDATION
	public boolean validateUser(String username, String password);
	public boolean isEmailValid(String email);
	public boolean isEmailAvailable(String email);
	public boolean isUsernameAvailable(String username);

}
