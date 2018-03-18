package com.planetofthetapes.restController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class IndexRestController {

	public interface ProductDetails extends Product.Basic, Product.OrderRelation, Product.PackRelation{}
	public interface PackDetails extends Pack.Basic, Pack.ProductRelation, Product.Basic{}
	public interface UserDetails extends User.Basic, User.OrderRelationUser, POrder.Basic{}
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PackRepository packRepository;
	@Autowired
	private UserRepository userRepository;
	
	@JsonView(UserDetails.class)
	@RequestMapping(value="/index/user", method=RequestMethod.GET)
	public User ShowLoggedUser(HttpServletResponse response, HttpServletRequest request){
			
		User user = userRepository.findByName(request.getUserPrincipal().getName());
			
		return user;
	}
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ResponseEntity <List<List<Product>>> indexRestProduct() {
		
		List<Product> series = productRepository.findGroupByType("Series");
		List<Product> movies = productRepository.findGroupByType("Movies");
		List<Product> videogames = productRepository.findGroupByType("Videogames");
	
		List<List<Product>> lp = new ArrayList<List<Product>>();
		lp.add(series);
		lp.add(movies);
		lp.add(videogames);
		return new ResponseEntity<>(lp, HttpStatus.OK);
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/index/packs", method=RequestMethod.GET)
	public ResponseEntity <List<Pack>> indexRestPack(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		List<Pack> packs = packRepository.findAll();

		return new ResponseEntity<>(packs, HttpStatus.OK);
	}
}
