package com.yumyap.beans;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.yumyap.dto.SimpleUserDto;

/**
 * An object representing a Recipe object that Users may create
 * @author vlad
 */
@Component
@Entity
@Table(name = "recipes")
public class Recipe implements Comparable<Recipe>{

	@Id
	@Column (name = "recipe_id")
	@SequenceGenerator (name = "recipe_id_sequence", sequenceName = "recipe_id_sequence")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "recipe_id_sequence")
	private int id;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column (name = "date_created", insertable = false)
	private Calendar dateCreated;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private User creator;

	@Column (name = "name", nullable = true)
	private String name;

	@Column (name = "description", nullable = true)
	private String description;

	@ElementCollection (fetch = FetchType.EAGER)
	@CollectionTable (
			name = "DIRECTION",
			joinColumns = @JoinColumn(name = "RECIPE_ID"))
	@Column (name = "CONTENT", nullable = false)
	@OrderBy ("CONTENT")
	private SortedSet<String> directions = new TreeSet<>();

	@Column (name = "image_url")
	private String imageUrl;

	@ElementCollection (fetch = FetchType.EAGER)
	@CollectionTable (
			name = "recipes_ingredients",
			joinColumns = @JoinColumn(name = "RECIPE_ID", nullable = false))
	@Column (name = "FOOD_ITEM")
	private Set<FoodItem> ingredients = new HashSet<>();

	@OneToMany
	@JoinColumn (name = "COMMENT_ID")
	@OrderBy ("COMMENT_DATE DESC")
	private SortedSet<Comment> comments = new TreeSet<>();

	@Column
	private double calories;

	@Column
	private double fat;

	@Column
	private double carbs;

	@Column
	private double protein;


	public Recipe() {}

	public Recipe(Calendar dateCreated, String name, String description, SortedSet<String> directions, String imageUrl,
			Set<FoodItem> ingredients, SortedSet<Comment> comments) {
		this.dateCreated = dateCreated;
		this.name = name;
		this.description = description;
		this.directions = directions;
		this.imageUrl = imageUrl;
		this.ingredients = ingredients;
		this.comments = comments;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
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
	public void getImageUrl(String imageUrl) {
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
	public Calendar getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets the time representing the time this Recipe was created
	 * @param timeCreated
	 */
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
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
	 * 
	 * @param description The new description of this Recipe
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the RecipeDirections for this Recipe 
	 * @return The RecipeDirections for this Recipe
	 */
	public SortedSet<String> getDirections() {
		return directions;
	}

	/**
	 * Sets the RecipeDirections for this Recipe
	 * @param directions The new RecipeDirections for this Recipe
	 */
	public void setDirections(SortedSet<String> directions) {
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
	 * Returns the Comments of this Recipe
	 * @return The Comments of this Recipe
	 */
	public SortedSet<Comment> getComments() {
		return comments;
	}

	/**
	 * Sets the Comments for this Recipe
	 * @param comments The new Comments for this Recipe
	 */
	public void setComments(SortedSet<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Recipe {id: " + id + 
				", dateCreated=" + dateCreated + 
				", creator: " + new SimpleUserDto(creator) + 
				", name: " + name +
				", description: " + description + 
				", directions: " + directions + 
				", imageUrl: " + imageUrl +
				", ingredients:" + ingredients + 
				", comments: " + comments + 
				", calories: " + calories + 
				", fat: " + fat +
				", carbs: " + carbs + 
				", protein: " + protein + " }";
	}

	@Override
	public int compareTo(Recipe that) {
		return (this.dateCreated != null) ? this.dateCreated.compareTo(that.getDateCreated()) : -1;
	}
	
	@Override
	public boolean equals(Object o) {
		Recipe r = (Recipe) o;
		return this.id == r.getId();
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
}
