package com.yumyap.beans;

import java.sql.Time;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

@Table(name = "RECIPE")
public class Recipe {

	@Id
	@Column(name = "RECIPEID")
	@SequenceGenerator(name = "RID_SEQ", sequenceName = "RID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RID_SEQ")
	private int id;

	private Time created;

	private int creatorId;

	private String description;

	private String directions;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Recipes_Ingredients", joinColumns = { @JoinColumn(name = "recipe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fooditem_id") })
	private Set<FoodItem> ingredients;

	public Recipe() {
		super();
	}

	public Recipe(int id, Time created, int creatorId, String description, String directions,
			Set<FoodItem> ingredients) {
		super();
		this.id = id;
		this.created = created;
		this.creatorId = creatorId;
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

	public Time getCreated() {
		return created;
	}

	public void setCreated(Time created) {
		this.created = created;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Set<FoodItem> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<FoodItem> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", created=" + created + ", creatorId=" + creatorId + ", description=" + description
				+ ", directions=" + directions + ", ingredients=" + ingredients + "]";
	}

}
