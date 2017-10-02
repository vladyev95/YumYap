package com.yumyap.beans;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.yumyap.dto.CommentDto;

/**
 * An object representing the comments that Users can make on Recipes
 * @author vlad
 */
@Component
@Entity
@Table(name = "comments")
public class Comment implements Comparable<Comment> {

	@Id
	@Column(name = "comment_id")
	@SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "RECIPE_ID")
	private Recipe recipe;

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User commenter;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "comment_date", nullable = true)
	private Calendar commentDate;

	@Column(name = "content", nullable = false)
	private String content;

	public Comment() {}

	public Comment(int id, Recipe recipe, User commenter, Calendar commentDate, String content) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.commenter = commenter;
		this.commentDate = commentDate;
		this.content = content;
	}

	
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Calendar getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Calendar commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * Returns the id of this Comment
	 * @return The id of this Comment
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this Comment
	 * @param id The new id of this Comment
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the User that made this Comment
	 * @return The User that made this Comment
	 */
	public User getCommenter() {
		return commenter;
	}

	/**
	 * Sets the User that made this comment
	 * @param user The User that made this comment
	 */
	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	/**
	 * Returns the java.sql.Date that this Comment was made
	 * @return The Date that this Comment was made
	 */
	public Calendar getDate() {
		return commentDate;
	}

	/**
	 * Sets the Date that this Comment was made
	 * @param comment_date The Date that this comment was made
	 */
	public void setDate(Calendar commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * Returns the contents of this Comment
	 * @return The contents of this Comment
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the contents of this Comment
	 * @param comment The new contents of this Comment
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Returns a nice String representation of a Comment
	 */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", recipe=" + recipe.getName() + ", commentDate=" + commentDate
				+ ", content=" + content + "]";
	}

	@Override
	public int compareTo(Comment that) {
		return (this.commentDate != null) ? -this.commentDate.compareTo(that.getDate()) : -1;
	}
	
	@Override
	public boolean equals(Object o) {
		Comment c = (Comment)o;
		return this.id == c.getId();
	}
	
	@Override
	public int hashCode() {
		return id;
	}
}
