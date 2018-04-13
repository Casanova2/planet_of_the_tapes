package com.planetofthetapes.restController;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;

@RestController
@RequestMapping("/api/mplist")
public class PlistRestController{
	
	public interface ProductDetails extends Product.Basic, Product.OrderRelation{}
	public interface PackDetails extends Pack.Basic, Pack.ProductRelation{}
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PackRepository packRepository;
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/", method=RequestMethod.GET)
    public ResponseEntity<List<Product>> mplistRest(Model model, int enlace){
		String type = "";
		if(enlace == 1) {
			type = "Series";
			List<Product> series = productRepository.findGroupByType("Series");
			if (!series.equals(null)){
				return new ResponseEntity<>(series, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		if(enlace == 2) {
			type = "Movies";
			List<Product> products = productRepository.findGroupByType("Movies");
			if (!products.equals(null)){
				return new ResponseEntity<>(products, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		if(enlace == 3){
			type = "Videogames";
			List<Product> products = productRepository.findGroupByType("Videogames");
			if (!products.equals(null)){
				return new ResponseEntity<>(products, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		model.addAttribute("type", type);
		
		List<Product> p = productRepository.findGroupByType(type);
		if (!p.equals(null)){
			return new ResponseEntity<>(p, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/packs", method=RequestMethod.GET)
    public ResponseEntity<List<Pack>> mplistpackRest(Model model, @RequestBody int enlace){
		if(enlace == 4) {
			String type = "Packs";
			List<Pack> packs = packRepository.findAll();
			if (!packs.equals(null)){
				return new ResponseEntity<>(packs, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		List<Pack> packs = packRepository.findAll();
		if (!packs.equals(null)){
			return new ResponseEntity<>(packs, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value = "/loadmore/{type}", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> moreProductsRest(@PathVariable String type) {
		
		List<Product> products = productRepository.findGroupByType(type);
		if (!products.equals(null)){
			return new ResponseEntity<>(products, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value = "/loadmore/packs", method=RequestMethod.GET)
	public ResponseEntity<List<Pack>> morePacksRest() {
		
		List<Pack> packs = packRepository.findAll();
		if (!packs.equals(null)){
			return new ResponseEntity<>(packs, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
