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
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hsbc.study.dao.DBhelper;
import com.hsbc.study.dao.DepositDao;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransferDao;
import com.hsbc.study.dao.WithdrawalDao;

@Controller
public class DepositController {
	
	@RequestMapping(value = "/checkCardNumber", method = RequestMethod.POST)
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
	
	/*@RequestMapping(value = "/depositC", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		String cardNumber=request.getParameter("cardNumber");
		String amount=request.getParameter("amount");
		System.out.println(cardNumber+" "+amount);
		DepositDao dao=new DepositDao();
		String result;
		result = dao.deposit(cardNumber, amount);
		PrintWriter out;
		//response.setContentType("text/html;character=utf-8");
		 if(result.equals("update success")){
			out=response.getWriter();		
			out.print("update success");	
			}
		 else{
			out=response.getWriter();
			out.print("unknown");	
		}	
	}*/
	
	final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head> ";
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public ModelAndView Deposit (HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String cardNumber=request.getParameter("cardNumber");
		String amount=request.getParameter("amount");
		double Damount=Double.parseDouble(amount);
		Map<String ,Object> model=new HashMap<String,Object>();
		DepositDao depositdao=new DepositDao();
		boolean card=depositdao.checkCardnumber(cardNumber);
		
		NewUserDao nuser=new NewUserDao();
		boolean isFronze=nuser.isFrozen(cardNumber);
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		
		if(!isFronze&&!isLocked){
			if(card!=true){
				model.put("message", "Card not found.");
				model.put("link", "todeposit");
				return new ModelAndView("cardNotFound",model);
			}
			else{
				if(Damount<50000){
					String deposit=depositdao.deposit(cardNumber, amount);
					if(deposit.equals("update success")){
						depositdao.setTransaction(Damount, cardNumber);
						try {
							PrintWriter out=response.getWriter();
						
							out.print(html);
							out.print("<body><script>bootbox.alert('<br><B>Your operation is successful.',function (){window.location.href='tomainpage';})</script></body>");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else{
						model.put("message", "Failed.");
						model.put("link", "todeposit");
						return new ModelAndView("cardNotFound",model);
					}
				}
				else{
					model.put("cardNumber", cardNumber);
					model.put("amount", amount);
					model.put("action", "DepositCode");
					return new ModelAndView("InputCode",model);
				}
			}
			return null;
		}
		else if(isFronze){
			model.put("message", "The card is frozen, you cannot do this operation.");
			model.put("link", "todeposit");
			return new ModelAndView("cardNotFound",model);
		}
		else{
			model.put("message", "The card is locked, you cannot do this operation.");
			model.put("link", "todeposit");
			return new ModelAndView("cardNotFound",model);
		}
	}
	
	@RequestMapping(value="/DepositCode",method = RequestMethod.POST)
	public ModelAndView DepositCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Connection con = DBhelper.connect();
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		DepositDao depositDao=new DepositDao();
		String sql="select * from hsbc.authcode";
		String cardNumber=request.getParameter("cardNumber");
		String amount=request.getParameter("amount");
		double Damount=Double.parseDouble(amount);
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
				String judge2=depositDao.deposit(cardNumber, amount);
				if(judge2.equals("update success")){
					depositDao.setTransaction(Damount, cardNumber);
					try {
						PrintWriter out=response.getWriter();
						out.print(html);
						out.print("<body><script>bootbox.alert('<br><B>Your operation is successful.',function(){window.location.href='tomainpage'})</script></body>");
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					model.put("message", "Failed.");
					model.put("link", "todeposit");
					return new ModelAndView("cardNotFound",model);
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>The code you input is incorrect. Please try again.',function(){window.history.back()})</script></body> ");
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
