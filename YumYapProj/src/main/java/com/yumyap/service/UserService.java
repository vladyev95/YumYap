package com.yumyap.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.*;
import com.yumyap.dao.Dao;
import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;


@Service
public class UserService implements ServiceInterface{
	
	@Autowired
	private Dao DaoImpl;
	
	public UserService() {}
	
	public UserService(Dao DaoImpl) {
		super();
		DaoImpl = DaoImpl;
	}


	public RecipesDto getDashboard(UserDto user, int page) {
		Set<User> following = user.getUser().getFollowing();
		Comparator<Recipe> byCreation = (Recipe o1, Recipe o2)->o1.getCreated().compareTo(o2.getCreated());
		Set<Recipe> recipes = new ConcurrentSkipListSet<>(byCreation);
		for(User u: following) {
			recipes.addAll(u.getFavoriteRecipes());
		}
		List<Recipe> recs = new ArrayList<Recipe>();
		Iterator<Recipe> r = recipes.iterator();
		while(r.hasNext()) {
			recs.add(r.next());
		}
		RecipesDto recDto = new RecipesDto();
		recDto.setRecipes(recs);
		return null;
	}

	public ProfileDto getProfile(UserDto userDto) {
		ProfileDto profile = new ProfileDto();
		List<Recipe> recs = userDto.getUser().getFavoriteRecipes();
		profile.setRecipes(recs);
		profile.setUser(userDto.getUser());
		return null;
	}

	@Override
	public User createUser(String email, String password, String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFood(Food food) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFollower(User user, User follower) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RecipesDto addRecipe(RecipesDto recipeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addComment(RecipesDto recipeDto, Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<User> getFollowing(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMacronutrients(RecipesDto recipeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMacronutrients(FoodItem foodItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDashboard(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deactivateUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUser(String username, String password) {
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

	@Override
	public boolean isUsernameAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean favoriteRecipe(Recipe recipe, UserDto user) {
		List<Recipe> recipes = user.getUser().getFavoriteRecipes();
		recipes.add(recipe);
		User u = user.getUser();
		DaoImpl.updateUser(u);
		return true;
	}

	@Override
	public UserDto validateUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto logoutUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
