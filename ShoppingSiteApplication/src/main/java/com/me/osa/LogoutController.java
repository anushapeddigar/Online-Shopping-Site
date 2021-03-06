package com.me.osa;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.ProductDAO;
import com.me.dao.UserDAO;

@Controller
@RequestMapping("/product/*")
public class LogoutController {


	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/product/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "logout";
    }

}
