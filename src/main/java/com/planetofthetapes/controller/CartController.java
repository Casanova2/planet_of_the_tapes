package com.planetofthetapes.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.Pedido;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.PedidoRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@Controller
public class CartController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private MasterController masterSession;
	
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
		
		double total = 0;
		Product p = productRepository.findOne(id);
		if(userRepository.findByName(request.getUserPrincipal().getName()) != null) { //Si estas logueado
			User loggedUser = userRepository.findByName(request.getUserPrincipal().getName());
			if(loggedUser.getPedidoActual() != null) {
				Pedido pedidoexistente = loggedUser.getPedidoActual();
				pedidoexistente.addProduct(p); //aniadimos producto
				total = pedidoexistente.getTotal() + p.getPbuy(); //sacamos el precio total antiguo
				pedidoexistente.setTotal(total);//aniadimos el precio total con el nuevo producto
			}else {
				Pedido pedido = new Pedido(loggedUser);
				pedido.addProduct(p);
			}
		}/*else { //Si eres un usario no logueado
			Pedido pedido = new Pedido(loggedUser);
			pedido.addProduct(p);
		}*/
				
		return "redirect:/";
	}
}
