package com.yumyap.beans;

import java.sql.Time;
import java.util.List;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * An object representing a Recipe object that Users may create
 * @author vlad
 */
@Component
@Entity
@Table(name = "recipes")
public class Recipe {
	
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
	private List<RecipeDirection> directions;
	
	@Column(name = "image_url", nullable = false)
	private String imageUrl;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "recipes_ingredients", 
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
			List<RecipeDirection> directions, 
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

	/**
	 * Returns the User that created this Recipe
	 * @return The User that created this Recipe
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * Sets the User that created this Recipe
	 * @param creator The new creator of this Recipe
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * Returns the description of this Recipe
	 * @return The description of this Recipe
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of this Recipe
	 * @param description The new description of this Recipe
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the RecipeDirections for this Recipe
	 * @return The RecipeDirections for this Recipe
	 */
	public List<RecipeDirection> getDirections() {
		return directions;
	}

	/**
	 * Sets the RecipeDirections for this Recipe
	 * @param directions The new RecipeDirections for this Recipe
	 */
	public void setDirections(List<RecipeDirection> directions) {
		this.directions = directions;
	}

	/**
	 * Returns the ingredients for this Recipe
	 * @return The ingredients for this Recipe
	 */
	public Set<FoodItem> getIngredients() {
		return ingredients;
	}
	
	/**
	 * Sets the ingredients for this Recipe
	 * @param ingredients The new ingredients for this Recipe
	 */
	public void setIngredients(Set<FoodItem> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Returns a nice String representation of a Recipe
	 */
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

//package com.yumyap.beans;
//
//import java.sql.Time;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import com.yumyap.dto.RecipeDto;
//
//@Entity
//@Table(name = "RECIPE")
//public class Recipe{
//
//	@Id
//	@Column(name = "RECIPEID")
//	@SequenceGenerator(name = "RID_SEQ", sequenceName = "RID_SEQ")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RID_SEQ")
//	private int id;
//
//	private Time created;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	private User creator;
//	
//	private String name;
//	
//	private String description;
//
//	private String directions;
//	
//	private String image;
//	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "Recipes_Ingredients", joinColumns = { @JoinColumn(name = "recipe_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "fooditem_id") })
//	private Set<FoodItem> ingredients;
//	
//	private double calories;
//	private double protein;
//	private double carbs;
//	private double fat;
//	
//
//	public double getCalories() {
//		return calories;
//	}
//
//	public void setCalories(double calories) {
//		this.calories = calories;
//	}
//
//	public double getProtein() {
//		return protein;
//	}
//
//	public void setProtein(double protein) {
//		this.protein = protein;
//	}
//
//	public double getCarbs() {
//		return carbs;
//	}
//
//	public void setCarbs(double carbs) {
//		this.carbs = carbs;
//	}
//
//	public double getFat() {
//		return fat;
//	}
//
//	public void setFat(double fat) {
//		this.fat = fat;
//	}
//
//	public void setIngredients(Set<FoodItem> ingredients) {
//		this.ingredients = ingredients;
//	}
//
//	public Recipe() {}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getImage() {
//		return image;
//	}
//
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	public Recipe(RecipeDto recipe) {
//		this.id = recipe.getId();
//		this.created = recipe.getCreated();
//		this.creator = recipe.getCreator();
//		this.name = recipe.getName();
//		this.description = recipe.getDescription();
//		this.directions = "";
//		for(String s : recipe.getDirections()) {
//			directions += s;
//		}
//		this.image = recipe.getImage();
//		this.ingredients = recipe.getIngredients();
//	}
//		
//	public Recipe(int id, Time created, User creator, String name, String description, String directions, String image,
//			Set<FoodItem> ingredients) {
//		super();
//		this.id = id;
//		this.created = created;
//		this.creator = creator;
//		this.name = name;
//		this.description = description;
//		this.directions = directions;
//		this.image = image;
//		this.ingredients = ingredients;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Time getCreated() {
//		return created;
//	}
//
//	public void setCreated(Time created) {
//		this.created = created;
//	}
//
//	public User getCreator() {
//		return creator;
//	}
//
//	public void setCreator(User creator) {
//		this.creator = creator;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getDirections() {
//		return directions;
//	}
//
//	public void setDirections(String directions) {
//		this.directions = directions;
//	}
//
//	public Set<FoodItem> getIngredients() {
//		return ingredients;
//	}
//
//	@Override
//	public String toString() {
//		return "Recipe [id=" + id + ", created=" + created + ", creator=" + creator + ", name=" + name
//				+ ", description=" + description + ", directions=" + directions + ", image=" + image + ", ingredients="
//				+ ingredients + "]";
//	}
//
//}
