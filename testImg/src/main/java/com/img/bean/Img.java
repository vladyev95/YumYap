package com.img.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IMAGEURL3")
public class Img {
	@Id
	@Column(name="ID")
	@SequenceGenerator(name="I_SEQ",sequenceName="I_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="I_SEQ")
	private int id;
	@Column(nullable =false, unique=true)
	private String link;
	@Column(nullable =false)
	private String description;
	
	public Img(){}
	public Img(int id, String link, String description) {
		super();
		this.id = id;
		this.link = link;
		this.description = description;
	}
	public Img(String link, String description){
		this.link = link;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
