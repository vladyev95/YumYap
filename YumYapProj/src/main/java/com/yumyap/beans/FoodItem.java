package com.yumyap.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;

/**
 * An object representing the FoodItems that constitute the ingredient list of a
 * Recipe
 * @author vlad
 */
@Embeddable
public class FoodItem {
	
	@Parent
	private Recipe recipe;

	@Column (name = "name", nullable = false)
	private String name;

	@Column (name = "measure", nullable = false)
	private String measure;

	@Column (name = "amount", nullable = false)
	private double amount;


	public FoodItem() {}

	public FoodItem(String name, String measure, double amount) {
		this.name = name;
		this.measure = measure;
		this.amount = amount;
	}

	/**
	 * Returns the name of this FoodItem
	 * @return The name of this FoodItem
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this FoodItem
	 * @param name The new name of this FoodItem
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the measure that this FoodItem uses
	 * @return The name of the measure that this FoodItem uses
	 */
	public String getMeasure() {
		return measure;
	}

	/**
	 * Sets the name of the measure that this FoodItem uses
	 * @param measure The new measure that this FoodItem uses
	 */
	public void setMeasure(String measure) {
		this.measure = measure;
	}

	/**
	 * Returns the amount of the measure for this FoodItem
	 * @return The amount of the measure for this FoodItem
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the measure for this FoodItem
	 * @param amount The new amount of measure for this FoodItem
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Returns a nice String representation of a FoodItem
	 */
	@Override
	public String toString() {
		return "FoodItem { name: " + name + ", measure: " + measure + ", amount: " + amount + " }";
	}
	
	@Override
	public boolean equals(Object that) {
		if (!(that instanceof FoodItem)) return false;
		FoodItem thatFood = (FoodItem) that;
		return  this.amount == thatFood.getAmount() &&
				this.name.equals(thatFood.getName()) &&
				this.measure.equals(thatFood.getMeasure());
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + new Double(amount).hashCode();
		hash = hash * 31 + measure.hashCode();
		hash = hash * 31 + name.hashCode();
		return hash;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
