package com.yumyap.service;

import java.util.List;

<<<<<<< HEAD
import com.yumyap.beans.Comment;
import com.yumyap.beans.Food;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.User;
=======
import com.yumyap.beans.*;
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

public interface ServiceInterface {

	// CREATE
<<<<<<< HEAD
	public UserDto createUser(UserDto userDto);
=======
	public User createUser(String email, String password, String firstname, String lastname);
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
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
<<<<<<< HEAD
	// Gets user's dashboard
=======
	//Gets user's dashboard
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
	public String getDashboard(User user);

	
	// UPDATE
	// rating is added to recipe's rating
	public boolean favoriteRecipe(RecipesDto recipe, UserDto user);
	public boolean deactivateUser(int userId);

	
	// VALIDATION
	public UserDto validateUser(UserDto userDto);
	public boolean isEmailValid(String email);
	public boolean isEmailAvailable(String email);
	public boolean isUsernameAvailable(String username);
	public UserDto logoutUser(UserDto userDto);

}
