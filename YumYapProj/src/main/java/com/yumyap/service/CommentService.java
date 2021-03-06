package com.yumyap.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dto.CommentDto;
import com.yumyap.dto.RecipeDto;

/**
 * A Service for retrieving Comments
 * @author vlad
 */
@Service
public class CommentService {
	
	private static final Logger logger = Logger.getLogger(CommentService.class);
	
	@Autowired
	private Dao dao;
	
	@Autowired
	public CommentService(Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * Returns all the Comments made on a Recipe corresponding to the given RecipeDto
	 * The List is sorted by the date the Comments were made, from newest Comment to oldest Comment
	 * @param recipeDto The RecipeDto corresponding to the actual Recipe for which to get the Comments
	 * @return A List of Comments for the specified Recipe, sorted from newest to oldest
	 */
	public List<CommentDto> getRecipeComments(RecipeDto recipeDto) {
		Recipe recipe = dao.getRecipeById(recipeDto.getId());
		Set<CommentDto> comment = new LinkedHashSet<CommentDto>();
		comment.addAll(dao.getCommentsByRecipe(recipe));
		logger.trace(comment);
		List<CommentDto> comments = new ArrayList<CommentDto>(); //recipe.getComments()
//		System.out.println(comments);
		comments.addAll(comment);
		if(comments != null && comments.size() > 1) {
			logger.trace("sorting comments by date");
		Collections.sort(comments, (c1, c2) -> -c1.getCommentDate().compareTo(c2.getCommentDate()));
		}
		return comments;
	}
	
	/**
	 * Creates a comment for the corresponding recipe
	 * @param comment The Comment to be added
	 */
	public void createComment(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setDate(new  GregorianCalendar());
		User commenter = dao.getUserById(commentDto.getCommenter().getId());
		comment.setCommenter(commenter);
		Recipe recipe = dao.getRecipeById(commentDto.getRecipe().getId());
		comment.setRecipe(recipe);
		comment.setContent(commentDto.getContent());
		logger.trace("createComment() with " + comment);
		dao.addComment(comment);
	}
}
