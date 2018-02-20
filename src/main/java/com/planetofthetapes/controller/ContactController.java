package com.planetofthetapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planetofthetapes.entity.Product;
import com.planetofthetapes.repository.ProductRepository;

@Controller
public class ContactController {
	
	
	@RequestMapping("/contact")
	public String contact(Model model, HttpServletRequest request) {
		return "contact";
	}

}
