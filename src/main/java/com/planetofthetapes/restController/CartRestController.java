package com.planetofthetapes.restController;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.component.UserComponent;
import com.planetofthetapes.controller.MasterService;
import com.planetofthetapes.controller.SimpleEmailController;
import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;
import com.planetofthetapes.restController.AdminRestController.OrderDetails;

import antlr.collections.List;
import javassist.bytecode.Descriptor.Iterator;

@Controller
@RequestMapping("/api")
public class CartRestController extends MasterService {
	
	public interface ProductDetails extends Product.Basic, Product.OrderRelation, Product.PackRelation{}
	public interface PackDetails extends Pack.Basic, Pack.ProductRelation, Product.Basic{}
	public interface UserDetails extends User.Basic, User.OrderRelationUser, POrder.Basic{}
	public interface OrderDetails extends POrder.Basic, POrder.ProductRelationOrder, POrder.PackRelation, Product.Basic, Pack.Basic{}
	
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PackRepository packRepository;
	@Autowired
	private POrderRepository POrderRepository;
	
	double totalCart;
	
	@JsonView(OrderDetails.class)
	@RequestMapping(value="/checkout/{id}", method=RequestMethod.PUT)
	public ResponseEntity<POrder> checkoutRest(Model model,HttpServletRequest request,@PathVariable Integer id,@RequestBody POrder orderUpdate, RedirectAttributes redirectAttrs,HttpServletResponse response) throws IOException, ServletException {
		this.session(model, request, redirectAttrs);
		if (request.authenticate(response)) {
				POrder order = POrderRepository.findById(id);
				if(order!=null) {
			    	order.setState(orderUpdate.getState());
			    	POrderRepository.save(order);
			    	return new ResponseEntity<>(order, HttpStatus.OK);
			    }else {
			    	return new ResponseEntity<>( HttpStatus.NOT_FOUND);
			    }	
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
			
	}

	@JsonView(OrderDetails.class)
	@RequestMapping(value="/{id}/{id2}/buy",method=RequestMethod.PUT)
	public ResponseEntity<POrder> reserveRestResource(Model model, @PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttrs,@PathVariable Integer id2,@RequestBody POrder orderUpdate) {
			
		//&& p.getStock() > 0
			double total = 0.0;
			Product p = productRepository.findOne(id2);
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			System.out.println(user.getName());
			System.out.println(user.hasOrders().toString());
			System.out.println(p.getName());
			
			
				if(user.hasOrders()&& p.getStock()> 0) {
							POrder order = POrderRepository.findById(id);
							order.addProduct(p); 
							p.setStock(p.getStock()-1);
							total = order.getTotal() + p.getPbuy(); 
							order.setTotal(total);
							POrderRepository.save(order);
							ArrayList<POrder> listPOrders = new ArrayList<POrder>();
							listPOrders.add(order);
							user.setOrders(listPOrders);
							return new ResponseEntity<>(order, HttpStatus.OK);
				}else {
					return new ResponseEntity<>( HttpStatus.NOT_FOUND);
				}			
	}
	@JsonView(OrderDetails.class)
	@RequestMapping(value="/{id2}/buy",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<POrder> createNewRestOrder(Model model, HttpServletRequest request,
			RedirectAttributes redirectAttrs,@PathVariable Integer id2,@RequestBody POrder order) {
			
		//&& p.getStock() > 0
			double total = 0.0;
			Product p = productRepository.findOne(id2);
			User user = userRepository.findByName(request.getUserPrincipal().getName());	
				if(p.getStock()> 0) {
					POrder neworder= new POrder(order.getState(),order.getPay(),order.getType(),order.getTotal());
					neworder.getProducts().add(p);
					p.setStock(p.getStock()-1);
					total = neworder.getTotal() + p.getPbuy();
					neworder.setTotal(total);
					user.addOrder(neworder);
					POrderRepository.save(neworder);
					model.addAttribute("totalOrder",neworder.getTotal());
					return new ResponseEntity<>(neworder,HttpStatus.OK);
				}else {
					return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			
	}
	
	@RequestMapping("/pack/{id}/buy")
	public String packbuy(Model model, @PathVariable Integer id, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			
			
			double total = 0.0;
			Pack pack = packRepository.findById(id);
			ArrayList<Product> l = new ArrayList(pack.getProducts());
			
			int cont = 0;
			while(cont<3) {
				Product p = l.get(cont);
				
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
				}else{
					redirectAttrs.addFlashAttribute("error","Product out of stock.");
					return "redirect:/cart";
				}
				cont++;
			}
			return "redirect:/";
		}else {
			redirectAttrs.addFlashAttribute("error","Please, login or register to buy products");
			return "redirect:/login";
		}
	}	
	
	@RequestMapping(value="/{id}/{id1}/remove",method=RequestMethod.PUT)
	@JsonView(OrderDetails.class)
	public ResponseEntity<POrder> removeResource(Model model, @PathVariable Integer id,@PathVariable Integer id1,@RequestBody POrder orderUpdate, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		User user = userRepository.findByName(request.getUserPrincipal().getName());
		Product p = productRepository.findById(id1);
		POrder order = POrderRepository.findById(id);
		if(order!=null) {
			order.getProducts().remove(p);
			order.setTotal(order.getTotal()-p.getPbuy());
			p.setStock(p.getStock()+1);
			POrderRepository.save(order);	
			return new ResponseEntity<>(order,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
}
}
