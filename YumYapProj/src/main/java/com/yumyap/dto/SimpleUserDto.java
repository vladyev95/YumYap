package com.yumyap.dto;

import com.yumyap.beans.User;

/**
 * A lightweight data transfer object only containing the necessary information of a User
 * @author vlad
 */
public class SimpleUserDto {

    private int id;

    private String firstName;

    private String lastName;
    
    public SimpleUserDto() {}
    
    public SimpleUserDto(User user) {
    	super();
    	this.id = user.getId();
    	this.firstName = user.getFirstName();
    	this.lastName = user.getLastName();
    }

	public SimpleUserDto(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public SimpleUserDto(UserDto userDto) {
		super();
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
	 * Checks for equivalence by checking the id of each object
	 * @return boolean value corresponding to object equivalence
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null || obj.getClass() != SimpleUserDto.class)
			return false;
		
		if (this.id == ((SimpleUserDto)obj).getId())
			return true;
		else return false;
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