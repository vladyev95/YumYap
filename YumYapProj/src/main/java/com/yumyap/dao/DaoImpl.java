package com.yumyap.dao;
import java.util.ArrayList;
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
import com.yumyap.beans.User;
import com.yumyap.dto.CommentDto;
import com.yumyap.dto.RecipeDto;

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

	@SuppressWarnings("unchecked")
	public List<Recipe> getRecipes() {
		Session s = sessionFactory.getCurrentSession();
		return (List<Recipe>) s.createCriteria(Recipe.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getComments(Recipe r) {
		return (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("recipe", r)).list();
	}

	@SuppressWarnings("unchecked")
	public  Set<Recipe> getRecipes(String search) {
		Set<Recipe> recipes = new LinkedHashSet<Recipe>();
		recipes.addAll(sessionFactory.getCurrentSession().createCriteria(Recipe.class)
				.add(Restrictions.ilike("name", "%" + search + "%")).list());
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
		
		if (user == null)
			return null;
		
		user.getFavoriteRecipes().stream()
			.forEach(recipe -> recipe.getId());
		
		user.getFollowing().stream()
			.forEach(userProxy -> userProxy.getId());
		
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

		logger.trace("getUsersRecipes() returning: " + recipes);
		return recipes;
	}

	@Override
	public User getUserById(int id) {
		logger.trace("getUserById() by " + id); 
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		logger.trace("got " + user);
		return user;
	}

	@Override
	public Recipe getRecipeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		logger.trace("getRecipeById() by " + id);
		Recipe recipe = (Recipe) session.get(Recipe.class, id);
		logger.trace("got " + recipe);
		return recipe;
	}

	@Override
	public void updateUser(User user) {

		currentSession().save(user);
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addCommentForRecipeById(int id, Comment comment) {
		logger.trace("addCommentForRecipyById() for id: " + id + " comment: " + comment);
		Session session = sessionFactory.getCurrentSession();
		Recipe recipe = getRecipeById(id);
		recipe.getComments().add(comment);
		session.merge(recipe);
	}
	
	public List<CommentDto> getCommentsByRecipe(Recipe recipe) {
		Session session = sessionFactory.getCurrentSession();
		List<Comment> comments = session.createCriteria(Comment.class).add(Restrictions.eq("recipe", recipe)).list();
		logger.trace("getting comments " +comments);
		List<CommentDto> commentDtos = new ArrayList<>();
		Comment a = comments.get(0);
		for(Comment c: comments) {
			logger.trace("converting "+c +" to comment dto");
			logger.trace(a.equals(c));
			commentDtos.add(new CommentDto(c));
			a = c;
		}
		System.out.println(comments);
		return commentDtos;
	}
}
