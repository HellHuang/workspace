package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.study.dao.BillDao;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransferDao;
import com.hsbc.study.dao.UserDao;
import com.hsbc.study.entity.Bill;
import com.hsbc.study.entity.NUser;

@Controller
public class PaymentController {
	final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head> ";
	@RequestMapping(value = "/billPayConfirm", method = RequestMethod.POST)
	public String userPaymentSelf(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws IOException{
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			NewUserDao userdao=new NewUserDao();
			String userId=nuser.getUserIdNumber();
			List cardList=userdao.getUserCard(userId);
			
			String userName=nuser.getUserName();

			String billNumber=request.getParameter("billNumber");
			
			
			String billItem = null;
			String billPeriod = null;
			String billAmount = null;
			String billUserName=null;
			BillDao billDao=new BillDao();
			ArrayList bill=billDao.BillByNumber(billNumber);
			if(bill.size()>0){
				Bill temp=(Bill)bill.get(0);
				billItem=temp.getBillItem();
				billPeriod=temp.getBillPeriod();
				billAmount=temp.getBillAmount();
				billUserName=temp.getBillUserName();
				model.addAttribute("billNumber", billNumber);
				model.addAttribute("billItem", billItem);
				model.addAttribute("billPeriod", billPeriod);
				model.addAttribute("billAmount", billAmount);
				model.addAttribute("billUserName", billUserName);
				model.addAttribute("cardList", cardList);
				model.addAttribute("message", "Hi "+userName);
				return "userPaymentConfirm";
			}
			if(bill.size()==0){
				PrintWriter out=response.getWriter();					
				out.print(html);// is not locked???
				out.print("<body><script>bootbox.alert('<br><B>This bill does not exist or has been paid.<br>',function (){window.history.back();})</script></body>");
				out.close();
			}
			
		}
		return "userLoginFirst";
	}
	@RequestMapping(value = "/getCardBalance", method = RequestMethod.POST)
	public void getCardBalance(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws IOException{
		PrintWriter out=response.getWriter();
		String cardNumber=request.getParameter("cardNumber");
		NewUserDao user=new NewUserDao();
		String balance=user.getCardBalance(cardNumber);
		double b=Double.parseDouble(balance);
		String b2=new DecimalFormat(" ,##0.00").format(b);

		out.print(b2);
		
	}
	@RequestMapping(value = "/billIsMatch", method = RequestMethod.GET)
	public void isMatch(HttpServletRequest request, HttpServletResponse response){
		String billNumber=request.getParameter("billNumber");
		String billUserName=request.getParameter("billUserName");
		BillDao billDao=new BillDao();
		
		boolean isMatch=billDao.isMatch(billNumber, billUserName);
		try {
			PrintWriter out=response.getWriter();
			if (isMatch){
				out.print("match");
			}
			else{
				out.print("notMatch");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value = "/billPay", method = RequestMethod.POST)
	public void billPay(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws IOException{
		
		NUser nuser=(NUser)session.getAttribute("nuser");
		NewUserDao userdao=new NewUserDao();
		String userId=nuser.getUserIdNumber();
	
		
		PrintWriter out=response.getWriter();
		String cardNumber=request.getParameter("cardNumber");
		String cardPin=request.getParameter("cardPin");
		String amount=request.getParameter("amount");
		String billNumber=request.getParameter("billNumber");
		
		BillDao billDao=new BillDao();
	
		//boolean isMatch=billDao.isMatch(billNumber, billUserName);
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		
		UserDao user=new UserDao();
		NewUserDao newuser=new NewUserDao();
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		boolean rightCardPin=user.cardPin(cardNumber, cardPin);
		boolean shouldLock=transfer.shouldLock(cardNumber);
		boolean isFrozen=newuser.isFrozen(cardNumber);
		String Sbalance=newuser.getCardBalance(cardNumber);
		double balance=Double.parseDouble(Sbalance);
		double rest=balance-Double.parseDouble(amount);
		
		ArrayList bill=billDao.BillByNumber(billNumber);
		String billType=null;
		if(bill.size()>0){
			Bill temp=(Bill)bill.get(0);
			billType=temp.getBillItem();
		}
			if(isFrozen){
				out.print("frozen");
			}
			else if(isLocked){
				out.print("locked");
				//your card is locked,you are not allowed to this operation
			}
			else{
				if(rightCardPin){
					if(rest>=0){
						transfer.deleteJournal(cardNumber);
						
						String result=billDao.billPay(cardNumber, userId, amount, billNumber,billType);
						if(result.equals("success")){
							out.print("success");
						}
						else{
							out.println("unkown");
						}
					}
					else{
						out.print("nobalance");
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
		
		
		
		
		
	}
}
