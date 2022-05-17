package com.se.ecofruits.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.se.ecofruits.entity.Image;
import com.se.ecofruits.entity.Product;
import com.se.ecofruits.service.ImageService;
import com.se.ecofruits.service.ProductService;

@Controller
@RequestMapping("/admin/image")
public class ImageAdminController {
	public static final String PATH_STORE_ROOT = "\\resources\\img\\product\\";
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/loadImgForm")
	public String showForm() {
		
		return "UploadImg";
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)  
	public String upload(@RequestParam MultipartFile[] files,@RequestParam int productID, HttpServletRequest request, Model model){  
		
		Product product = productService.getProduct(productID);
		List<Image> images = imageService.getImagesByProductID(productID);
		if (images.size() != 0) {
			for (Image image : images) {
				imageService.deleteImage(image);
			}
		}
		
		for (MultipartFile file : files) {
			String src = PATH_STORE_ROOT+saveImage(file, request);
			imageService.saveImage(new Image(src, product));
		}
		return "redirect:/admin/product/list";
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
				
				System.out.println(path+"src\\main\\webapp"+PATH_STORE_ROOT+filename);  
				
				BufferedOutputStream bout=new BufferedOutputStream(  
						new FileOutputStream(path+"src\\main\\webapp"+PATH_STORE_ROOT+filename));  
				bout.write(barr);  
				bout.flush();  
				bout.close();  
				return filename;
				
			}catch(Exception e){System.out.println(e);} 
		}
		return filename;
	}
	
}