package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.hsbc.study.entity.Transaction;

public class TransactionDao {
	public List Lasttransaction(String cardNumber){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="SELECT * FROM `transaction` WHERE cardFrom='"+cardNumber+"' order by transactionId desc limit 10";
		System.out.println(sql);
		List transactionList=new ArrayList();
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			while(rs.next()){
				String date=rs.getString("transactionDate");
				String type=rs.getString("transactionType");
				String amount=rs.getString("transactionAmount");
				double b=Double.parseDouble(amount);
				String b2=new DecimalFormat(" ,###.00").format(b);
				
				Transaction t=new Transaction(date,type,b2);
				transactionList.add(t);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return transactionList;
	}
	//SELECT * FROM `transaction` WHERE `transactionDate` between '2016-05-27' and '2016-05-29' ORDER BY transactionId desc 
	public List History(String cardNumber,String startDate,String endDate) throws ParseException{
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc =new GregorianCalendar();
		Date date = sf.parse(endDate);
		gc.setTime(date);
		gc.add(5, +1);   // enddate +1
		endDate=sf.format(gc.getTime());
		String sql="SELECT * FROM `transaction` WHERE cardFrom='"+cardNumber+"'  and transactionDate between '"+startDate+"' and '"+endDate+"' order by transactionId desc ";
		System.out.println(sql);
		List transactionList=new ArrayList();
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			while(rs.next()){
				String d=rs.getString("transactionDate");
				String type=rs.getString("transactionType");
				String amount=rs.getString("transactionAmount");
				double b=Double.parseDouble(amount);
				String b2=new DecimalFormat(" ,###.00").format(b);
				
				Transaction t=new Transaction(d,type,b2);
				transactionList.add(t);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return transactionList;
	}
	
}
	
