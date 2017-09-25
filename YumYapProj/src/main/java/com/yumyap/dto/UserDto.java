package com.yumyap.dto;

import java.util.List;
import java.util.Set;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

public class UserDto {

	private int id;
	
	private List<Recipe> favoriteRecipes;
	
	private Set<User> following;
	
	private String firstname;
	
	private String lastname;
	
	private String password;
	
	private String email;
	

	private int active;
	
	private boolean loggedIn;
	
	public UserDto(int id, Set<User> following, String firstname, String lastname, String password, String email,
			int active, boolean loogedIn, List<Recipe> favoriteRecipes) {
		super();
		this.id = id;
		this.following = following;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.active = active;
		this.loggedIn = loogedIn;
		this.favoriteRecipes = favoriteRecipes;
	}

	

	public UserDto() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loogedIn) {
		this.loggedIn = loogedIn;
	}



	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}



	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}
	
	
}
