package com.yumyap.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "recipe_directions")
public class RecipeDirection {
	
	@Id
	@Column(name = "recipe_direction_id")
	@SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="course_id_seq")
	private int id;
	
	@Column(name = "direction")
	private String direction;
	
	public RecipeDirection() {
		
	}
	
	public RecipeDirection(String direction) {
		this.direction = direction;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return direction;
	}
	
	@Override
	public String toString() {
		return "RecipeDirection { id: " + id +
				", direction: " + direction + " }";	
		}
}
