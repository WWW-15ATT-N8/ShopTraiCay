package com.se.ecofruits.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se.ecofruits.entity.Category;
import com.se.ecofruits.entity.Order;
import com.se.ecofruits.entity.Order_Detail;
import com.se.ecofruits.entity.Status;
import com.se.ecofruits.service.OrderDetailService;
import com.se.ecofruits.service.OrderService;
import com.se.ecofruits.service.StatusService;

@Controller
@RequestMapping("/admin/order")
public class OrderAdminController {
	public static final String PATH_CONTEXT = "/EcoFruits";
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private StatusService statusService;
	
	@GetMapping("/list")
	public String listOrder(HttpServletRequest request, Model model) {
		List<Order> orders;
		if (request.getParameter("phone") != null && request.getParameter("statusID") != null && request.getParameter("filter") != null ) {
			int statusID = Integer.parseInt(request.getParameter("statusID"));
			int filter = Integer.parseInt(request.getParameter("filter"));
			String phone = request.getParameter("phone");
			model.addAttribute("phone", phone);
			model.addAttribute("statusID",statusID);
			model.addAttribute("filter",filter);
			String dieukien = "where ";
			boolean check = false;
			if(!phone.toString().trim().equals("")) {
				dieukien += " shipPhone = '" + phone +"' ";
				check = true;
			}
			if(statusID > 0) {
				if(check) dieukien += " and";
				dieukien += " statusID =" + statusID;
			}
			
			if(filter == 1){
				dieukien += " order by createdAt desc";
			} else if(filter == 2){
				dieukien += " order by total asc";
			} else{
				dieukien += " order by total desc";
			}
			
			
			System.out.println(dieukien);
			if(dieukien.equals("where ")) 
				orders =  orderService.getOrdersByDK(" order by createdAt desc");
			else 
				orders =  orderService.getOrdersByDK(dieukien);
			
			model.addAttribute("url" , PATH_CONTEXT + "/admin/order/list?statusID="+String.valueOf(statusID)+"&filter=" + String.valueOf(filter) + "&phone=" +phone);
		} else {
			model.addAttribute("url" , PATH_CONTEXT + "/admin/order/list?");
			model.addAttribute("statusID",0);
			model.addAttribute("filter",1);
			orders =  orderService.getOrdersByDK(" order by createdAt desc");
		}
		
		List<Status> status = statusService.getStatuses();
		model.addAttribute("status",status );
		
		PagedListHolder<Order> pagedListHolder = new PagedListHolder<Order>(orders);
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		pagedListHolder.setPage(page - 1);
		pagedListHolder.setPageSize(10);
		
		
		model.addAttribute("orders", pagedListHolder);
		model.addAttribute("pageCount", pagedListHolder.getPageCount());
		model.addAttribute("pageCurrent", pagedListHolder.getPage());
		return "admin-order-list";
	}
	@GetMapping("/")
	public String defaultOrder() {
		return "redirect:/admin/order/list";
	}
	
	@GetMapping("/detail")
	public String getOrder(@RequestParam("orderID") int orderID, Model theModel) {
		Order o = orderService.getOrder(orderID);
		List<Order_Detail> detail = orderDetailService.getOrdersDetailsByOrderID(orderID);
		
		theModel.addAttribute("order",o);
		theModel.addAttribute("detail",detail);
		return "admin-order-detail";
	}
	
	@PostMapping("/updatestatus")
	public String updateStatus(@RequestParam("statusID") int statusID, @RequestParam("orderID") int orderID) {
		Order order = orderService.getOrder(orderID);
		Status status = statusService.getStatus(statusID);
		order.setStatus(status);
		orderService.updateOrder(order);	
		return "redirect:/admin/order/list";
	}
	
}
