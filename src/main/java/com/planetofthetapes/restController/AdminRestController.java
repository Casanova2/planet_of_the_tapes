package com.planetofthetapes.restController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.controller.MasterService;
import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;
import com.planetofthetapes.restController.PlistRestController.ProductDetails;

import java.util.List;



@RestController
@RequestMapping("/api")
public class AdminRestController extends MasterService{
	
	public interface ProductDetails extends Product.Basic, Product.OrderRelation{}
	public interface PackDetails extends Pack.Basic, Pack.ProductRelation, Product.Basic{}
	public interface UserDetails extends User.Basic, User.OrderRelationUser, POrder.Basic{}
	public interface OrderDetails extends POrder.Basic, POrder.ProductRelationOrder, POrder.PackRelation, Product.Basic, Pack.Basic{}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private POrderRepository porderRepository;
	
	@Autowired
	private PackRepository packRepository;

	
	
	@RequestMapping("/admin")
	public String admin(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		model.addAttribute("products",productRepository.findAll());
		model.addAttribute("users",userRepository.findAll());
		model.addAttribute("orders", porderRepository.findAll());
		model.addAttribute("packs", packRepository.findAll());
		
		
		return "/admin/admin-dashboard";
	}
	
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/admin-products", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> adminproductsRest(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		List<Product> products = productRepository.findAll();
		if (!products.equals(null)){
			return new ResponseEntity<>(products, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(OrderDetails.class)
	@RequestMapping(value="/admin-orderlist", method=RequestMethod.GET)
	public ResponseEntity<List<POrder>> orderlistRest(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		List<POrder> orders = porderRepository.findAll();
		if (!orders.equals(null)){
			return new ResponseEntity<>(orders, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin-userList", method=RequestMethod.GET)
	public ResponseEntity<List<User>> adminUserListRest(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		List<User> users = userRepository.findAll();
		if (!users.equals(null)){
			return new ResponseEntity<>(users, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/admin-packlist", method=RequestMethod.GET)
	public ResponseEntity<List<Pack>> adminPackListRest(Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttrs) throws IOException, ServletException {
		this.session(model, request, redirectAttrs);
		
		if (request.authenticate(response)) {
			List<Pack> packs = packRepository.findAll();
			if (!packs.equals(null)){
				return new ResponseEntity<>(packs, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
			
	}
	
	@JsonView(OrderDetails.class)
	@RequestMapping(value="/user-orderlist", method=RequestMethod.GET)
	public ResponseEntity<List<POrder>> userOrderListRest(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		User user = userRepository.findByName(request.getUserPrincipal().getName());
		List<POrder> ousers = user.getOrders();
		if (!ousers.equals(null)){
			return new ResponseEntity<>(ousers, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping("/admin-add-pack")
	public String addPack(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		model.addAttribute("products",productRepository.findAll());
		
		model.addAttribute("packs",packRepository.findAll());
		
		return "admin/admin-add-pack-action";
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/admin-add-pack-action", method=RequestMethod.POST)
	public Pack addPackActionRest(Model model, HttpServletRequest request, @RequestParam String namePack, @RequestParam String nameP1,
			@RequestParam String nameP2, @RequestParam String nameP3, @RequestParam Integer price, @RequestParam MultipartFile img, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		ArrayList<Product> l = new ArrayList<Product>();
		
		Product product1 = productRepository.findByName(nameP1);
		Product product2 = productRepository.findByName(nameP2);
		Product product3 = productRepository.findByName(nameP3);
		
		l.add(product1);
		l.add(product2);
		l.add(product3);
		
		Pack p = new Pack(namePack, price, l);
		packRepository.save(p);
		
		String imgName = "pack" + p.getId() + ".jpg";
		if (!img.isEmpty()) {
			try {
				File imgFolder = new File("src/main/resources/static/img/ProductImages");
				if (!imgFolder.exists()) {
					imgFolder.mkdirs();
				}
				File uploadedImage = new File(imgFolder.getAbsolutePath(), imgName);
				img.transferTo(uploadedImage);
			} catch (Exception e) {
			}
			p.setImg(imgName);
			packRepository.save(p);
		}
		redirectAttrs.addFlashAttribute("success", "Pack added successfully");
		return p;
	}
	
	@RequestMapping("/admin-modify-pack/{id}")
	public String modifyPack(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
	
		this.session(model, request, redirectAttrs);
		
		model.addAttribute("pack", packRepository.findById(id));
		return "/admin/admin-modify-pack";
	}
	
	@RequestMapping("/admin/pack/modify/{id}") // modify
	public String modifyPackAction(Model model, @RequestParam Integer id, @RequestParam String namePack, @RequestParam String nameP1,
			@RequestParam String nameP2, @RequestParam String nameP3, @RequestParam Integer price,
			HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
			this.session(model, request, redirectAttrs);
			
			Pack pack = packRepository.findById(id);
			
			pack.setName(namePack);
			pack.setPrice(price);
			
			ArrayList<Product> l = new ArrayList<Product>();
			
			Product product1 = productRepository.findByName(nameP1);
			Product product2 = productRepository.findByName(nameP2);
			Product product3 = productRepository.findByName(nameP3);
			
			l.add(product1);
			l.add(product2);
			l.add(product3);
			
			pack.setProducts(l);
			
			packRepository.save(pack);
			
			redirectAttrs.addFlashAttribute("success","Pack modified succesfully.");
			return "redirect:/admin-packlist";
	}
	
	
	@RequestMapping("/admin/pack/remove/{id}")
	public String removePackAction(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
			try {
				Pack pack = packRepository.findById(id);
				packRepository.delete(pack);
			} catch (Exception e) {
				return "redirect:/admin-packlist/deleteError";
			}
			
			redirectAttrs.addFlashAttribute("success", "Pack deleted successfully");
			return "redirect:/admin-packlist";
}
	
	@RequestMapping("/admin-user")
	public String adminuserprofile(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		model.addAttribute("products",productRepository.findAll());
		User user = userRepository.findByName(request.getUserPrincipal().getName());
		model.addAttribute("user",user);
	
		
		return "/admin/admin-user";
	}

	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin-modify-user/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> adminmodifyuserRest(Model model,@PathVariable Integer id, @RequestBody User user, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			
		this.session(model, request, redirectAttrs);
			
		User userUpdated = userRepository.findById(id);
				
		if(userUpdated!=null) {
			userUpdated.setAddress(user.getAddress());
			userUpdated.setDni(user.getDni());
			userUpdated.setEmail(user.getEmail());
			userUpdated.setPasswordHash(user.getPasswordHash());
			userUpdated.setAvatar(user.getAvatar());
			userUpdated.setTelephone(user.getTelephone());
				
			userRepository.save(userUpdated);
			return new ResponseEntity<>(userUpdated, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/admin-modify-product-action")
	public String modifyProductAction(Model model,@RequestParam String nam, @RequestParam String namep, @RequestParam String descriptionp, @RequestParam String typep,
			@RequestParam String genrep, @RequestParam int stockp, @RequestParam double pbuyp, @RequestParam double prentp, @RequestParam int scorep,
			@RequestParam String trailerp,@RequestParam String directorp, @RequestParam String castp, @RequestParam int yearp, @RequestParam MultipartFile img,
			HttpServletRequest request, RedirectAttributes redirectAttrs){
				
		this.session(model, request, redirectAttrs);
				
				Product product = productRepository.findByName(nam);
				System.out.println(product.toString());
				
				product.setName(namep);
				product.setDescription(descriptionp);
				product.setType(typep);
				product.setGenre(genrep);
				product.setStock(stockp);
				product.setPbuy(pbuyp);
				product.setScore(scorep);
				product.setTrailer(trailerp);
				product.setDirector(directorp);
				product.setCast(castp);
				product.setYear(yearp);
				
				productRepository.save(product);
				
				String imgName = product.getId() + ".jpg";
				if (!img.isEmpty()) {
					try {
						File imgFolder = new File("src/main/resources/static/img/ProductImages");
						if (!imgFolder.exists()) {
							imgFolder.mkdirs();
						}
						File uploadedImage = new File(imgFolder.getAbsolutePath(), imgName);
						img.transferTo(uploadedImage);
					} catch (Exception e) {
					}
					product.setUrlimg(imgName);
					productRepository.save(product);
				}
				redirectAttrs.addFlashAttribute("messages", "Product Modified");
				
		return "redirect:/admin-products";
	}

	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin-add-user", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User addUserRest(Model model, @RequestBody User user, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			
			this.session(model, request, redirectAttrs);
				
				String avatar = "usern.png";
				User newuser = new User(user.getName(), user.getPasswordHash(),user.getDni(), user.getEmail(), user.getTelephone(),
						user.getAddress(),avatar,"ROLE_USER");

				userRepository.save(newuser);

				return user;
	}
	
	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin/user/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<User> removeUsertActionRest(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
			this.session(model, request, redirectAttrs);
			
			User user = userRepository.findById(id);
			if (user!=null) {
			userRepository.delete(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			
	}
	
	/////////// ******************** PRODUCTS ************************* ////////
	
	
	@RequestMapping("/admin-products-add")
	public String addProduct(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		return "admin/admin-add-product";
	}
	
	@RequestMapping("/admin-add-product")
	public String addProduct(Model model, @RequestParam String name, @RequestParam String description, @RequestParam String type,
			@RequestParam String genre, @RequestParam int stock, @RequestParam double pbuy,
			@RequestParam double prent, @RequestParam int score,@RequestParam String trailer,@RequestParam String director,
			@RequestParam String cast, @RequestParam int year, @RequestParam MultipartFile img, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			
			this.session(model, request, redirectAttrs);
			
			Product product = new Product(name, description, type, genre, stock, pbuy, prent, score, trailer, director, cast, year);
				
			productRepository.save(product);
				
			String imgName = product.getId() + ".jpg";
			if (!img.isEmpty()) {
				try {
					File imgFolder = new File("src/main/resources/static/img/ProductImages");
					if (!imgFolder.exists()) {
						imgFolder.mkdirs();
					}
					File uploadedImage = new File(imgFolder.getAbsolutePath(), imgName);
					img.transferTo(uploadedImage);
				} catch (Exception e) {
				}
				product.setUrlimg(imgName);
				productRepository.save(product);
			}
			
			redirectAttrs.addFlashAttribute("success","Product added succesfully.");
			return "redirect:/admin-products";
	}
	
	
	
	@RequestMapping("/admin/product/remove/{id}") // remove
	public String removeProduct(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		try {
			Product product = productRepository.findById(id);
			productRepository.delete(product);
		} catch (Exception e) {
			return "redirect:/admin-products/deleteError";
		}
		
		redirectAttrs.addFlashAttribute("success","Product deleted succesfully.");
		return "redirect:/admin-products";
	}
	
	@RequestMapping("/admin-modify-product/{id}")
	public String ModifyProduct(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
	
		this.session(model, request, redirectAttrs);
		
		model.addAttribute("product",productRepository.findById(id));
		return "/admin/admin-modify-product2";
	}
	
	@RequestMapping("/admin/product/modify/{id}") // modify
	public String modifyProduct(Model model, @RequestParam Integer id, @RequestParam String name, @RequestParam String description, @RequestParam String type,
			@RequestParam String genre, @RequestParam int stock, @RequestParam double pbuy, @RequestParam double prent, @RequestParam int score,
			@RequestParam String trailer,@RequestParam String director, @RequestParam String cast, @RequestParam int year, @RequestParam MultipartFile img,
			HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
			this.session(model, request, redirectAttrs);
			
			Product product = productRepository.findById(id);
			
			product.setName(name);
			product.setDescription(description);
			product.setType(type);
			product.setGenre(genre);
			product.setStock(stock);
			product.setPbuy(pbuy);
			product.setScore(score);
			product.setTrailer(trailer);
			product.setDirector(director);
			product.setCast(cast);
			product.setYear(year);
			
			productRepository.save(product);
			
			String imgName = product.getId() + ".jpg";
			if (!img.isEmpty()) {
				try {
					File imgFolder = new File("src/main/resources/static/img/ProductImages");
					if (!imgFolder.exists()) {
						imgFolder.mkdirs();
					}
					File uploadedImage = new File(imgFolder.getAbsolutePath(), imgName);
					img.transferTo(uploadedImage);
				} catch (Exception e) {
				}
				product.setUrlimg(imgName);
				productRepository.save(product);
			}
			
			redirectAttrs.addFlashAttribute("success","Product modified succesfully.");
			return "redirect:/admin-products";
	}
	
	//----------------------------------------------------------
	//////////************** END PRODUCTS **************//////////////////
}
