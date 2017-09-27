package com.yumyap.dto;

import java.util.Set;

import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.FollowingDto;

public class UserDto {
	
	private int id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private List<SimpleUserDto> following;
	
	private List<RecipeDto> favoriteRecipes;
}
