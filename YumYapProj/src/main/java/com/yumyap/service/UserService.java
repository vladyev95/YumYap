package com.yumyap.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
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
		// Validation
		if (userDto == null)
			return null;
		if (userDto.getEmail().equals("") || userDto.getFirstName().equals("") ||
				userDto.getLastName().equals("") || userDto.getPassword().equals("")) {
			System.out.println("One or more required fields are empty");
			return null;
		}
		User thisUser = DaoImpl.getUser(userDto.getEmail());
		if (thisUser != null && thisUser.getEmail().equals(userDto.getEmail())) {
			System.out.println("A user already exists with the given email");
			return null;
		}
		if (!isEmailValid(userDto.getEmail())) {
			System.out.println("Given email is invalid");
			return null;
		}

		try {
			User user = getUserBean(userDto);
			user = DaoImpl.addUser(user);
			userDto.setId(user.getId());

			return userDto;

		} catch (DataIntegrityViolationException e) {
			// This catch block should never be reached. Table validation is
			// done before the try block
			System.out.println("A constraint on the user table was violated");
			return null;
		}
	}
	@Override
	public UserDto addFollowing(UserDto user, UserDto follower) {

		return null;
	}

	@Override
	public Set<User> getFollowing(UserDto user) {

		return user.getFollowing();
	}

	/*
	 * takes the RecipeDto & UserDto
	 * saves new user information to database
	 * returns the updated userDto
	 * @see com.yumyap.service.UserServiceInterface#favoriteRecipe(com.yumyap.dto.RecipeDto, com.yumyap.dto.UserDto)
	 */
	public UserDto favoriteRecipe(RecipeDto recipe, UserDto user) {
		List<RecipeDto> recipeDtos = user.getFavoriteRecipes();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipeDtos.add(recipe);
		user.setFavoriteRecipes(recipeDtos);
		for(RecipeDto r: recipeDtos) {recipes.add(getRecipeBean(r));}
		User u = getUserBean(user);
		DaoImpl.updateUser(u);
		return user;
	}

	private Recipe getRecipeBean(RecipeDto r) {
		return DaoImpl.getRecipeById(r.getId());
	}

	private User getUserBean(UserDto user) {
		return DaoImpl.getUserById(user.getId());
	}

	@Override
	public RecipesDto getDashboard(UserDto user, int page) {
		Set<User> following = user.getFollowing();
		Set<Recipe> recipes;
		if(DaoImpl.getRecipes().size() > 1) {
			Comparator<Recipe> byCreation = (Recipe o1, Recipe o2) -> o1.getTimeCreated().compareTo(o2.getTimeCreated());
			recipes = new ConcurrentSkipListSet<>(byCreation);
			if(following != null) {
				for (User u : following) {
					recipes.addAll(u.getFavoriteRecipes());
				}
			}
			
		}
		else{recipes = new CopyOnWriteArraySet<>(DaoImpl.getRecipes());}
		System.out.println(recipes);
		List<RecipeDto> recs = new ArrayList<RecipeDto>();
		Iterator<Recipe> r = recipes.iterator();
		ArrayList<String> desc = new ArrayList<String>();
		while(r.hasNext()) {
			recs.add(new RecipeDto(r.next()));
		}
		return new RecipesDto(recs);
	}


	@Override
	public ProfileDto getProfile(UserDto userDto) {
		String email = userDto.getEmail();
		ProfileDto profile = new ProfileDto();
		User user = DaoImpl.getUser(email);
		Set<Recipe> recs = user.getFavoriteRecipes();
		List<RecipeDto> rec = new ArrayList<RecipeDto>();
		for(Recipe r: recs) {
			rec.add(new RecipeDto(r));
			}
		profile.setRecipes(rec);
		profile.setUser(user);
		return profile;
	}

	@Override
	public UserDto deactivateUser(UserDto userDto) {
		userDto.setActive(0);
		User user = getUserBean(userDto);
		DaoImpl.updateUser(user);
		return userDto;
	}

	@Override
	public UserDto validateUser(UserDto userDto) {
		User user = DaoImpl.getUser(userDto.getEmail());
		if (user != null && (user.getPassword().equals(userDto.getPassword()))) {
			System.out.println("setting userDto to true");
			userDto = new UserDto(user);
			userDto.setLoggedIn(true);

		} else {
			return null;
		}
		System.out.println("returning user dto" + userDto.toString());

		return userDto;
	}

	@Override
	public boolean isEmailValid(String email) {
		if (email.contains("@") && email.contains(".com"))
			if (email.lastIndexOf('@') < email.lastIndexOf(".com"))
				return true;

		System.out.println("Email addresses must contain a valid domain such as \"something@something.com\"");
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
	
	@Override
	public RecipeDto addRecipe(RecipeDto recipe) {
		DaoImpl.addRecipe(getRecipeBean(recipe));
		return recipe;
	}

	@Override
	public String getMacronutrients(RecipeDto recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeDto addComment(RecipesDto recipeDto, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileDto getProfile(String email) {
		// TODO Auto-generated method stub
		return null;
	}



}
