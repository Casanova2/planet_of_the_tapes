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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.Pack;
import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.POrderRepository;
import com.planetofthetapes.repository.PackRepository;
import com.planetofthetapes.repository.ProductRepository;
import com.planetofthetapes.repository.UserRepository;

import java.util.List;

@Controller
public class AdminController extends MasterService{
	
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
	
	@RequestMapping("/admin-products")
	public String adminproducts(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
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
	public String orderlist(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		List<POrder> ord = porderRepository.findAll();
		List<Product> prod = new ArrayList<Product>();
		
		model.addAttribute("orders", porderRepository.findAll());
		
		
		return "/admin/admin-orderlist";
	}
	@RequestMapping("/admin-userList")
	public String adminUserList(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		model.addAttribute("users",userRepository.findAll());
		
		return "/admin/admin-userList";
	}
	
	@RequestMapping("/admin-packlist")
	public String adminPackList(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		
		model.addAttribute("packs",packRepository.findAll());
		
		return "/admin/admin-packlist";
	}
	
	@RequestMapping("/admin-add-pack")
	public String addPack(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		this.session(model, request, redirectAttrs);
		
		return "admin/admin-add-pack-action";
	}
	
	@RequestMapping("/admin-add-pack-action")
	public String addPackAction(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs, @RequestParam String namePack, @RequestParam String nameP1,
			@RequestParam String nameP2, @RequestParam String nameP3, @RequestParam Integer price) {
		
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
		
		redirectAttrs.addFlashAttribute("success", "Pack added successfully");
		return "redirect:/admin-packlist";
	}
	
	@RequestMapping("/admin-remove-pack")
	public String removePack(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);

		return "admin/admin-remove-pack-action";
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
	
		
		return "/admin/admin-user";
	}
	
	@RequestMapping("/admin-modify-user")
	public String modifyUser(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
	
		this.session(model, request, redirectAttrs);
		return "/admin/admin-modify-user";
	}
	
	@RequestMapping("/admin/user/editProfile") // modify
	public String modifyuser(Model model, @RequestParam String name, @RequestParam String passwordHash, @RequestParam String dni,
			@RequestParam String email,@RequestParam String telephone, @RequestParam String address,String avatar, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			this.session(model, request, redirectAttrs);
			if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
				User user = userRepository.findByName(request.getUserPrincipal().getName());
			
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
			}
		redirectAttrs.addFlashAttribute("success", "User modified successfully");
		return "redirect:/admin-user";
	}
		
	@RequestMapping(value="/admin-modify-product")
	public String ModifyProduct(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {

		this.session(model, request, redirectAttrs);
		return "admin/admin-modify-product-action";
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
	
	@RequestMapping("/add-user")
	public String addUser(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);
		model.addAttribute("products",productRepository.findAll());
	
		return "/admin/admin-add-user";
	}
	
	@RequestMapping("/admin-user-add")
	public String adduser(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);

		return "admin/admin-add-user";
	}
	
	@RequestMapping("/admin-add-user")
	public String addUser(Model model, @RequestParam String name, @RequestParam String passwordHash, @RequestParam String dni,
			@RequestParam String email,@RequestParam String telephone, @RequestParam String address,String avatar, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
			
			this.session(model, request, redirectAttrs);
				User user = new User(name,passwordHash,dni,email,telephone,address,avatar,"ROLE_USER");
				try {
					userRepository.save(user);
				} catch (Exception e) {
					return "redirect:/admin-userList/addError";
				}
				redirectAttrs.addFlashAttribute("success", "User Added.");

				return "redirect:/admin-userList";
	}
	
	@RequestMapping("/admin-remove-product")
	public String removeProduct(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);

		return "admin/admin-remove-product-action";
	}
	
	@RequestMapping("/admin-remove-user")
	public String removeUse(Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);

		return "admin/admin-remove-user-action";
	}

	@RequestMapping("/admin/user/remove/{id}")
	public String removeUsertAction(Model model, @PathVariable Integer id, HttpServletRequest request, RedirectAttributes redirectAttrs) {
			this.session(model, request, redirectAttrs);
				try {
					User user = userRepository.findById(id);
					userRepository.delete(user);
				} catch (Exception e) {
					redirectAttrs.addFlashAttribute("error", "Error to modify user.");
					return "redirect:/admin-user/deleteError";
				}
				redirectAttrs.addFlashAttribute("success", "User Deleted.");
				return "redirect:/admin-userList";
	}
	
	/////////// ******************** PRODUCTS ************************* ////////
	
	// Así NO se debe hacer
	/*@RequestMapping("/admin-remove-product-action")
	public String removeProductAction(Model model, @RequestParam String name, @RequestParam String type, HttpServletRequest request, RedirectAttributes redirectAttrs) {
			this.session(model, request, redirectAttrs);
				try {
					Product product = productRepository.findByNameAndType(name, type);
					productRepository.delete(product);
				} catch (Exception e) {
					return "redirect:/admin-products/deleteError";
				}

				return "redirect:/admin-products";
	}*/
	
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
	
	
	// ASÍ SE DEBE HACER -----------------------------------------------------------
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
