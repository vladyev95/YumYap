package com.yumyap.beans;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * An object representing a User of our system
 * @author vlad
 */
@Component
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	private int id;

	// TODO: Check if many-to-many self join is implemented correctly
	@ManyToMany
	@JoinTable (
			name = "users_following_junc",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "following_id"))
	private Set<User> followers = new HashSet<>();

	@ManyToMany (mappedBy = "followers")
	private Set<User> following = new HashSet<>();

	@Column (name = "first_name", nullable = false)
	private String firstName;

	@Column (name = "last_name", nullable = false)
	private String lastName;

	@Column (name = "email", nullable = false, unique = true)
	private String email;

	@Column (name = "password", nullable = false)
	private String password;

	@ManyToMany
	@JoinTable (
			name = "FAVORITE_RECIPES",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "RECIPE_ID"))
	private Set<Recipe> favoriteRecipes = new HashSet<>();

	@OneToMany (mappedBy = "CREATOR")
	@OrderColumn (name = "date_created")
	private SortedSet<Recipe> createdRecipes = new TreeSet<>();


	public User() {}

	public User(Set<User> following, String firstName, String lastName, String password, String email,
			Set<Recipe> favoriteRecipes, SortedSet<Recipe> createdRecipes) {
		this.following = following;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.favoriteRecipes = favoriteRecipes;
		this.createdRecipes = createdRecipes;
	}

	/**
	 * Returns the id of this User
	 * @return The id of this User
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this User
	 * @param id The new id of this User
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	/**
	 * Returns the firstName of this User
	 * @return The firstName of this User
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstName for this User
	 * @param firstName The new firstName for this User
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the lastName of this User
	 * @return The lastName of this User
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lastName of this User
	 * @param lastName The new lastName of this User
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the password of this User
	 * @return The password of this User
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of this User
	 * @param password The new password of this User
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the email of this User
	 * @return The email of this User
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of this User
	 * @param email The new email of this User
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(Set<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public Set<Recipe> getCreatedRecipes() {
		return createdRecipes;
	}

	public void setCreatedRecipes(SortedSet<Recipe> createdRecipes) {
		this.createdRecipes = createdRecipes;
	}

	/**
	 * Returns a nice String representation of this User
	 */
	@Override
	public String toString() {
		return "User { id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", password: " + password
				+ ", email: " + email + ", following: " + following + ", favoriteRecipes: " + favoriteRecipes
				+ ", createdRecipes: " + createdRecipes + " }";
	}
}
