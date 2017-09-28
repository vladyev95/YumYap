package com.yumyap.dto;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

public class RecipeDto {

    private int id;
    
    private int creatorId;

    private String name;

    private String description;

    private String imageUrl;
    
    public RecipeDto() {}
    
    public RecipeDto(Recipe recipe) {
    	super();
    	this.id = recipe.getId();
    	User creator = recipe.getCreator();
    	this.creatorId = (creator != null) ? creator.getId() : -1;
    	this.name = recipe.getName();
    	this.description = recipe.getDescription();
    	this.imageUrl = recipe.getImageUrl();
    }

	public RecipeDto(int id, int creatorId, String name, String description, String imageUrl) {
		super();
		this.id = id;
		this.creatorId = creatorId;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
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
		return "RecipeDto [id=" + id + ", creatorId=" + creatorId + ", name=" + name + ", description=" + description
				+ ", imageUrl=" + imageUrl + "]";
	}
}
