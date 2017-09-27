package com.yumyap.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.Logger;


@Entity
@Table(name = "users")
public class User {
	
	private final static Logger logger = Logger.getLogger(User.class);

	@Id
	@Column(name = "user_id")
	@SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
	private int id;

	
	//private Set<User> following;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;
	
	/*
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_favorite_recipes_junction", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns =  @JoinColumn(name = "recipe_id"))
	private Set<Recipe> favoriteRecipes;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_created_recipes_junction",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	private Set<Recipe> createdRecipes;*/

	public User() {}

	public User(Set<User> following, 
			String firstName, 
			String lastName, 
			String password, 
			String email, 
			Set<Recipe> favoriteRecipes) {
		//this.following = following;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		/*this.favoriteRecipes = favoriteRecipes;*/
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/*
	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}
*/
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
/*	public Set<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(Set<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}
	
	public Set<Recipe> getCreatedRecipes() {
		return createdRecipes;
	}
	
	public void setCreatedRecipes(Set<Recipe> createdRecipes) {
		this.createdRecipes = createdRecipes;
	}*/

	@Override
	public String toString() {
		return "User { id: " + id + 
				/*", following: " + following + */
				", firstName: " + firstName + 
				", lastName: " + lastName +
				", password: " + password + 
				", email: " + email + " }";
				/*", favoriteRecipes: " + favoriteRecipes + " }";*/
	}

}
