package com.yumyap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.UserDto;

/**
 * An implementation of a UserService
 * Do not use this as the variable type, user UserService
 * @author vlad
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private Dao dao;

	@Autowired
	public UserServiceImpl(Dao dao) {
		this.dao = dao;
	}

	@Override
	public User attemptLogin(String email, String password) {
		log.trace("attemptLogin(" + email + ", " + password + ") ");
		return dao.getUserByEmailAndPassword(email, password);
	}

	@Override
	public boolean attemptRegister(User user) {
		return dao.addUser(user);
	}

	@Override
	public Set<Recipe> getFollowingRecipes(User user) {
		return null;
		//return dao.getFollowingRecipesById(user.getId());
	}

	/*
	 * takes the RecipeDto & UserDto
	 * saves new user information to database
	 * returns the updated userDto
	 * @see com.yumyap.service.UserServiceInterface#favoriteRecipe(com.yumyap.dto.RecipeDto, com.yumyap.dto.UserDto)
	 */
	@Override
	public void addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto) {
		User user = dao.getUserById(userDto.getId());
		Recipe recipe = dao.getRecipeById(recipeDto.getId());
		user.getFavoriteRecipes().add(recipe);
		dao.updateUser(user);
	}

	@Override
	public List<RecipeDto> getDashboard(UserDto userDto) {
		List<RecipeDto> recipes = new ArrayList<>();
		log.trace("in getDashboard(userDto= "+userDto+")");
		
		User user = dao.getUserById(userDto.getId());
		if (user == null) return recipes;
		
		Set<User> following = user.getFollowing();
		
		recipes.addAll(
			dao.getRecipesByUser(user)
			.stream().map(recipe -> new RecipeDto(recipe))
			.collect(Collectors.toList()));		
		
		following.stream()
			.map(followedUser -> dao.getRecipesByUser(followedUser))
			.flatMap(list -> list.stream())
			.map(recipe -> new RecipeDto(recipe))
			.forEach(recipeDto -> recipes.add(recipeDto));
		
		// TODO: Sort recipes by date created
		
		return recipes;
	}

	/*
	@Override
	public UserDto logoutUser(UserDto userDto) {
		if (!userDto.isLoggedIn()) {
			logger.trace("No user is currently logged in");
			return userDto;
		}
		logger.trace("Logging out user: " + userDto.getEmail());
		userDto.setLoggedIn(false);
		return userDto;
	}
	*/
	
	@Override
	public void addRecipe(Recipe recipe) {
		dao.addRecipe(recipe);
	}

	@Override
	public String getMacronutrients(RecipeDto recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeDto addComment(List<RecipeDto> recipeDto, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getProfile(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}
}