package com.planetofthetapes.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@Controller
public class PlistController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MasterController masterSession;
	
	@RequestMapping("/mplist")
    public String mostrarplist(Model model, @RequestParam int enlace, HttpServletRequest request){
		
		masterSession.session(model, request);
		
		//model.addAttribute("products", productRepository.findGroupByType(enlace));
		String type = "";
		if(enlace == 1) {
			type = "Series";
			Page<Product> series = productRepository.findGroupByType("Series", new PageRequest(0, 8));
			model.addAttribute("products", series);
		}
		if(enlace == 2) {
			type = "Movies";
			Page<Product> products = productRepository.findGroupByType("Movies", new PageRequest(0, 8));
			model.addAttribute("products", products);
		}
		if(enlace == 3){
			type = "Videogames";
			Page<Product> products = productRepository.findGroupByType("Videogames", new PageRequest(0, 8));
			model.addAttribute("products", products);
		}
        
		model.addAttribute("type", type);
        return "plist";
    }
	
	@RequestMapping(value = "/loadmore")
	public String moreBooks(Model model, @RequestParam int page, @RequestParam String type, HttpServletRequest request) {
		
		masterSession.session(model, request);
		Page<Product> products = productRepository.findGroupByType(type, new PageRequest(page, 4));
		model.addAttribute("products", products);
		
		System.out.println(products);

		return "listItems";
	}
}
