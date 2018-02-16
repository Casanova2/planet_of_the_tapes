package com.planet_of_the_tapes.controller;

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

import com.planet_of_the_tapes.entity.Product;
import com.planet_of_the_tapes.entity.User;
import com.planet_of_the_tapes.repository.ProductRepository;
import com.planet_of_the_tapes.repository.UserRepository;

@Controller
public class plistController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private masterController masterSession;
	
	@RequestMapping("/mostrar")
    public String mostrar(Model model, @RequestParam(value="series", required=false) String series,
    	@RequestParam (value="movies", required=false) String movies,
    	@RequestParam (value="videogames", required=false) String videogames){
        
		if(series != null) {
			model.addAttribute("products", productRepository.findGroupByType("Series"));
		}
		if(movies != null) {
			model.addAttribute("products", productRepository.findGroupByType("Movies"));
		}
		if(videogames != null){
			model.addAttribute("products", productRepository.findGroupByType("Videogames"));
		}
        
        return "plist";
    }
	
	@RequestMapping("/mostrarplist")
    public String mostrarplist(Model model, @RequestParam int enlace){
        
		if(enlace == 1) {
			model.addAttribute("products", productRepository.findGroupByType("Series"));
		}
		if(enlace == 2) {
			model.addAttribute("products", productRepository.findGroupByType("Movies"));
		}
		if(enlace == 3){
			model.addAttribute("products", productRepository.findGroupByType("Videogames"));
		}
        
        return "plist";
    }
}
