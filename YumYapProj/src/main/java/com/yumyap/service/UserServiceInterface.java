package com.yumyap.service;

import java.util.List;
import java.util.Set;

import com.yumyap.beans.*;
import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

public interface UserServiceInterface {

	// CREATE
	public UserDto createUser(UserDto userDto);
	public UserDto addFollowing(UserDto user, UserDto follower);


	// READ
	public Set<User> getFollowing(UserDto user);

	// UPDATE
	// rating is added to recipe's rating
	public UserDto favoriteRecipe(RecipeDto recipe, UserDto user);
	public RecipesDto getDashboard(UserDto user, int page);
	public ProfileDto getProfile(String email);

	// UPDATE
	// rating is added to recipe's rating
	public UserDto deactivateUser(UserDto userDto);

	// VALIDATION
	public UserDto validateUser(UserDto userDto);
	public boolean isEmailValid(String email);
	public UserDto logoutUser(UserDto userDto);
	ProfileDto getProfile(UserDto userDto);
	RecipeDto addRecipe(RecipeDto recipe);
	String getMacronutrients(RecipeDto recipe);
	RecipeDto addComment(RecipesDto recipeDto, Comment comment);

}
