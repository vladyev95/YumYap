package com.yumyap.dto;

import com.yumyap.beans.User;

/**
 * A lightweight data transfer object only containing the necessary information of a User
 * Lighter than UserDto and contains only info about the User
 * @author vlad
 */
public class SimpleUserDto {

    private int id;

    private String firstName;

    private String lastName;
    
    public SimpleUserDto() {}
    
    /**
     * Converts a User into a SimpleUserDto with the corresponding fields
     * @param user The User which to convert into a SimpleUserDto
     */
    public SimpleUserDto(User user) {
    	this.id = user.getId();
    	this.firstName = user.getFirstName();
    	this.lastName = user.getLastName();
    }

    /**
     * Converts a UserDto into a SimpleUserDto with the corresponding fields
     * @param userDto The UserDto which to convert into a SimpleUserDto
     */
	public SimpleUserDto(UserDto userDto) {
		this.id = userDto.getId();
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns a nice String representation of a SimpleUserDto
	 * @return A nice String representation of a SimpleUserDto
	 */
	@Override
	public String toString() {
		return "SimpleUserDto { id: " + id + 
				", firstName: " + firstName + 
				", lastName: " + lastName + " }";
	}
}