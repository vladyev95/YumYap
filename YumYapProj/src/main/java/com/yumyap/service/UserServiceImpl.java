package com.yumyap.service;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;

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
		for (RecipeDto r : recipeDtos) {
			recipes.add(new Recipe(r));
		}
		User u = new User(user);
		DaoImpl.updateUser(u);
		return user;
	}

	@Override
	public RecipesDto getDashboard(UserDto user, int page) {
		Set<User> following = user.getFollowing();
		Set<Recipe> recipes;
		if (DaoImpl.getRecipes().size() > 1) {
			Comparator<Recipe> byCreation = (Recipe o1, Recipe o2) -> o1.getCreated().compareTo(o2.getCreated());
			recipes = new ConcurrentSkipListSet<>(byCreation);
			if (following != null) {
				for (User u : following) {
					recipes.addAll(u.getFavoriteRecipes());
				}
			}

		} else {
			recipes = new CopyOnWriteArraySet<>(DaoImpl.getRecipes());
		}
		System.out.println(recipes);
		List<RecipeDto> recs = new ArrayList<RecipeDto>();
		Iterator<Recipe> r = recipes.iterator();
		ArrayList<String> desc = new ArrayList<String>();
		while (r.hasNext()) {
			recs.add(new RecipeDto(r.next()));
		}
		return new RecipesDto(recs);
	}

	@Override
	public ProfileDto getProfile(UserDto userDto) {
		String email = userDto.getEmail();
		ProfileDto profile = new ProfileDto();
		User user = DaoImpl.getUser(email);
		List<Recipe> recs = user.getFavoriteRecipes();

		List<RecipeDto> rec = new ArrayList<RecipeDto>();
		for (Recipe r : recs) {
			rec.add(new RecipeDto(r));
			System.out.println(r);
		}

		rec.add(new RecipeDto(1, null, null, "name", "description", null, "image", null));
		profile.setRecipes(rec);
		profile.setUser(user);
		return profile;
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
}
