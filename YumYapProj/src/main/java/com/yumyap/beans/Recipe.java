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

import com.yumyap.dto.UserDto;

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

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipes_ingredients")
    private Set<FoodItem> ingredients;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Set<Comment> comments;
    
    @Column
    private double calories;
    
    @Column
    private double fat;
    
    @Column
    private double carbs;
    
    @Column
    private double protein;

    public Recipe() {
    }

    public Recipe(Time timeCreated, String name, String description, List<RecipeDirection> directions, String imageUrl,
            Set<FoodItem> ingredients) {
        this.timeCreated = timeCreated;
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

    public double getCalories() {
        return calories;
    }

    public void ListCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void ListProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void ListCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFat() {
        return fat;
    }

    public void ListFat(double fat) {
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
    public void ListName(String name) {
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
     * Returns the Comments of this Recipe
     * @return The Comments of this Recipe
     */
    public Set<Comment> getComments() {
    	return comments;
    }
    
    /**
     * Sets the Comments for this Recipe
     * @param comments The new Comments for this Recipe
     */
    public void setComments(Set<Comment> comments) {
    	this.comments = comments;
    }

    /**
     * Returns a nice String representation of a Recipe
     */
    @Override
    public String toString() {
        return "Recipe { id: " + id + 
        		", timeCreated: " + timeCreated + 
        		", creator: " + new UserDto(creator) + 
        		", name: " + name +
        		", description: " + description + 
                ", directions: " + directions + 
                ", imageUrl: " + imageUrl +
                ", ingredients: " + ingredients + " }";
    }

}