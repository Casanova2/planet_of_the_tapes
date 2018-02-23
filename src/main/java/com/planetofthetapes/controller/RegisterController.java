package com.planetofthetapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.UserRepository;


@Controller
public class RegisterController extends MasterService {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/register")
	public String register(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {

		this.session(model, request, redirectAttrs);

		return "register";
	}

	@RequestMapping("/register/add")
	public String addUser(@RequestParam String name, @RequestParam String password, @RequestParam String dni, @RequestParam String email, @RequestParam String telephone,
			@RequestParam String address, String avatar, Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		User user = new User(name, password, dni, email, telephone, address,"", "ROLE_USER");
		try {
			userRepository.save(user);
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("error","Duplicated user.");
			return "redirect:/registerError";
		}
		
		redirectAttrs.addFlashAttribute("success","User registered succesfully. Please login");
		return "redirect:/login";
	}

	@RequestMapping("/registerError")
	public String registerError(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);

		model.addAttribute("unlogged", true);
		model.addAttribute("alreadyReg", true);

		return "register";
	}


}
