package com.yumyap.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.RecipeDirection;
import com.yumyap.beans.User;

public class RecipeDto {
	
	private int id;
	
	private Time created;

	private User creator;
	
	private String name;
	
	private String description;
	
	private List<RecipeDirection> directions;
	
	private String imageUrl;
	
	private Set<FoodItem> ingredients;
	
	public RecipeDto() {}
	
	public RecipeDto(int id, Time created, User creator, String name, String description, List<RecipeDirection> directions,
			String imageUrl, Set<FoodItem> ingredients) {
		super();
		this.id = id;
		this.created = created;
		this.creator = creator;
		this.name = name;
		this.description = description;
		this.directions = directions;
		this.imageUrl = imageUrl;
		this.ingredients = ingredients;
	}
	
	public RecipeDto(Recipe recipe) {
		this.id = recipe.getId();
		this.created = recipe.getTimeCreated();
		this.creator = recipe.getCreator();
		this.name = recipe.getName();
		this.description = recipe.getDescription();
		this.directions = recipe.getDirections();
		this.imageUrl = recipe.getImageUrl();
		this.ingredients = recipe.getIngredients();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getCreated() {
		return created;
	}

	public void setCreated(Time created) {
		this.created = created;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RecipeDirection> getDirections() {
		return directions;
	}

	public void setDirections(List<RecipeDirection> directions) {
		this.directions = directions;
	}

	public String getImage() {
		return imageUrl;
	}

	public void setImage(String image) {
		this.imageUrl = image;
	}

	public Set<FoodItem> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<FoodItem> ingredients) {
		this.ingredients = ingredients;
	}
	
}
