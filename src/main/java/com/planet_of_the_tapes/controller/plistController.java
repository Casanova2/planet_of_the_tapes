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
	
	/*@RequestMapping("/plist")
	public String plist(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		
		Page<Product> products = productRepository.findAll(new PageRequest(0, 4));
		model.addAttribute("products",products);
		return "plist";
	}*/
	
	/*@RequestMapping("/mostrarplist")
	@RequestMapping(method = RequestMethod.POST)
	public String mostrar_plist(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		
		Page<Product> products = productRepository.findAll(new PageRequest(0, 4));
		model.addAttribute("products",products);
		return "plist";
	}*/
	
	/*@RequestMapping(value="/mostrarplist", method=RequestMethod.POST)
	@ResponseBody
	public String mostrar_plist(Model model, HttpServletRequest request){
		//masterSession.session(model, request);
		
		
		
		Page<Product> products = productRepository.findAll(new PageRequest(0, 4));
		model.addAttribute("products",products);
		
		model.addAttribute("id","hola");
		return "plist";
	}*/

	@RequestMapping(value = "/mostrarplistseries", method = RequestMethod.POST)
    public String mostrarplist(@Valid @ModelAttribute("series")Product p, ModelMap model) {
        String name = "Series";
        model.addAttribute("name", name);
        List<Product> serie = productRepository.findGroupByType("Serie");
        model.addAttribute("products", serie);
        return "plist";
    }
	@RequestMapping(value = "/mostrarplistmovies", method = RequestMethod.POST)
    public String mostrarplistmovies(@Valid @ModelAttribute("movies")Product p, ModelMap model) {
        String name = "Movies";
        model.addAttribute("name", name);
        List<Product> movie = productRepository.findGroupByType("Movie");
        model.addAttribute("products", movie);
        return "plist";
    }
	@RequestMapping(value = "/mostrarplistvideogames", method = RequestMethod.POST)
    public String mostrarplistvideogames(@Valid @ModelAttribute("videogames")Product p, ModelMap model) {
        String name = "Videogames";
        model.addAttribute("name", name);
        List<Product> videogame = productRepository.findGroupByType("Videogame");
        model.addAttribute("products", videogame);
        return "plist";
    }
}
