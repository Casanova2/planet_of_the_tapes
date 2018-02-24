package com.planetofthetapes.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

public class MasterService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private POrderRepository POrderRepository;
	@Autowired
	private PackRepository packRepository;
	
	public void session (Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
			User loggedUser = userRepository.findByName(request.getUserPrincipal().getName());
			if(request.isUserInRole("ROLE_ADMIN")) {
				model.addAttribute("admin", true);
			}
			System.out.println("Este es el masterService");			
			
			if(loggedUser.hasOrders()) {
				POrder actual = POrderRepository.findByState("progress");
				for(POrder p: loggedUser.getOrders()) {
					if(p.equals(actual)) {
						model.addAttribute("productsOrders", actual.getProducts());
						model.addAttribute("totalOrder",actual.getTotal());
					}
				}
			}
			
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
			
			this.numbers(model);
		} else {
			model.addAttribute("logged", false);
			model.addAttribute("profile", true);
		}
	}
	
	public void numbers (Model model) {
		model.addAttribute("numberProducts",productRepository.findAll().size());
		model.addAttribute("numberUsers",userRepository.findAll().size());
		model.addAttribute("numberOrders",POrderRepository.findAll().size());
		model.addAttribute("numberPacks",packRepository.findAll().size());
	}
}
