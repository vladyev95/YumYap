package com.yumyap.dto;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

/**
 * @author Surplus User / Recipe DTO
 */
public class UsrRecDto {

	private User user;
	private Recipe recipe;

	public UsrRecDto() {
		super();
	}

	public UsrRecDto(User user, Recipe recipe) {
		super();
		this.user = user;
		this.recipe = recipe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "UsrRecDto [user=" + user.getId() + ", recipe=" + recipe.getId() + "]";
	}

}
