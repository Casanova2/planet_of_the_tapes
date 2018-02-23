package com.planetofthetapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.Product;
import com.planetofthetapes.repository.ProductRepository;

@Controller
public class ContactController extends MasterService{
	
	@RequestMapping("/contact")
	public String contact(Model model, HttpServletRequest request,RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		return "contact";
	}

}
