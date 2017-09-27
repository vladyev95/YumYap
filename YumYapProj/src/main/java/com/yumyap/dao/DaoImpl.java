package com.yumyap.dao;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yumyap.beans.Comment;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.RecipeDirection;
import com.yumyap.beans.User;

/**
 * An implementation of the Dao interface
 * Works directly with our database specified in the beans.xml
 * Do not use this as the variable type, use the Dao interface
 * @author vlad
 */
@Repository
@Transactional
public class DaoImpl implements Dao {
	
	
	private static final Logger logger = Logger.getLogger(DaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	public DaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean addUser(User user) {
		logger.trace("addUser() " + user);
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		logger.trace("added " + user);
		return user != null;
	}

	@Override
	public FoodItem addFoodItem(FoodItem foodItem) {
		logger.trace("addFoodItem() " + foodItem);
		Session s = sessionFactory.getCurrentSession();
		s.save(foodItem);
		logger.trace("added " + foodItem);
		return foodItem;
	}

	@Override
	public Comment addComment(Comment comment) {
		logger.trace("addComment() " + comment);
		Session s = sessionFactory.getCurrentSession();
		s.save(comment);
		logger.trace("added " + comment);
		return comment;
	}

	@Override
	public Recipe addRecipe(Recipe recipe) {
		logger.trace("addRecipe() " + recipe);
		Session s = sessionFactory.getCurrentSession();
		s.save(recipe);
		logger.trace("added " + recipe);
		return recipe;
	}
	
	@Override
	public RecipeDirection addRecipeDirection(RecipeDirection recipeDirection) {
		logger.trace("addRecipeDirection() " + recipeDirection);
		Session session = sessionFactory.getCurrentSession();
		session.save(recipeDirection);
		logger.trace("added " + recipeDirection);
		return recipeDirection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> getRecipesByUser(User user) {
		logger.trace("getRecipesByUser() " + user);
		Session session = sessionFactory.getCurrentSession();
		List<Recipe> recipes = (List<Recipe>) session
						.createCriteria(Recipe.class)
						.add(Restrictions.eq("creator", user))
						.list();
		logger.trace("got recipes " + recipes);
		return recipes;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		logger.trace("getUserByEmail() " + email);
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session
					.createCriteria(User.class)
					.add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password)).uniqueResult();
					
		logger.trace("got " + user);
		return user;
	}	
	
	@Override
	public Set<Recipe> getFollowingRecipes(User user) {
		logger.trace("getFollowingRecipes() using " + user);
		
		Set<Recipe> recipes = new LinkedHashSet<>();
		
		
		
		logger.trace("got " + recipes);
		return recipes;
	}
	
	
	@Override
	public Set<Recipe> getUsersRecipes(User user) {
		logger.trace("getUserRecipesById() using " + user);
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Set<Recipe> recipes = new LinkedHashSet<>(session.createCriteria(Recipe.class)
														.add(Restrictions.eq("creator", user))
														.list());
		
		logger.trace("got " + recipes);
		return recipes;
	}
}
