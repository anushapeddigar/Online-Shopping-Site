package com.me.osa;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.me.dao.ProductDAO;
import com.me.dao.CartDAO;
import com.me.dao.DAO;
import com.me.dao.UserDAO;
import com.me.pojo.Cart;
import com.me.pojo.MyPDF;
import com.me.pojo.Product;
import com.me.pojo.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cart/*")
public class OrderController extends MyPDF{
	
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
	
	
	@RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
	public ModelAndView showPdfReport(@ModelAttribute("cart") Cart cart,
									  ModelMap model,
			                          BindingResult result, 
			                          HttpServletRequest request) throws Exception
	{
		
		HttpSession session = (HttpSession) request.getSession();
		User u = (User)session.getAttribute("user");
		List<Cart> c=cartDao.list();
		Set<Product> productDetails = new HashSet<Product>();
		for(Cart cc:c){
			 if(u.getUserID()==cc.getId()){
				 
				 productDetails = cc.getProducts();
			 }}
		
		ArrayList<Product> al = new ArrayList<Product>(productDetails);
		ArrayList<Cart> cartDet = new ArrayList<Cart>();
		
		for(int j=0;j<al.size();j++) {
			Product p = al.get(j);
			Product product1 = productDao.findProductById(p.getId());
			Cart cm =  new  Cart();
			cm.setTitle(product1.getProductName());
			cm.setCategory(product1.getProductCategory());
			cm.setTotalprice(product1.getPrice().toString());
			cartDet.add(cm);
		}

		
		
		model.addAttribute("cartitems", cartDet);
		
		View v = new MyPDF();
		return new ModelAndView(v);
		
	}
	


}
