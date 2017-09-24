package com.yumyap.beans;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "LOGS")
public class Log {
	
	@Id
	@Column(name = "LOGID")
	@SequenceGenerator(name = "LID_SEQ", sequenceName="ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_SEQ")
	private int id;
	
	private Date date;
	
	@OneToMany(fetch = FetchType.EAGER)
	private ArrayList<FoodItem> foodItems;
	
	public Log() {}
	
	public Log(int id, Date date, ArrayList<FoodItem> foodItems) {
		super();
		this.id = id;
		this.date = date;
		this.foodItems = foodItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<FoodItem> getMeals() {
		return foodItems;
	}

	public void setFood(ArrayList<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", date=" + date + ", meals=" + foodItems + "]";
	}
	
	
	
}
