package com.ecommerce.web.controller;



import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.web.entity.Product;
import com.ecommerce.web.helper.MessageHelper;
import com.ecommerce.web.repository.ProductRepo;
import com.ecommerce.web.repository.ProductRepository;
@Controller
public class ProductController {
	
	//Dependency Injection-------------->
	@Autowired
	ProductRepository<Product> repo;

	
	//AdminDashboard handler--------------->
	@RequestMapping(value="adminDashboard")
	public String dashboardt(Model model) {
		model.addAttribute("title", "AdminDashboard - SportyShoes.com");
		return "adminDashboard";
		
		
	}
	
	//Manage Product handler--------------->
	@RequestMapping("/manageProduct")
	public String manageProduct(Model model) {
		model.addAttribute("title", "ManageProduct - SportyShoes.com");
		return "manageProduct";
		
	}
	
	//Add Product handler--------------->
	@RequestMapping("addproduct")
	public String addProduct(Model model) {
		model.addAttribute("title", "AddProduct - SportyShoes.com");
		return "addProduct";
		
		
	}
	
	//Process add Product handler--------------->
	@PostMapping("/processAddproduct")
	public String processAddProduct(@ModelAttribute Product product,HttpSession session) {
		
	try {
		
		
		System.out.println("Addedd" +product);
		repo.addProduct(product);
		session.setAttribute("message", new MessageHelper("Product added Successfully !","success"));
	}catch(Exception e) {
		System.out.println("Something is wrong" +e.getMessage());
		e.printStackTrace();
		//session.setAttribute("message", new MessageHelper("Something went wrong try again","danger"));
	}
	
			return "addproduct";
	
	}
	
	//Managing Product Category wise handler--------------->
	@RequestMapping("/manage-cat")
	public String manageCategory(Model model) {
		
		return "manageproduct-category";
	}
	
	//SoccerShoes category handler--------------->
	@RequestMapping(value="/soccer")
	public String getProductSoccer(Model model) {
		model.addAttribute("title", "Soccer - SportyShoes.com");
		List<Product> product = null;
		
		product = repo.findSoccerShoes();
		model.addAttribute("product", product);
		System.out.println("List of soccer shoes: " +product);
		return "manageproduct-category";
		
		
		
	}
	
	
	//GolfShoes category handler--------------->
	@RequestMapping(value="/golf")
	public String getProductGolf(Model model) {
		model.addAttribute("title", "Golf - SportyShoes.com");
		List<Product> product = null;
		
	     product= repo.findGolfShoes();
		model.addAttribute("product", product);
		System.out.println("List of golf shoes:" +product);
		return "manageproduct-category";
		
		
	}
	
	//RunningShoes category handler--------------->
	@RequestMapping(value="/running")
	public String getProductRunning(Model model) {
		model.addAttribute("title", "Running - SportyShoes.com");
		List<Product> product = null;
		
		product = repo.findRunningShoes();
		model.addAttribute("product", product);
		System.out.println( "List of running shoes: "+ product);
		return "manageproduct-category";
		
		
	}
	
	//HighTop Shoes category handler--------------->
	@RequestMapping(value="/hightop")
	public String getProductHighTop(Model model) {
		model.addAttribute("title", "HighTop- SportyShoes.com");
		List<Product> product = null;
		
		product = repo.findHighTopShoes();
		model.addAttribute("product", product);
		System.out.println( "List of high top shoes: "+ product);
		return "manageproduct-category";
		
		
	}
	//Boots Shoes category handler--------------->
	@RequestMapping(value="/boots")
	public String getProductHikingBoots(Model model) {
		model.addAttribute("title", "HikingBoots - SportyShoes.com");
		List<Product> product = null;
		
		product = repo.findHikingBootsShoes();
		model.addAttribute("product", product);
		System.out.println("List of hiking boots shoes: "+ product);
		return "manageproduct-category";
		
		
	}
	
	
	//Dependency Injection---------------->
	@Autowired
	ProductRepo repository;
	
	//Manage Product handler--------------->
	@GetMapping("/manageProduct/{product_id}")
	public String deleteProduct(@PathVariable("product_id") Integer product_id) {
		
		Optional<Product> productoptional= repository.findById(product_id);
		Product product = productoptional.get();
		this.repository.delete(product);
		
	
		return "redirect:/manageProduct/";
		
	}
	
	//Update Product  handler--------------->
	@PostMapping("/updateproduct/{product_id}")
	public String updateProduct(@PathVariable ("product_id") Integer product_id,Model model) {
		model.addAttribute("title", "Update Product - SportyShoes.com");
		Product product  =repository.findById(product_id).get();
		
		model.addAttribute("product",product);
		
		return "updateproduct";
	}
	
	//Process Update Product handler--------------->
	@PostMapping("/process")
	public String proccesUpdateProduct(@ModelAttribute Product product,HttpSession ss) {
		try {
		
		 this.repository.save(product);
		 System.out.println("Product Category: " +product.getProduct_category());
		 System.out.println("Product Id: " +product.getProduct_id());
		 ss.setAttribute("message", new MessageHelper("Product Updated Successfully","success"));
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return "updateproduct";
	}
	
	
		
		
	
			
			
		
	
		
		
		

		
		
	

}
