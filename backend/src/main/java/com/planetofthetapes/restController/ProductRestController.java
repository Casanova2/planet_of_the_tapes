package com.planetofthetapes.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductRestController{
	
	public interface ProductDetails extends Product.Basic, Product.OrderRelation{}
	public interface PackDetails extends Pack.Basic, Pack.ProductRelation, Product.Basic{}
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PackRepository packRepository;
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> product(@PathVariable Integer id) {
	
		Product producto = productRepository.findOne(id);
		if (!producto.equals(null)){
			return new ResponseEntity<>(producto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/product/{id}/related", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> productRelated(@PathVariable Integer id) {
		
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
	public ResponseEntity<Pack> pack(@PathVariable Integer id) {
		
		Pack pack = packRepository.findOne(id);
		List<Product> l = new ArrayList(pack.getProducts());
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
	public ResponseEntity<List<Pack>> packRelated(@PathVariable Integer id) {

		Pack pack = packRepository.findOne(id);
		List<Pack> packs = packRepository.findAll();
		List<Product> l = new ArrayList(pack.getProducts());
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
	public ResponseEntity<List<Product>> moreMovies() {
		
		List<Product> movies = productRepository.findGroupByType("Movie");
		
		if (!movies.equals(null)){
			return new ResponseEntity<>(movies, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
