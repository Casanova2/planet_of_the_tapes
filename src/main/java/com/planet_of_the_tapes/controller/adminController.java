package com.planet_of_the_tapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@Autowired
	private masterController masterSession;
	
	@RequestMapping("/admin")
	public String admin(Model model,HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("products",productRepository.findAll());
		model.addAttribute("users",userRepository.findAll());
		
		
		return "/admin/admin-dashboard";
	}
	
	@RequestMapping("/admin-products")
	public String adminproducts(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		
		
		Page<Product> series = productRepository.findGroupByType("Serie", new PageRequest(0, 4));
		Page<Product> movies = productRepository.findGroupByType("Movie", new PageRequest(0, 4));
		Page<Product> videogames = productRepository.findGroupByType("Videogame", new PageRequest(0, 4));

		model.addAttribute("series",series);
		model.addAttribute("movies",movies);
		model.addAttribute("videogames",videogames);
		
		model.addAttribute("products",productRepository.findAll());
		
		
		return "/admin/admin-products";
	}
	@RequestMapping("/admin-userList")
	public String adminUserList(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("users",userRepository.findAll());
		
		return "/admin/admin-userList";
	}
	
	@RequestMapping("/admin-user")
	public String adminuserprofile(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("products",productRepository.findAll());
	
		
		return "/admin/admin-user";
	}
	
<<<<<<< HEAD
	
	@RequestMapping("/add-product")
	public String addUser(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("products",productRepository.findAll());
	
		return "/admin/admin-add-user";
=======
	@RequestMapping("/admin-products-add")
	public String addProduct(Model model, HttpServletRequest request) {

		masterSession.session(model, request);

		return "admin/admin-add-product";
>>>>>>> 77eec6b0f6e6cbb5c548e8737edc0f4b3f5df400
	}
	
	@RequestMapping("/admin-add-product")

	public String addProduct(Model model, @RequestParam String name, @RequestParam String description, @RequestParam String type,
			@RequestParam String genre, @RequestParam int stock, @RequestParam double pbuy,
			@RequestParam double prent, @RequestParam int score,@RequestParam String trailer,@RequestParam String director,
			@RequestParam String cast, @RequestParam int year, @RequestParam String urlimg, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
			masterSession.session(model, request);
				Product product = new Product(name, description, type, genre, stock, pbuy, prent, score, trailer, director,
						cast, year, urlimg);
				try {
					productRepository.save(product);
				} catch (Exception e) {
					return "redirect:/admin-products/addError";
				}
				redirectAttrs.addFlashAttribute("messages", "Añadido nuevo producto.");

				return "redirect:/admin-products";
	}
	
	@RequestMapping("/admin/users/add/action")
	public String addUserAction(@RequestParam String name ,HttpServletRequest request,
			RedirectAttributes redirectAttrs) {

		try {
		} catch (Exception e) {
			return "redirect:/admin-product/addError";
		}
		redirectAttrs.addFlashAttribute("messages", "Añadido nuevo usuario.");

		return "redirect:/admin-product";
	}
}
