package com.yumyap.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
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
	
	public CommentService(Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * Returns all the Comments made on a Recipe corresponding to the given RecipeDto
	 * The List is sorted by the date the Comments were made, from newest Comment to oldest Comment
	 * @param recipeDto The RecipeDto corresponding to the actual Recipe for which to get the Comments
	 * @return A List of Comments for the specified Recipe, sorted from newest to oldest
	 */
	public List<Comment> getRecipeComments(RecipeDto recipeDto) {
		Recipe recipe = dao.getRecipeById(recipeDto.getId());
		List<Comment> comments = new ArrayList<>(recipe.getComments());
		Collections.sort(comments, (c1, c2) -> -c1.getDate().compareTo(c2.getDate()));
		return comments;
	}
	
	/**
	 * Creates a comment for the corresponding recipe
	 * @param recipeDto The Recipe for which to add the Comment
	 * @param comment The Comment to be added
	 */
	public void createComment(RecipeDto recipeDto, Comment comment) {
		logger.trace("createComment() with " + recipeDto + ", " + comment);
		User commenter = dao.getUserById(recipeDto.getCreatorId());
		comment.setCommenter(commenter);
		dao.addCommentForRecipeById(recipeDto.getId(), comment);
	}
}
