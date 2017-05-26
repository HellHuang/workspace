package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.UserDao;
import com.hsbc.study.entity.User;

@Controller
public class InquireController {
	@RequestMapping(value = "/inquireUser", method = RequestMethod.POST)
	public String inquiry(HttpServletRequest request, HttpServletResponse response,Model model) throws SQLException{
		String type=request.getParameter("type");
		String userId=request.getParameter("inqUser");
		System.out.println("inquery"+userId);
		UserDao dao=new UserDao();
		String judge=dao.userIsExist(userId);
		
		if(judge.equals("user&card")){
			if(type.equals("1")){
				if(dao.inquireUser(userId)!=null){
					ArrayList rs=dao.inquireUser(userId);
					model.addAttribute("arr",rs);
					return "status";
				}
				else{
					model.addAttribute("message", "Unknow error.");
					model.addAttribute("link", "toinquire");
					return "cardNotFound";
				}
			}
			if(type.equals("2")){
				NewUserDao dao2=new NewUserDao();
				String result=dao2.getNUserStatus(userId);
				model.addAttribute("message", result);
				model.addAttribute("link", "toinquire");
				return "cardNotFound";
			}
			
		}
		else if(judge.equals("user%nocard")){
			model.addAttribute("message", "The user has no card.");
			model.addAttribute("link", "toinquire");
			return "cardNotFound";
		}
		else if(judge.equals("nouser")){
			model.addAttribute("message", "The user does not exist.");
			model.addAttribute("link", "toinquire");
			return "cardNotFound";
		}
		else{
			model.addAttribute("message", "Unknow error.");
			model.addAttribute("link", "toinquire");
			return "cardNotFound";
		}
		return "cardNotFound";
	}
	//6.3
	@RequestMapping(value = "/inquireOnline", method = RequestMethod.POST)
	public void inquiryOnline(HttpServletRequest request, HttpServletResponse response,Model model) throws SQLException{
		String idNumber=request.getParameter("inqUser");
		NewUserDao dao=new NewUserDao();
		String result=dao.getNUserStatus(idNumber);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
