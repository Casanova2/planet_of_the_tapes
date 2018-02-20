package com.planetofthetapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.UserRepository;


@Controller
public class RegisterController {
	
	@Autowired
	private UserRepository userRepository;

	
	@RequestMapping("/register")
	public String register(Model model, HttpServletRequest request) {

		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER"))
			return "redirect:/";
		model.addAttribute("unlogged", true);

		return "register";
	}

	@RequestMapping("/register/add")
	public String addUser(@RequestParam String name, @RequestParam String password, @RequestParam String dni, @RequestParam String email, @RequestParam String telephone,
			@RequestParam String address, String avatar) {

		User user = new User(name, password, dni, email, telephone, address,"", "ROLE_USER");
		try {
			userRepository.save(user);
		} catch (Exception e) {
			return "redirect:/registerError";
		}

		return "redirect:/";
	}

	@RequestMapping("/registerError")
	public String registerError(Model model) {

		model.addAttribute("unlogged", true);
		model.addAttribute("alreadyReg", true);

		return "register";
	}


}
