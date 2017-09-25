package com.img.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

//import java.io.ByteArrayInputStream;
import java.io.File;
//import java.io.InputStream;
//import java.io.StringWriter;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.sql.Blob;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.servlet.http.Part;
import org.apache.log4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
//import com.amazonaws.HttpMethod;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.img.bean.Img;
import com.img.dao.ImgDAO;

@WebServlet("/upload")
public class amazonS3Servlet extends HttpServlet{
	static Logger l = Logger.getRootLogger();
	private static String bucketName = "yumyapimg/img";
	private static String key;
	private static String uploadFileName;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Map<String, String[]> myMap = req.getParameterMap();
		Set<String> txObject = myMap.keySet();
		ObjectMapper jackson = new ObjectMapper();
		Object obj = txObject.toArray()[0];
		ArrayList<String> tx =  jackson.readValue(((String)obj), ArrayList.class);
		
		String descript =tx.get(0);	System.out.println(descript);
		String file = tx.get(1);	System.out.println(file);
		System.out.println(file);
		uploadFileName = file;
		Random r = new Random();
		key = r.nextInt(1000000)+1+"";
		AmazonS3 s3 = new AmazonS3Client();
		try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File f = new File(uploadFileName);
            System.out.println(f);
            System.out.println(bucketName);
            System.out.println(key);
            
            PutObjectResult result = s3.putObject(new PutObjectRequest(bucketName, key, f));
            //System.out.println("Result= "+result.getVersionId());
           
         } catch (AmazonServiceException ase) {
         	//System.out.println("Image upload failed: "+ase.getLocalizedMessage());
            l.error(("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason"+            
           "Error Message:    " + ase.getMessage() +
           " HTTP Status Code: " + ase.getStatusCode() +
            "AWS Error Code:   " + ase.getErrorCode())+
            "Error Type:       " + ase.getErrorType() +
            "Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
        	//System.out.println("Image upload failed: "+ace.getLocalizedMessage());
            l.error(("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network." +
           "Error Message: " + ace.getMessage()));
        }
		ImgDAO imgdao=new ImgDAO();
		Img img=new Img("https://s3.amazonaws.com/"+bucketName+"/"+key, descript);
		imgdao.addImg(img);
	}
}
