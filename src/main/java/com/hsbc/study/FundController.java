package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.study.dao.FundDao;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransferDao;
import com.hsbc.study.dao.UserDao;
import com.hsbc.study.entity.Fund;
import com.hsbc.study.entity.NUser;

@Controller
public class FundController {
	
	@RequestMapping(value = "/testdata", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code=request.getParameter("code");
		//String  code="000003";
		System.out.println(code);
		FundDao fundDao=new FundDao();
		String info=fundDao.getZichan(code);
		System.out.println(info);
		Pattern p=Pattern.compile((".*(%)(.*)债券"));
		Matcher m=p.matcher(info);  
		String n1="";
		PrintWriter pw=response.getWriter();
		 while(m.find())
	        { 
	          System.out.println("getttt"+m.group(2)); 
	          n1=m.group(2).replace(")", "");
	        }
		if(n1.length()==0){
			n1="0.01";
		}
		
		String[] a=info.split("(%)");
		
	
		String n2="";
		n2=a[2].replace(")", "");
		if(n2.length()==0){
			n2="0.01";
		}
		System.out.println(n1.length()+"llasdfa");
		System.out.println(n2.length());
		String n3="99.9";
		if(n2.length()>1 &&n1.length()>1){
			n3=(100-(Double.parseDouble(n1)+Double.parseDouble(n2)))+"";
		}
		
		
		pw.print(n1+","+n2+","+n3);
		
	}
	@RequestMapping(value = "/bardata", method = RequestMethod.GET)
	public void bar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code=request.getParameter("code");
		//String  code="000003";
		System.out.println(code);
		FundDao fundDao=new FundDao();
		String info=fundDao.codeNav(code);
		System.out.println(info);
		PrintWriter out =response.getWriter();
		out.print(info);
		
	}
	@RequestMapping(value = "/userFundDetail", method = RequestMethod.POST)
	public String userFundDetailController(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		response.setContentType("text/html");
		request.setCharacterEncoding("gb2312");
	    response.setCharacterEncoding("gb2312");
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			FundDao fund=new FundDao();
			//
		
			
			String code=request.getParameter("code");
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String rate=request.getParameter("rate");
			//String nav=request.getParameter("nav");
			
			String[] detail;
			detail=fund.fundDetail(code);
			String background=detail[1];
			String manager=detail[0];
			String manageRate=detail[2];
			String zichan=fund.getZichan(code);
			String nav=detail[3];
			double Dnav=Double.parseDouble(nav);
			nav=new DecimalFormat(" ,##0.00").format(Dnav);
			
			
			System.out.println(zichan);
			String[] a=zichan.split("(%)");
			a[0]=a[0].trim().replace("股票投资比例", "");
			a[0]=a[0].replace("总资产(元)", "");
			a[0]=a[0].replace("(", "");
			System.out.println(a[0]);
			
			//System.out.println(fund.codeNav(code));
			// 
			
			model.addAttribute("code", code);
			model.addAttribute("name", name);
			model.addAttribute("type", type);
			model.addAttribute("rate", rate);
			model.addAttribute("nav", nav);
			model.addAttribute("total", a[0]);
			
			model.addAttribute("zichan", zichan);
			model.addAttribute("background",background );
			model.addAttribute("manager",manager );
			model.addAttribute("manageRate",manageRate );
			return "userFundDetail";
		}
	}
	
	@RequestMapping(value = "/FundPurchase", method = RequestMethod.POST)
	public void userFundPurchaseController(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		response.setContentType("text/html");
		request.setCharacterEncoding("gb2312");
	    response.setCharacterEncoding("gb2312");
		
		String name=(String)session.getAttribute("name");
		String type=(String)session.getAttribute("type");
		String rate=request.getParameter("rate");
		String code=request.getParameter("code");
		
		FundDao funddao=new FundDao();
		String[] fundd=funddao.fundDetail(code);
		String Snav=fundd[3];
		double nav=Double.parseDouble(Snav);
		String Samount=request.getParameter("amount");
		double amount=Double.parseDouble(Samount);
		String cardpin=request.getParameter("cardpin");
		String cardNumber=request.getParameter("select");
		double cost=nav*amount;
		UserDao user=new UserDao();
		TransferDao transfer=new TransferDao();
		boolean haveBalance=transfer.haveBalance(cardNumber, cost);
		boolean rightPin=user.cardPin(cardNumber, cardpin);
		System.out.println(type+name+code+rate+Samount+nav);
		
		boolean isLocked=transfer.isLocked(cardNumber);
		NewUserDao nuser=new NewUserDao();
		boolean isFronze=nuser.isFrozen(cardNumber);
		boolean shouldLock=transfer.shouldLock(cardNumber);
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		
		if(cardNumber==""){
			try {
				PrintWriter out=response.getWriter();
				out.print("noCardNumber");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(isFronze){
			try {
				PrintWriter out=response.getWriter();
				out.print("frozen");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(isLocked){
			try {
				PrintWriter out=response.getWriter();
				out.print("locked");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!rightPin){
			transfer.setJournal(sdate, cardNumber);
			if(shouldLock){
				transfer.lock(cardNumber);
				try {
					PrintWriter out=response.getWriter();
					out.print("shouldLocked");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print("wrongPin");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else if(!haveBalance){
			transfer.deleteJournal(cardNumber);
			try {
				PrintWriter out=response.getWriter();
				out.print("noBalance");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			transfer.deleteJournal(cardNumber);
			FundDao fund=new FundDao();
			boolean check=fund.buyFund(cardNumber, code, Samount, name, type, rate,cost);
			if(check){
				try {
					PrintWriter out=response.getWriter();
					out.print("success");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print("failed");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value = "/FundManage", method = RequestMethod.POST)
	public String userFundManageController(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			NewUserDao user=new NewUserDao();
			NUser nuser=(NUser)session.getAttribute("nuser");
			String userId=nuser.getUserIdNumber();
			List cardList=user.selectUserCard(userId);
			System.out.println(cardList.size());
			ArrayList fundCard=new ArrayList();
			int i=cardList.size();
			
			for(int j=0;j<i;j++){
				String card=(String)cardList.get(j);
				System.out.println(card);
				ArrayList fList=user.getFund(card);
				int n=fList.size();
				System.out.println(n);
				for(int m=0;m<n;m++){
					Fund fund=(Fund)fList.get(m);
					System.out.println(fund.toString());
					fundCard.add(fund);
				}
			}
			model.addAttribute("fundCard", fundCard);
			return "userFundManagePage";			
		}
	}
	
	@RequestMapping(value = "/fundRedeem", method = RequestMethod.POST)
	public void userFundRedeemController(HttpSession session,HttpServletRequest request, HttpServletResponse response){
		UserDao user=new UserDao();
		FundDao fund=new FundDao();
		String cardPin=request.getParameter("cardpin");
		String RedeemCard=request.getParameter("RedeemCard");
		String RedeemCode=request.getParameter("RedeemCode");
		String Snumber=request.getParameter("number");
		double number=Double.parseDouble(Snumber);
		String sunAmount=fund.getFundAmount(RedeemCard, RedeemCode);
		double amount=Double.parseDouble(sunAmount);		
		double RedeemTotal1=Double.parseDouble(request.getParameter("RedeemTotal"));	
		double RedeemTotal2=number/amount*RedeemTotal1;
		boolean rightPin=user.cardPin(RedeemCard, cardPin);
		
		NewUserDao nuser=new NewUserDao();
		boolean isFronze=nuser.isFrozen(RedeemCard);
		TransferDao transfer=new TransferDao();
		boolean isLocked=transfer.isLocked(RedeemCard);
		boolean shouldLock=transfer.shouldLock(RedeemCard);
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		
		if(isFronze){
			try {
				PrintWriter out=response.getWriter();
				out.print("frozen");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(isLocked){
			try {
				PrintWriter out=response.getWriter();
				out.print("locked");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!rightPin){
			transfer.setJournal(sdate, RedeemCard);
			if(shouldLock){
				transfer.lock(RedeemCard);
				try {
					PrintWriter out=response.getWriter();
					out.print("shouldLocked");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print("wrongPin");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			transfer.deleteJournal(RedeemCard);
			String redeemS=fund.redeemFund(RedeemCard, RedeemCode, RedeemTotal2,number);
			if(redeemS.equals("success")){
				try {
					PrintWriter out=response.getWriter();
					out.print("success");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(redeemS.equals("failed")){
				try {
					PrintWriter out=response.getWriter();
					out.print("failed");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print("error");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
