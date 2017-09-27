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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_following_junc",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "following_id"))
	private Set<User> users;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
	private Set<User> following;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_favorites_junc", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns =  @JoinColumn(name = "recipe_id"))
	private Set<Recipe> favoriteRecipes;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipe_id")
	private Set<Recipe> createdRecipes;

	public User() {}

	public User(Set<User> following, 
			String firstName, 
			String lastName, 
			String password, 
			String email,
			Set<Recipe> favoriteRecipes,
			Set<Recipe> createdRecipes) {
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
	
	public void setCreatedRecipes(Set<Recipe> createdRecipes) {
		this.createdRecipes = createdRecipes;
	}

	/**
	 * Returns a nice String representation of this User
	 */
	@Override
	public String toString() {
		return "User { id: " + id + 
				", firstName: " + firstName + 
				", lastName: " + lastName +
				", password: " + password + 
				", email: " + email + 
				", following: " + following +
				", favoriteRecipes: " + favoriteRecipes + 
				", createdRecipes: " + createdRecipes + " }";
	}

}

//package com.yumyap.beans;
//
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import com.yumyap.dto.RecipeDto;
//import com.yumyap.dto.UserDto;
//
//
//@Entity
//@Table(name = "USERS")
//public class User {
//
//	@Id
//	@Column(name = "USERID")
//	@SequenceGenerator(name = "UID_SEQ", sequenceName = "UID_SEQ")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UID_SEQ")
//	private int id;
//
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(
//	        name = "User_Following", 
//	        joinColumns = { @JoinColumn(name = "user_id") }, 
//	        inverseJoinColumns = { @JoinColumn(name = "suer_id") }
//	    )
//	private Set<User> following;
//
//	@Column(name = "FIRSTNAME")
//	private String firstname;
//
//	@Column(name = "LASTNAME", nullable = false)
//	private String lastname;
//
//	@Column(name = "PASSWORD", nullable = false)
//	private String password;
//
//	@Column(name = "EMAIL", nullable = false, unique = true)
//	private String email;
//
//	@Column(name = "Active")
//	private int active;
//
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "User_Recipes", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "recipe_id") })
//	private List<Recipe> favoriteRecipes;
//
//	public User() {}
//
//
//	public User(int id, Set<User> following, String firstname, String lastname, String password, String email,
//			int active, List<Recipe> favoriteRecipes) {
//		super();
//		this.id = id;
//		this.following = following;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.password = password;
//		this.email = email;
//		this.active = active;
//		this.favoriteRecipes = favoriteRecipes;
//	}
//
//	public User(UserDto userDto) {
//		super();
//		this.id = userDto.getId();
//		this.following = userDto.getFollowing();
//		this.firstname = userDto.getFirstname();
//		this.lastname = userDto.getLastname();
//		this.password = userDto.getPassword();
//		this.email = userDto.getEmail();
//		this.active = userDto.getActive();
////		for(RecipeDto r: userDto.getFavoriteRecipes()) {
////		this.favoriteRecipes.add(new Recipe(r));}
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
//	public Set<User> getFollowing() {
//		return following;
//	}
//
//	public void setFollowing(Set<User> following) {
//		this.following = following;
//	}
//
//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public int getActive() {
//		return active;
//	}
//
//	public void setActive(int active) {
//		this.active = active;
//	}
//
//	public List<Recipe> getFavoriteRecipes() {
//		return favoriteRecipes;
//	}
//
//	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
//		this.favoriteRecipes = favoriteRecipes;
//	}
//
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", following=" + following + ", firstname=" + firstname + ", lastname=" + lastname
//				+ ", password=" + password + ", email=" + email + ", active=" + active
//				+ ", favoriteRecipes=" + favoriteRecipes + "]";
//	}
//
//}
