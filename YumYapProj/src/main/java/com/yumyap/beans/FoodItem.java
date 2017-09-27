package com.yumyap.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItem {

	@Id
	@Column(name = "food_item_id")
	@SequenceGenerator(name = "food_item_id_sequence", sequenceName = "food_item_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_item_id_sequence")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "measure")
	private String measure;
	
	@Column(name = "amount")
	private double amount;


	public FoodItem() {
	}

	public FoodItem(String measure, double amount) {
		this.measure = measure;
		this.amount = amount;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "FoodItem { id=" + id + 
				", name: " + name +
				", measure: " + measure + 
				", amount: " + amount + " }";
	}
}
