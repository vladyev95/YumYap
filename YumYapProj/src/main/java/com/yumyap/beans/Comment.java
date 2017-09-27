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

import org.apache.log4j.Logger;

@Entity
@Table(name = "comments")
public class Comment {
	
	private static final Logger logger = Logger.getLogger(Comment.class);

	@Id
	@Column(name = "comment_id")
	@SequenceGenerator(name = "comment_id_sequence", sequenceName = "comment_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_sequence")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "comment_date", nullable = false)
	private Date comment_date;

	@Column(name = "comment", nullable = false)
	private String comment;

	public Comment() {
	}

	public Comment(User user, Date comment_date, String comment) {
		this.user = user;
		this.comment_date = comment_date;
		this.comment = comment;
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
	public User getUser() {
		return user;
	}

	/**
	 * Sets the User that made this comment
	 * @param user The User that made this comment
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the java.sql.Date that this Comment was made
	 * @return The Date that this Comment was made
	 */
	public Date getDate() {
		return comment_date;
	}

	/**
	 * Sets the Date that this Comment was made
	 * @param comment_date The Date that this comment was made
	 */
	public void setDate(Date comment_date) {
		this.comment_date = comment_date;
	}

	/**
	 * Returns the contents of this Comment
	 * @return The contents of this Comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the contents of this Comment
	 * @param comment The new contents of this Comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Returns a nice String representation of a Comment
	 */
	@Override
	public String toString() {
		return "Comment { id: " + id + 
				", user: " + user + 
				", comment_date: " + comment_date + 
				", comment: " + comment + " }";
	}

}
