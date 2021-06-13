package com.me.osa;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.UserDAO;
import com.me.exception.ProductException;
import com.me.dao.CartDAO;
import com.me.dao.ProductDAO;
import com.me.pojo.Cart;
import com.me.pojo.Product;
import com.me.pojo.User;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
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
	
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public ModelAndView addProduct( @ModelAttribute("product") Product product, BindingResult result) throws Exception 
	{
		try {			
			
			User u = userDao.get(product.getPostedBy());
			product.setUser(u);
			product = productDao.create(product);
			
            
			
            return new ModelAndView("product-success", "product", product);
            
		} catch (ProductException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}	
	}
	
	@RequestMapping(value="/product/add", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();		
		mv.addObject("product", new Product());
		mv.setViewName("product-form");
		return mv;
	}
	
	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public ModelAndView listProducts(HttpServletRequest request) throws Exception {

		try {			
			
			List<Product> products = productDao.list();
			return new ModelAndView("admin-product-list", "products", products);
			
		} catch (ProductException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/product/delete", method = RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request, @ModelAttribute("product") Product product) throws Exception {

		try {			
			
			//User u = userDao.get(product.getPostedBy());
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			List<Product> products = productDao.findProductsByUserID(u.getUserID());
			return new ModelAndView("product-delete-list", "products", products);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	/*
	@RequestMapping(value = "/product/deleteProduct/{id}", method = RequestMethod.POST)
	public ModelAndView deleteProductID(@PathVariable long id) {
		Product product = productDao.findProductById(id);
		try
		{
		productDao.delete(product);
		return new ModelAndView("product-delete-success", "product", product);
		}
		catch (Exception e)
		{
			return new ModelAndView("product-delete-success", "product", product);
		}
	}
	*/
	@RequestMapping(value = "/product/deleteProduct", method = RequestMethod.POST)
	public ModelAndView deleteProductID(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("delete"));
		
		Product product = productDao.findProductById(id);
		try
		{
		productDao.delete(product);
		return new ModelAndView("product-delete-success", "product", product);
		}
		catch (Exception e)
		{
			return new ModelAndView("product-delete-success", "product", product);
		}
	}
	
	@RequestMapping(value = "/product/custlist", method = RequestMethod.GET)
	public ModelAndView listProduct(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("product-list");
		List<Product> products = productDao.list();
		mav.addObject("products", products);
		
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		
		Cart cd=null;
		List<Cart> c=cartDao.list();
		 int i=0;
		 Cart cw = null;
		 for(Cart cc:c){
			 if(u.getUserID()==cc.getId()){
				 cd = cc;
			 }
		 }
	 if(cd!=null)
	 {
		 mav.addObject("exCart", cd);
		 return mav;
	 }
		
        mav.addObject("cart", new Cart());
        return mav;
		
	}
	
}
