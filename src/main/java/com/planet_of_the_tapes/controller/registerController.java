package com.planet_of_the_tapes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.planet_of_the_tapes.entity.User;
import com.planet_of_the_tapes.repository.UserRepository;


@Controller
public class registerController {
	
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

		User user = new User(name, password, dni, email, telephone, address,"", "ROLE_ADMIN");
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
