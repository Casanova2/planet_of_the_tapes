package com.planetofthetapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@Component
public class MasterController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private POrderRepository porderRepository;
	@Autowired
	private PackRepository packRepository;
	
	POrder carrito;
	
	public POrder getCarrito() {
		return carrito;
	}
	
	public void session (Model model,HttpServletRequest request) {
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			User loggedUser = userRepository.findByName(request.getUserPrincipal().getName());
			carrito = loggedUser.getOrders().get(0);
			System.out.println(carrito.getProducts());
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
		model.addAttribute("numberOrders",porderRepository.findAll().size());
		model.addAttribute("numberPacks",packRepository.findAll().size());
	}
}
