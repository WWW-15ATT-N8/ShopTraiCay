package com.se.ecofruits.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.ecofruits.entity.Cart;
import com.se.ecofruits.entity.Order;
import com.se.ecofruits.entity.Order_Detail;
import com.se.ecofruits.entity.Status;
import com.se.ecofruits.entity.User;
import com.se.ecofruits.service.OrderService;
import com.se.ecofruits.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/saveorder")
	public String saveOrder(HttpServletRequest request, HttpSession session,
							@ModelAttribute("Order") Order theOrder,
							@RequestParam("UName") String name) {
		Date now = new Date(System.currentTimeMillis());
		User u;
		List<Cart> carts = (List<Cart>) session.getAttribute("carts");
		List<Order_Detail> details = new ArrayList<Order_Detail>();
		for(Cart c : carts)
			details.add(new Order_Detail(c.getProduct(), c.getAmount(), c.getProduct().getPrice()));
		try {
			u = userService.getUserbyPhone(theOrder.getShipPhone());
		} catch (Exception e) {
			u  = new User(0, name, theOrder.getShipPhone());
			userService.saveUser(u);
			u = userService.getUserbyPhone(theOrder.getShipPhone());
		}
		theOrder.setUser(u);
		theOrder.setCreatedDate(now);
		theOrder.setOrderID(0);
		theOrder.setStatus(new Status(1));
		theOrder.setDiscount(0.0f);
		theOrder.setOrder_Details(details);

//		System.out.println(theOrder);
		orderService.saveOrder(theOrder);
		session.removeAttribute("carts");
		int orderID = orderService.getMaxID();
		return "redirect:/order/" + orderID;
	}
	
	@GetMapping("/{orderID}")
	public String getOrder(HttpServletRequest request, HttpSession session,
							@PathVariable int orderID, Model theModel) {
		Order o = orderService.getOrder(orderID);
		System.out.println(o);
		theModel.addAttribute("ORDER", o);
		return "ChiTietDonHang";
	}
}
