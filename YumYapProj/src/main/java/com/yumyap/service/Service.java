package com.yumyap.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Food;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dao.DaoImpl;
import com.yumyap.dto.UserDto;

//@Service
public class Service implements ServiceInterface {

//	@Autowired
	private Dao daoImpl;

	public Service() {
		super();
	}

	public void setDaoImpl(Dao daoImpl) {
		this.daoImpl = daoImpl;
	}

	public UserDto authenticateUser(UserDto userDto) {
		User user = daoImpl.findUserByEmail(userDto.getUser().getEmail());
		if (user != null && (user.getPassword().equals(userDto.getUser().getPassword()))) {
			userDto.setAuthenticated(true);
		} else {
			return null;
		}
		return userDto;
	}

	public UserDto createUser(UserDto userDto) {
		// FIXME user validation
		// FIXME enumerate other fields in user
		User user = new User();
		user.setEmail(userDto.getUser().getEmail());
		user.setPassword(userDto.getUser().getPassword());
		user = daoImpl.addUser(user);
		userDto.getUser().setId(user.getId());
		return userDto;
	}

	@Override
	public User createUser(String username, String password, String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFood(Food food) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFollower(User user, User follower) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Recipe addRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addComment(Recipe recipe, Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public List<User> getFollowers(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMacronutrients(Recipe recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMacronutrients(Food food) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMacronutrients(FoodItem foodItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNutrientsForDate(User user, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean rateRecipe(Recipe recipe, int rating) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deactivateUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailValid(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailAvailable(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsernameAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
