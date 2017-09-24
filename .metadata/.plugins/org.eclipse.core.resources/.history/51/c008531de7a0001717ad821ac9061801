package com.yumyap.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RECIPE")
public class Recipe {
	
	@Id
	@Column(name = "RECIPEID")
	@SequenceGenerator(name = "RID_SEQ", sequenceName="RID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RID_SEQ")
	private int id;
	
//	@OneToOne(fetch=FetchType.EAGER)
//	private Food food;
	
	private String description;
	
	
	private String directions;
	
	private Set<FoodItem> ingredients;
	
	public Recipe() {}
	
	
	public Recipe(int id, Food food, String description, String directions, Set<FoodItem> ingredients) {
		super();
		this.id = id;
//		this.food = food;
		this.description = description;
		this.directions = directions;
		this.ingredients = ingredients;
	}

	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDirections() {
		return directions;
	}


	public void setDirections(String directions) {
		this.directions = directions;
	}


	public void setIngredients(Set<FoodItem> ingredients) {
		this.ingredients = ingredients;
	}


//
//
//	public Food getFood() {
//		return food;
//	}
//	public void setFood(Food food) {
//		this.food = food;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public Set<FoodItem> getIngredients() {
		return ingredients;
	}


	@Override
	public String toString() {
		return "Recipe [id=" + id  + ", description=" + description + ", ingredients=" + ingredients + "]";
	}
	
	
	

}
