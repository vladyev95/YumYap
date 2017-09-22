package com.img.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.img.bean.Img;
import com.img.dao.ImgDAO;
import com.img.dto.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
@WebServlet("/load")
public class loadImgServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		ImgDAO imgdao=new ImgDAO();
		ArrayList<Img> imgs=(ArrayList<Img>) imgdao.getAllImgs();
		for(int i=0; i<imgs.size(); i++){
			System.out.println(imgs.get(i).getId());
		}
		DTO dto =new DTO(imgs);
		ObjectMapper mapper= new ObjectMapper();
		String json=mapper.writeValueAsString(dto);
		PrintWriter out=res.getWriter();
		res.setContentType("applicaton/json");
		out.write(json);
	}
}
