package com.planetofthetapes.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MasterController masterSession;
	
	
	@RequestMapping("product/{id}")
	public String product(Model model, @PathVariable Integer id, HttpServletRequest request) {
		
		masterSession.session(model, request);
		Product producto = productRepository.findOne(id);
		Page<Product> products = productRepository.findByGenreAndType(producto.getGenre(), producto.getType(),new PageRequest(0, 4));
		model.addAttribute("producto",producto);
		model.addAttribute("relate",products );
		return "product";
	}
	
	@RequestMapping("moreMovies")
	public String moreMovies(Model model, HttpServletRequest request) {

		masterSession.session(model, request);
		Page<Product> movies = productRepository.findGroupByType("Movie", new PageRequest(0, 4));
		model.addAttribute("item",movies);

		return "listItems";
	}
	
	/*@RequestMapping(value = "/moreGames")
	public String moreGames(Model model, @RequestParam int page) {

		Page<Resource> revistas = resourceRepository.findByResourceType(type, new PageRequest(page, 2));
		model.addAttribute("items", revistas);

		return "listItemsPage";
	}*/
	
	/*@RequestMapping(value = "/moreMovies")
	public String moreMovies(Model model, @RequestParam int page) {

		Page<Resource> movies = resourceRepository.findByResourceType(type, new PageRequest(page, 2));
		model.addAttribute("items", revistas);

		return "listItemsPage";
	}*/
}
