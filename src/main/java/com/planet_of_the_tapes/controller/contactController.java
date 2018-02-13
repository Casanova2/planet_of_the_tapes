package com.planet_of_the_tapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planet_of_the_tapes.entity.Product;
import com.planet_of_the_tapes.repository.ProductRepository;

@Controller
public class contactController {
	
	
	@RequestMapping("/contact")
	public String contact(Model model, HttpServletRequest request) {
		return "contact";
	}

}
