package com.se.ecofruits.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.ecofruits.entity.Cart;
import com.se.ecofruits.service.ImageService;
import com.se.ecofruits.service.ProductService;

@Controller
@RequestMapping("/giohang")
public class CartController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ImageService imageService;
	
	
	
	@GetMapping("/")
	public String GioHang(HttpServletRequest request, HttpSession session, Model theModel){
		List<Cart> carts = (List<Cart>)session.getAttribute("carts");

		
		theModel.addAttribute("carts", carts);
		return "GioHang";
	}
	
	
	@PostMapping("/updatecart")
	public String updateCart(HttpServletRequest request, HttpSession session, Model theModel) {
		String []listAmount = request.getParameterValues("amount");
		List<Cart> carts = (List<Cart>) session.getAttribute("carts");
		int total = 0;
		for(int i = carts.size() - 1;i >= 0 ; i--) {
			if(Integer.valueOf(listAmount[i])>0) {
				carts.get(i).setAmount(Integer.valueOf(listAmount[i]));
				total += carts.get(i).getProduct().getPrice()*carts.get(i).getAmount();
			}
				
			else
				carts.remove(i);
		}	
		session.setAttribute("total",total);
		session.setAttribute("carts", carts);
		return "redirect:/giohang/";
	}

	@PostMapping("/addcart")
	public String addCart(HttpServletRequest request, HttpSession session, 
			@RequestParam("productID") int productID,
			@RequestParam("quantity") int quantity , Model theModel){
		List<Cart> carts = (List<Cart>)session.getAttribute("carts");
		Cart cart = new Cart(productService.getProduct(productID), null, quantity);
		boolean ch =false;
		if(carts == null)
			carts = new ArrayList<Cart>();
		for(Cart c : carts) {
			if(c.getProduct().getProductID() == productID) {
				c.setAmount(c.getAmount() + quantity);
				ch = true;
			}
		} 
		if(!ch)
			carts.add(cart);
		for(Cart c : carts) {
			System.out.println(c);
		}
		session.setAttribute("carts", carts);
		
		return "redirect:/product/" + productID;
	}
}
