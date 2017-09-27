package com.yumyap.dao;
import java.util.List;

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

@Repository
@Transactional
public class DaoImpl implements Dao {
	
	
	private static final Logger logger = Logger.getLogger(DaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 
	 * @param sessionFactory
	 */
	@Autowired
	public DaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean addUser(User user) {
		logger.fatal("addUser() " + user);
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		logger.fatal("added " + user);
		return user != null;
	}

	@Override
	public FoodItem addFoodItem(FoodItem foodItem) {
		logger.fatal("addFoodItem() " + foodItem);
		Session s = sessionFactory.getCurrentSession();
		s.save(foodItem);
		logger.fatal("added " + foodItem);
		return foodItem;
	}

	@Override
	public Comment addComment(Comment comment) {
		logger.fatal("addComment() " + comment);
		Session s = sessionFactory.getCurrentSession();
		s.save(comment);
		logger.fatal("added " + comment);
		return comment;
	}

	@Override
	public Recipe addRecipe(Recipe recipe) {
		logger.fatal("addRecipe() " + recipe);
		Session s = sessionFactory.getCurrentSession();
		s.save(recipe);
		logger.fatal("added " + recipe);
		return recipe;
	}
	
	@Override
	public RecipeDirection addRecipeDirection(RecipeDirection recipeDirection) {
		logger.fatal("addRecipeDirection() " + recipeDirection);
		Session session = sessionFactory.getCurrentSession();
		session.save(recipeDirection);
		logger.fatal("added " + recipeDirection);
		return recipeDirection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> getRecipesByUser(User user) {
		logger.fatal("getRecipesByUser() " + user);
		Session session = sessionFactory.getCurrentSession();
		List<Recipe> recipes = (List<Recipe>) session
						.createCriteria(Recipe.class)
						.add(Restrictions.eq("creator", user))
						.list();
		logger.fatal("got recipes " + recipes);
		return recipes;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		logger.fatal("getUserByEmail() " + email);
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session
					.createCriteria(User.class)
					.add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password)).uniqueResult();
					
		logger.fatal("got " + user);
	
		return user;
	}	
}
