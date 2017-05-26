package com.hsbc.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.study.dao.DataDao;
import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.entity.NUser;
@Controller
public class DataController {
	
	@RequestMapping(value = "/dataSelect", method = RequestMethod.GET)
	public String dataSelect(HttpSession session,HttpServletRequest request, HttpServletResponse response,Model model){
		if(session.getAttribute("nuser")!=null){
			NUser nuser=(NUser)session.getAttribute("nuser");
			String userId=nuser.getUserIdNumber();
			NewUserDao user=new NewUserDao();
			List cardList=user.selectUserCard(userId);
			model.addAttribute("cardList",cardList);
			return "dataSelect";
		}
		
		return "userLoginFirst";
	}
	
	
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public String data(HttpServletRequest request, HttpServletResponse response,Model model){
		String cardNumber=request.getParameter("cardNumber");
		model.addAttribute("cardNumber", cardNumber);
		return "aaa";
	}
	@RequestMapping(value = "/getIncomedata", method = RequestMethod.POST)
	public void getIncomedata(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		//return "loginpage";
		
			//NUser nuser=(NUser)session.getAttribute("nuser");
			//String userId=nuser.getUserIdNumber();
			String cardNumber=request.getParameter("cardNumber");
			DataDao dataDao=new DataDao();
			String result=dataDao.getIncome(cardNumber);
			System.out.println("r"+result);
			PrintWriter out=response.getWriter();
			out.print(result);

	}
	
	@RequestMapping(value = "/getExpensedata", method = RequestMethod.POST)
	public void getExpensedata(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		//return "loginpage";
		
			//NUser nuser=(NUser)session.getAttribute("nuser");
			//String userId=nuser.getUserIdNumber();
		String cardNumber=request.getParameter("cardNumber");
			DataDao dataDao=new DataDao();
			String result=dataDao.getExpense(cardNumber);
			System.out.println("r"+result);
			PrintWriter out=response.getWriter();
			out.print(result);

	}
	@RequestMapping(value = "/getDetaildata", method = RequestMethod.POST)
	public void getDetaildata(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		//return "loginpage";
		
			//NUser nuser=(NUser)session.getAttribute("nuser");
			//String userId=nuser.getUserIdNumber();
			String cardNumber=request.getParameter("cardNumber");
			DataDao dataDao=new DataDao();
			ArrayList result=dataDao.detailData(cardNumber);
			System.out.println("r"+result);
			
			PrintWriter out=response.getWriter();
			out.print(result);

	}
}
