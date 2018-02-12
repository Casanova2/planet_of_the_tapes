package com.planet_of_the_tapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planet_of_the_tapes.entity.Product;
import com.planet_of_the_tapes.entity.User;
import com.planet_of_the_tapes.repository.ProductRepository;
import com.planet_of_the_tapes.repository.UserRepository;

import antlr.collections.List;

@Controller
public class adminController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		
		//Esto no debe ir asi, tiene que ir en una clase privada donde se llame cada constantemente.
		User user = userRepository.findById(3);
		User loggedAdmin = userRepository.findByName("Ruben");
		Page<Product> products = productRepository.findAll(new PageRequest(0, 18));
		int numberProducts = products.getNumberOfElements();
		
		model.addAttribute("admin", loggedAdmin);
		model.addAttribute("user",user);
		
		model.addAttribute("products",products);
		model.addAttribute("numberProducts",numberProducts);
		
		return "/admin/admin-dashboard";
	}
	
	@RequestMapping("/admin-products")
	public String adminproducts(Model model) {
		Page<Product> series = productRepository.findGroupByType("Serie", new PageRequest(0, 4));
		Page<Product> movies = productRepository.findGroupByType("Movie", new PageRequest(0, 4));
		Page<Product> videogames = productRepository.findGroupByType("Videogame", new PageRequest(0, 4));
		Page<Product> products = productRepository.findAll(new PageRequest(0, 18));
		int numberProducts = products.getNumberOfElements();
		
		//Esto no debe ir asi, tiene que ir en una clase privada donde se llame cada constantemente.
		User user = userRepository.findById(3);
		User loggedAdmin = userRepository.findByName("Ruben");
		model.addAttribute("admin", loggedAdmin);
		model.addAttribute("user",user);
		
		model.addAttribute("products",products);
		model.addAttribute("series",series);
		model.addAttribute("movies",movies);
		model.addAttribute("videogames",videogames);
		model.addAttribute("numberProducts",numberProducts);
		return "/admin/admin-products";
	}
	
	@RequestMapping("/admin-user")
	public String adminuserprofile(Model model) {
		//Esto no debe ir asi, tiene que ir en una clase privada donde se llame cada constantemente.
		User user = userRepository.findById(3);
		User loggedAdmin = userRepository.findByName("Ruben");
		model.addAttribute("admin", loggedAdmin);
		model.addAttribute("user",user);
		
		Page<Product> products = productRepository.findAll(new PageRequest(0, 18));
		int numberProducts = products.getNumberOfElements();
		model.addAttribute("numberProducts",numberProducts);
		return "/admin/admin-user";
	}
}
