package com.yumyap.beans;

import java.sql.Time;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "recipes")
public class Recipe {
	
	private static final Logger logger = Logger.getLogger(Recipe.class);

	@Id
	@Column(name = "recipe_id")
	@SequenceGenerator(name = "recipe_id_sequence", sequenceName = "recipe_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_id_sequence")
	private int id;

	@Column(name = "time_created")
	private Time timeCreated;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User creator;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipe_direction_id")
	private Set<RecipeDirection> directions;
	
	@Column(name = "image_url", nullable = false)
	private String imageUrl;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Recipes_Ingredients", 
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "food_item_id"))
	private Set<FoodItem> ingredients;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private Set<Comment> comments;

	public Recipe() {}
	
	public Recipe(Time timeCreated, 
			String name, 
			String description, 
			Set<RecipeDirection> directions, 
			String imageUrl,
			Set<FoodItem> ingredients) {
		this.timeCreated = timeCreated;
		this.name = name;
		this.description = description;
		this.directions = directions;
		this.imageUrl = imageUrl;
		this.ingredients = ingredients;
	}


	/**
     * Returns the name of this recipe
	 * @return The name of this recipe
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the this recipe
	 * @param name The new name for this recipe
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the imageUrl for this recipe
	 * @return The imageUrl for this recipe
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the imageUrl for this image
	 * @param imageUrl The new imageUrl for this image
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/**
	 * Returns the id of this image
	 * @return The id of this image
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this image
	 * @param id The new id of this image
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the java.sql.Time representing the time this Recipe was created
	 * @return The java.sql.Time representing the time this Recipe was created
	 */
	public Time getTimeCreated() {
		return timeCreated;
	}
	
	/**
	 * Sets the time representing the time this Recipe was created
	 * @param timeCreated
	 */
	public void setCreated(Time timeCreated) {
		this.timeCreated = timeCreated;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<RecipeDirection> getDirections() {
		return directions;
	}

	public void setDirections(Set<RecipeDirection> directions) {
		this.directions = directions;
	}

	public Set<FoodItem> getIngredients() {
		return ingredients;
	}

	@Override
	public String toString() {
		return "Recipe { id: " + id + 
				", timeCreated: " + timeCreated + 
				", creator: " + creator + 
				", name: " + name +
				", description: " + description + 
				", directions: " + directions + 
				", imageUrl: " + imageUrl + 
				", ingredients: " + ingredients + " }";
	}

}
