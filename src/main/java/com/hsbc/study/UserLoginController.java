package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import com.hsbc.study.dao.CurrencyDao;
import com.hsbc.study.dao.FundDao;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.dao.TransactionDao;
import com.hsbc.study.dao.TransferDao;
import com.hsbc.study.dao.UserDao;
import com.hsbc.study.entity.Fund;
import com.hsbc.study.entity.NUser;
import service.EncryptionUtil;
import service.MailUtil;
import com.hsbc.study.entity.userCard;

import net.sf.json.JSONObject;

@Controller
public class UserLoginController {
	
	final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
			"<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
		    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
		    "<script src='./static/js/bootstrap.min.js'></script>"+ 
		    "<script src='./static/js/bootbox.min.js'></script> </head> ";
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String userLogin(HttpSession session){
		//session.invalidate();
		return "userLogin";
	}
	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//return "loginpage";
		session.invalidate();
		return "userLogin";
	}
	@RequestMapping(value = "/userMainpage", method = RequestMethod.GET)
	public String userMainpage(HttpSession session){
		if(session.getAttribute("nuser")!=null){
			return "userMainpage";
		}
		return "userLoginFirst";
	}
	
	/*@RequestMapping(value = "/userDeposit", method = RequestMethod.GET)
	public String userDeposit(HttpSession session,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			NewUserDao userdao=new NewUserDao();
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			ArrayList list=userdao.getUserCard(userId);
			model.addAttribute("list", list);
			model.addAttribute("message", "Hi "+userName);
			
			return "userDeposit";
		}
		return "userLoginFirst";
	}*/
	
	@RequestMapping(value = "/FundPage", method = RequestMethod.GET)
	public String FundPage(HttpSession session,Model model){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			FundDao fundDao=new FundDao();
			ArrayList fundListTotal=fundDao.getAllfund();
			ArrayList fundList=new ArrayList();
						
			for(int i=0;i<10;i++){
				fundList.add(fundListTotal.get(i));
			}
			session.setAttribute("fundCurrentPage", "1");
			model.addAttribute("currentPage", 1);
			model.addAttribute("fundList", fundList);
			return "userFundPage";
		}
	}
	//paging
	@RequestMapping(value = "/FundPaging", method = RequestMethod.GET)
	public String FundPaging(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			FundDao fundDao=new FundDao();
			ArrayList fundListTotal=fundDao.getAllfund();
			int currentPage=Integer.parseInt(request.getParameter("currentPage"));
			ArrayList fundList=new ArrayList();
			int length=fundListTotal.size();
			int pages=length/10;
			int start=((currentPage*10)-10);
			int end=(currentPage*10)-1;
			if(end>length){
				end=length;
			}
			for(int i=start;i<end;i++){
				fundList.add(fundListTotal.get(i));
			}
			session.setAttribute("fundCurrentPage", currentPage+"");
			model.addAttribute("fundList", fundList);
			
			model.addAttribute("currentPage", currentPage);
			return "userFundPage";
		}
	}

	@RequestMapping(value = "/fundSearch", method = RequestMethod.POST)
	public String Fundsearch(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws UnsupportedEncodingException{
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			FundDao fundDao=new FundDao();
			
			response.setContentType("text/html");
			request.setCharacterEncoding("gb2312");
		    response.setCharacterEncoding("gb2312");
		    
			String search=request.getParameter("search");
			ArrayList fundList=fundDao.fundSearch(search);
			System.out.println(search);
			model.addAttribute("fundList", fundList);			
			
			return "userFundPage";
		}
	}

	
	
	@RequestMapping(value = "/userBalancePage", method = RequestMethod.GET)
	public String userBalancePage(HttpSession session){
		if(session.getAttribute("nuser")!=null){
			return "userBalancePage";
		}
		return "userLoginFirst";
	}
	@RequestMapping(value = "/userFundPurchase", method = RequestMethod.POST)
	public String userFundPurchase(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			NewUserDao userdao=new NewUserDao();
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			ArrayList list=userdao.getUserCard(userId);
			
			response.setContentType("text/html");
			request.setCharacterEncoding("gb2312");
		    response.setCharacterEncoding("gb2312");
		    
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String rate=request.getParameter("rate");
			String code=request.getParameter("code");
			String nav=request.getParameter("nav");
			
			System.out.println(type+name+code+rate+nav);
			session.setAttribute("nav", nav);
			session.setAttribute("name", name);
			session.setAttribute("type", type);
			session.setAttribute("rate", rate);
			session.setAttribute("code", code);
			model.addAttribute("nav", nav);
			model.addAttribute("name", name);
			model.addAttribute("type", type);
			model.addAttribute("rate", rate);
			model.addAttribute("code", code);
			model.addAttribute("list", list);
			model.addAttribute("message", "Hi "+userName);
			return "userFundTradingPage";
		}
		return "userLoginFirst";
	}
	
	@RequestMapping(value = "/userFundPurchase", method = RequestMethod.GET)
	public void balance(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response){
		String card=request.getParameter("card");
		if(card!=""){
			NewUserDao user=new NewUserDao();
			String balance=user.getCardBalance(card);
			double b=Double.parseDouble(balance);
			String b2=new DecimalFormat(" ,##0.00").format(b);
			
			try {
				PrintWriter out=response.getWriter();
				out.print(b2);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{}
	}
	
	@RequestMapping(value = "/fundRedeemConfirm", method = RequestMethod.POST)
	public String fundRedeemConfirm(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if(session.getAttribute("nuser")!=null){
			response.setContentType("text/html");
			request.setCharacterEncoding("gb2312");
		    response.setCharacterEncoding("gb2312");
			
			String RedeemCard=request.getParameter("RedeemCard");
			String RedeemIncome=request.getParameter("RedeemIncome");
			String RedeemCode=request.getParameter("RedeemCode");
			String RedeemName=request.getParameter("RedeemName");
			String RedeemTotal=request.getParameter("RedeemTotal");			
			
			model.addAttribute("RedeemCard", RedeemCard);
			model.addAttribute("RedeemIncome", RedeemIncome);
			model.addAttribute("RedeemCode", RedeemCode);
			model.addAttribute("RedeemName", RedeemName);
			model.addAttribute("RedeemTotal", RedeemTotal);
			return "userFundRedeem";
		}
		return "userLoginFirst";
	}
	
	@RequestMapping(value = "/userTransfer", method = RequestMethod.GET)
	public String userTransfer(HttpSession session,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			NewUserDao userdao=new NewUserDao();
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			ArrayList list=userdao.getUserCard(userId);
			model.addAttribute("list", list);
			model.addAttribute("message", "Hi "+userName);
			return "Transfer";
		}
		return "userLoginFirst";
	}
	@RequestMapping(value = "/userRegister", method = RequestMethod.GET)
	public String userRegister(){
		return "userRegister";
	}
	@RequestMapping(value = "/userForgetPass", method = RequestMethod.GET)
	public String userForgetPass(){
		return "userForgetPass";
	}
	@RequestMapping(value = "/userRegisterC", method = RequestMethod.POST)
	public void userRegisterController(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("password");
		String email=request.getParameter("email");
		String userIdNumber=request.getParameter("idNumber");
		NewUserDao user=new NewUserDao();
		PrintWriter out=response.getWriter();
		String result1 =user.select(userIdNumber);
		boolean result2=user.isHaveAccount(userName, userIdNumber);
		
		if(result1.equals("existed")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>This account is existed.',function (){window.location.href='userLogin';})</script></body>");
		}
		else if(result1.equals("Not Activated")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Regisited before, please check your email to activate.',function (){window.location.href='userLogin';})</script></body>");
		}
		else if(result2==false){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Please open an account first.',function (){window.location.href='userLogin';})</script></body>");
		}
		else{
			user.notActived(userName, userPassword, email, userIdNumber);
			
			//userName=EncryptionUtil.encrypt(userName);
			//userPassword=EncryptionUtil.encrypt(userPassword);
			//email=EncryptionUtil.encrypt(email);
			//userIdNumber=EncryptionUtil.encrypt(userIdNumber);
			String title="If you want to activate your account,please click the link \n";
			
			String content=title+"<a href='http://localhost:8080/study/active?";
			//String data="1="+userName+"&2="+userPassword+"&3="+email+"&4="+userIdNumber;
			userIdNumber=EncryptionUtil.encrypt(userIdNumber);
			String data="1="+userIdNumber;
			data=data+"'>please click here</a>";
			content=content+data;
			
			MailUtil.send(email, content);
			
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Please check your email and click the link to activate.',function (){window.location.href='userLogin';})</script></body>");
		}
//		NUser result=user.add(userName, userPassword, email, userIdNumber);
//		if(user!=null){
//			model.addAttribute("message", "congratulations,your ");
//			return "userLogin";
//		}
//		return "userRegister";
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String active(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		String userIdNumber=EncryptionUtil.decrypt(request.getParameter("1"));
		NewUserDao user=new NewUserDao();
		String isExsited =user.select(userIdNumber);
		PrintWriter out=response.getWriter();
		if(isExsited.equals("not existed")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Register Failed.',function (){window.location.href='userLogin';})</script></body>");
		}
		else if(isExsited.equals("existed")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Your account is existed.',function (){window.location.href='userLogin';})</script></body>");			
		}
		else if(isExsited.equals("Not Activated")){
			String  result=user.active(userIdNumber);
			if(result.equals("actived")){
				out.print(html);
				out.print("<body><script>bootbox.alert('<br><B>Congratulations,your registion is successful.',function (){window.location.href='userLogin';})</script></body>");			
			}
			else{
				out.print(html);
				out.print("<body><script>bootbox.alert('<br><B>Sorry, register failed.',function (){window.location.href='userLogin';})</script></body>");			
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/userNameExisted", method = RequestMethod.POST)
	public void userNameExisted(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		String userName=request.getParameter("userName");
		NewUserDao user=new NewUserDao();
		String result=user.userNameExisted(userName);
		PrintWriter pw=response.getWriter();
		pw.print(result);
	}
	@RequestMapping(value = "/userIdExisted", method = RequestMethod.POST)
	public void userExisted(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userIdNumber=request.getParameter("idNumber");
		NewUserDao user=new NewUserDao();
		String result =user.select(userIdNumber);
		PrintWriter pw=response.getWriter();
		if(result.equals("existed"))
			pw.print("existed");
		else if(result.equals("Not Activated"))
			pw.print("Not Activated");
		else
			pw.print("not existed");
	}
	
	@RequestMapping(value = "/userLoginC", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws SQLException{
		String userId=request.getParameter("userId");
		String userPassword=request.getParameter("userPassword");
		System.out.println(userId+userPassword);
		NewUserDao user=new NewUserDao();
		String userIdExisted=user.select(userId);
		
		if(userIdExisted.equals("Not existed")){
			try {
				PrintWriter out=response.getWriter();
				out.print("notExisted");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//model.addAttribute("message", "The user is not existed");
			//return "userLogin";
		}
		else{
			String userName=user.getUserName(userId);
			String result =user.userLogin(userId, userPassword);
			if(result.equals("right")){
				NUser nuser=new NUser(userId,userPassword,userName);
				session.setAttribute("nuser", nuser);
				session.setAttribute("message","Hi "+userName);
				//model.addAttribute("message","Hi "+userName);
				try {
					PrintWriter out=response.getWriter();
					out.print("success");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//return "userMainpage";
			}
			else if(result.equals("Not Activated")){
				try {
					PrintWriter out=response.getWriter();
					out.print("notActivated");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//model.addAttribute("message", "This id is not activated, please activate first");
				//return "userLogin";
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print("wrongPin");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//model.addAttribute("message", "Username or password is wrong");
				//return "userLogin";
			}
		}
		return null;
	}
	
	//(5.30����)
	//userEnquiry
	@RequestMapping(value = "/userEnquiry", method = RequestMethod.GET)
	public String userEnquiry(HttpSession session,Model model){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			NewUserDao user=new NewUserDao();
			NUser nuser=(NUser)session.getAttribute("nuser");
			String userId=nuser.getUserIdNumber();
			List cardList=user.selectUserCard(userId);
			model.addAttribute("cardList",cardList);
			return "userEnquiry";
			
		}
		
	}
	//userBlance
	@RequestMapping(value = "/getUserBalance", method = RequestMethod.POST)
	public String getUserBalance(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			String cardNumber=request.getParameter("cardNumber");
			System.out.println(cardNumber);
			NewUserDao user=new NewUserDao();
			
			NewUserDao nuser=new NewUserDao();
			boolean isFronze=nuser.isFrozen(cardNumber);
			if(!isFronze){
				String balance=user.getCardBalance(cardNumber);
				double b=Double.parseDouble(balance);
				String b2=new DecimalFormat(" ,##0.00").format(b);
				model.addAttribute("cardNumber",cardNumber);
				model.addAttribute("balance",b2);
				String[] currency=user.cardCurrency(cardNumber);
				//int i=currency.length;
				//for(int j=0;j<i;j++){
					model.addAttribute("cur", currency);
				return "userBalance";
			}
			else{
				try {
					PrintWriter out=response.getWriter();					
					out.print(html);
					out.print("<body><script>bootbox.alert('<br><B>Your card is frozen, please contact administrator to unfreeze.',function (){window.history.back();})</script></body>");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	//user Last 10 transaction
	@RequestMapping(value = "/userLastTen", method = RequestMethod.POST)
	public String getUserLastTen(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			String cardNumber=request.getParameter("cardNumber");
			System.out.println(cardNumber);
			TransactionDao dao=new TransactionDao();
			List transactionList=dao.Lasttransaction(cardNumber);
			model.addAttribute("cardNumber",cardNumber);
			model.addAttribute("list",transactionList);
			NewUserDao user=new NewUserDao();
			String balance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(balance);
			String b2=new DecimalFormat(" ,##0.00").format(b);
			model.addAttribute("balance",b2);
			
			String[] currency=user.cardCurrency(cardNumber);
			//int i=currency.length;
			//for(int j=0;j<i;j++){
				model.addAttribute("cur", currency);
			
			return "userLastTen";
			
		}
		
	}

			//user history transaction
	@RequestMapping(value = "/userHistory", method = RequestMethod.POST)
	public String userHistory(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response) throws ParseException{
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			String cardNumber=request.getParameter("cardNumber");
			NewUserDao user=new NewUserDao();
			String balance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(balance);
			String b2=new DecimalFormat(" ,##0.00").format(b);
			System.out.println(cardNumber);
//			String startDate=request.getParameter("startDate");
//			String endDate=request.getParameter("endDate");
//			System.out.print(startDate +" "+endDate);
//			TransactionDao dao=new TransactionDao();
//			List transactionList=dao.History(cardNumber, startDate, endDate);
			model.addAttribute("cardNumber",cardNumber);
			model.addAttribute("balance",b2);
//			model.addAttribute("list",transactionList);
			String[] currency=user.cardCurrency(cardNumber);
			//int i=currency.length;
			//for(int j=0;j<i;j++){
				model.addAttribute("cur", currency);
			return "userHistory";
			
		}
		
	}

			
			
	@RequestMapping(value = "/userHistoryC", method = RequestMethod.POST)
	public String userHistoryC(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response) throws ParseException{
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			String cardNumber=request.getParameter("cardNumber");
			String currentPage=request.getParameter("currentPage");
			if(currentPage==""){
				currentPage="1";
			}
			NewUserDao user=new NewUserDao();
			String balance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(balance);
			String b2=new DecimalFormat(" ,##0.00").format(b);
			model.addAttribute("balance",b2);
			
			
			int current=Integer.parseInt(currentPage);
			System.out.println(cardNumber);
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			System.out.print(startDate +" "+endDate);
			TransactionDao dao=new TransactionDao();
			List transactionList=dao.History(cardNumber, startDate, endDate);
			double tmp=transactionList.size()/10;
			int pages=(int)Math.ceil(tmp)+1;
			if (pages==0){
				pages=1;
			}
			System.out.println("pages"+pages);
			List showList=new ArrayList();
			int start=(current)*10-10;
			int end=current*10;
			if(current==pages){
				end=transactionList.size();
			}
			for(int i=start;i<end;i++){
				showList.add(transactionList.get(i));
			}
			
			
			
			
			session.setAttribute("pages",pages+"");
			session.setAttribute("startDate",startDate);
			session.setAttribute("endDate",endDate);
			session.setAttribute("currentPage",currentPage);
			session.setAttribute("cardNumber",cardNumber);
			
			String[] currency=user.cardCurrency(cardNumber);
			//int i=currency.length;
			//for(int j=0;j<i;j++){
				model.addAttribute("cur", currency);
			//}
			
			
			model.addAttribute("pages",pages);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("cardNumber",cardNumber);
			model.addAttribute("list",showList);
			return "userHistoryResult";
			
		}
		
	}
			
	@RequestMapping(value = "/userHistoryC", method = RequestMethod.GET)
	public String userHistoryCget(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response) throws ParseException{
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			String cardNumber=(String) session.getAttribute("cardNumber");
			String startDate=(String) session.getAttribute("startDate");
			String endDate=(String) session.getAttribute("endDate");
			TransactionDao dao=new TransactionDao();
			List transactionList=dao.History(cardNumber, startDate, endDate);
			
			System.out.print(startDate +" "+endDate);
			
			String currentPage=request.getParameter("currentPage");
			if(currentPage==""){
				currentPage="1";
			}
			int current=Integer.parseInt(currentPage);
			System.out.println(cardNumber);
			double tmp=transactionList.size()/10;
			int pages=(int)Math.ceil(tmp)+1;
			if (pages==0){
				pages=1;
			}
			System.out.println("pages"+pages);
			List showList=new ArrayList();
			int start=(current)*10-10;
			int end=current*10;
			if(current==pages){
				end=transactionList.size();
			}
			for(int i=start;i<end;i++){
				showList.add(transactionList.get(i));
			}
			NewUserDao user=new NewUserDao();
			String balance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(balance);
			String b2=new DecimalFormat(" ,##0.00").format(b);
			model.addAttribute("balance",b2);
			
			String[] currency=user.cardCurrency(cardNumber);
			//int i=currency.length;
			//for(int j=0;j<i;j++){
				model.addAttribute("cur", currency);
			
			model.addAttribute("pages",pages);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("cardNumber",cardNumber);
			model.addAttribute("list",showList);
			return "userHistoryResult";
			
		}
		
	}

			//userDeposit 6.3
			@RequestMapping(value = "/userDeposit", method = RequestMethod.GET)
			public String userDeposit (HttpSession session,Model model){
				if(session.getAttribute("nuser")!=null){
					NUser nuser=(NUser)session.getAttribute("nuser");
					NewUserDao userdao=new NewUserDao();
					String userName=nuser.getUserName();
					String userId=nuser.getUserIdNumber();
					ArrayList list=userdao.getUserCard(userId);
					model.addAttribute("list", list);
					model.addAttribute("message", "Hi "+userName);
					return "userDeposit";
				}
				return "userLoginFirst";
			}
			//hasUserBalance 6.3
				@RequestMapping(value = "/hasUserBalance", method = RequestMethod.POST)
				public void hasUserBalance(HttpSession session,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
					
					
					String cardNumber=request.getParameter("cardNumber");
					System.out.println(cardNumber);
					NewUserDao user=new NewUserDao();
						
					String balance=user.getCardBalance(cardNumber);
					double b=Double.parseDouble(balance);
					PrintWriter out=response.getWriter();
					System.out.println(b);
					out.print(b);

				}
				
	//������6.11
	/*@RequestMapping(value = "/userForeignExchange", method = RequestMethod.GET)
	public String userForeignExchange(HttpSession session){
		if(session.getAttribute("nuser")!=null){
			return "userForeignExchange";
		}
		return "userLoginFirst";
	}*/
	
	@RequestMapping(value = "/userForeignExchange", method = RequestMethod.GET)
	public String userExchangePageA(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			NewUserDao userdao=new NewUserDao();
			ArrayList list=userdao.getUserCard(userId);
			model.addAttribute("list", list);
			model.addAttribute("message", "Hi "+userName);
			return "userExchangePageA";
		}
		else{
			return "userLoginFirst";
		}
	}
	
	@RequestMapping(value = "/userExchangePageB", method = RequestMethod.GET)
	public String userExchangePageB(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			NewUserDao userdao=new NewUserDao();
			ArrayList list=userdao.getUserCard(userId);
			model.addAttribute("list", list);
			model.addAttribute("message", "Hi "+userName);
			return "userExchangePageB";
		}
		else{
			return "userLoginFirst";
		}
	}
	
	@RequestMapping(value = "/userExchangePageAInfo", method = RequestMethod.POST)
	public void userExchangePageAInfo(HttpServletRequest request, HttpServletResponse response){
		String card=request.getParameter("card");
		if(card!=""){
			NewUserDao user=new NewUserDao();
			String Sbalance=user.getCardBalance(card);
			double b=Double.parseDouble(Sbalance);
			String b2=new DecimalFormat(" ,##0.00").format(b);
			JSONObject currency=new JSONObject();
			String balance="RMB Balance: CNY "+b2;
			
			String[] currencyInfo=user.cardCurrency(card);
			currency.put("balance", balance);
			int i=currencyInfo.length;	
			for(int j=0;j<i;j++){
				currency.put("fb"+(j+1), currencyInfo[j]);
			}
			try {
				PrintWriter out=response.getWriter();
				out.print(currency);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			JSONObject currency=new JSONObject();
			currency.put("balance", "");
			currency.put("fb1", "");
			currency.put("fb2", "");
			currency.put("fb3", "");
			currency.put("fb4", "");
			currency.put("fb5", "");	
			try {
				PrintWriter out=response.getWriter();
				out.print(currency);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/userFEtransactionA", method = RequestMethod.POST)
	public String userFEtransactionA(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")!=null){
			String cardNumber=request.getParameter("select");
			NewUserDao user=new NewUserDao();
			String Sbalance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(Sbalance);
			String balance=new DecimalFormat(" ,##0.00").format(b);
			
			model.addAttribute("cardNumber", cardNumber);
			model.addAttribute("balance", balance);
			return "userFEtransactionA";
		}
		else{
			return "userLoginFirst";
		}
	}
	
	@RequestMapping(value = "/userFEtransactionB", method = RequestMethod.POST)
	public String userFEtransactionB(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")!=null){
			String cardNumber=request.getParameter("select");
			NewUserDao user=new NewUserDao();
			String Sbalance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(Sbalance);
			String balance=new DecimalFormat(" ,##0.00").format(b);
			String[] currencyInfo=user.cardCurrencyFu(cardNumber);
			String[] currencyType=user.cardCurrencyType(cardNumber);
			ArrayList type=new ArrayList();
			int i=currencyInfo.length;	
			for(int j=0;j<i;j++){
				model.addAttribute("currencyInfo"+(j+1), currencyInfo[j]);
				type.add(currencyType[j]);
			}
			
			model.addAttribute("type", type);
			model.addAttribute("cardNumber", cardNumber);
			model.addAttribute("balance", balance);
			return "userFEtransactionB";
		}
		else{
			return "userLoginFirst";
		}
	}
	
	@RequestMapping(value = "/userFEtransactionA", method = RequestMethod.GET)
	public void userFEtransactionAJAX(HttpServletRequest request, HttpServletResponse response){
		String currencyType=request.getParameter("currencyType");
		if(currencyType!=""){	
			String Sbalance=request.getParameter("balance").replaceAll(",", "");
			double balance=Double.parseDouble(Sbalance);
			FundDao fund=new FundDao();
			JSONObject currency=fund.getCurrency(currencyType);
			String SbuyPrice=currency.getString("fSellPri");
			double buyPrice=Double.parseDouble(SbuyPrice)/100;
			String SSbuyPrice=new DecimalFormat(" ,##0.0000").format(buyPrice);
			String canBuy=new DecimalFormat(" ,##0.00").format(balance/buyPrice);
			
			String info1="Buying Rate: "+SSbuyPrice;
			String info2="Maximum Amount Can Buy: "+canBuy;
			JSONObject info=new JSONObject();
			info.put("info1", info1);
			info.put("info2", info2);
			info.put("rate", SSbuyPrice);
			try {
				PrintWriter out=response.getWriter();
				out.print(info);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			JSONObject info=new JSONObject();
			info.put("info1", "");
			info.put("info2", "");
			info.put("rate", "");
			try {
				PrintWriter out=response.getWriter();
				out.print(info);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/userFEtransactionB", method = RequestMethod.GET)
	public void userFEtransactionBAJAX(HttpServletRequest request, HttpServletResponse response){
		String currencyType=request.getParameter("currencyType");
		if(currencyType!=""){
			FundDao fund=new FundDao();
			JSONObject currency=fund.getCurrency(currencyType);
			String SsellPri=currency.getString("fBuyPri");
			double sellPrice=Double.parseDouble(SsellPri)/100;
			String SSsellPrice=new DecimalFormat(" ,##0.0000").format(sellPrice);
			String Sinfo="Selling Rate: "+SSsellPrice;
			JSONObject info=new JSONObject();
			info.put("Sinfo", Sinfo);
			info.put("rate", sellPrice);
			
			try {
				PrintWriter out=response.getWriter();
				out.print(info);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				PrintWriter out=response.getWriter();
				out.print("");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/userFEtransactionBincome", method = RequestMethod.GET)
	public void userFEtransactionBincome(HttpServletRequest request, HttpServletResponse response){
		String currencyType=request.getParameter("currencyType");
		String cardNumber=request.getParameter("cardNumber");
		String price=request.getParameter("price");
		String amount=request.getParameter("amount");
		double Dincome=Double.parseDouble(price)*Double.parseDouble(amount);
		String income=new DecimalFormat(" ,##0.0000").format(Dincome);
		if(currencyType!=""&&amount!=""){
			if(income!=null){
				JSONObject info=new JSONObject();
				String Sincome="Income: CNY "+income;
				info.put("Sincome", Sincome);
				info.put("income", income);
				try {
					PrintWriter out=response.getWriter();
					out.print(info);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					PrintWriter out=response.getWriter();
					out.print("");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			try {
				PrintWriter out=response.getWriter();
				out.print("");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/userFEtransactionAMoney", method = RequestMethod.GET)
	public void userFEtransactionAMoney(HttpServletRequest request, HttpServletResponse response){
		String currencyType=request.getParameter("currencyType");
		String Samount=request.getParameter("amount");
		String rate=request.getParameter("rate");
		if(currencyType!=""&&Samount!=""){
			double amount=Double.parseDouble(Samount);
			/*FundDao fund=new FundDao();
			JSONObject currency=fund.getCurrency(currencyType);
			String SbuyPrice=currency.getString("fSellPri");
			double buyPrice=Double.parseDouble(SbuyPrice)/100;*/
			double buyPrice=Double.parseDouble(rate);
			double requiredMoney=amount*buyPrice;
			String SrequiredMoney=new DecimalFormat(" ,##0.00").format(requiredMoney);
			String info3="Required RMB Amount: "+SrequiredMoney;
			JSONObject required=new JSONObject();
			required.put("info3", info3);
			required.put("requiredMoney", SrequiredMoney);
			try {
				PrintWriter out=response.getWriter();
				out.print(required);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			JSONObject required=new JSONObject();
			required.put("info3", "");
			required.put("requiredMoney", "");
			try {
				PrintWriter out=response.getWriter();
				out.print(required);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/userFEConfirmA", method = RequestMethod.POST)
	public String userFEConfirmA(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")!=null){
			String cardNumber=request.getParameter("cardNumber");
			String RMBbalance=request.getParameter("balance");
			String currencyType=request.getParameter("select");
			String rate=request.getParameter("rate");
			String requiredMoney=request.getParameter("requiredMoney");
			String amount=request.getParameter("amount");
			
			model.addAttribute("cardNumber", cardNumber);
			model.addAttribute("RMBbalance", RMBbalance);
			model.addAttribute("currencyType", currencyType);
			model.addAttribute("amount", amount);
			model.addAttribute("rate", rate);
			model.addAttribute("requiredMoney", requiredMoney);
			return "userFEConfirmationA";
		}
		else{
			return "userLoginFirst";
		}
	}
	
	@RequestMapping(value = "/userFEConfirmB", method = RequestMethod.POST)
	public String userFEConfirmB(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")!=null){
			String cardNumber=request.getParameter("cardNumber");
			String type=request.getParameter("select");
			String amount=request.getParameter("amount");
			String price=request.getParameter("price");
			price=new DecimalFormat(" ,##0.0000").format(Double.parseDouble(price));
			String income=request.getParameter("income");
			NewUserDao user=new NewUserDao();
			String Sbalance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(Sbalance);
			String balance=new DecimalFormat(" ,##0.00").format(b);
			
			model.addAttribute("balance", balance);
			model.addAttribute("cardNumber", cardNumber);
			model.addAttribute("type", type);
			model.addAttribute("price", price);
			model.addAttribute("income", income);
			model.addAttribute("amount", amount);
			
			return "userFEConfirmationB";
		}
		else{
			return "userLoginFirst";
		}
	}
	
	@RequestMapping(value = "/userCurrencyBuy", method = RequestMethod.POST)
	public void userCurrencyBuy(HttpServletRequest request, HttpServletResponse response){
		String cardNumber=request.getParameter("cardNumber");
		String currencyType=request.getParameter("currencyType");
		String requiredMoney=request.getParameter("requiredMoney");
		String cardpin=request.getParameter("cardpin");
		String Samount=request.getParameter("amount");
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		
		UserDao user=new UserDao();
		NewUserDao nuser=new NewUserDao();
		TransferDao transfer=new TransferDao();
		CurrencyDao cur=new CurrencyDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		boolean shouldLock=transfer.shouldLock(cardNumber);
		boolean isFronze=nuser.isFrozen(cardNumber);
		boolean rightCardPin=user.cardPin(cardNumber, cardpin);
		requiredMoney=requiredMoney.replaceAll(",", "");
		double cost=Double.parseDouble(requiredMoney);
		boolean haveBalance=transfer.haveBalance(cardNumber, cost);
		
		if(isFronze){
			try {
				PrintWriter out=response.getWriter();
				out.print("isFronze");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(isLocked){
			try {
				PrintWriter out=response.getWriter();
				out.print("isLocked");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!haveBalance){
			try {
				PrintWriter out=response.getWriter();
				out.print("noBlance");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!rightCardPin){
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
					out.print("wrongCradPin");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			transfer.deleteJournal(cardNumber);
			String result=cur.currencyBuy(cardNumber, currencyType, requiredMoney, Samount);
			if(result.equals("success")){
				try {
					PrintWriter out=response.getWriter();
					out.print("success");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(result.equals("failed")){
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
					out.print("wrong");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value = "/userCurrencySell", method = RequestMethod.POST)
	public void userCurrencySell(HttpServletRequest request, HttpServletResponse response){
		String cardNumber=request.getParameter("cardNumber");
		String type=request.getParameter("type");
		String price=request.getParameter("price");
		String cardpin=request.getParameter("cardpin");
		String Samount=request.getParameter("amount");
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		
		UserDao user=new UserDao();
		NewUserDao nuser=new NewUserDao();
		TransferDao transfer=new TransferDao();
		CurrencyDao cur=new CurrencyDao();
		boolean isLocked=transfer.isLocked(cardNumber);
		boolean shouldLock=transfer.shouldLock(cardNumber);
		boolean isFronze=nuser.isFrozen(cardNumber);
		boolean rightCardPin=user.cardPin(cardNumber, cardpin);
		
		if(isFronze){
			try {
				PrintWriter out=response.getWriter();
				out.print("isFronze");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(isLocked){
			try {
				PrintWriter out=response.getWriter();
				out.print("isLocked");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!rightCardPin){
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
					out.print("wrongCradPin");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			transfer.deleteJournal(cardNumber);
			String result=cur.currencySell(cardNumber, type, Samount, price);
			if(result.equals("notype")){
				try {
					PrintWriter out=response.getWriter();
					out.print("notype");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(result.equals("nobalance")){
				try {
					PrintWriter out=response.getWriter();
					out.print("nobalance");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(result.equals("failed")){
				try {
					PrintWriter out=response.getWriter();
					out.print("failed");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(result.equals("success")){
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
					out.print("wrong");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value = "/userForeignEnquiry", method = RequestMethod.GET)
	public String userForeignEnquiry(HttpSession session,Model model){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{
			NewUserDao user=new NewUserDao();
			NUser nuser=(NUser)session.getAttribute("nuser");
			String userId=nuser.getUserIdNumber();
			List cardList=user.selectUserCard(userId);
			model.addAttribute("cardList",cardList);
			return "userForeignEnquiry";			
		}		
	}
	
	@RequestMapping(value = "/getUserCurrency", method = RequestMethod.POST)
	public String getUserCurrency(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")==null){
			return "userLoginFirst";
		}
		else{	
			String cardNumber=request.getParameter("cardNumber");
			NewUserDao user=new NewUserDao();
			String balance=user.getCardBalance(cardNumber);
			double b=Double.parseDouble(balance);
			String Sbalance=new DecimalFormat(" ,##0.00").format(b);
			String[] currency=user.cardCurrency(cardNumber);
			int i=currency.length;
			for(int j=0;j<i;j++){
				model.addAttribute("cur"+(j+1), currency[j]);
			}
			model.addAttribute("balance", Sbalance);
			model.addAttribute("cardNumber", cardNumber);
			return "userCurrencyInfo";
		}
	}
	
	@RequestMapping(value = "/userPayment", method = RequestMethod.GET)
	public String userPayment(HttpSession session,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			NewUserDao userdao=new NewUserDao();
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			
			
			model.addAttribute("message", "Hi "+userName);
			return "userPayment";
		}
		return "userLoginFirst";
	}
	@RequestMapping(value = "/userPaymentSelf", method = RequestMethod.GET)
	public String userPaymentSelf(HttpSession session,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			NewUserDao userdao=new NewUserDao();
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			BillDao billDao=new BillDao();
			ArrayList billList=billDao.selectBill(userId);
			System.out.println("bill:"+billList.size());
			model.addAttribute("billList", billList);
			model.addAttribute("message", "Hi "+userName);
			return "userPaymentSelf";
		}
		return "userLoginFirst";
	}
	@RequestMapping(value = "/userPaymentOther", method = RequestMethod.GET)
	public String userPaymentOther(HttpSession session,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			NewUserDao userdao=new NewUserDao();
			String userName=nuser.getUserName();
			String userId=nuser.getUserIdNumber();
			
			
			model.addAttribute("message", "Hi "+userName);
			return "userPaymentOther";
		}
		return "userLoginFirst";
	}

	
}
