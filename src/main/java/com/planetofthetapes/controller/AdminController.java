package com.planetofthetapes.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

import java.util.List;

@Controller
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private POrderRepository porderRepository;
	@Autowired
	private PackRepository packRepository;
	@Autowired
	private MasterController masterSession;
	
	@RequestMapping("/admin")
	public String admin(Model model,HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("products",productRepository.findAll());
		model.addAttribute("users",userRepository.findAll());
		model.addAttribute("orders", porderRepository.findAll());
		model.addAttribute("packs", packRepository.findAll());
		
		
		return "/admin/admin-dashboard";
	}
	
	@RequestMapping("/admin-products")
	public String adminproducts(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		
		
		Page<Product> series = productRepository.findGroupByType("Serie", new PageRequest(0, 4));
		Page<Product> movies = productRepository.findGroupByType("Movie", new PageRequest(0, 4));
		Page<Product> videogames = productRepository.findGroupByType("Videogame", new PageRequest(0, 4));

		model.addAttribute("series",series);
		model.addAttribute("movies",movies);
		model.addAttribute("videogames",videogames);
		
		model.addAttribute("products",productRepository.findAll());
		
		
		return "/admin/admin-products";
	}
	@RequestMapping("/admin-orderlist")
	public String orderlist(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		List<POrder> ord = porderRepository.findAll();
		List<Product> prod = new ArrayList<Product>();
		/*for (POrder p : ord) {
			prod = p.getProducts();
			for(Product o : prod) {
				String name = o.getName();
				model.addAttribute("names", name);
			}
			
		}
		*/
		model.addAttribute("orders", porderRepository.findAll());
		
		
		return "/admin/admin-orderlist";
	}
	@RequestMapping("/admin-userList")
	public String adminUserList(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("users",userRepository.findAll());
		
		return "/admin/admin-userList";
	}
	
	@RequestMapping("/admin-packlist")
	public String adminPackList(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("packs",packRepository.findAll());
		
		return "/admin/admin-packlist";
	}
	
	@RequestMapping("/admin-user")
	public String adminuserprofile(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("products",productRepository.findAll());
	
		
		return "/admin/admin-user";
	}
	
	@RequestMapping("/admin-modify-user")
	public String adminmodifyuser(Model model, @RequestParam String name,@RequestParam Integer id, @RequestParam String passwordHash, @RequestParam String dni,
			@RequestParam String email,@RequestParam String telephone, @RequestParam String address,String avatar, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			masterSession.numbers(model);
			masterSession.session(model, request);
			
				User user = userRepository.findById(id);
				user.setName(name);
				user.setAddress(address);
				user.setDni(dni);
				user.setEmail(email);
				user.setPasswordHash(passwordHash);
				user.setAvatar(avatar);
				user.setTelephone(telephone);
				try {
					userRepository.save(user);
				} catch (Exception e) {
					return "redirect:/admin-userList/addError";
				}
				redirectAttrs.addFlashAttribute("messages", "Modificado usuario");
		return "/admin/admin-user";
	}
	
	
	
	@RequestMapping("/add-product")
	public String addUser(Model model, HttpServletRequest request) {
		masterSession.session(model, request);
		masterSession.numbers(model);
		model.addAttribute("products",productRepository.findAll());
	
		return "/admin/admin-add-user";
	}
	@RequestMapping("/admin-products-add")
	public String addProduct(Model model, HttpServletRequest request) {

		masterSession.session(model, request);
		masterSession.numbers(model);
		return "admin/admin-add-product";
	}
	
	@RequestMapping("/admin-add-product")

	public String addProduct(Model model, @RequestParam String name, @RequestParam String description, @RequestParam String type,
			@RequestParam String genre, @RequestParam int stock, @RequestParam double pbuy,
			@RequestParam double prent, @RequestParam int score,@RequestParam String trailer,@RequestParam String director,
			@RequestParam String cast, @RequestParam int year, @RequestParam MultipartFile img, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			
			masterSession.numbers(model);
			masterSession.session(model, request);
			
			Product product = new Product(name, description, type, genre, stock, pbuy, prent, score, trailer, director, cast, year);
				
			productRepository.save(product);
				
			String imgName = "img/" + product.getId() + ".jpg";
			if (!img.isEmpty()) {
				try {
					File imgFolder = new File("src/main/resources/static/img");
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
			
				redirectAttrs.addFlashAttribute("messages", "Añadido nuevo producto.");

				return "redirect:/admin-products";
	}
	
	@RequestMapping("/admin-user-add")
	public String adduser(Model model, HttpServletRequest request) {

		masterSession.session(model, request);
		masterSession.numbers(model);

		return "admin/admin-add-user";
	}
	
	@RequestMapping("/admin-add-user")
	public String addUser(Model model, @RequestParam String name, @RequestParam String passwordHash, @RequestParam String dni,
			@RequestParam String email,@RequestParam String telephone, @RequestParam String address,String avatar, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			masterSession.numbers(model);
			masterSession.session(model, request);
				User user = new User(name,passwordHash,dni,email,telephone,address,avatar,"ROLE_USER");
				try {
					userRepository.save(user);
				} catch (Exception e) {
					return "redirect:/admin-userList/addError";
				}
				redirectAttrs.addFlashAttribute("messages", "Añadido nuevo usuario.");

				return "redirect:/admin-userList";
	}
	
	@RequestMapping("/admin-remove-product")
	public String removeProduct(Model model, HttpServletRequest request) {

		masterSession.session(model, request);
		masterSession.numbers(model);

		return "admin/admin-remove-product-action";
	}
	
	@RequestMapping("/admin-remove-product-action")
	public String removeProductAction(Model model, @RequestParam String name, @RequestParam String type, HttpServletRequest request) {
			masterSession.numbers(model);
			masterSession.session(model, request);
				try {
					Product product = productRepository.findByNameAndType(name, type);
					productRepository.delete(product);
				} catch (Exception e) {
					return "redirect:/admin-products/deleteError";
				}

				return "redirect:/admin-products";
	}
	
	@RequestMapping("/admin-remove-user")
	public String removeUse(Model model, HttpServletRequest request) {

		masterSession.session(model, request);
		masterSession.numbers(model);

		return "admin/admin-remove-user-action";
	}
	
	@RequestMapping("/admin-remove-user-action")
	public String removeUsertAction(Model model, @RequestParam String name, @RequestParam String email, HttpServletRequest request) {
			masterSession.numbers(model);
			masterSession.session(model, request);
				try {
					User user = userRepository.findByNameAndEmail(name, email);
					userRepository.delete(user);
				} catch (Exception e) {
					return "redirect:/admin-user/deleteError";
				}

				return "redirect:/admin-userList";
	}
}
