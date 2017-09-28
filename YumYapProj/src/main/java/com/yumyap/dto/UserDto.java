package com.yumyap.dto;

import java.util.ArrayList;
import java.util.List;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

/**
 * A lightweight User data transfer object, slightly heavier than UserDto
 * Contains information about Users you follow, and favorite Recipes
 * @author vlad
 */
public class UserDto {
	
	private int id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private List<SimpleUserDto> following = new ArrayList<>();
	
	private List<RecipeDto> favoriteRecipes = new ArrayList<>();
	
	public UserDto() {}
	
	public UserDto(User user) {
		super();
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		for (User u : user.getFollowing()) {
			this.following.add(new SimpleUserDto(u));
		}
		for (Recipe r : user.getFavoriteRecipes()) {
			this.favoriteRecipes.add(new RecipeDto(r));
		}
	}

	public UserDto(int id, String email, String firstName, String lastName, List<SimpleUserDto> following,
			List<RecipeDto> favoriteRecipes) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.following = following;
		this.favoriteRecipes = favoriteRecipes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<SimpleUserDto> getFollowing() {
		return following;
	}

	public void setFollowing(List<SimpleUserDto> following) {
		this.following = following;
	}

	public List<RecipeDto> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<RecipeDto> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	/**
	 * Returns a nice String representation of a UserDto
	 * @return A nice String representation of a UserDto
	 */
	@Override
	public String toString() {
		return "UserDto { id: " + id + 
				", email: " + email + 
				", firstName: " + firstName + 
				", lastName: " + lastName +
				", following: " + following + 
				", favoriteRecipes: " + favoriteRecipes + " }";
	}
}
