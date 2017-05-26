package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransferDao;
import com.hsbc.study.dao.UserDao;
import com.hsbc.study.dao.UserDepositDao;
import com.hsbc.study.entity.NUser;

@Controller
public class UserDepositController {
	final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
		    "<link href='./static/css/templatemo-style.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head> ";
	@RequestMapping(value = "/userDepositConfirm", method = RequestMethod.POST)
	public String CheckUser(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		String cardNumber=request.getParameter("cardNumber");
		String term=request.getParameter("term");
		double amount=Double.parseDouble(request.getParameter("amount"));
		Float time=null;
		Float interestRate = null;
		String maturityDate=null;
		Float returnRate=null;
		
		NewUserDao user=new NewUserDao();
		String Sbalance=user.getCardBalance(cardNumber);
		double B1=Double.parseDouble(Sbalance);
		String B2=new DecimalFormat(" ,###.00").format(B1);
		model.addAttribute("balance",B2);
		
		SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc =new GregorianCalendar();
		Date today=new Date();
		gc.setTime(today);
		
		String balance=user.getCardBalance(cardNumber);
		double b=Double.parseDouble(balance);
		System.out.println(b);
		boolean isFronze=user.isFrozen(cardNumber);
		
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		if(!isFronze&&!isLocked){
			if(b<amount){
				PrintWriter out=response.getWriter();
				out.println("");
				out.print(html);
				out.print("<body><script>bootbox.alert('<br><B>Sorry, you do not have enough money<br><br>',function(){window.history.back()})</script></body>");				
				out.close();
			}
			else{
				model.addAttribute("cardNumber", cardNumber);
				model.addAttribute("term", term);
				System.out.println("term2"+term);
				String b2=new DecimalFormat(" ,###.00").format(amount);
				model.addAttribute("amount1", b2);
				model.addAttribute("amount2", amount);
				if(term.equals("3 months")){
					interestRate=(float) 1.35;
					gc.add((GregorianCalendar.MONTH), +3);  
					time=(float) 0.25;
				}
				else if(term.equals("6 months")){
					interestRate=(float) 1.55;
					gc.add((GregorianCalendar.MONTH), +6);
					time=(float) 0.5;
				}
				else if(term.equals("1 year")){
					interestRate=(float) 1.75;
					gc.add((GregorianCalendar.YEAR), +1);
					time=(float) 1;
				}
				else if(term.equals("2 years")){
					interestRate=(float) 2.25;
					gc.add((GregorianCalendar.YEAR), +2);
					time=(float) 2;
				}
				else if(term.equals("3 years")){
					interestRate=(float) 2.75;	
					gc.add((GregorianCalendar.YEAR), +3);
					time=(float) 3;
				}
				else if(term.equals("5 years")){
					interestRate=(float) 2.75;
					gc.add((GregorianCalendar.YEAR), +5);
					time=(float) 5;
				}
				else{
					
				}
				model.addAttribute("time", time);
				model.addAttribute("interestRate", interestRate);
				maturityDate=sf.format(gc.getTime());
				model.addAttribute("maturityDate", maturityDate);
				Float total=(float) (amount *time*interestRate*0.01);
				returnRate=(float) ((double)total/(amount));
				System.out.println("int"+returnRate);
				NumberFormat nf   =   NumberFormat.getPercentInstance();  //设置百分数格式
				nf.setMinimumFractionDigits( 2 );//设置小数点后最小几位
				model.addAttribute("returnRate", nf.format(returnRate));
			}
			
			return "userDepositConfirm";  
		}
		else if(isFronze){
			PrintWriter out=response.getWriter();
			out.println("");
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Your card is frozen, cannot do this operation.<br><br>',function(){window.history.back()})</script></body>");				
			out.close();
		}
		else{
			PrintWriter out=response.getWriter();
			out.println("");
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Your card is locked, cannot do this operation.<br><br>',function(){window.history.back()})</script></body>");				
			out.close();
		}
		return null;
	}
	
	@RequestMapping(value = "/userDepositConfirmCheck", method = RequestMethod.POST)
	public void userDepositConfirmCheck(HttpServletRequest request, HttpServletResponse response,Model model,HttpSession session) throws IOException{
		String cardNumber=request.getParameter("cardNumber");
		String term=request.getParameter("term");
		System.out.println("term"+term);
		String amount=request.getParameter("amount");
		String interestRate=request.getParameter("interestRate");
		String cardPin=request.getParameter("cardpin");
		
		UserDepositDao dao=new UserDepositDao();
		NUser nuser=(NUser) session.getAttribute("nuser");
		String userId=nuser.getUserIdNumber();
		
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		
		UserDao user=new UserDao();
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		boolean rightCardPin=user.cardPin(cardNumber, cardPin);
		boolean shouldLock=transfer.shouldLock(cardNumber);
		PrintWriter out=response.getWriter();
		if(isLocked){
			out.print("locked");
			//your card is locked,you are not allowed to this operation
		}
		else{
			if(rightCardPin){
				transfer.deleteJournal(cardNumber);
				
				String depositResult=dao.timeDeposit(cardNumber, userId, term, interestRate, amount);
				if(depositResult.equals("success")){
					out.print("right");
				}
				else{
					out.println("unkown");
				}
				
				
			}
			else{
				if(shouldLock){
					transfer.lock(cardNumber);
					out.print("shouldLocked");
				}
				else{
					transfer.setJournal(sdate, cardNumber);
					out.print("wrong");
				}
				
			}
		}
		
		out.close();
		
		
	}
	@RequestMapping(value = "/userTermEnquiry1", method = RequestMethod.GET)
	public String userTermEnquiry1(HttpServletRequest request, HttpServletResponse response,Model model,HttpSession session) throws IOException{
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			NewUserDao user=new NewUserDao();
			NUser nuser=(NUser)session.getAttribute("nuser");
			String userId=nuser.getUserIdNumber();
			List cardList=user.selectUserCard(userId);
			model.addAttribute("cardList",cardList);
			return "userTermEnquiry1";
			
		}
	}
	@RequestMapping(value = "/userTermEnquiry", method = RequestMethod.POST)
	public String userTermEnquiry(HttpServletRequest request, HttpServletResponse response,Model model,HttpSession session) throws IOException{
		if(session.getAttribute("nuser")!=null){
			
			String cardNumber=request.getParameter("cardNumber");
			UserDepositDao dao=new UserDepositDao();
			ArrayList result=dao.timeDepositEnquiryByCard(cardNumber);
			model.addAttribute("result",result);
			return "userTermEnquiry";
		}
		
		
		return "userLoginFirst";
	}
}
