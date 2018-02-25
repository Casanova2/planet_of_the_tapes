package com.planetofthetapes.controller;

import java.security.Principal;
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
	
	double totalCart;
	 
	@RequestMapping("/cart")
	public String cart(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		return "cart";
	}
	
	@RequestMapping("/checkout")
	public String checkout(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs, String name,  String email,  String to,
			 String subject,String body, String from)throws Exception {
		this.session(model, request, redirectAttrs);
		if (name==null&&email==null&&to==null&&subject==null&&body==null&&from==null) {
				
			if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
				User user = userRepository.findByName(request.getUserPrincipal().getName());
			    ArrayList<POrder> listActualUSer = new ArrayList<POrder>(user.getOrders());
			    for(POrder o: listActualUSer) {
			    	if(o.getState().equals("progress")) {
			    		o.setState("completed");
			    		POrderRepository.save(o);
			    	}
			    }
			    
			    redirectAttrs.addFlashAttribute("success","Your order was processed. We sent you an email with the infomation of your shipment");
			    return "redirect:/selectpay";
			 }
		}
		
		redirectAttrs.addFlashAttribute("success","Your order was processed. We sent you an email with the infomation of your shipment");
		emailcont.sendEmail(name, email, to, subject, body,from);
		return "redirect:/";
	}
	
	@RequestMapping("/selectpay")
	 public String selectpay(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs, String name,  String email,  String to,
			 String subject,String body, String from)throws Exception {
		this.session(model, request, redirectAttrs);
		redirectAttrs.addFlashAttribute("success","Your order was processed. We sent you an email with the infomation of your shipment");
		return "/selectpay";
	}
	
	@RequestMapping("/{id}/buy")
	public String reserveResource(Model model, @PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			
			
			double total = 0.0;
			Product p = productRepository.findOne(id);
			if(p.getStock() > 0) {		
				
				if(user.hasOrders()) {
					ArrayList<POrder> listActualUSer = new ArrayList<POrder>(user.getOrders());
					for(POrder o: listActualUSer) {
						if(o.getState().equals("progress")) {
							o.addProduct(p); 
							p.setStock(p.getStock()-1);
							total = o.getTotal() + p.getPbuy(); 
							o.setTotal(total);
							POrderRepository.save(o);
							
							ArrayList<POrder> listPOrders = new ArrayList<POrder>();
							listPOrders.add(o);
							user.setOrders(listPOrders);
							model.addAttribute("totalOrder",o.getTotal());
							model.addAttribute("productsOrder",o.getProducts());
						}else {
							POrder op= new POrder("progress","Credit Card","",0.0);
							op.getProducts().add(p);
							p.setStock(p.getStock()-1);
							total = op.getTotal() + p.getPbuy();
							op.setTotal(total);
							user.addOrder(op);
							POrderRepository.save(op);
							model.addAttribute("totalOrder",op.getTotal());
						}
					}
				}else {
					POrder o= new POrder("progress","Credit Card","",0.0);
					o.getProducts().add(p);
					p.setStock(p.getStock()-1);
					total = o.getTotal() + p.getPbuy();
					o.setTotal(total);
					user.addOrder(o);
					POrderRepository.save(o);
					model.addAttribute("totalOrder",o.getTotal());
				}				
					
				redirectAttrs.addFlashAttribute("success","Product added to the cart correctly.");
				return "redirect:/";
			}else{
				redirectAttrs.addFlashAttribute("error","Product out of stock.");
				return "redirect:/cart";
			}
		}else {
			redirectAttrs.addFlashAttribute("error","Please, login or register to buy products");
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/{id}/remove")
	public String removeResource(Model model, @PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		User user = userRepository.findByName(request.getUserPrincipal().getName());

		ArrayList<POrder> listActualUSer = new ArrayList<POrder>(user.getOrders());
		for(POrder o: listActualUSer) {
			if(o.getState().equals("progress")) {
				int cont=0;
				java.util.Iterator<Product> it = o.getProducts().iterator();
				while(it.hasNext()) {
					Product posible=it.next();
					if(posible.getId() == id) {
						it.remove();
						o.setTotal(o.getTotal()-posible.getPbuy());
						posible.setStock(posible.getStock()+1);
						break;
					}
					cont++;
				}
			}
			model.addAttribute("totalOrder",o.getTotal());
			POrderRepository.save(o);
		}	
		
		return "redirect:/cart";
	}
}
