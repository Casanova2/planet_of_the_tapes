package com.planetofthetapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@Component
public class MasterController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public void session (Model model,HttpServletRequest request) {
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			User loggedUser = userRepository.findByName(request.getUserPrincipal().getName());
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
		} else
			model.addAttribute("unlogged", true);
			//return "redirect:/login";
		if (request.isUserInRole("ADMIN"))
			model.addAttribute("admin", true);
			//return "redirect:/";
	}
	
	public void numbers (Model model) {
		model.addAttribute("numberProducts",productRepository.findAll().size());
		model.addAttribute("numberUsers",userRepository.findAll().size());
	}
}