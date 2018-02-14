package com.planet_of_the_tapes.controller;

import java.util.ArrayList;
import java.util.Iterator;

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
public class mainController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private masterController masterSession;
	
	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {
		
		masterSession.session(model, request);
		
		Page<Product> series = productRepository.findGroupByType("Serie", new PageRequest(0, 4));
		Page<Product> movies = productRepository.findGroupByType("Movie", new PageRequest(0, 4));
		Page<Product> videogames = productRepository.findGroupByType("Videogame", new PageRequest(0, 4));
		model.addAttribute("series",series);
		model.addAttribute("movies",movies);
		model.addAttribute("videogames",videogames);
		return "index";
	}
}
