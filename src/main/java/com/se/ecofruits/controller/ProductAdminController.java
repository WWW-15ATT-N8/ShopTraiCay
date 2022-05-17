package com.se.ecofruits.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.ecofruits.entity.Category;
import com.se.ecofruits.entity.Image;
import com.se.ecofruits.entity.Product;
import com.se.ecofruits.service.CategoryService;
import com.se.ecofruits.service.ImageService;
import com.se.ecofruits.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {
	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final String PATH_CONTEXT = "/EcoFruits";
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ImageService imageService;
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		List<Product> products = null;
		List<Category> categories = categoryService.getCategories();
        // add the customers to the model
		
		if (request.getParameter("name") != null || 
				request.getParameter("category") != null ||
				request.getParameter("price") != null ||
				request.getParameter("stock") != null) {
			
			String name = request.getParameter("name");
			int categoryID = Integer.parseInt(request.getParameter("category"));
			String[] rangePrice = request.getParameter("price").split(";");
			String[] rangStock = request.getParameter("stock").split(";");
			String isBestSaler = request.getParameter("bestSaler");
			String isNewProduct = request.getParameter("newProduct");
			
			products = productService.getProductsFilter(name, categoryID, isNewProduct, isBestSaler, rangePrice, rangStock);
			
			model.addAttribute("url" , PATH_CONTEXT + "/admin/product/list?name="+ name +"&category="+categoryID+"&bestSaler="+isBestSaler+"&newProduct"+isNewProduct+"&price="+rangePrice[0]+"%3B"+rangePrice[1]+"&stock="+rangStock[0]+"%3B"+rangStock[1]+"&");
			model.addAttribute("categoryID", categoryID);
			model.addAttribute("bestSaler", isBestSaler);
			model.addAttribute("newProduct", isNewProduct);
			model.addAttribute("rangePrice", request.getParameter("price"));
		} else {
			products = productService.getProducts();
			model.addAttribute("url" , PATH_CONTEXT + "/admin/product/list?");
		}
		
		if (products == null) {
			products = new ArrayList<Product>();
		}
		
		PagedListHolder<Product> pagedListHolder = new PagedListHolder<Product>(products);
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		pagedListHolder.setPage(page - 1);
		pagedListHolder.setPageSize(10);
		pagedListHolder.getPage();
		
//		System.out.println(pagedListHolder);
		model.addAttribute("products", pagedListHolder);
		model.addAttribute("categories", categories);
		model.addAttribute("pageCount", pagedListHolder.getPageCount());
		model.addAttribute("pageCurrent", pagedListHolder.getPage());
		
		return "products";
	}
	
	@GetMapping("/create")
	public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categories);
		return "product-form";
	}
	
	@PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, @ModelAttribute("categoryID") int categoryID, HttpServletResponse response) throws UnsupportedEncodingException {
		
		System.out.println(new String(product.getName().getBytes("UTF-8")));
		Category category = categoryService.getCategory(categoryID);
		product.setCategory(category);
		productService.saveProduct(product);
		System.out.println(product.getName());
        return "redirect:/admin/product/update?productID="+product.getProductID();
    }
	
	@GetMapping("/update")
	public String update(@RequestParam("productID") int id, Model model) {
		Product product = productService.getProduct(id);
		List<Category> categories = categoryService.getCategories();
		List<Image> images = imageService.getImagesByProductID(id);
		model.addAttribute("images",images);
		model.addAttribute("categories", categories);
		model.addAttribute("categoryID", product.getCategory().getCategoryID());
		model.addAttribute("product", product);
		return "product-form";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("productID") int id) {

	     // delete the customer
		productService.deleteProduct(id);
		return "redirect:/admin/product/list";
	}
}
