package com.img.test;

import com.img.bean.Img;
import com.img.dao.ImgDAO;

public class MainTest {
	public static void main(String[] args){
		ImgDAO imgdao=new ImgDAO();
		//Img img=new Img("C:/Users/1Z4XS/Desktop/htai/junkj","This is a test image");
		Img img1=new Img();
		img1.setDescription("this is a test image");
		img1.setLink("https://s3.amazonaws.com/yumyapimg/img/3295eaa311792045ad7206828307aff9.jpg");
		//img1.setLink("http://i1152.photobucket.com/albums/p499/4xsTheMasterRace/molten-chocolate-cake_zpsaofcslrs.jpg");
		imgdao.addImg(img1);
		
		Img img2=new Img();
		img2.setDescription("this is a test image");
		img2.setLink("https://s3.amazonaws.com/yumyapimg/img/FINECK015-800x800.jpg");
		//img2.setLink("http://i1152.photobucket.com/albums/p499/4xsTheMasterRace/SD_FD_F09A_00346825_NC_X_EC_0_zpsrjt53kxc.jpg");
		imgdao.addImg(img2);
		
		Img img3=new Img();
		img3.setDescription("this is a test image");
		img3.setLink("https://s3.amazonaws.com/yumyapimg/img/SD_FD_F09A_00346825_NC_X_EC_0.jpg");
		//img3.setLink("http://i1152.photobucket.com/albums/p499/4xsTheMasterRace/FINECK015-800x800_zpsavpfuzn8.jpg");
		imgdao.addImg(img3);
		
		Img img4=new Img();
		img4.setDescription("this is a test image");
		img4.setLink("https://s3.amazonaws.com/yumyapimg/img/feine-torten.jpg");
		//img4.setLink("http://i1152.photobucket.com/albums/p499/4xsTheMasterRace/signature-cakes-cakebatterconfetti_zpsfr1rnomh.jpg");
		imgdao.addImg(img4);
		
		Img img5=new Img();
		img5.setDescription("this is a test image");
		img5.setLink("https://s3.amazonaws.com/yumyapimg/img/molten-chocolate-cake.jpg");
		//img5.setLink("http://i1152.photobucket.com/albums/p499/4xsTheMasterRace/3295eaa311792045ad7206828307aff9_zpsheugzgkp.jpg");
		imgdao.addImg(img5);
		
		Img img6=new Img();
		img6.setDescription("this is a test image");
		img6.setLink("https://s3.amazonaws.com/yumyapimg/img/signature-cakes-cakebatterconfetti.jpg");
		//img6.setLink("http://i1152.photobucket.com/albums/p499/4xsTheMasterRace/feine-torten_zps5gfofvko.jpg");
		imgdao.addImg(img6);
	}
}
