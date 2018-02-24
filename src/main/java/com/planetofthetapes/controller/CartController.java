package com.planetofthetapes.controller;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class CartController extends MasterService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private POrderRepository POrderRepository;
	@Autowired
	private SimpleEmailController emailcont;
	
	@Autowired User user;
	
	@RequestMapping("/cart")
	public String cart(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		return "cart";
	}
	
	@RequestMapping("/checkout")
	public String checkout(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);		
		
		return "selectpay";
	}
	@RequestMapping("/selectpay")
	public String selectpay(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs, String name, @RequestParam String email,@RequestParam String to,@RequestParam String subject,@RequestParam String body, @RequestParam String from)throws Exception {
		this.session(model, request, redirectAttrs);
		redirectAttrs.addFlashAttribute("success","Your order was processed. We sent you an email with the infomation of your shipment");
		emailcont.sendEmail(name, email, to, subject, body,from);
		redirectAttrs.addFlashAttribute("success","Your order was processed. We sent you an email with the infomation of your shipment");
		return "orderdata";
	}
	
	@RequestMapping("/{id}/buy")
	public String reserveResource(Model model, @PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		
			double total = 0.0;
			Product p = productRepository.findOne(id);
			Boolean inProgress = false;
		if(p.getStock() > 0) {		
				POrder actual = POrderRepository.findByState("progress");
				if(actual != null) {
					actual.addProduct(p); //aniadimos producto
					p.setStock(p.getStock()-1);
					total = actual.getTotal() + p.getPbuy(); //sacamos el precio total antiguo
					actual.setTotal(total);//aniadimos el precio total con el nuevo produc
					POrderRepository.save(actual);
					
					inProgress = true;
					
					ArrayList<POrder> listPOrders = new ArrayList<POrder>();
					listPOrders.add(actual);
					user.setOrders(listPOrders);
					
					model.addAttribute("productsOrder",actual.getProducts());
				}
				
				
				if(!inProgress) {
					actual= new POrder("progress","","",0.0);
					actual.getProducts().add(p);
					p.setStock(p.getStock()-1);
					ArrayList<POrder> listPOrders = new ArrayList<POrder>();
					listPOrders.add(actual);
					user.setOrders(listPOrders);
					POrderRepository.save(actual);
					
					model.addAttribute("productsOrder",actual.getProducts());
				}
				redirectAttrs.addFlashAttribute("success","Product added to the cart correctly.");
				return "redirect:/";
		}else{
			redirectAttrs.addFlashAttribute("error","Product out of stock.");
			return "redirect:/";
		}		
	}
	
	@RequestMapping("/{id}/remove")
	public String removeResource(Model model, @PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		POrder actual = POrderRepository.findByState("progress");
		int cont=0;
		for(Product posible: actual.getProducts()) {
			
			if(posible.getId() == id) {
				System.out.println(actual.getProducts().toString());
				System.out.println("REMOVEEEEEEE "+ posible.getId() + " " + id + " " + cont);
				actual.getProducts().remove(cont);
				POrderRepository.save(actual);
			}
			cont++;
		}
		return "redirect:/cart";
	}
}
