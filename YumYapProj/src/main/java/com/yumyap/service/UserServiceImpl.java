package com.yumyap.service;

import java.util.ArrayList;
import java.util.Collections;
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
import com.yumyap.dto.SimpleUserDto;
import com.yumyap.dto.UserDto;

/**
 * An implementation of a UserService
 * Do not use this as the variable type, user UserService
 * @author vlad
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private Dao dao;

	@Autowired
	public UserServiceImpl(Dao dao) {
		this.dao = dao;
	}

	@Override
	public User attemptLogin(String email, String password) {
		logger.trace("attemptLogin(" + email + ", " + password + ") ");
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
		logger.trace("in getDashboard(userDto= "+userDto+")");
		
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
		
		Collections.sort(recipes, (r1, r2) -> -r1.getDateCreated().compareTo(r2.getDateCreated()));
		
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
		User creator = recipe.getCreator();
		if (creator != null) {
			Set<Recipe> favorites = creator.getFavoriteRecipes();
			favorites.add(recipe);
			User creatorDb = dao.getUserById(creator.getId());
			recipe.setCreator(creatorDb);
			dao.addRecipe(recipe);
		}

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

	@Override
	public boolean addFollower(UserDto user, UserDto follower) {
		SimpleUserDto follow = new SimpleUserDto(follower);
		
		if (user.getFollowing().contains(follow))
			return false;
		else {
			user.getFollowing().add(follow);
			return true;
		}
	}

	public UserDto simpleUserDtoToUserDto(SimpleUserDto simpleUserDto) {
		logger.trace("simpleUserDtoToUserDto() by " + simpleUserDto);
		return new UserDto(dao.getUserById(simpleUserDto.getId()));
	}

}