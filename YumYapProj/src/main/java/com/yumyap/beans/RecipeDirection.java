package com.yumyap.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


/**
 * An object representing a step in the creation of a Recipe
 * Required for us to be able to store the steps of a recipe
 * in a table since only Entities can be stored in a table
 * @author vlad
 */
@Component
@Entity
@Table(name = "recipe_directions")
public class RecipeDirection {
	
	@Id
	@Column(name = "recipe_direction_id")
	@SequenceGenerator(name = "recipe_direction_id_seq", sequenceName = "recipe_direction_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="recipe_direction_id_seq")
	private int id;
	
	@Column(name = "direction")
	private String direction;
	
	public RecipeDirection() {
		
	}
	
	public RecipeDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * Returns the id of this RecipeDirection
	 * @return The id of this RecipeDirection
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this RecipeDirection
	 * @param id The new id of this RecipeDirection
	 */
	public void setId(int id) {
		this.id = id;
	}
	 
	/**
	 * Returns the direction of this RecipeDirection
	 * @return The direction of this RecipeDirection
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Sets this RecipeDirection's direction
	 * @param direction The new direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * Returns a nice String representation of a RecipeDirection
	 */
	@Override
	public String toString() {
		return "RecipeDirection { id: " + id +
				", direction: " + direction + " }";	
		}
}