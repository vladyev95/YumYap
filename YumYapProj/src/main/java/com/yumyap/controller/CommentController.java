package com.yumyap.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yumyap.beans.Comment;
import com.yumyap.dto.RecipeDto;
import com.yumyap.service.CommentService;

/**
 * A Controller that handles requests for information regarding Comments
 * @author vlad
 */
@Controller
@RequestMapping(value = "/comments")
public class CommentController {
	private static final Logger logger = Logger.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	/**
	 * Receives a Comment and the Recipe on which it was made
	 * Persists the Comment for the Corresponding Recipe
	 * @param recipeDto
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/createComment", 
				method = RequestMethod.POST, 
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createComment(@RequestBody RecipeDto recipeDto, @RequestBody Comment comment){
		logger.trace("createComment() " + comment + " for " + recipeDto);
		commentService.createComment(recipeDto, comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Receives a RecipeDto and returns all the Comments corresponding to the Recipe
	 * The comments are returned in order from newest to oldest
	 * @param recipeDto The Recipe for which to get the Comments
	 * @return The Comments for the Recipe, in order from newest to oldest
	 */
	@RequestMapping(value = "/recipeComments",
				method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comment>> recipeComments(@RequestBody RecipeDto recipeDto) {
		logger.trace("recipeComments() by " + recipeDto);
		List<Comment> comments = commentService.getRecipeComments(recipeDto);
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
}
