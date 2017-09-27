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

	public Comment(Recipe recipe, User user, Date comment_date, String comment) {
		this.user = user;
		this.comment_date = comment_date;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return comment_date;
	}

	public void setDate(Date comment_date) {
		this.comment_date = comment_date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment { id: " + id + 
				", user: " + user + 
				", comment_date: " + comment_date + 
				", comment: " + comment + " }";
	}

}
