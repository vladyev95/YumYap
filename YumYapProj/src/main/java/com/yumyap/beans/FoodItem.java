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
 * An object representing the FoodItems that Recipes consist of
 * @author vlad
 */
@Component
@Entity
@Table(name = "food_items")
public class FoodItem {

	@Id
	@Column(name = "food_item_id")
	@SequenceGenerator(name = "food_item_id_seq", sequenceName = "food_item_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_item_id_seq")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "measure")
	private String measure;
	
	@Column(name = "amount")
	private double amount;


	public FoodItem() {
	}

	public FoodItem(String name, String measure, double amount) {
		this.name = name;
		this.measure = measure;
		this.amount = amount;
	}
	
	/**
	 * Returns the id of this FoodItem
	 * @return The id of this FoodItem
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this FoodItem
	 * @param id The new id of this FoodItem
	 */
	public void setId(int id) {
		this.id = id;
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
		return "FoodItem { id: " + id + 
				", name: " + name +
				", measure: " + measure + 
				", amount: " + amount + " }";
	}
}
