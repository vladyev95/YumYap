package com.yumyap.test;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dao.DaoImpl;

public class TestData {
	@Autowired
	private static Dao dao;
	
	public static void main(String[] args){
		//Dao dao=new DaoImpl(null);
		User testUser1 =new User(null, "first1", "last1", "pwd", "user1@test.com", null, null);
		User testUser2 =new User(null, "first2", "last2", "pwd", "user2@test.com", null, null);
		
		Recipe rec1= new Recipe();
		Recipe rec2= new Recipe();
		
		Date date = new Date(System.currentTimeMillis());
		
		Comment comment1 =new Comment(testUser1, date, "test comment1");
		Comment comment2 =new Comment(testUser2, date, "test comment2");
		
		dao.addUser(testUser1);
		dao.addUser(testUser2);
		dao.addComment(comment1);
		dao.addComment(comment2);
	}
}
