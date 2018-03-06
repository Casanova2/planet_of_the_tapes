package com.planetofthetapes.restController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.planetofthetapes.restController.ProductRestController.ProductDetails;

@RestController
@RequestMapping("/api")
public class PlistRestController extends MasterService {
	
	public interface ProductDetails extends Product.Basic, Product.OrderRelation{}
	public interface PackDetails extends Pack.Basic, Pack.ProductRelation{}
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PackRepository packRepository;
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/mplist", method=RequestMethod.GET)
    public ResponseEntity<List<Product>> mplistRest(Model model, int enlace, HttpServletRequest request, RedirectAttributes redirectAttrs){
		this.session(model, request, redirectAttrs);
		
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
	@RequestMapping(value="/mplist/packs", method=RequestMethod.GET)
    public ResponseEntity<List<Pack>> mplistpackRest(Model model, @RequestBody int enlace, HttpServletRequest request, RedirectAttributes redirectAttrs){
		this.session(model, request, redirectAttrs);

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
	@RequestMapping(value = "/loadmore", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> moreProductsRest(Model model, @RequestBody int page, @RequestBody String type, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		List<Product> products = productRepository.findGroupByType(type);
		if (!products.equals(null)){
			return new ResponseEntity<>(products, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value = "/loadmore/packs", method=RequestMethod.GET)
	public ResponseEntity<List<Pack>> morePacksRest(Model model, @RequestBody int page, @RequestBody String type, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		List<Pack> packs = packRepository.findAll();
		if (!packs.equals(null)){
			return new ResponseEntity<>(packs, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
