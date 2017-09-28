package com.yumyap.dto;

import java.util.List;

public class CommentsDto {
	private List<CommentDto> comments;

	public CommentsDto(){}
	public CommentsDto(List<CommentDto> comments) {
		super();
		this.comments = comments;
	}
	public List<CommentDto> getComments() {
		return comments;
	}
	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	
	
}
