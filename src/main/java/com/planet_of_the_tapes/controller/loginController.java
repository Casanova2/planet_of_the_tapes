package com.planet_of_the_tapes.controller;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.planet_of_the_tapes.repository.UserRepository;
import com.planet_of_the_tapes.entity.User;

@Controller
public class loginController {

	@Autowired
	private UserRepository userRepository;
	

	@RequestMapping("/logreg")
	public String show() {
		return "login";
	}
	
	@RequestMapping(value= "/register", method = RequestMethod.GET)
	public String preparation(Model model, String email, String password) {
		
		model.addAttribute("user", new User(email, password));
		
		return "login";
		
	}
	
	
	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {

		userRepository.save(user);
		
		return "index";
		
	}
	
	
}
