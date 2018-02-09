package com.planet_of_the_tapes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
	
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		
		return "/admin/admin-dashboard";
	}
}
