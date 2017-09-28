package com.yumyap.dto;

import java.util.List;

import com.yumyap.dto.RecipeDto;

public class UserDto {
	
	private int id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private List<SimpleUserDto> following;
	
	private List<RecipeDto> favoriteRecipes;
}
