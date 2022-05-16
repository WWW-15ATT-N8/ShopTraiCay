package com.se.ecofruits.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.se.ecofruits.entity.Category;
import com.se.ecofruits.entity.Product;

@Controller
public class ImageAdminController {
	@RequestMapping("/loadImgForm")
	public String showForm() {
		
		return "UploadImg";
	}

	@RequestMapping(value="/savefile",method=RequestMethod.POST)  
	public String upload(@RequestParam MultipartFile[] files,HttpServletRequest request, Model model){  
		String fileName = null;
		for (MultipartFile file : files) {
			fileName = saveImage(file, request);
			
			break;
		}
		System.out.println("img "+  fileName);
		model.addAttribute("fileName", fileName);
		return "upload-success";
	} 
	
	public String saveImage(MultipartFile file, HttpServletRequest request) {
		String filename = null;
		if (file != null && !file.isEmpty()) {
			try{  
				byte barr[]=file.getBytes();  
				String path = request.getServletContext().getRealPath("/");  
				String path2 = request.getContextPath();
				filename=file.getOriginalFilename();  
				
				path = path.replace(".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\", "");
				
				System.out.println(path+"src\\main\\webapp\\resources\\img\\"+filename);  
				
				BufferedOutputStream bout=new BufferedOutputStream(  
						new FileOutputStream(path+"src\\main\\webapp\\resources\\img\\"+filename));  
				bout.write(barr);  
				bout.flush();  
				bout.close();  
				return filename;
				
			}catch(Exception e){System.out.println(e);} 
		}
		return filename;
		
	}
	
}