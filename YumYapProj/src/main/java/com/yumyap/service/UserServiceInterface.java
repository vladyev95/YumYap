package com.yumyap.service;

import java.util.List;

import com.yumyap.beans.*;
import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

public interface UserServiceInterface {

	// CREATE
	public UserDto createUser(UserDto userDto);
	public boolean addFollowing(User user, User follower);
	// comment is added to recipe's list of comments
	public boolean addComment(RecipesDto recipeDto, Comment comment);

	// READ
	public List<User> getFollowing(User user);

	// UPDATE
	// rating is added to recipe's rating
	public boolean favoriteRecipe(RecipeDto recipe, UserDto user);
	public RecipesDto getDashboard(UserDto user, int page);
	public ProfileDto getProfile(String email);
	
	// UPDATE
	// rating is added to recipe's rating
	public boolean deactivateUser(int userId);

	// VALIDATION
	public UserDto validateUser(UserDto userDto);
	public boolean isEmailValid(String email);
	public boolean isEmailAvailable(String email);
	public UserDto logoutUser(UserDto userDto);

}
