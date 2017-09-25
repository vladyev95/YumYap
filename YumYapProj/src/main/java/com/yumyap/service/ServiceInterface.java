package com.yumyap.service;

import java.sql.Date;
import java.util.List;

import com.yumyap.beans.*;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

public interface ServiceInterface {

	// CREATE
	public UserDto createUser(UserDto userDto);
	
	// Verify that user != follower
	// follower is added to user's list of followers
	public boolean addFollowing(User user, User follower);
	
	// READ
	// Return a list of user's followers
	public List<User> getFollowing(User user);

	//Gets user's dashboard
	public RecipesDto getDashboard(UserDto user, int page);
	
	// UPDATE
	// rating is added to recipe's rating
	public boolean favoriteRecipe(Recipe recipe, UserDto user);
	public boolean deactivateUser(int userId);

	
	// VALIDATION
	public boolean validateUser(String username, String password);
	public boolean isEmailValid(String email);
	public boolean isEmailAvailable(String email);
	public UserDto validateUser(UserDto userDto);
	public UserDto logoutUser(UserDto userDto);

}
