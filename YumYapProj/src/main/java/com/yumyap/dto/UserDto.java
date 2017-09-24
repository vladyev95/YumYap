package com.yumyap.dto;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

public class UserDto {

	private int id;
	private Set<User> following;
	private String firstname;
	private String lastname;
	private String password;
	private String Username;
	private int active;
	private List<Recipe> favoriteRecipes;

	private boolean loggedIn;

	public UserDto() {
		super();
	}

	public UserDto(int id, Set<User> following, String firstname, String lastname, String password, String Username,
			int active, List<Recipe> favoriteRecipes, boolean loggedIn) {
		super();
		this.id = id;
		this.following = following;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.Username = Username;
		this.active = active;
		this.favoriteRecipes = favoriteRecipes;
		this.loggedIn = loggedIn;
	}

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

	public String getUsername() {
		return Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", following=" + following + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", Username=" + Username + ", active=" + active + ", favoriteRecipes="
				+ favoriteRecipes + ", loggedIn=" + loggedIn + "]";
	}

}
