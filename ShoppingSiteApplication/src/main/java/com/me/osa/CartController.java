package com.me.osa;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.ProductDAO;
import com.me.dao.DAO;
import com.me.dao.UserDAO;
import com.me.dao.CartDAO;
import com.me.pojo.Product;
import com.me.pojo.Cart;
import com.me.pojo.User;

@Controller
@RequestMapping("/cart/*")
public class CartController extends DAO{

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;
	
	@Autowired
	ServletContext servletContext;
	
	
	/*
	@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
	public ModelAndView insertCart(@ModelAttribute("cart") Cart cart, @RequestParam("id") long id, BindingResult result, HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User u = (User)session.getAttribute("user");
		cart.setUser(u);
		u.setCart(cart);
		
		Product product = productDao.findProductById(id);
		Set<Product> products = new HashSet<Product>();
		products.add(product);
		cart.setProducts(products);
		
		cart.setTitle(product.getProductName());
		cart.setCategory(product.getProductCategory());
		cart.setTotalprice(product.getPrice().toString());
		//cart.setCategory(product.getProductCategory());
		
		Cart cd=null;
//		Cart c = cartDao.insert(cart);
//		User user1 = cartDao.update(u);
//		 getSession().update(u);
		
		/*
		List<Cart> c=cartDao.list();
		 int i=0;
		 Cart cw = null;
		 for(Cart cc:c){
			 if(u.getUserID()==cc.getId()){
				 cw = cartDao.updateCart(cc);
				 i=1;
				 return new ModelAndView("user-cart","c",cw);
			 }
		 }
	 if (i==0){
		  cd = cartDao.insert(cart);
	 }
	 
	 
		Cart c = cartDao.findByUserId(u.getUserID());
		if (c==null){
			  cd = cartDao.insert(cart);
			  return new ModelAndView("user-cart","cd",cd);
		 }
			 Cart cartDetails = c;
			 List<Integer> productCart = cartDao.findByCartId(cartDetails.getId());
			 if(productCart.contains(id)) {
				 //update quantity
				 cartDetails.setQuantity(cartDetails.getQuantity()+1);
			 }
			 else {
				 cartDao.update(cart);
				 c.add(cart);
			 }
		
			 return new ModelAndView("user-cart","cd",cd);
	  
		
	}

*/

@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
public ModelAndView insertCart(@ModelAttribute("cart") Cart cart, @RequestParam("id") long id, BindingResult result, HttpServletRequest request) throws Exception {
	HttpSession session = (HttpSession) request.getSession();
	User u = (User)session.getAttribute("user");
	cart.setUser(u);
	u.setCart(cart);
	
	Product product = productDao.findProductById(id);
	Set<Product> products = new HashSet<Product>();
	products.add(product);
	cart.setProducts(products);
	
	cart.setTitle(product.getProductName());
	cart.setCategory(product.getProductCategory());
	cart.setTotalprice(product.getPrice().toString());
	Cart cd=null;
//Cart c1 = cartDao.insert(cart);
//	User user1 = cartDao.update(u);   
//	 getSession().update(u);
	
	
	List<Cart> c=cartDao.list();
	
	 int i=0;
	 Cart cw = null;
	 for(Cart cc:c){
		 if(u.getUserID()==cc.getId()){
			Set<Product> productDetails = new HashSet<Product>();
			productDetails = cc.getProducts();
			productDetails.add(product);
			cc.setProducts(productDetails);
			cw = cartDao.updateCart(cc);
			 i=1;
			 ArrayList<Product> al = new ArrayList<Product>(productDetails);
		
			List<Cart> cartDet = new ArrayList<Cart>();
			
			for(int j=0;j<al.size();j++) {
				Product p = al.get(j);
				Product product1 = productDao.findProductById(p.getId());
				Cart cm =  new  Cart();
				cm.setTitle(product1.getProductName());
				cm.setCategory(product1.getProductCategory());
				cm.setTotalprice(product1.getPrice().toString());
				cartDet.add(cm);
				
			}
			 return new ModelAndView("user-cart","c",cartDet);
		 }
	 }
 if (i==0){
	  cd = cartDao.insert(cart);
 }
 
 

		 return new ModelAndView("user-cart","c",cd);
  
	
}
}
