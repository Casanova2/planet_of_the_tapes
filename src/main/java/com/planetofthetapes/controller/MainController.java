package com.planetofthetapes.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

import antlr.collections.List;

@Controller
public class MainController extends MasterService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		Page<Product> series = productRepository.findGroupByType("Series", new PageRequest(0, 4));
		Page<Product> movies = productRepository.findGroupByType("Movies", new PageRequest(0, 4));
		Page<Product> videogames = productRepository.findGroupByType("Videogames", new PageRequest(0, 4));
		
		model.addAttribute("series",series);		
		model.addAttribute("movies",movies);
		model.addAttribute("videogames",videogames);
		return "index";
	}
}
