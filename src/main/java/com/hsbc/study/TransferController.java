package com.hsbc.study;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hsbc.study.dao.UserDao;

@Controller
public class TransferController {
	@RequestMapping(value="/Transfer", method = RequestMethod.POST)
	public void transfer(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String FcardNumber=request.getParameter("select");
		double amount=Double.parseDouble(request.getParameter("amount"));
		String RcardNumber=request.getParameter("receivercard");
		String Rname=request.getParameter("receivername");
		String cardPin=request.getParameter("cardpin");
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		UserDao user=new UserDao();
		TransferDao transfer=new TransferDao();
		boolean RhaveCard=user.RhaveCard(RcardNumber);
		boolean Rmatch=user.Rmatch(RcardNumber, Rname);
		boolean rightCardPin=user.cardPin(FcardNumber, cardPin);
		boolean haveBalance=transfer.haveBalance(FcardNumber, amount);
		boolean shouldLock=transfer.shouldLock(FcardNumber);
		boolean isLocked=transfer.isLocked(FcardNumber);
		boolean RisLocked=transfer.isLocked(RcardNumber);
		System.out.println(FcardNumber+" and "+RcardNumber);
		
		NewUserDao nuser=new NewUserDao();
		boolean FisFronze=nuser.isFrozen(FcardNumber);
		boolean RisFronze=nuser.isFrozen(RcardNumber);
		
		if(FcardNumber==""){
			try {
				PrintWriter out=response.getWriter();
				out.print("noFcardNumber");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(FisFronze){
			try {
				PrintWriter out=response.getWriter();
				out.print("Ffrozen");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(RisFronze){
			try {
				PrintWriter out=response.getWriter();
				out.print("Rfrozen");
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
		else if(RisLocked){
			try {
				PrintWriter out=response.getWriter();
				out.print("Rlocked");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(FcardNumber.equals(RcardNumber)){
			try {
				PrintWriter out=response.getWriter();
				out.print("same");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*else if(shouldLock){
			transfer.lock(FcardNumber);
			try {
				PrintWriter out=response.getWriter();
				out.print("shouldLocked");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		else if(!rightCardPin){
			transfer.setJournal(sdate, FcardNumber);
			if(shouldLock){
				transfer.lock(FcardNumber);
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
		else if(!RhaveCard){
			transfer.deleteJournal(FcardNumber);
			try {
				PrintWriter out=response.getWriter();
				out.print("noRcard");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!Rmatch){
			transfer.deleteJournal(FcardNumber);
			try {
				PrintWriter out=response.getWriter();
				out.print("noRmatch");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(!haveBalance){
			transfer.deleteJournal(FcardNumber);
			try {
				PrintWriter out=response.getWriter();
				out.print("noBalance");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			transfer.deleteJournal(FcardNumber);
			String check=transfer.transfer(FcardNumber, RcardNumber, amount);
			if(check.equals("success")){
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
}
