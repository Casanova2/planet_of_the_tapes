package com.planetofthetapes.controller;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

import antlr.collections.List;
import javassist.bytecode.Descriptor.Iterator;

@Controller
public class CartController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private POrderRepository pedidoRepository;
	@Autowired
	private MasterController masterSession;
	
	@Autowired User user;
	
	@RequestMapping("/cart")
	public String cart(Model model,HttpServletRequest request) {
		masterSession.session(model, request);
		
		return "cart";
	}
	
	@RequestMapping("/checkout")
	public String checkout(Model model,HttpServletRequest request) {
		masterSession.session(model, request);		
		
		return "checkout";
	}
	
	@RequestMapping("/{id}/buy")
	public String reserveResource(Model model, @PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
		double total = 0.0;
		Product p = productRepository.findOne(id);
		System.out.println(p.toString()+"hola joder ");
		//if(userRepository.findByName(request.getUserPrincipal().getName()) != null) { //Si estas logueado
			Pedido actual = new Pedido("","","",0.0,user);
			Boolean inProgress = false;
			
		if(user.getOrders()!=null) {
			for(Pedido ped: user.getOrders()) {
				if(ped.getState() == "progress") {
					inProgress = true;
					actual = ped;
					actual.addProduct(p); //aniadimos producto
					total = actual.getTotal() + p.getPbuy(); //sacamos el precio total antiguo
					actual.setTotal(total);//aniadimos el precio total con el nuevo producto
				}
			}
			if(!inProgress) {
				actual= new Pedido("progress","","",0.0,user);
				actual.addProduct(p);
			}
			model.addAttribute("productsOrder",actual.getProducts());
			
		}else {
				actual= new Pedido("progress","","",0.0,user);
				actual.addProduct(p);
			    user.addOrder(actual);
				
		}
		System.out.println(user.getOrders()+ "kjkjhkl");
		System.out.println(actual.getProducts().toString());
		model.addAttribute("productsOrder",actual.getProducts());
		return "redirect:/";
	}
}
