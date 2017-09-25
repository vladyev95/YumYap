package com.yumyap.service;

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
import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

@Service
public class UserService implements ServiceInterface {

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
		List<Recipe> recs = new ArrayList<Recipe>();
		Iterator<Recipe> r = recipes.iterator();
		while (r.hasNext()) {
			recs.add(r.next());
		}
		RecipesDto recDto = new RecipesDto();
		recDto.setRecipes(recs);
		return null;
	}

	public ProfileDto getProfile(UserDto userDto) {
		ProfileDto profile = new ProfileDto();
		profile.setRecipes(userDto.getFavoriteRecipes());
		User u = new User();
		u.setFirstname(userDto.getFirstname());
		u.setLastname(userDto.getLastname());
		profile.setUser(u);
		return null;
	}

	@Override
	public boolean favoriteRecipe(RecipesDto recipe, UserDto user) {

		return false;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		// FIXME need username validation
		// FIXME add the rest of the necessary fields
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user = DaoImpl.addUser(user);
		userDto.setId(user.getId());
		return userDto;
	}

	@Override
	public UserDto validateUser(UserDto userDto) {
		User user = DaoImpl.getUser(userDto.getUsername());

		if (user != null && (user.getPassword().equals(userDto.getPassword()))) {
			System.out.println("setting userdto to true");
			userDto.setLoggedIn(true);
		} else {
			return null;
		}
		System.out.println("---------------------returning user dto" + userDto.toString());
		return userDto;
	}

	@Override
	public UserDto logoutUser(UserDto userDto) {
		if (!userDto.isLoggedIn()) {
			System.out.println("No user is currently logged in");
			return userDto;
		}
		System.out.println("Logging out user: " + userDto.getUsername());
		userDto.setLoggedIn(false);
		return userDto;
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

}
