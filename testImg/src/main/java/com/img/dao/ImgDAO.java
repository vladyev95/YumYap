package com.img.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.img.bean.Img;
import com.img.util.ConUtil;

public class ImgDAO {
	//CREATE 
	public void addImg(Img img){
		Session session = ConUtil.getSession();
		try{
			Transaction tx = (Transaction) session.beginTransaction();
			session.save(img);
			tx.commit();
		}finally{
			session.close();
		}
	}

	public List<Img> getAllImgs(){
		Session session= ConUtil.getSession();
		Criteria criteria =session.createCriteria(Img.class);
		List<Img> imgs = criteria.list();
		session.close();
		return imgs;
	}
}
