package com.example.website.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.website.entities.*;
import com.example.website.repository.CategoryRepo;
import com.example.website.repository.ProductRepo;
import com.example.website.repository.PurchaseRepo;
import com.example.website.repository.UserRepo;

@Controller
public class WebsiteController {
	
	@Autowired
	UserRepo userrepo;
	@Autowired
	CategoryRepo caterepo;
	@Autowired
	ProductRepo prorepo;
	@Autowired
	PurchaseRepo purrepo;
	

	@GetMapping("/login")
	public ModelAndView Login() {
        return new ModelAndView("Login", "log", new Login());

	}
	
	@RequestMapping(value="/purchase", method = RequestMethod.POST)
	public String purchase(@RequestParam("productId") Long productId,@RequestParam("userId") Long userId,Model mv) {
		System.out.println(productId+" "+userId);
		
		Purchase purchase = new Purchase();
		User user = userrepo.getById(userId);
		List<Product> listOfProduct = prorepo.findAll();
		for(Product product:listOfProduct){
		System.out.println(product.getProductId());
		System.out.println(product.getPriceOfProduct());
		if(product.getProductId() == productId) {
			purchase.setCategoryName(product.getCategory().getCategoryName());
			purchase.setPrice(product.getPriceOfProduct());
			break;
			}
		}
		purchase.setUserId(userId);
		purchase.setProductId(productId);
		
		System.out.println(purchase.getCategoryName()+""
				+ " "+purchase.getProductId()+" "+purchase.getUserId()+" "+purchase.getPrice());
		
		LocalDateTime now = LocalDateTime.now();  
		purchase.setDateOfPurchase(now);
		mv.addAttribute(user);
		mv.addAttribute(prorepo.findAll());
		purrepo.save(purchase);
		
		
		return "user.html";
		
	}
		
	@GetMapping("/logout")
	public String Logout() {
        return "Login.html";

	}
	
	@GetMapping("/register")
	public ModelAndView Register() {
		 return new ModelAndView("Register", "reg", new User());
	}
	
	@RequestMapping(value = "/registerin", method = RequestMethod.POST)
    public String registerByUser(@Validated @ModelAttribute("user") User user, 
      BindingResult result, Model mv) {
        if (result.hasErrors()) {
            return "error";
        }
        user.setUserType("normal");
        userrepo.save(user);
        
        System.out.println(user.getUserName()+" "+user.getUserContact()+" "+user.getUserMailId()+" "+user.getUserPassword());
        return "Login.html";
    }	
	@RequestMapping(value = "/loggedin", method = RequestMethod.POST)
    public String loginByUser(@Validated @ModelAttribute("log") Login log, 
      BindingResult result, Model mv,@RequestParam("usertype") String type) {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println(log.getUserMailId()+" "+log.getUserPassword()+" "+type);
        List<User> listOfUser = userrepo.findAll();
       
        
        for(User user : listOfUser) {
        	if(user.getUserMailId().compareTo(log.getUserMailId()) == 0 && user.getUserPassword().compareTo(log.getUserPassword())==0){
            	System.out.println(user.getUserMailId()+" "+log.getUserMailId());
        		if(user.getUserType().compareTo("admin")== 0){
        			System.out.println(user.getUserType()+" "+type);
        			mv.addAttribute("userList",userrepo.findAll());
        			mv.addAttribute("productList",prorepo.findAll());
        			mv.addAttribute("purchaseList",purrepo.findAll());
        			return "admin.html";
        		}else {
        		mv.addAttribute("productList",prorepo.findAll());
        		mv.addAttribute(user);
    			return "user.html";
        		}
        	}
        }
        return "Login.html";
    }
	
	@GetMapping("/addproduct")
	public ModelAndView AddProduct() {
		 return new ModelAndView("AddProduct", "product", new Product());
	}
	
	private List<Product> getProductsList(Category c,BindingResult result){
		Product pro = new Product();
		pro.setProductName((String) result.getFieldValue("productName"));
		pro.setPriceOfProduct(Long.parseUnsignedLong((String) result.getFieldValue("priceOfProduct")));
		pro.setProductType((String) result.getFieldValue("productType"));
		pro.setCategory(c);
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(pro);
		return productList;
	}
	
	@RequestMapping(value = "/addproductbyadmin", method = RequestMethod.POST)
    public String addedProductByAdmin(@Validated @ModelAttribute("product") Product product, 
      BindingResult result, Model mv) {
//		System.out.println("Anand");
//		System.out.println(result.getFieldValue("category"));
//		System.out.println(result.getFieldValue("productName"));
//		System.out.println(result.getFieldValue("priceOfProduct"));
//		System.out.println(result.getFieldValue("productType"));
        
        
       Category c = new Category();
       c.setCategoryName((String) result.getFieldValue("category"));
       
       List<Product> productList = getProductsList(c,result);
       caterepo.save(c);
       for(Product product1 : productList) { 
           prorepo.saveAndFlush(product1);
       }
       mv.addAttribute("purchaseList",purrepo.findAll());
       mv.addAttribute("productList",prorepo.findAll());
       mv.addAttribute("userList",userrepo.findAll());
		return "admin.html";
    }
	
	@GetMapping("/")
	public String openingPage() {
		return "index.html";
	}
	
	@GetMapping("/byDate")
	public String sortPurchaseBasedDate(Model mv){
		mv.addAttribute("purchaseList",purrepo.findAllByOrderByDateOfPurchaseAsc());
	    mv.addAttribute("productList",prorepo.findAll());
	    mv.addAttribute("userList",userrepo.findAll());
	    return "admin.html";	
	    }
	
	@GetMapping("/byCategory")
	public String sortPurchaseBasedCategory(Model mv){
		mv.addAttribute("purchaseList",purrepo.findAllByOrderByCategoryNameAsc());
	    mv.addAttribute("productList",prorepo.findAll());
	    mv.addAttribute("userList",userrepo.findAll());
	    return "admin.html";	
	}	
}