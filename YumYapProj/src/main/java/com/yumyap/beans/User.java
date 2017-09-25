package com.yumyap.beans;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.JoinColumn;
=======
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@Column(name = "USERID")
	@SequenceGenerator(name = "UID_SEQ", sequenceName = "UID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UID_SEQ")
	private int id;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> following;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;

	@Column(name = "LASTNAME", nullable = false)
	private String lastname;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
<<<<<<< HEAD

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "Active", columnDefinition = "1")
	private int active;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "User_Recipes", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "recipe_id") })
	private List<Recipe> favoriteRecipes;

=======
	
	@Column(name ="EMAIL", nullable = false, unique=true)
	private String email;
	
	@Column(name="Active", columnDefinition = "1")
	private int active;
	
	@ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(
		        name = "User_Recipes", 
		        joinColumns = { @JoinColumn(name = "user_id") }, 
		        inverseJoinColumns = { @JoinColumn(name = "recipe_id") }
		    )
	private List<Recipe> favoriteRecipes;
	
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
	public User() {
	}

<<<<<<< HEAD
	public User(int id, Set<User> following, String firstname, String lastname, String password, String username,
			List<Recipe> favoriteRecipes) {
=======
	public User(int id, Set<User> following, String firstname, String lastname, String password, String username, List<Recipe> favoriteRecipes) {
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
		super();
		this.id = id;
		this.following = following;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = username;
		this.favoriteRecipes = favoriteRecipes;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return email;
	}

	public void setUsername(String username) {
		this.email = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public String getEmail() {
		return email;
	}

<<<<<<< HEAD
=======

	public String getEmail() {
		return email;
	}


>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
	public void setEmail(String email) {
		this.email = email;
	}

<<<<<<< HEAD
=======

>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
	public int getActive() {
		return active;
	}

<<<<<<< HEAD
=======

>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172
	public void setActive(int active) {
		this.active = active;
	}

<<<<<<< HEAD
	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}
=======

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}


	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172

	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", following=" + following + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", email=" + email + ", active=" + active + ", favoriteRecipes="
				+ favoriteRecipes + "]";
	}
<<<<<<< HEAD
=======

	
	
>>>>>>> 5702d83360f792691cdcc0192db685d0fad17172

}