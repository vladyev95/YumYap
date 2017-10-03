package com.yumyap.dto;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

/**
 * @author Surplus User / Recipe DTO
 */
public class UsrRecDto {

	private UserDto user;
	private RecipeDto recipe;

	public UsrRecDto() {
		super();
	}

	public UsrRecDto(UserDto user, RecipeDto recipe) {
		super();
		this.user = user;
		this.recipe = recipe;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUserDto(UserDto user) {
		this.user = user;
	}

	public RecipeDto getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeDto recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "UsrRecDto [user=" + user.getId() + ", recipe=" + recipe.getId() + "]";
	}

}
