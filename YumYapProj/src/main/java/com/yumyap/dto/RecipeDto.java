package com.yumyap.dto;

import com.yumyap.beans.Recipe;

/**
 * A lightweight version of a Recipe that will be transported to and from the back end
 * @author vlad
 */
public class RecipeDto {

    private int id;
    
    private SimpleUserDto creator;

    private String name;

    private String description;

    private String imageUrl;
    
    public RecipeDto() {}
    
    public RecipeDto(Recipe recipe) {
    	this.id = recipe.getId();
    	this.creator = new SimpleUserDto(recipe.getCreator());
    	this.name = recipe.getName();
    	this.description = recipe.getDescription();
    	this.imageUrl = recipe.getImageUrl();
    }

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SimpleUserDto getCreator() {
		return creator;
	}
	
	public void setCreator(SimpleUserDto creator) {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "RecipeDto { id: " + id + 
				", creator: " + creator + 
				", name: " + name + 
				", description: " + description
				+ ", imageUrl: " + imageUrl + " }";
	}
}
