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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.ecofruits.service.CategoryServiceImpl;
import com.se.ecofruits.entity.Category;
import com.se.ecofruits.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {
	public static final String PATH_CONTEXT = "/EcoFruits";
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public String defaultCategory() {
		return "redirect:/admin/category/list";
	}
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model) {
        // add the customers to the model
		List<Category> categories = null;
		List<Category> categoriesFilter = new ArrayList<Category>();
		if (request.getParameter("name") != null || request.getParameter("rangeNumOfProduct") != null) {
			String name = request.getParameter("name");
			String rangeNumOfProduct = request.getParameter("rangeNumOfProduct");
			String[] rangePrice = rangeNumOfProduct.split(";");
			int min = Integer.parseInt(rangePrice[0]);
			int max = Integer.parseInt(rangePrice[1]);
			
			categories = categoryService.getCategoriesFilter(name);
			for (Category category : categories) {
				int numOfProduct = category.getProducts().size();
				if (numOfProduct <= max && numOfProduct >= min) {
					categoriesFilter.add(category);
				}
			}
			model.addAttribute("name", name);
			model.addAttribute("rangeNumOfProduct", rangeNumOfProduct);
			model.addAttribute("url" , PATH_CONTEXT + "/admin/category/list?name="+name+"&rangeNumOfProduct="+rangeNumOfProduct.replace(";", "%3B")+"&");
		} else {
			categories = categoryService.getCategories();
			model.addAttribute("url" , PATH_CONTEXT + "/admin/category/list?");
		}
		
		categories = categoriesFilter.size() == 0 ? categories : categoriesFilter;
		
		if (categories == null) {
			categories = new ArrayList<Category>();
		}
		
		PagedListHolder<Category> pagedListHolder = new PagedListHolder<Category>(categories);
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		pagedListHolder.setPage(page - 1);
		pagedListHolder.setPageSize(3);
		
//		System.out.println(pagedListHolder);
		model.addAttribute("categories", pagedListHolder);
		model.addAttribute("pageCount", pagedListHolder.getPageCount());
		model.addAttribute("pageCurrent", pagedListHolder.getPage());
		return "categories";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("category", new Category());
		return "category-form";
	}
	
	@PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
		
        categoryService.saveCategory(category);
        return "redirect:/admin/category/list";
    }
	
	@GetMapping("/update")
	public String update(@RequestParam("categoryID") int id, Model model) {
		Category category = categoryService.getCategory(id);
		model.addAttribute("category", category);
		return "category-form";
	}
	
	@GetMapping("/delete")
	public String deleteCategory(@RequestParam("categoryID") int id) {

	     // delete the customer
		categoryService.deleteCategory(id);
		return "redirect:/admin/category/list";
	}
	
}
