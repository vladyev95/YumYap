package com.yumyap.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * An object representing the comments that Users can make on Recipes
 * @author vlad
 */
@Component
@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@Column(name = "comment_id")
	@SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User commenter;

	@Column(name = "comment_date", nullable = false)
	private Date commentDate;

	@Column(name = "content", nullable = false)
	private String content;

	public Comment() {
	}

	public Comment(User commenter, Date commentDate, String content) {
		this.commenter = commenter;
		this.commentDate = commentDate;
		this.content = content;
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
	public Date getDate() {
		return commentDate;
	}

	/**
	 * Sets the Date that this Comment was made
	 * @param comment_date The Date that this comment was made
	 */
	public void setDate(Date commentDate) {
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
		return "Comment { id: " + id + 
				", commenter: " + commenter + 
				", commentDate: " + commentDate + 
				", content: " + content + " }";
	}

}

//package com.yumyap.beans;
//
//import java.sql.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "COMMENT")
//public class Comment {
//
//	@Id
//	@Column(name = "COMMENTID")
//	@SequenceGenerator(name = "CID_SEQ", sequenceName = "CID_SEQ")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CID_SEQ")
//	private int id;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	private Recipe recipe;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	private User user;
//
//	@Column(name = "DATE", nullable = false)
//	private Date date;
//
//	@Column(name = "COMMENT", nullable = false)
//	private String comment;
//
//	public Comment() {
//	}
//
//	public Comment(int id, Recipe recipe, User user, Date date, String comment) {
//		super();
//		this.id = id;
//		this.recipe = recipe;
//		this.user = user;
//		this.date = date;
//		this.comment = comment;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Recipe getRecipe() {
//		return recipe;
//	}
//
//	public void setRecipe(Recipe recipe) {
//		this.recipe = recipe;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	@Override
//	public String toString() {
//		return "Comment [id=" + id + ", recipeDto=" + recipe + ", user=" + user + ", date=" + date + ", comment="
//				+ comment + "]";
//	}
//
//}
