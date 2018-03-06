package com.planetofthetapes.restController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.controller.MasterService;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ProductRestController extends MasterService {
	
	public interface ProductDetails extends Product.Basic, Product.OrderRelation{}
	public interface PackDetails extends Pack.Basic, Pack.ProductRelation{}
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PackRepository packRepository;
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> product(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		Product producto = productRepository.findOne(id);
		Page<Product> products = productRepository.findByGenreAndType(producto.getGenre(), producto.getType(),new PageRequest(0, 4));
		if (!producto.equals(null)){
			return new ResponseEntity<>(producto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/product/{id}/related", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> productRelated(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		Product producto = productRepository.findOne(id);
		List<Product> products = productRepository.findByGenreAndType(producto.getGenre(), producto.getType());
		if (!products.equals(null)){
			return new ResponseEntity<>(products, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/pack/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pack> pack(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		Pack pack = packRepository.findOne(id);
		ArrayList<Product> l = new ArrayList(pack.getProducts());
		int cont = 0;
		double suma = 0;
		while(cont < 3) {
			Product p = l.get(cont);
			suma = suma + p.getPbuy();
			cont++;
		}
		
		if (!pack.equals(null)){
			return new ResponseEntity<>(pack, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/pack/{id}/related", method=RequestMethod.GET)
	public ResponseEntity<List<Pack>> packRelated(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		Pack pack = packRepository.findOne(id);
		List<Pack> packs = packRepository.findAll();
		ArrayList<Product> l = new ArrayList(pack.getProducts());
		int cont = 0;
		double suma = 0;
		while(cont < 3) {
			Product p = l.get(cont);
			suma = suma + p.getPbuy();
			cont++;
		}
		
		if (!packs.equals(null)){
			return new ResponseEntity<>(packs, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="moreMovies", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> moreMovies(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		List<Product> movies = productRepository.findGroupByType("Movie");
		
		if (!movies.equals(null)){
			return new ResponseEntity<>(movies, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
