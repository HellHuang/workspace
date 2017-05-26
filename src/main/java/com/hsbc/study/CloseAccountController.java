package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hsbc.study.dao.CloseAccountDao;
import com.hsbc.study.dao.DBhelper;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransferDao;

@Controller
public class CloseAccountController{
	final static String html=
			"<html><head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
			"<meta http-equiv='Content-Type' content='text/html; charset=gb2312'/>"+
			"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head></html> ";
	
	@RequestMapping(value="/closeCard", method = RequestMethod.POST)
	public ModelAndView CloseAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException{		
		String cardNumber=request.getParameter("cardNumber");
		Double balance=Double.parseDouble(request.getParameter("balance").replace(",", ""));
		String balance2=new DecimalFormat(" ,##0.00").format(balance);
		CloseAccountDao close=new CloseAccountDao();
		String judgeBalance=close.Close(cardNumber, balance);
		Map<String ,Object> model=new HashMap<String,Object>();
		
		NewUserDao nuser=new NewUserDao();
		boolean isFronze=nuser.isFrozen(cardNumber);
		boolean haveCurrency=nuser.haveCurrency(cardNumber);
		String haveFund=nuser.havefund(cardNumber);
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		
		if(!isFronze){
			if(isLocked){
				try{
					PrintWriter out=response.getWriter();
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>The card is locked, cannot be closed.',function (){window.history.back();})</script></body>");
					out.close();
				}catch(IOException e){
					e.getStackTrace();
				}
			}
			else if(judgeBalance.equals("cannot be closed")){
				try{
					PrintWriter out=response.getWriter();
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>The balance of this account is CNY "+balance2+", cannot be closed.',function (){window.history.back();})</script></body>");
					out.close();
				}catch(IOException e){
					e.getStackTrace();
				}
			}
			else if(haveCurrency){
				String[] currencyInfo=nuser.cardCurrencyFu(cardNumber);
				String info="";
				for(int i=0;i<currencyInfo.length;i++){
					info=info+currencyInfo[i];
				}
				try{
					PrintWriter out=response.getWriter();
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>The card has foreign currency: "+info+", cannot be closed.',function (){window.history.back();})</script></body>");
					out.close();
				}catch(IOException e){
					e.getStackTrace();
				}
			}
			else if(haveFund!=""){
				try{
					PrintWriter out=response.getWriter();
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>The card has fund: "+haveFund+", cannot be closed.',function (){window.history.back();})</script></body>");
					out.close();
				}catch(IOException e){
					e.getStackTrace();
				}
			}
			else{
				model.put("cardNumber", cardNumber);
				model.put("action", "CloseInputCode");
				return new ModelAndView("InputCode",model);
				/*String closeCard=close.CloseCard(cardNumber);
				if(closeCard.equals("close successfully")){
					model.put("message","Close successfully." );
					model.put("link","toinquire");
					return new ModelAndView("cardNotFound",model);
				}
				else{			
					try{
						PrintWriter out=response.getWriter();
						out.print(html);
						out.print("<body><script>bootbox.alert('Your operation is failed.',function (){window.history.back()';})</script></body>");
						out.close();
					} catch (IOException e){
						e.getStackTrace();
					}
				}*/
			}
			return null;
		}
		else{
			try{
				PrintWriter out=response.getWriter();
				out.print(html);
				out.print("<body><script>bootbox.alert('<br><B>The card is frozen, cannot be closed.',function (){window.history.back();})</script></body>");
				out.close();
			}catch(IOException e){
				e.getStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value="/CloseInputCode", method = RequestMethod.POST)
	public ModelAndView CloseAccountCodeController(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		Connection con = DBhelper.connect();
		PreparedStatement stmt = null;
		String sql="select * from hsbc.authcode";
		String cardNumber=request.getParameter("cardNumber");
		Map<String ,Object> model=new HashMap<String,Object>();
		CloseAccountDao close=new CloseAccountDao();
		String code=request.getParameter("code");
		String authcode=null;
		
		try{
			stmt = con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				authcode=rs.getString("authcode");
			}
			if(authcode.equals(code)){
				String closeCard=close.CloseCard(cardNumber);
				if(closeCard.equals("close successfully")){
					model.put("message","Close successfully." );
					model.put("link","toinquire");
					return new ModelAndView("cardNotFound",model);
				}
				else{
					model.put("message","Close failed." );
					model.put("link","toinquire");
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
				DBhelper.closePreparedStatement(stmt);
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
