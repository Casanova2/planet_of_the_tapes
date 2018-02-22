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
	private POrderRepository POrderRepository;
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
		//System.out.println(p.toString()+"hola joder ");
		//if(userRepository.findByName(request.getUserPrincipal().getName()) != null) { //Si estas logueado
		
		Boolean inProgress = false;
		//POrder actual = POrderRepository.findByState("progress");
		//System.out.println(actual);
		//if(actual!=null) {
			for(POrder ped: user.getOrders()) {
				if(ped.getState().equals("progress")) {
					inProgress = true;
					POrder actual = ped;
					actual.addProduct(p); //aniadimos producto
					total = actual.getTotal() + p.getPbuy(); //sacamos el precio total antiguo
					actual.setTotal(total);//aniadimos el precio total con el nuevo produc
					System.out.println(actual.getProducts().toString());
					model.addAttribute("productsOrder",actual.getProducts());
					break;
				}
			}
			
			
			if(!inProgress) {
				//actual= new POrder("progress","","",0.0,user);
				//actual.addProduct(p)
				POrder actual= new POrder("progress","","",0.0);
				actual.getProducts().add(p);
				ArrayList<POrder> listPOrders = new ArrayList<POrder>();
				System.out.println("HOLAA111111");
				listPOrders.add(actual);
				user.setOrders(listPOrders);
				POrderRepository.save(actual);
				//System.out.println(actual);
				System.out.println(actual.getProducts().toString());
				System.out.println(user.getOrders().toString()+ "HOLAAA22222");
			    //user.addOrder(actual);
				System.out.println();
				model.addAttribute("productsOrder",actual.getProducts());
			}
		
		return "redirect:/";
	}
}
