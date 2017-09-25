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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yumyap.dto.RecipeDto;

@Entity
@Table(name = "RECIPE")
public class Recipe {

	@Id
	@Column(name = "RECIPEID")
	@SequenceGenerator(name = "RID_SEQ", sequenceName = "RID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RID_SEQ")
	private int id;

	private Time created;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User creator;
	
	private String name;
	
	private String description;

	private String directions;
	
	private String image;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Recipes_Ingredients", joinColumns = { @JoinColumn(name = "recipe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fooditem_id") })
	private Set<FoodItem> ingredients;

	public Recipe() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	public Recipe(RecipeDto recipe) {
		this.id = recipe.getId();
		this.created = recipe.getCreated();
		this.creator = recipe.getCreator();
		this.name = recipe.getName();
		this.description = recipe.getDescription();
		this.directions = "";
		for(String s : recipe.getDirections()) {
			directions += s;
		}
		this.image = recipe.getImage();
		this.ingredients = recipe.getIngredients();
	}
		
	public Recipe(int id, Time created, User creator, String name, String description, String directions, String image,
			Set<FoodItem> ingredients) {
		super();
		this.id = id;
		this.created = created;
		this.creator = creator;
		this.name = name;
		this.description = description;
		this.directions = directions;
		this.image = image;
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

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", created=" + created + ", creator=" + creator + ", name=" + name
				+ ", description=" + description + ", directions=" + directions + ", image=" + image + ", ingredients="
				+ ingredients + "]";
	}

}
