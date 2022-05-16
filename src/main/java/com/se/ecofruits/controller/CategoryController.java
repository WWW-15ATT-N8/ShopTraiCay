package com.se.ecofruits.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se.ecofruits.service.CategoryServiceImpl;
import com.se.ecofruits.service.ProductService;
import com.se.ecofruits.entity.Category;
import com.se.ecofruits.entity.Product;
import com.se.ecofruits.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{categoryID}/page/{pages}")
	public String listProductPage(@PathVariable int categoryID, @PathVariable int pages,  Model model) {

		Category category = categoryService.getCategory(categoryID);
		if(category == null)
			return "redirect:/";
		int cnt = 0;
		List<Product> products = new ArrayList<Product>();
		for(Product p : productService.getProducts()) {
			if(p.getCategory().getCategoryID() == categoryID) {
				if(cnt>(pages-1)*8 && cnt<=pages*8)
					products.add(p);
				cnt++;
			}
		};
		model.addAttribute("products", products);
		model.addAttribute("category", category);
		model.addAttribute("size_product", cnt);
		model.addAttribute("page", pages);
		if(pages == 1)
			return "redirect:/category/" + categoryID;
		if(products.size() == 0)
			return "redirect:/category/" + categoryID;
		return "DanhSachSanPham";
	}
	
	@GetMapping("/{categoryID}")
	public String listProduct(@PathVariable int categoryID,  Model model) {
		Category category = categoryService.getCategory(categoryID);
		int cnt = 0;
		List<Product> products = new ArrayList<Product>();
		for(Product p : productService.getProducts()) {
			if(p.getCategory().getCategoryID() == categoryID) {
				if(products.size()<8)
					products.add(p);
				cnt++;
			}
		};
		model.addAttribute("products", products);
		model.addAttribute("category", category);
		model.addAttribute("size_product", cnt);
		model.addAttribute("page", 1);
		return "DanhSachSanPham";
	}
	

//	@GetMapping("/{categoryID}")
//	public String listProductPage(@PathVariable int categoryID, @RequestParam("page") int pages,  Model model) {
//
//		Category category = categoryService.getCategory(categoryID);
//		if(category == null)
//			return "redirect:/";
////		if(pages == null) pages = 1;
//		int cnt = 0;
//		List<Product> products = new ArrayList<Product>();
//		for(Product p : productService.getProducts()) {
//			if(p.getCategory().getCategoryID() == categoryID) {
//				if(products.size()>(pages-1)*8 && products.size()<=pages*8)
//					products.add(p);
//				cnt++;
//			}
//		};
//		model.addAttribute("products", products);
//		model.addAttribute("category", categoryID);
//		model.addAttribute("size_product", cnt);
//		model.addAttribute("page", pages);
//		if(pages == 1)
//			return "redirect:/category/" + categoryID;
//		if(products.size() == 0)
//			return "redirect:/category/" + categoryID;
//		return "DanhSachSanPham";
//	}
}
