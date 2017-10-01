package com.yumyap.dto;

import java.util.Calendar;

import com.yumyap.beans.Comment;

public class CommentDto {
	
	private int id;
	private RecipeDto recipe;
	private UserDto commenter;
	private Calendar commentDate;
	private String content;
	
	public CommentDto(Comment c) {
		this.id = c.getId();
		this.recipe = new RecipeDto(c.getRecipe());
		this.commenter = new UserDto(c.getCommenter());
		this.commentDate = c.getCommentDate();
		this.content = c.getContent();
	}
	
	public CommentDto(int id, RecipeDto recipe, UserDto commenter, Calendar commentDate, String content) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.commenter = commenter;
		this.commentDate = commentDate;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RecipeDto getRecipe() {
		return recipe;
	}
	public void setRecipe(RecipeDto recipe) {
		this.recipe = recipe;
	}
	public UserDto getCommenter() {
		return commenter;
	}
	public void setCommenter(UserDto commenter) {
		this.commenter = commenter;
	}
	public Calendar getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Calendar commentDate) {
		this.commentDate = commentDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public boolean equals(Object o) {
		CommentDto c = (CommentDto)o;
		return this.id == c.getId();
	}

}
