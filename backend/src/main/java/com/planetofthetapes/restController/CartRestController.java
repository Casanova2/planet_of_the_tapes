package com.planetofthetapes.restController;

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


@RestController
@RequestMapping("/api")
public class CartRestController{

	public interface ProductDetails extends Product.Basic, Product.OrderRelation, Product.PackRelation {}

	public interface PackDetails extends Pack.Basic, Pack.ProductRelation, Product.Basic {}

	public interface UserDetails extends User.Basic, User.OrderRelationUser, POrder.Basic {}

	public interface OrderDetails extends POrder.Basic, POrder.ProductRelationOrder, POrder.PackRelation, Product.Basic, Pack.Basic {}
	
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
	@RequestMapping(value = "/checkout/{id}", method = RequestMethod.PUT)
	public ResponseEntity<POrder> checkoutRest(HttpServletResponse response, HttpServletRequest request,
			@PathVariable Integer id) throws IOException, ServletException {

		if (request.authenticate(response)) {
			POrder order = POrderRepository.findById(id);
			if (order != null) {
				order.setState("completed");
				POrderRepository.save(order);
				return new ResponseEntity<>(order, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	// PRODUCTS//

	@JsonView(OrderDetails.class)
	@RequestMapping(value = "/{id}/{id2}/product", method = RequestMethod.PUT)
	public ResponseEntity<POrder> reserveRestResource(HttpServletResponse response, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer id2, @RequestBody POrder orderUpdate)
			throws IOException, ServletException {

		if (request.authenticate(response)) {
			double total = 0.0;
			Product p = productRepository.findOne(id2);
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			System.out.println(user.getName());
			System.out.println(user.hasOrders().toString());
			System.out.println(p.getName());

			if (user.hasOrders() && p.getStock() > 0) {
				POrder order = POrderRepository.findById(id);
				order.addProduct(p);
				p.setStock(p.getStock() - 1);
				total = order.getTotal() + p.getPbuy();
				order.setTotal(total);

				POrderRepository.save(order);
				ArrayList<POrder> listPOrders = new ArrayList<POrder>();
				listPOrders.add(order);
				user.setOrders(listPOrders);
				return new ResponseEntity<>(order, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@JsonView(OrderDetails.class)
	@RequestMapping(value = "/order/{id2}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<POrder> createNewRestOrder(HttpServletResponse response, HttpServletRequest request,
			@PathVariable Integer id2) throws IOException, ServletException {

		if (request.authenticate(response)) {
			double total = 0.0;
			Product p = productRepository.findOne(id2);
			User user = userRepository.findByName(request.getUserPrincipal().getName());

			if (p.getStock() > 0) {
				
				POrder neworder = new POrder("progress", "Credit card", "Buy", 0);
				neworder.getProducts().add(p);
				p.setStock(p.getStock() - 1);
				total = neworder.getTotal() + p.getPbuy();
				neworder.setTotal(total);
				user.addOrder(neworder);
				total = total + neworder.getTotal();
				
				POrderRepository.save(neworder);
				return new ResponseEntity<>(neworder, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/{id}/{id1}/r", method = RequestMethod.PUT)
	@JsonView(OrderDetails.class)
	public ResponseEntity<POrder> removeRestResource(HttpServletResponse response, @PathVariable Integer id, @PathVariable Integer id1,
			@RequestBody POrder orderUpdate, HttpServletRequest request) throws IOException, ServletException {

		if (request.authenticate(response)) {
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			Product p = productRepository.findById(id1);
			POrder order = POrderRepository.findById(id);
			if (order != null) {
				order.getProducts().remove(p);
				order.setTotal(order.getTotal() - p.getPbuy());
				p.setStock(p.getStock() + 1);
				POrderRepository.save(order);
				return new ResponseEntity<>(order, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	// PACKS//
	@JsonView(OrderDetails.class)
	@RequestMapping(value = "/pack/{id}/{id2}/product", method = RequestMethod.PUT)
	public ResponseEntity<POrder> packRestbuy(HttpServletResponse response, @PathVariable Integer id, @PathVariable Integer id2,
			HttpServletRequest request, @RequestBody POrder orderUpdated) throws IOException, ServletException {

		if (request.authenticate(response)) {
			double total = 0.0;
			Pack p = packRepository.findOne(id2);
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			System.out.println(user.getName());
			System.out.println(user.hasOrders().toString());
			System.out.println(p.getName());
	
			if (user.hasOrders()) {
				POrder order = POrderRepository.findById(id);
				order.addPack(p);
				total = order.getTotal() + p.getPrice();
				order.setTotal(total);
				POrderRepository.save(order);
				ArrayList<POrder> listPOrders = new ArrayList<POrder>();
				listPOrders.add(order);
				user.setOrders(listPOrders);
				return new ResponseEntity<>(order, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@JsonView(OrderDetails.class)
	@RequestMapping(value = "/pack/{id2}/product", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<POrder> createNewRestPackOrder(HttpServletResponse response, HttpServletRequest request,
			@PathVariable Integer id2, @RequestBody POrder order) throws IOException, ServletException {

		if (request.authenticate(response)) {
			double total = 0.0;
			Pack p = packRepository.findOne(id2);
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			if (p != null) {
				POrder neworder = new POrder(order.getState(), order.getPay(), order.getType(), order.getTotal());
				neworder.getPacks().add(p);
				total = neworder.getTotal() + p.getPrice();
				neworder.setTotal(total);
				user.addOrder(neworder);
				POrderRepository.save(neworder);
				total = total + neworder.getTotal();
				return new ResponseEntity<>(neworder, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@JsonView(OrderDetails.class)
	@RequestMapping(value = "/pack/{id}/{id1}/r", method = RequestMethod.PUT)
	public ResponseEntity<POrder> removeRestPack(HttpServletResponse response, @PathVariable Integer id, @PathVariable Integer id1,
			@RequestBody POrder orderUpdate, HttpServletRequest request) throws IOException, ServletException {

		if (request.authenticate(response)) {
			User user = userRepository.findByName(request.getUserPrincipal().getName());
			Pack p = packRepository.findById(id1);
			POrder order = POrderRepository.findById(id);
			if (order != null) {
				order.getProducts().remove(p);
				order.setTotal(order.getTotal() - p.getPrice());
				POrderRepository.save(order);
				return new ResponseEntity<>(order, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
