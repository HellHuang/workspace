package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hsbc.study.dao.DBhelper;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransferDao;
import com.hsbc.study.dao.WithdrawalDao;

@Controller
public class WithdrawalController{
	final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head> ";
	@RequestMapping(value="/withdrawal", method = RequestMethod.POST)
	public ModelAndView Withdraw(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String cardNumber=request.getParameter("cardnumber");
		String type=request.getParameter("select");
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		WithdrawalDao withdrawalDao=new WithdrawalDao();
		Map<String ,Object> model=new HashMap<String,Object>();
		String judge1=withdrawalDao.checkCardnumber(cardNumber,amount);
		String judge3=withdrawalDao.checkUsercard(cardNumber, amount);
		
		NewUserDao nuser=new NewUserDao();
		boolean isFronze=nuser.isFrozen(cardNumber);
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		
		if(!isFronze&&!isLocked){
			if(type.equals("Ordinary withdrawal")){
				if(judge1.equals("card and balance")){
					if(amount>=50000){
						model.put("cardNumber", cardNumber);
						model.put("amount", amount);
						model.put("action", "WithdrawalCode");
						return new ModelAndView("InputCode",model);
					}
					else{
						String judge2=withdrawalDao.Withdrawal(cardNumber, amount);
						if(judge2.equals("successful")){
							withdrawalDao.setTransaction( amount, cardNumber);
							try {
								PrintWriter out=response.getWriter();					
								out.print(html);
								out.print("<body><script>bootbox.alert('<br><B>Your operation is successful.',function (){window.location.href='tomainpage';})</script></body>");
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else{
							model.put("message", "Failed.");
							model.put("link", "towithdrawal");
							return new ModelAndView("cardNotFound",model);
						}
					}
				}
				else if(judge1.equals("card no balance")){
					model.put("message", "Balance is not enough.");
					model.put("link", "towithdrawal");
					return new ModelAndView("cardNotFound",model);
				}
				else{
					model.put("message", "Card not Found.");
					model.put("link", "towithdrawal");
					return new ModelAndView("cardNotFound",model);
				}
				return null;
			}
			else{
				System.out.println(judge3);
				if(judge3.equals("card and balance")){
					if(amount>=50000){
						model.put("cardNumber", cardNumber);
						model.put("amount", amount);
						model.put("action", "userWithdrawalCode");
						return new ModelAndView("InputCode",model);
					}
					else{
						String judge4=withdrawalDao.userWithdrawal(cardNumber, amount);
						if(judge4.equals("successful")){
							try {
								PrintWriter out=response.getWriter();					
								out.print(html);
								out.print("<body><script>bootbox.alert('<br><B>Your operation is successful.',function (){window.location.href='tomainpage';})</script></body>");
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else{
							model.put("message", "Failed.");
							model.put("link", "towithdrawal");
							return new ModelAndView("cardNotFound",model);
						}
					}
				}
				else if(judge3.equals("card no balance")){
					model.put("message", "Balance is not enough.");
					model.put("link", "towithdrawal");
					return new ModelAndView("cardNotFound",model);
				}
				else{
					model.put("message", "Deposit number not Found.");
					model.put("link", "towithdrawal");
					return new ModelAndView("cardNotFound",model);
				}
			}
		}
		else if(isFronze){
			model.put("message", "The card is frozen, you cannot do this operation.");
			model.put("link", "towithdrawal");
			return new ModelAndView("cardNotFound",model);
		}
		else{
			model.put("message", "The card is locked, you cannot do this operation.");
			model.put("link", "towithdrawal");
			return new ModelAndView("cardNotFound",model);
		}
		return null;
	}
	
	@RequestMapping(value="/WithdrawalCode",method = RequestMethod.POST)
	public ModelAndView ModelAndView(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Connection con = DBhelper.connect();
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		WithdrawalDao withdrawalDao=new WithdrawalDao();
		String sql="select * from hsbc.authcode";
		String cardNumber=request.getParameter("cardNumber");
		Double amount=Double.parseDouble(request.getParameter("amount"));
		String code=request.getParameter("code");
		String authcode=null;
		Map<String ,Object> model=new HashMap<String,Object>();
		
		try{
			stmt1 = con.prepareStatement(sql);
			ResultSet rs=stmt1.executeQuery();
			while(rs.next()){
				authcode=rs.getString("authcode");
			}
			if(authcode.equals(code)){
				String judge2=withdrawalDao.Withdrawal(cardNumber, amount);
				if(judge2.equals("successful")){
					withdrawalDao.setTransaction( amount, cardNumber);
					try {
						PrintWriter out=response.getWriter();
						out.print(html);
						out.print("<body><script>bootbox.alert('<br><B>Your operation is successful.',function(){window.location.href='tomainpage'})</script></body>");
						//out.print("<script>window.location.href='tomainpage'</script>");
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					model.put("message", "Failed.");
					model.put("link", "towithdrawal");
					return new ModelAndView("cardNotFound",model);
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>Wrong code. Please try again.',function(){window.history.back()})</script></body> ");
					//out.print("<script>window.history.back()</script>");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			try{
				DBhelper.closePreparedStatement(stmt1);
			}catch(Exception e){
				e.getStackTrace();
			}
			try{
				DBhelper.closePreparedStatement(stmt2);
			}catch(Exception e){
				e.getStackTrace();
			}
			try{
				DBhelper.closeConneciton(con);
				}catch(Exception e){
					e.getStackTrace();
				}	
		}
		return null;
	}
	
	@RequestMapping(value="/userWithdrawalCode",method = RequestMethod.POST)
	public ModelAndView userWith(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Connection con = DBhelper.connect();
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		WithdrawalDao withdrawalDao=new WithdrawalDao();
		String sql="select * from hsbc.authcode";
		String cardNumber=request.getParameter("cardNumber");
		Double amount=Double.parseDouble(request.getParameter("amount"));
		String code=request.getParameter("code");
		String authcode=null;
		Map<String ,Object> model=new HashMap<String,Object>();
		
		try{
			stmt1 = con.prepareStatement(sql);
			ResultSet rs=stmt1.executeQuery();
			while(rs.next()){
				authcode=rs.getString("authcode");
			}
			if(authcode.equals(code)){				
				String judge2=withdrawalDao.userWithdrawal(cardNumber, amount);
				if(judge2.equals("successful")){
					try {
						PrintWriter out=response.getWriter();
						out.print(html);
						out.print("<body><script>bootbox.alert('<br><B>Your operation is successful.',function(){window.location.href='tomainpage'})</script></body>");
						//out.print("<script>window.location.href='tomainpage'</script>");
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					model.put("message", "Failed.");
					model.put("link", "towithdrawal");
					return new ModelAndView("cardNotFound",model);
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>Wrong code. Please try again.',function(){window.history.back()})</script></body> ");
					//out.print("<script>window.history.back()</script>");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			try{
				DBhelper.closePreparedStatement(stmt1);
			}catch(Exception e){
				e.getStackTrace();
			}
			try{
				DBhelper.closePreparedStatement(stmt2);
			}catch(Exception e){
				e.getStackTrace();
			}
			try{
				DBhelper.closeConneciton(con);
				}catch(Exception e){
					e.getStackTrace();
				}	
		}
		return null;
	}
}