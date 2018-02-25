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

import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@Controller
public class ProductController extends MasterService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PackRepository packRepository;
	
	
	@RequestMapping("product/{id}")
	public String product(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		Product producto = productRepository.findOne(id);
		Page<Product> products = productRepository.findByGenreAndType(producto.getGenre(), producto.getType(),new PageRequest(0, 4));
		model.addAttribute("producto",producto);
		model.addAttribute("relate",products );
		return "product";
	}
	
	@RequestMapping("pack/{id}")
	public String pack(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		Pack pack = packRepository.findOne(id);
		model.addAttribute("pack", pack);
		model.addAttribute("relate",packRepository.findAll());
		model.addAttribute("productspack", pack.getProducts());
		return "product";
	}
	
	@RequestMapping("moreMovies")
	public String moreMovies(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		Page<Product> movies = productRepository.findGroupByType("Movie", new PageRequest(0, 4));
		model.addAttribute("item",movies);

		return "listItems";
	}
}
