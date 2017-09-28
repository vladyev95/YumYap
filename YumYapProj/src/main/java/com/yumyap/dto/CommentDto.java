package com.yumyap.dto;

import java.sql.Date;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

public class CommentDto {
	private int id;
	private Recipe recipe;
	private User user;
	private Date date;
	private String comment;
	
	public CommentDto(){}
	public CommentDto(int id, Recipe recipe, User user, Date date, String comment) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.user = user;
		this.date = date;
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
