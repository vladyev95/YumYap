package com.yumyap.dto;

import java.util.List;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

public class ProfileDto {

	private List<RecipeDto> recipes;
	private User user;

	public List<RecipeDto> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeDto> recipes) {
		this.recipes = recipes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
