package com.yumyap.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yumyap.beans.Comment;
import com.yumyap.beans.FoodItem;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

@Repository
@Transactional
public class DaoImpl implements Dao {

	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * Adds User and returns user with the new Id (non-Javadoc)
	 * 
	 * @see com.yumyap.dao.Dao#addUser(com.yumyap.beans.User)
	 */
	public User addUser(User u) {
		Session s = sessionFactory.getCurrentSession();
		int i = (Integer) s.save(u);
		// Should we log the id(i);
		// log the userSystem.out.println("User: "+ u);
		return u;
	}

	/*
	 * Adds FoodItem & returns FoodItem w/ new Id (non-Javadoc)
	 * 
	 * @see com.yumyap.dao.Dao#addFoodItem(com.yumyap.beans.FoodItem)
	 */
	public FoodItem addFoodItem(FoodItem fi) {
		Session s = sessionFactory.getCurrentSession();
		s.save(fi);
		return fi;
	}

	public Comment addComment(Comment c) {
		Session s = sessionFactory.getCurrentSession();
		s.save(c);
		return c;
	}

	public Recipe addRecipe(Recipe r) {
		Session s = sessionFactory.getCurrentSession();
		s.save(r);
		return r;
	}

	// begin get methods

	public User getUser(String email) {
		Session s = sessionFactory.getCurrentSession();
		return (User) s.createCriteria(User.class).add(Restrictions.ilike("email", email)).uniqueResult();

	}

	public List<Comment> getComments(Recipe r) {
		return (List<Comment>) currentSession().createCriteria(Comment.class).add(Restrictions.eq("recipe", r)).list();
	}

	public List<Recipe> getRecipes(String search) {

		return (List<Recipe>) currentSession().createCriteria(Recipe.class)
				.add(Restrictions.ilike("name", "%" + search + "%")).list();
	}

	public List<Recipe> getRecipes(int foodId) {
		List<Recipe> foods = new ArrayList<>();

		for (Recipe r : (List<Recipe>) currentSession().createCriteria(Recipe.class).list()) {
			for (FoodItem fi : r.getIngredients()) {
				if (foodId == fi.getFood()) {
					foods.add(r);
				}
			}
		}
		return foods;
	}

	public boolean updateUser(User user) {
		currentSession().saveOrUpdate(user);
		return false;
	}

	public boolean deleteComment(Comment c) {
		currentSession().saveOrUpdate(c);
		return false;
	}

	public boolean deleteUser(User u) {
		currentSession().delete(u);
		return false;
	}

	public boolean deleteFoodItem(FoodItem fi) {
		currentSession().delete(fi);
		return false;
	}

	public boolean deleteRecipe(Recipe r) {
		currentSession().delete(r);
		return false;
	}

	@Override
	public void setRecipes(List<Recipe> recs) {
		// TODO Auto-generated method stub
		
	}

}
