package com.yumyap.dao;

import java.util.List;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Food;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Log;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

public interface Dao {
	
	
	public User addUser(User u);
	public boolean addFoodItem(FoodItem fi);
	public boolean addComment(Comment c);
	public boolean addRecipe(Recipe r);
	
	
	public User getUser(String username);
//	public int getFoods(String search);
	public List<Comment> getComments(Recipe r);
	public List<Recipe>	getRecipes(String search);
	public List<Recipe>	getRecipes(int foodId);
	
	public boolean updateUser(User user);
	
	public boolean deleteComment(Comment c);
	public boolean deleteUser(User u);
	public boolean deleteFoodItem(FoodItem fi);
	public boolean deleteRecipe(Recipe r);
	
	
	

}
