package com.se.ecofruits.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se.ecofruits.service.CategoryServiceImpl;
import com.se.ecofruits.entity.Category;
import com.se.ecofruits.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		List<Category> categories = categoryService.getCategories();
        // add the customers to the model
//		PagedListHolder<Category> pagedListHolder = new PagedListHolder<Category>(categories);
//		
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		pagedListHolder.setPage(page);
//		pagedListHolder.setPageSize(4);
		
//		System.out.println(pagedListHolder);
		model.addAttribute("categories", categories);
		
		
		return "categories";
	}
}
