package com.planetofthetapes.restController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminRestController {
	
	public interface ProductDetails extends Product.Basic, Product.OrderRelation, Product.PackRelation{}
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
	
	
/////////// ******************** ORDERS ************************* ////////
	
	@JsonView(OrderDetails.class)
	@RequestMapping(value="/admin-orderlist", method=RequestMethod.GET)
	public ResponseEntity<List<POrder>> orderlistRest(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			List<POrder> orders = porderRepository.findAll();
			if (!orders.equals(null)){
				return new ResponseEntity<>(orders, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(OrderDetails.class)
	@RequestMapping(value="/user-orderlist", method=RequestMethod.GET)
	public ResponseEntity<List<POrder>> userOrderListRest(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			List<POrder> ousers = user.getOrders();
			if (!ousers.equals(null)){
				return new ResponseEntity<>(ousers, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
/////////// ******************** PACKS ************************* ////////

	@JsonView(PackDetails.class)
	@RequestMapping(value="/admin-packlist", method=RequestMethod.GET)
	public ResponseEntity<List<Pack>> adminPackListRest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
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
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/admin-add-pack-action/{id1}/{id2}/{id3}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pack> addPackActionRest(HttpServletResponse response, HttpServletRequest request, @RequestBody Pack pack, 
			@PathVariable Integer id1, @PathVariable Integer id2, @PathVariable Integer id3) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			List<Product> all =productRepository.findAll();
	
			Pack newpack = new Pack(pack.getName(), pack.getPrice());
			
			List<Product> l = new ArrayList<Product>();
			l.add(all.get(id1-1));
			l.add(all.get(id2-1));
			l.add(all.get(id3-1));
	
			pack.setProducts(l);
			
			String imgName = "packi.jpg";
			pack.setImg(imgName);
			
			newpack.setProducts(pack.getProducts());
			newpack.setImg(pack.getImg());
			packRepository.save(newpack);
			
			return new ResponseEntity<>(pack, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/admin/pack/modify/pack-{id}/{id1}-{id2}-{id3}", method=RequestMethod.PUT) // modify
	public ResponseEntity<Pack> modifyPackActionRest(HttpServletResponse response, @PathVariable Integer id, @RequestBody Pack pack,
			@PathVariable Integer id1, @PathVariable Integer id2, @PathVariable Integer id3, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			List<Product> all =productRepository.findAll();
			
			Pack packUpdated = packRepository.findById(id);
			
			if (packUpdated!=null) {
				packUpdated.setName(pack.getName());
				packUpdated.setPrice(pack.getPrice());
			
				List<Product> l = new ArrayList<Product>();
				l.add(all.get(id1-1));
				l.add(all.get(id2-1));
				l.add(all.get(id3-1));

				pack.setProducts(l);
				pack.setImg("packi.jpg");
			
				packUpdated.setProducts(pack.getProducts());
				packUpdated.setImg(pack.getImg());
			
				packRepository.save(packUpdated);
			
				return new ResponseEntity<>(packUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(PackDetails.class)
	@RequestMapping(value="/admin/pack/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Pack> removePackAction(HttpServletResponse response, @PathVariable Integer id, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			Pack pack = packRepository.findById(id);
			
			if(pack!=null) {
					
				packRepository.delete(pack);
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			} else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

/////////// ******************** USERS ************************* ////////
	
	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin-userList", method=RequestMethod.GET)
	public ResponseEntity<List<User>> adminUserListRest(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			List<User> users = userRepository.findAll();
			if (!users.equals(null)){
				return new ResponseEntity<>(users, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin-user", method=RequestMethod.GET)
	public ResponseEntity<User> adminuserprofileRest(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			User user = userRepository.findByName(request.getUserPrincipal().getName());
	
			if(user!=null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin-modify-user/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> adminmodifyuserRest(HttpServletResponse response, @PathVariable Integer id, @RequestBody User user, HttpServletRequest request)
			throws IOException, ServletException {
			
		if (request.authenticate(response)) {	
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
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin-add-user", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUserRest(HttpServletResponse response, @RequestBody User user, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			
			List<String> role = new ArrayList<String>();
			role.add("ROLE_USER");
			
			user.setAvatar("usern.png");
			user.setRoles(role);
			User newuser = new User(user.getName(), user.getPasswordHash(),user.getDni(), user.getEmail(), user.getTelephone(),
			user.getAddress(),user.getAvatar(),user.getRoles());

			userRepository.save(newuser);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(UserDetails.class)
	@RequestMapping(value="/admin/user/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<User> removeUsertActionRest(HttpServletResponse response, @PathVariable Integer id, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			User user = userRepository.findById(id);
			if (user!=null) {
			userRepository.delete(user);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/////////// ******************** PRODUCTS ************************* ////////
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/admin-products", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> adminproductsRest(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			List<Product> products = productRepository.findAll();
			if (!products.equals(null)){
				return new ResponseEntity<>(products, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/newproduct", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> addProductRest(HttpServletResponse response, @RequestBody Product product, 
			HttpServletRequest request) throws IOException, ServletException {
			
		if (request.authenticate(response)) {
			Product newproduct = new Product(product.getName(), product.getDescription(), product.getType(), product.getGenre(),
					product.getStock(), product.getPbuy(), product.getPrent(), product.getScore(), product.getTrailer(), product.getDirector(),
					product.getCast(), product.getYear(), product.getUrlimg());

			productRepository.save(newproduct);
				
			return new ResponseEntity<>(newproduct, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/admin/product/remove/{id}", method=RequestMethod.DELETE) // remove
	public ResponseEntity<Product> removeProductRest(HttpServletResponse response, @PathVariable Integer id, HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			Product product = productRepository.findById(id);
			if(product!=null) {
			productRepository.delete(product);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(ProductDetails.class)
	@RequestMapping(value="/admin/product/modify/{id}", method=RequestMethod.PUT) // modify
	public ResponseEntity<Product> modifyProductRest(HttpServletResponse response, @PathVariable Integer id, @RequestBody Product product,
			HttpServletRequest request) throws IOException, ServletException {
		
		if (request.authenticate(response)) {
			Product productUpdated = productRepository.findById(id);
			
			if(productUpdated!=null) {
			productUpdated.setName(product.getName());
			productUpdated.setDescription(product.getDescription());
			productUpdated.setType(product.getType());
			productUpdated.setGenre(product.getGenre());
			productUpdated.setStock(product.getStock());
			productUpdated.setPbuy(product.getPbuy());
			productUpdated.setScore(product.getScore());
			productUpdated.setTrailer(product.getTrailer());
			productUpdated.setDirector(product.getDirector());
			productUpdated.setCast(product.getCast());
			productUpdated.setYear(product.getYear());
			
			productRepository.save(productUpdated);
			return new ResponseEntity<>(productUpdated, HttpStatus.OK);
			
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
