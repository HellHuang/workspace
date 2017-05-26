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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hsbc.study.dao.DBhelper;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.UnlockDao;

@Controller
public class UnlockController {
	final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head> ";
	
	@RequestMapping(value="/unLock", method = RequestMethod.POST)
	public ModelAndView unLock(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String cardNumber=request.getParameter("cardNumber");
		String status=request.getParameter("status");
		Map<String ,Object> model=new HashMap<String,Object>();
		
		if(status.equals("Locked")){
			model.put("cardNumber", cardNumber);
			model.put("action", "UnlockInputCode");
			return new ModelAndView("InputCode",model);
		}
		else{
			try {
				PrintWriter out=response.getWriter();					
				out.print(html);
				out.print("<body><script>bootbox.alert('<br><B>The status of this card is not locked. It does not need this operation.',function (){window.history.back();})</script></body>");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value="/UnlockInputCode", method = RequestMethod.POST)
	public ModelAndView unLockStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		Connection con = DBhelper.connect();
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		String sql="select * from hsbc.authcode";
		String cardNumber=request.getParameter("cardNumber");
		String code=request.getParameter("code");
		String authcode=null;
		Map<String ,Object> model=new HashMap<String,Object>();
		UnlockDao unlockDao=new UnlockDao();
		
		try{
			stmt1 = con.prepareStatement(sql);
			ResultSet rs=stmt1.executeQuery();
			while(rs.next()){
				authcode=rs.getString("authcode");
			}
			if(authcode.equals(code)){
				String judge=unlockDao.unlock(cardNumber);
				if(judge.equals("success")){
					try {
						PrintWriter out=response.getWriter();
						out.print(html);
						out.print("<body><script>bootbox.alert('<br><B>Your card status is normal now.',function(){window.location.href='toinquire'})</script></body>");
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					System.out.println(judge);
					model.put("message", "Operation Failed.");
					model.put("link", "toinquire");
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
	
	@RequestMapping(value="/frozen", method = RequestMethod.POST)
	public String frozen (HttpServletRequest request, HttpServletResponse response,Model model){
		UnlockDao dao=new UnlockDao();
		String cardNumber=request.getParameter("cardNumber");
		System.out.println("eeee44");
		String result=dao.frozen(cardNumber);
		System.out.println("eeee"+result);
		if(result.equals("has been Frozen")){
			model.addAttribute("message", "This card has been frozen.");
			model.addAttribute("link", "toinquire");
			return "cardNotFound";
		}
		else if(result.equals("has Frozen")){
			model.addAttribute("message", "Successful, this card is frozen now.");
			model.addAttribute("link", "toinquire");
			return "cardNotFound";
		}
		else if(result.equals("has Locked")){
			model.addAttribute("message", "This card has been locked,so it can not be frozen.");
			model.addAttribute("link", "toinquire");
			return "cardNotFound";
		}
		model.addAttribute("message", "Unknow error.");
		model.addAttribute("link", "toinquire");
		return "cardNotFound";
	}
	
	@RequestMapping(value="/Unfreeze", method = RequestMethod.POST)
	public String unfreeze(HttpServletRequest request, HttpServletResponse response,Model model){
		String cardNumber=request.getParameter("cardNumber");
		String status=request.getParameter("status");
		UnlockDao dao=new UnlockDao();
		
		if(!status.equals("Frozen")){
			model.addAttribute("message", "The status of this card is not frozen, you cannot do this operation.");
			model.addAttribute("link", "toinquire");
			return "cardNotFound";
		}
		else{
			String check=dao.usfreeze(cardNumber);
			if(check.equals("success")){
				model.addAttribute("message", "Successful, this card is normal now.");
				model.addAttribute("link", "toinquire");
				return "cardNotFound";
			}
			else{
				model.addAttribute("message", "Your operation is failed, please try again.");
				model.addAttribute("link", "toinquire");
				return "cardNotFound";
			}
		}
	}
}
