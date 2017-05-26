package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hsbc.study.dao.AdminDao;
import com.hsbc.study.dao.UserDao;
import com.hsbc.study.entity.Admin;
import com.hsbc.study.entity.User;

@Controller

public  class LoginController    {
	
	private AdminDao adminDao;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	

	public void login(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws  IOException{
		//String name=request.getParameter("name");
		String name=request.getParameter("login_username");
		//String password=request.getParameter("password");
		String password=request.getParameter("login_password");
		PrintWriter out=response.getWriter();;
		System.out.println(name+":"+password);
		//User user=getUser(name,password);
		adminDao=new AdminDao();
		PrintWriter pw=response.getWriter();
		Admin admin;
		try {
			admin = adminDao.findAdmin(name, password);
			if(admin!=null){		
				session.setAttribute("admin", name);
				model.addAttribute("name", name);
				//model.put("name", name);
				//return new ModelAndView("mainpage",model);
				//MailUtil.send("601567611@qq.com", "heleosfsfsafsafsfsf");
				pw.print("1");
				//return new ModelAndView("mainpage");
			}
			else{
				
					
				
//				//out.print("<script>alert('Admin name or password is not correct!')</script>");
//			
//				out.println("<script type='text/javascript' src='statc/js/jquery-1.11.2.min.js'></script><script src='static/js/sweetalert.min.js'></script><link rel='stylesheet' type='text/css' href='static/css/sweetalert.css'>");
//				out.println("<body><script> swal('Admin name or password is not correct!'); </script> ");
//				out.flush();
//				out.print("<script> window.history.back();</script>");
//				
//	            out.close();	
				/*String html="<script type='text/javascript' src='static/js/jquery-1.11.2.min.js'></script>"+
						"<script src='static/js/sweetalert.min.js'></script>"+
						"<link rel='stylesheet' type='text/css' href='static/css/sweetalert.css'></head><body><script>"+						
						"window.onload=test();function test(){"+
							"swal({  "+
								"title: 'tips',  "+ 
								"text: 'sucessful:,  "+ 
								"type: '',  "+
								"showCancelButton: true,  "+ 
								"closeOnConfirm: false,"+   
								"animation: 'slide-from-top',  " +
								//inputPlaceholder: "填点东西到这里面吧" 
							"}, function(inputValue){ "+  
								"if (inputValue === false) return false;"+      
								"if (inputValue === '') { swal.showInputError('');  "  +"" +    
									"return false "+  
								"} "+     
								//swal("棒极了!", "您填写的是: " + inputValue, "success"); 
								"window.location.href='todeposit';"+
							"});"+
							
						"}";*/
//					out.println(html);
//					out.flush();
//					out.close();
				pw.print("2");
				model.addAttribute("message", "Admin name or password is not correct");
				model.addAttribute("link", "tomainpage");
				//return new ModelAndView("loginNew");
				}
				
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//out=response.getWriter();
			out.print("3");
			//out.print("<script>alert('Admin name or password is not correct!')</script>");
			//out.print("<script>window.location.href='tologinpage'</script>");
			e1.printStackTrace();
			out.flush();
            out.close();
			//return new ModelAndView("loginNew");
		}
		//Map<String ,Object> model=new HashMap<String,Object>();
		
		
		
		
	}
//	private String successView;
//	private String failView;
//	private UserDao userDao;
//	
//	public String getSuccessView() {
//		return successView;
//	}
//
//	public void setSuccessView(String successView) {
//		this.successView = successView;
//	}
//
//	public String getFailView() {
//		return failView;
//	}
//
//	public void setFailView(String failView) {
//		this.failView = failView;
//	}
//	
//	@Override
//	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String name=request.getParameter("name");
//		String password=request.getParameter("password");
//		System.out.println(name+":"+password);
//		
//		userDao=new UserDao();
//		User user=userDao.findUser(name, password);
//		Map<String ,Object> model=new HashMap<String,Object>();
//		if(user!=null){
//			model.put("name", name);
//			return new ModelAndView("home",model);
//		}
//		else{
//			model.put("error", "wrong");
//			return new ModelAndView("erview",model);
//		}
//	}
//	public User getUser(String name,String password){
//		if(name.equals("fen")&&password.equals("fen")){
//			return new User(name,password);
//		}
//		else{
//			return null;
//		}
//	}



}
