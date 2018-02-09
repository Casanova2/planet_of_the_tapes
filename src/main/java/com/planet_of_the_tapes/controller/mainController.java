package com.planet_of_the_tapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planet_of_the_tapes.entity.User;
import com.planet_of_the_tapes.repository.UserRepository;

@Controller
public class mainController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model, HttpServletRequest request) {
		User user = userRepository.findById(3);
		User loggedAdmin = userRepository.findByName("Ruben");
		model.addAttribute("admin", loggedAdmin);
		model.addAttribute("user",user);
		
		
		return "/admin/admin-dashboard";
	}
}
