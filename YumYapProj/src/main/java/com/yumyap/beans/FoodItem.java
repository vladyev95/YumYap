package com.yumyap.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FOODITEMS")
public class FoodItem {

	@Id
	@Column(name = "FOODITEMID")
	@SequenceGenerator(name = "FIID_SEQ", sequenceName = "FIID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FIID_SEQ")
	private int id;
	private int food;
	private String measure;
	private double amount;

	public FoodItem() {
	}

	public FoodItem(int id, int food, String measure, double amount) {
		super();
		this.id = id;
		this.food = food;
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

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", food=" + food + ", measure=" + measure + ", amount=" + amount + "]";
	}

}