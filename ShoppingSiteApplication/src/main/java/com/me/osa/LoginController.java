package com.me.osa;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.me.dao.UserDAO;
import com.me.exception.UserException;
import com.me.pojo.User;
import com.me.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class LoginController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String userHome(HttpServletRequest request) throws Exception {
		return "home";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	protected String aboutPage(HttpServletRequest request) throws Exception {
		return "about";
	}
	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		ModelAndView mav = new ModelAndView("register");
		Map<String,String> usertype = new LinkedHashMap<String,String>();
		usertype.put("Customer", "Customer");
		usertype.put("Admin", "Admin");
		
		mav.addObject("usertype", usertype);
        mav.addObject("user", new User());
        return mav;

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("register");
			Map<String,String> usertype = new LinkedHashMap<String,String>();
			usertype.put("Customer", "Customer");
			usertype.put("Admin", "Admin");
			
			mav.addObject("usertype", usertype);
	        mav.addObject("user", user);
	        return mav;
		}

		try {

			System.out.print("registerNewUser");

			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			
			
			return new ModelAndView("registration-success", "user", u);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {
			System.out.print("loginUser");
			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			else if(u.getUsertype().equals("Customer")){
				session.setAttribute("user", u);
				return "customer-home";
			}
			
			else if(!(u.getUsertype().equals("Customer"))&&!(u.getUsertype().equals("Admin"))){
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			else{
				session.setAttribute("user", u);
				return "user-home";
			}
			
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}
	}
}
