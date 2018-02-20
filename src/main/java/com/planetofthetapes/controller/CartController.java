package com.planetofthetapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@Controller
public class CartController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MasterController masterSession;
	
	@RequestMapping("/cart")
	public String cart(Model model,HttpServletRequest request) {
		masterSession.session(model, request);
		
		return "cart";
	}
	
	@RequestMapping("/checkout")
	public String checkout(Model model,HttpServletRequest request) {
		masterSession.session(model, request);
		
		return "checkout";
	}
}
