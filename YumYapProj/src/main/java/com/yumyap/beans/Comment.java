package com.yumyap.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENT")
public class Comment {

	@Id
	@Column(name = "COMMENTID")
	@SequenceGenerator(name = "CID_SEQ", sequenceName = "CID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CID_SEQ")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Recipe recipeDto;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "COMMENT", nullable = false)
	private String comment;

	public Comment() {
	}

	public Comment(Recipe recipe, User user, Date date, String comment) {
		super();
		this.user = user;
		this.date = date;
		this.comment = comment;
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