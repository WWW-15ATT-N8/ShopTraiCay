package com.se.ecofruits.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.ecofruits.entity.Cart;
import com.se.ecofruits.entity.Category;
import com.se.ecofruits.entity.Image;
import com.se.ecofruits.entity.Order;
import com.se.ecofruits.entity.Product;
import com.se.ecofruits.entity.User;
import com.se.ecofruits.service.CategoryService;
import com.se.ecofruits.service.ImageService;
import com.se.ecofruits.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ImageService imageService;;
	
	

	@GetMapping("/")
    public String home(HttpServletRequest request, HttpSession session,Model theModel) {
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
	
	@GetMapping("/vevinfruits")
	public String gioiThieu(){
		return "GioiThieu";
	}

	
	@GetMapping("/dichvu")
	public String dichVu(){
		return "DichVu";
	}
	
	
	@GetMapping("/product/{productID}")
	public String getProduct(@PathVariable int productID, Model theModel) {
		
		Product product = productService.getProduct(productID);
		if(product == null) {
			throw new RuntimeException("Không tìm thấy id  product : " + productID);
		}
		Category category = product.getCategory();
		
		List<Image> imgs = imageService.getImagesByProductID(productID);
		for( Image i : imgs)
			System.out.println(i);
		int cnt = 0;
		List<Product> products = new ArrayList<Product>();
		for(Product p : productService.getProducts()) {
			if(p.getCategory().getCategoryID() == category.getCategoryID()) {
				products.add(p);
				cnt++;
			}
			if(cnt == 4) break;	
		}
			
		
		theModel.addAttribute("product",product);
		theModel.addAttribute("category",category);
		theModel.addAttribute("img",imgs);
		theModel.addAttribute("desc",product.getDescription());
		theModel.addAttribute("sam_product",products);
		return "ChiTietSanPham";		
	}
	
	@GetMapping("/thanhtoan")
	public String thanhToan(HttpServletRequest request, HttpSession session, Model theModel){
		List<Cart> carts = (List<Cart>) session.getAttribute("carts");
		
		
		theModel.addAttribute("Order",new Order());
		theModel.addAttribute("carts", carts);
		return "ThanhToan";
	}

}
