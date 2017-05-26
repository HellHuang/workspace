package com.hsbc.study;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.study.dao.NewUserDao;
import com.hsbc.study.entity.NUser;
import service.EncryptionUtil;
import service.MailUtil;

@Controller
public class UserForgetController {
	@RequestMapping(value = "/RetrievalPage", method = RequestMethod.GET)
	public String Retrievalpage(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		String userAccount=request.getParameter("1");
		String userId=request.getParameter("2");
		model.addAttribute("userAccount", userAccount);
		model.addAttribute("userId", userId);
		return "Retrievalpage";
	}
	
	@RequestMapping(value = "/CheckUserAccount", method = RequestMethod.POST)
	public void CheckUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userAccount=request.getParameter("userAccount");
		NewUserDao newUser=new NewUserDao();
		boolean haveUserAccount=newUser.userAccount(userAccount);
		PrintWriter out=response.getWriter();
		
		if(haveUserAccount==true){
			out.print("has");
		}
		else{
			out.print("no");
		}
	}


@RequestMapping(value = "/CheckIdNumber", method = RequestMethod.POST)
public void CheckId(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userId=request.getParameter("idNumber");
		NewUserDao newUser=new NewUserDao();
		String haveId=newUser.select(userId);
		PrintWriter out=response.getWriter();
	
		if(haveId.equals("existed")){
			out.print("has");
		}
		else if(haveId.equals("Not Activated")){
			out.print("notactived");
		}
		else{
			out.print("no");
		}
	}	

@RequestMapping(value = "/CheckEmail", method = RequestMethod.POST)
public void CheckEmail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userAccount=request.getParameter("userAccount");
		String userId=request.getParameter("idNumber");
		String email=request.getParameter("email");
		NewUserDao newUser=new NewUserDao();
		PrintWriter out=response.getWriter();
		boolean haveEmail=newUser.checkEmail(userAccount, userId, email);
		
		if(haveEmail==true){
			out.print("has");
		}
		else{
			out.print("no");
		}
	}
	
final static String html=   "<head><link href='./static/css/bootstrap.min.css' rel='stylesheet'>"+
		"<link href='./static/css/font-awesome.min.css' rel='stylesheet'>"+
		"<link href='./static/css/font-face.css' rel='stylesheet'>"+
	    "<link href='./static/css/font-face.css' rel='stylesheet'>"+
	    "<script type='text/javascript' src='./static/js/jquery-1.11.2.min.js'></script> "+  
	    "<script src='./static/js/bootstrap.min.js'></script>"+ 
	    "<script src='./static/js/bootbox.min.js'></script> </head> ";

	@RequestMapping(value = "/Retrieval", method = RequestMethod.POST)
	public void Retrieval(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userAccount=request.getParameter("userAccount");
		String userId=request.getParameter("idNumber");
		String email=request.getParameter("email");
		NewUserDao newUser=new NewUserDao();
		System.out.println(userAccount+userId+email);
		PrintWriter out=response.getWriter();
		boolean haveUserAccount=newUser.userAccount(userAccount);
		String haveId=newUser.select(userId);
		boolean haveEmail=newUser.checkEmail(userAccount, userId, email);
		boolean match=newUser.match(userAccount, userId);
		Map<String ,Object> model=new HashMap<String,Object>();
		
		if(haveUserAccount==false){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>The user account does not exist.',function (){window.location.href='userForgetPass';})</script></body>");
		}
		else if(haveId.equals("not existed")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>The id number does not exist.',function (){window.location.href='userForgetPass';})</script></body>");
		}
		else if(haveId.equals("Not Activated")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>The id number is not actived.',function (){window.location.href='userForgetPass';})</script></body>");
		}
		else if(match==false){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>The user account is not matched with id number.',function (){window.location.href='userForgetPass';})</script></body>");
		}
		else if(haveEmail==false){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>The email address is not matched with the one when you regisited.',function (){window.location.href='userForgetPass';})</script></body>");
		}
		else{
			model.put("userId", userId);
			model.put("userAccount", userAccount);
			String title="If you want to retrive your account,please click the link \n";
			String content=title+"<a href='http://localhost:8080/study/RetrievalPage?";
			userAccount=EncryptionUtil.encrypt(userAccount);
			userId=EncryptionUtil.encrypt(userId);
			String data="1="+userAccount+"&2="+userId+"'>Please click here.</a>";
			content=content+data;
			
			MailUtil.send(email, content);
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>A mail was sent to you mainbox.Click the link and retrieval password.',function (){window.location.href='userLogin';})</script></body>");
			
		}
	}
	
	@RequestMapping(value = "/RetrievalPassword", method = RequestMethod.POST)
	public void RetrievalPassword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userAccount=request.getParameter("userAccount"); 
		userAccount=EncryptionUtil.decrypt(userAccount);
		String userId=request.getParameter("userId");
		userId=EncryptionUtil.decrypt(userId);
		String password=request.getParameter("password");
		NewUserDao newUser=new NewUserDao();
		PrintWriter out=response.getWriter();
		System.out.println(userAccount+userId);
		String judge=newUser.RetrievalPassword(userId, userAccount, password);
		
		if(judge.equals("success")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Operation successful. Please remember your password.',function (){window.location.href='userLogin';})</script></body>");
		}
		else if(judge.equals("failed")){
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Operation failed.',function (){window.location.href='userForgetPass';})</script></body>");
		}
		else{
			out.print(html);
			out.print("<body><script>bootbox.alert('<br><B>Unknow error, failed.',function (){window.location.href='userForgetPass';})</script></body>");
		}
	}
	
}
