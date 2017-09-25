package com.yumyap.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TEst {

	public static void main(String[] args) {

		ApplicationContext ac= new ClassPathXmlApplicationContext("beans.xml");
	}

}
