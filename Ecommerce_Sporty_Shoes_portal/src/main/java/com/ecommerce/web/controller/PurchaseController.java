package com.ecommerce.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.web.entity.Purchase;
import com.ecommerce.web.repository.PurchaseRepository;

@Controller
public class PurchaseController {
	
	//Dependency Injection--------->
	@Autowired
	PurchaseRepository repo;

	
	
	//PurchaseReport handler--------------->
	@RequestMapping("/purchase")
	public String getPurchaseReport(Model model) {
		model.addAttribute("title", "Purchase Report - SportyShoes.com");
		List<Purchase>purchase= repo.findAll();
		model.addAttribute("purchase",purchase);
		System.out.println("Purchase Report " +purchase);
		return "purchasereport";
	}
	
	
	//PurchaseReport Search handler--------------->
	@PostMapping("/search-result")
	public String searchPurchaseHistory(Model model,@RequestParam String category,@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		
		model.addAttribute("title", "SearchResult - SportyShoes.com");
		List<Purchase> purchase = repo.findByFilter(category, date);
		model.addAttribute("purchase", purchase);
		System.out.println("Search result " +purchase );
		
		
		
		return "purchasereport";
	}
}
