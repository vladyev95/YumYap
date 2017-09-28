package com.yumyap.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.yumyap.beans.Recipe;

/**
 * A lightweight version of a Recipe that will be transported to and from the back end
 * Contains enough information to construct meaningful front end view
 * @author vlad
 */
public class RecipeDto {

    private int id;
    
    private SimpleUserDto creator;
    
    private Time timeCreated;

    private String name;

    private String description;

    private String imageUrl;
    
    private double calories, fat, carbs, protein;
    
    private List<String> ingredients = new ArrayList<>();
    
    private List<String> directions = new ArrayList<>();
    
    public RecipeDto() {}
    
    public RecipeDto(Recipe recipe) {
    	this.id = recipe.getId();
    	this.creator = new SimpleUserDto(recipe.getCreator());
    	this.name = recipe.getName();
    	this.description = recipe.getDescription();
    	this.imageUrl = recipe.getImageUrl();
    	this.setTimeCreated(recipe.getTimeCreated());
    	this.calories = recipe.getCalories();
    	this.fat = recipe.getFat();
    	this.carbs = recipe.getCarbs();
    	this.protein = recipe.getProtein();
    	
    	recipe.getIngredients().stream()
    		.forEach(foodItem -> ingredients.add(foodItem.getAmount() + " " + foodItem.getMeasure() + " of " + foodItem.getName()));
    	
    	recipe.getDirections().stream()
    		.forEach(recipeDirection -> directions.add(recipeDirection.getDirection()));
    }

    /**
     * Returns the id of the corresponding Recipe
     * @return The id of the corresponding Recipe
     */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this RecipeDto
	 * @param id The new id of this RecipeDto
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * Returns the creator of the corresponding Recipe as a SimpleUserDto
	 * @return The creator of the corresponding Recipe as a SimpleUserDto
	 */
	public SimpleUserDto getCreator() {
		return creator;
	}
	
	/**
	 * Sets the creator of this RecipeDto
	 * @param creator The new creator of this RecipeDto
	 */
	public void setCreator(SimpleUserDto creator) {
		this.creator = creator;
	}
	
	/**
	 * Returns the Time that the corresponding Recipe was created
	 * @return The Time that the corresponding Recipe was created
	 */
	public Time getTimeCreated() {
		return timeCreated;
	}
	
	/**
	 * Sets the timeCreated
	 * @param timeCreated The new Time
	 */
	public void setTimeCreated(Time timeCreated) {
		this.timeCreated = timeCreated;
	}

	/**
	 * Returns the name of the corresponding Recipe
	 * @return The name of the corresponding Recipe
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this RecipeDto
	 * @param name The new name of this RecipeDto
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the corresponding Recipe
	 * @return The description of the corresponding Recipe
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of this RecipeDto
	 * @param description The new description of this RecipeDto
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the imageUrl of the corresponding Recipe
	 * @return The imageUrl of the corresponding Recipe
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the imageUrl of this RecipeDto
	 * @param imageUrl The imageUrl of this RecipeDto
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

	/**
	 * Returns a nice String representation of this RecipeDto
	 * @return A nice String representation of this RecipeDto
	 */
	@Override
	public String toString() {
		return "RecipeDto { id: " + id + 
				", creator: " + creator + 
				", timeCreated: " + timeCreated +
				", name: " + name + 
				", description: " + description +
				", imageUrl: " + imageUrl +
				", calories: " + calories +
				", fat: " + fat +
				", carbs: " + carbs +
				", protein: " + protein + 
				", ingredients: " + ingredients +
				", directions: " + directions + " }";
	}

	
}
