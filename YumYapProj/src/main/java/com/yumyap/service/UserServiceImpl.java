package com.yumyap.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
	public List<RecipeDto> getUsersRecipes(SimpleUserDto simpleUserDto) {
		logger.trace("getUsersRecipes() by " + simpleUserDto);
		User user = dao.getUserById(simpleUserDto.getId());
		List<RecipeDto> recipes = dao.getUsersRecipes(user).stream()
			.map(recipe -> new RecipeDto(recipe)).collect(Collectors.toList());
		 
		recipes.sort((r1, r2) -> {
			if (r1.getDateCreated() == null && r2.getDateCreated() == null)
				return 0;
			else if (r1.getDateCreated() == null)
				return 1;
			else if (r2.getDateCreated() == null)
				return -1;
			return -r1.getDateCreated().compareTo(r2.getDateCreated());
		});
		return recipes;
	}
	
	
	@Override
	public Set<Recipe> getFollowingRecipes(User user) {
		return null;
		//return dao.getFollowingRecipesById(user.getId());
	}
	
	@Override
	public boolean addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto) throws NullPointerException {
		User user = dao.getUserById(userDto.getId());
		Recipe recipe = dao.getRecipeById(recipeDto.getId());
		
		if (user.getFavoriteRecipes().add(recipe)) {
			dao.updateUser(user);
			return true;
		} else return false;
	}

	@Override
	public List<RecipeDto> getDashboard(UserDto userDto) {
		Set<RecipeDto> recipes = new LinkedHashSet<>();
		logger.trace("in getDashboard() by " + userDto);
		
		User user = dao.getUserById(userDto.getId());
		logger.trace("getDashboard() User: " + user);
		
		if (user == null) 
			return new ArrayList<>();
		
		Set<User> following = user.getFollowing();
		
		
		dao.getUsersRecipes(user)
			.stream()
			.map(recipe -> new RecipeDto(recipe))
			.forEach(recipe -> recipes.add(recipe));
	
		if (following == null || following.isEmpty()) {
			logger.trace("getDashBoard() returning recipes: " + recipes);
			return new ArrayList<>(recipes);
		}
		
		following.stream()
			.map(followedUser -> dao.getUsersRecipes(followedUser))
			.flatMap(list -> list.stream())
			.map(recipe -> new RecipeDto(recipe))
			.forEach(recipeDto -> recipes.add(recipeDto));
	
		
		logger.trace("getDashBoard() returning recipes: " + recipes);
		return new ArrayList<>(recipes);
	}

	
	@Override
	public void addRecipe(Recipe recipe) throws NullPointerException {
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

	public UserDto simpleUserDtoToUserDto(SimpleUserDto simpleUserDto) throws NullPointerException {
		logger.trace("simpleUserDtoToUserDto() by " + simpleUserDto);
		return new UserDto(dao.getUserById(simpleUserDto.getId()));
	}

	@Override
	public List<RecipeDto> searchRecipe(String search) {
		List<RecipeDto> recipes = new ArrayList<RecipeDto>();
		dao.getRecipes(search)
		.stream()
		.map(recipe -> new RecipeDto(recipe))
		.forEach(recipe -> recipes.add(recipe));
		System.out.println(recipes);
		return recipes;
	}

}