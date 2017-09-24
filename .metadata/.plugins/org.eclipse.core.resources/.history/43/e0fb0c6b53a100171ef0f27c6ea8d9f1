package com.yumyap.beans;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class Food {
	
//	@Id
//	@Column(name = "FOODID")
//	@SequenceGenerator(name = "FID_SEQ", sequenceName="FID_SEQ")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FID_SEQ")
	
	private int id;
	private String name;
	private double calories;
	private double carbohydrates;
	private double fats;
	private double proteins;
	private String picture;

	public Food() {
	}
	
	public Food(int id, String name, double calories, double carbohydrates, double fats, double proteins, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.carbohydrates = carbohydrates;
		this.fats = fats;
		this.proteins = proteins;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public double getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	public double getFats() {
		return fats;
	}
	public void setFats(double fats) {
		this.fats = fats;
	}
	public double getProteins() {
		return proteins;
	}
	public void setProteins(double proteins) {
		this.proteins = proteins;
	}



	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", calories=" + calories + ", carbohydrates=" + carbohydrates
				+ ", fats=" + fats + ", proteins=" + proteins + "]";
	}


	
	

}
