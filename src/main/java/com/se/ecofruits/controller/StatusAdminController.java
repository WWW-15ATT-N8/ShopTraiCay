package com.se.ecofruits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se.ecofruits.entity.Status;
import com.se.ecofruits.service.StatusService;

@Controller
@RequestMapping("/admin/status")
public class StatusAdminController {
	
	@Autowired
	private StatusService  statusService ;
	
	@GetMapping("/list")
	public String getStatus(Model theModel) {
		List<Status> list = statusService.getStatuses();
		
		theModel.addAttribute("statuses", list );
		return "admin-status-list";
	}

}
