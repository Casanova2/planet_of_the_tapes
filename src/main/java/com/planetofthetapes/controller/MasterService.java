package com.planetofthetapes.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

import javassist.bytecode.Descriptor.Iterator;

public class MasterService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private POrderRepository POrderRepository;
	@Autowired
	private PackRepository packRepository;
	
	public void session (Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		int zero = 0;
		if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
			User loggedUser = userRepository.findByName(request.getUserPrincipal().getName());
			if(request.isUserInRole("ROLE_ADMIN")) {
				model.addAttribute("admin", true);
			}
			if(request.isUserInRole("ROLE_USER")) {
				model.addAttribute("user", true);
			}
			
			
			if(loggedUser.hasOrders()) {
				ArrayList<POrder> listActualUser = new ArrayList<POrder>(loggedUser.getOrders());
				
				for(POrder o: listActualUser) {
					if(o.getState().equals("progress")) {
						model.addAttribute("productsOrders", o.getProducts());
						model.addAttribute("sizeProducts",o.getProducts().size());
						model.addAttribute("totalOrder",o.getTotal());
					}
				}
			}
			
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
			
			this.numbers(model,request);
		} else {
			model.addAttribute("sizeProducts",zero);
			model.addAttribute("logged", false);
			model.addAttribute("profile", true);
		}
	}
	
	public void numbers (Model model,HttpServletRequest request) {
		User loggedUser = userRepository.findByName(request.getUserPrincipal().getName());
		model.addAttribute("numberProducts",productRepository.findAll().size());
		model.addAttribute("numberUsers",userRepository.findAll().size());
		model.addAttribute("numberOrders",POrderRepository.findAll().size());
		model.addAttribute("numberPacks",packRepository.findAll().size());
		model.addAttribute("numberOrderlist",loggedUser.getOrders().size());
	}
}
