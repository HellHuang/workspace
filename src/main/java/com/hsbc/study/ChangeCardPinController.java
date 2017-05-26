package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.hsbc.study.dao.ChangeCardPinDao;
import com.hsbc.study.dao.DepositDao;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransferDao;
import com.hsbc.study.dao.UserDao;

@Controller
public class ChangeCardPinController{
	final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head> ";
	@RequestMapping(value="/changePassword", method = RequestMethod.POST)
	public ModelAndView ChangeCardPin(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String authCode=request.getParameter("authcode");
		String cardNumber=request.getParameter("cardNumber");
		String userId=request.getParameter("userid");
		String cardPin=request.getParameter("cardpin");		
		
		ChangeCardPinDao changePassword=new ChangeCardPinDao();
		Map<String ,Object> model=new HashMap<String,Object>();
		String cardanduser=changePassword.CardAndUser(userId, cardNumber);
		
		NewUserDao nuser=new NewUserDao();
		boolean isFronze=nuser.isFrozen(cardNumber);
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		
		if(!isFronze&&!isLocked){
			if(cardanduser.equals("existed")){
				response.setContentType("text/html;character=utf-8");
				if(authCode.equals("")||authCode==null){
					try{
						PrintWriter out=response.getWriter();
						out.print(html);
						out.print("<body><script>bootbox.alert('<br><B>Please input the authentication code.',function(){window.history.back()})</script></body>");
						//out.print("<script>alert('Please input the authentication code.')</script>");
						//out.print("<script>window.history.back()</script>");
						out.close();
					//model.put("message", "Please input the authentication code.");
					//model.put("link", "tochangeCardPin");
					//return new ModelAndView("cardNotFound",model);
					} catch(Exception e){
						e.getStackTrace();
					}
				}
				else if(changePassword.AuthCode(authCode).equals("error")){
					model.put("message", "Unknow Error.");
					model.put("link", "tochangeCardPin");
					return new ModelAndView("cardNotFound",model);
				}
				else if(changePassword.AuthCode(authCode).equals("wrong")){
					/*model.put("message", "Wrong code. Please input again.");
					model.put("link", "tochangeCardPin");
					return new ModelAndView("cardNotFound",model);*/
					try{
						PrintWriter out=response.getWriter();
						out.print(html);
						out.print("<body><script>bootbox.alert('<br><B>The authorization  code is wrong. Please try again.',function(){window.history.back()})</script></body>");
						//out.print("<script>window.history.back()</script>");
						out.close();
					} catch(Exception e){
						e.getStackTrace();
					}
				}
				else if(changePassword.AuthCode(authCode).equals("correct")){
					if(changePassword.Change(cardPin, userId, cardNumber).equals("changed")){
						model.put("message", "This operation is successful. Your card pin has been changed.");
						model.put("link", "tomainpage");
						return new ModelAndView("cardNotFound",model);
					}
					else if(changePassword.Change(cardPin, userId, cardNumber).equals("failed")){
						model.put("message", "Failed. The card pin has not been changed.");
						model.put("link", "tochangeCardPin");
						return new ModelAndView("cardNotFound",model);
					}
					else{
						model.put("message", "Unknow Error.");
						model.put("link", "tochangeCardPin");
						return new ModelAndView("cardNotFound",model);
					}
				}
			}
			else if(cardanduser.equals("card isn't existed")){
				model.put("message", "The card does not exist or is not matched with user.");
				model.put("link", "tochangeCardPin");
				return new ModelAndView("cardNotFound",model);
			}
			else if(cardanduser.equals("the user isn't existed")){
				model.put("message", "The user does not exist.");
				model.put("link", "tochangeCardPin");
				return new ModelAndView("cardNotFound",model);
			}
			else{
				model.put("message", "Unknow Error.");
				model.put("link", "tochangeCardPin");
				return new ModelAndView("cardNotFound",model);
			}
			return null;
		}
		else if(isFronze){
			model.put("message", "The card is frozen, you cannot do this operation.");
			model.put("link", "tochangeCardPin");
			return new ModelAndView("cardNotFound",model);
		}
		else{
			model.put("message", "The card is locked, you cannot do this operation.");
			model.put("link", "tochangeCardPin");
			return new ModelAndView("cardNotFound",model);
		}
	}
	
	@RequestMapping(value = "/hasUserId", method = RequestMethod.POST)
	public void hasUserId(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String userId=request.getParameter("inqUser");
		PrintWriter out=response.getWriter();
		UserDao dao=new UserDao();
		boolean hasUserId=dao.select(userId);
		if(hasUserId==true)
			out.print("has");
		else
			out.print("no");
			
	}
	
	@RequestMapping(value = "CheckCardNumber", method = RequestMethod.POST)
	public void checkCardNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cardNumber=request.getParameter("cardNumber");
		DepositDao dao=new DepositDao();
		boolean hasCard=dao.checkCardnumber(cardNumber);
		PrintWriter out=response.getWriter();
		
		if(hasCard){
			out.print("has");
			
		}
		else{
			out.print("no");
		}
		
	}

	/*@RequestMapping(value = "checkMatch", method = RequestMethod.POST)
	public void checkMatch(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cardNumber=request.getParameter("cardNumber");
		String userId=request.getParameter("userId");
		ChangeCardPinDao change=new ChangeCardPinDao();
		boolean match=change.match(userId, cardNumber);
		PrintWriter out=response.getWriter();
		
		if(match==true){
			out.println("true");
		}
		else{
			out.print("false");
		}
	}*/
}