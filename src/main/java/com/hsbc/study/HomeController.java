package com.hsbc.study;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("name")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toLoginpage() {
		//return "loginpage";
		return "loginNew";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//return "loginpage";
		session.invalidate();
		return "loginNew";
	}
	
	@RequestMapping(value = "/loginfirst", method = RequestMethod.GET)
	public String loginfirst() {
		
		//return "loginpage";
		return "loginNew";
	}
	@RequestMapping(value="/tologinpage", method=RequestMethod.GET)
	public String toLoginpage2(HttpSession session){
		//logout 
		//session.invalidate();
		//return "loginpage";
		return "loginNew";
	}
	
	@RequestMapping(value="/todeposit", method=RequestMethod.GET)
	public String toDeposit(HttpSession session){
		if (session.getAttribute("admin")!=null)
			return "deposit";
		
		return "logfirst";
	}
	
	@RequestMapping(value="/toinquire", method=RequestMethod.GET)
	public String toInquire(HttpSession session){
		if (session.getAttribute("admin")!=null)
			return "inquire";
		
		return "logfirst";
	}
	
	@RequestMapping(value="/towithdrawal", method=RequestMethod.GET)
	public String toWithdrawal(HttpSession session){
		if (session.getAttribute("admin")!=null)
			return "withdrawal";
		return "logfirst";
	}
	
	
	@RequestMapping(value="/toopenaccount", method=RequestMethod.GET)
	public String toOpenaccount(HttpSession session){
		if (session.getAttribute("admin")!=null)
			return "openaccount";
		return "logfirst";
	}
	
	@RequestMapping(value="/toopensuccess", method=RequestMethod.POST)
	public String toOpensuccess(HttpSession session){
		if (session.getAttribute("admin")!=null)
			return "opensuccess";
		return "logfirst";
	}
	
	@RequestMapping(value="/tomainpage", method=RequestMethod.GET)
	public String toMainpage(HttpSession session){
		if (session.getAttribute("admin")!=null)
		//if(Session["name"]!=null)
			return "mainpage";
		return "logfirst";
	}
	
	@RequestMapping(value="/tochangeCardPin", method=RequestMethod.GET)
	public String tochangeCardPin(HttpSession session){
		if (session.getAttribute("admin")!=null)
			return "changeCardPin";
		return "logfirst";
	}
}
