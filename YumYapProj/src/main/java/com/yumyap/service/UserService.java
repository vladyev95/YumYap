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

	public ProfileDto getProfile(UserDto userDto) {
		ProfileDto profile = new ProfileDto();
		List<Recipe> recs = userDto.getFavoriteRecipes();
		profile.setRecipes(recs);
		profile.setUser(new User(userDto));
		return null;
	}
	
	public boolean favoriteRecipe(Recipe recipe, UserDto user) {
		List<Recipe> recipes = user.getFavoriteRecipes();
		recipes.add(recipe);
		User u = new User(user);
		DaoImpl.updateUser(u);
		return true;
	}
	
//	public ProfileDto getProfile(String email) {
//		ProfileDto profile = new ProfileDto();
//		List<Recipe> recs = userDto.getUser().getFavoriteRecipes();
//		profile.setRecipes(recs);
//		profile.setUser(userDto.getUser());
//		return null;
//	}


	// Verify that user != follower
	// follower is added to user's list of followers
	@Override
	public boolean addFollowing(User user, User follower) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<User> getFollowing(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deactivateUser(int userId) {
		// TODO Auto-generated method stub
		return false;
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


	public UserDto createUser(UserDto userDto) {
		// FIXME need username validation
		// FIXME add the rest of the necessary fields
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user = DaoImpl.addUser(user);
		userDto.setId(user.getId());
		return userDto;
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
