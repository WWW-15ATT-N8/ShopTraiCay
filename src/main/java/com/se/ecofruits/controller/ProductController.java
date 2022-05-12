package com.se.ecofruits.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se.ecofruits.entity.Category;
import com.se.ecofruits.entity.Product;
import com.se.ecofruits.service.CategoryService;
import com.se.ecofruits.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/")
    public String home(Model theModel) {
		List<Product> products = productService.getProducts();
		List<Product> newProducts = new ArrayList<Product>();
		List<Product> bestSalerProducts = new ArrayList<Product>();
		for(Product p : products) {
			if(p.isNewProduct())
				newProducts.add(p);
			if(p.isBestSaler())
				bestSalerProducts.add(p);
		}
		theModel.addAttribute("list_new_product" , newProducts);
		theModel.addAttribute("list_best_saler_product" , bestSalerProducts);
        return "Home"; 
    }
	@GetMapping("/dichvu")
	public String dichVu(){
		return "DichVu";
	}
	
	@GetMapping("/vevinfruits")
	public String gioiThieu(){
		return "GioiThieu";
	}
	
	@GetMapping("/chitietsanpham")
	public String chiTietSanPham(){
		return "ChiTietSanPham";
	}
	
	@GetMapping("/giohang")
	public String GioHang(){
		return "GioHang";
	}
}
