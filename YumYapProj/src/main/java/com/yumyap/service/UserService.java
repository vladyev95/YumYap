package com.yumyap.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Food;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dao.DaoImpl;
import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private Dao DaoImpl;

	public UserService() {
	}

	public UserService(Dao DaoImpl) {
		super();
		this.DaoImpl = DaoImpl;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		// FIXME need username validation
		// FIXME add the rest of the necessary fields
		System.out.println(userDto);
		User user = new User(userDto);
		user = DaoImpl.addUser(user);
		userDto.setId(user.getId());
		return userDto;
	}
	@Override
	public UserDto addFollowing(UserDto user, UserDto follower) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getFollowing(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/*
	 * takes the RecipeDto & UserDto
	 * saves new user information to database
	 * returns the updated userDto
	 * @see com.yumyap.service.UserServiceInterface#favoriteRecipe(com.yumyap.dto.RecipeDto, com.yumyap.dto.UserDto)
	 */
	public UserDto favoriteRecipe(RecipeDto recipe, UserDto user) {
		List<Recipe> recipes = user.getFavoriteRecipes();
		recipes.add(new Recipe(recipe));
		user.setFavoriteRecipes(recipes);
		User u = new User(user);
		DaoImpl.updateUser(u);
		return user;
	}

	@Override
	public RecipesDto getDashboard(UserDto user, int page) {
		Set<User> following = user.getFollowing();
		Comparator<Recipe> byCreation = (Recipe o1, Recipe o2) -> o1.getCreated().compareTo(o2.getCreated());
		Set<Recipe> recipes = new ConcurrentSkipListSet<>(byCreation);
		for (User u : following) {
			recipes.addAll(u.getFavoriteRecipes());
		}
		List<RecipeDto> recs = new ArrayList<RecipeDto>();
		Iterator<Recipe> r = recipes.iterator();
		ArrayList<String> desc = new ArrayList<String>();
		while(r.hasNext()) {
			recs.add(new RecipeDto(r.next()));
		}
		return new RecipesDto(recs);
	}


	@Override
	public ProfileDto getProfile(String email) {
		ProfileDto profile = new ProfileDto();
		User user = DaoImpl.getUser(email);
		List<Recipe> recs = user.getFavoriteRecipes();
		profile.setRecipes(recs);
		profile.setUser(user);
		return null;
	}

	@Override
	public boolean deactivateUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDto validateUser(UserDto userDto) {
		User user = DaoImpl.getUser(userDto.getEmail());

		if (user != null && (user.getPassword().equals(userDto.getPassword()))) {
			System.out.println("setting userdto to true");
			userDto.setLoggedIn(true);
		} else {
			return null;
		}
		System.out.println("returning user dto" + userDto.toString());
		return userDto;
	}

	@Override
	public boolean isEmailValid(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailAvailable(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDto logoutUser(UserDto userDto) {
		if (!userDto.isLoggedIn()) {
			System.out.println("No user is currently logged in");
			return userDto;
		}
		System.out.println("Logging out user: " + userDto.getEmail());
		userDto.setLoggedIn(false);
		return userDto;
	}



}
