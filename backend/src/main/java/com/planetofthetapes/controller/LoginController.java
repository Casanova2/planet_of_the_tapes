package com.planetofthetapes.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController extends MasterService {


	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		return "login";
	}
	
	@RequestMapping("/signIn")
	public String signin(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		redirectAttrs.addFlashAttribute("error","Your username or password is incorrect. If you aren´t register yet, please register now.");
		return "redirect:/login";
	}
	
	@RequestMapping("/loginError")
	public String loginError(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		model.addAttribute("error", true);	
		model.addAttribute("unlogged", true);
		model.addAttribute("profile", true);
		redirectAttrs.addFlashAttribute("error","Please register now.");
		return "login";
	}
}
