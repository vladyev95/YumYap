package com.img.dto;

import java.util.ArrayList;

import com.img.bean.Img;

public class DTO {
	private ArrayList<Img> imgs;
	
	public DTO(){}
	public DTO(ArrayList<Img> imgs) {
		super();
		this.imgs = imgs;
	}
	public ArrayList<Img> getImgs() {
		return imgs;
	}
	public void setImgs(ArrayList<Img> imgs) {
		this.imgs = imgs;
	}
	
	
}
