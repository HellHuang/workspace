package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hsbc.study.entity.Admin;

public class DepositDao {
	public boolean checkCardnumber(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				return true;
			}
			else{
				return false;
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
				DBhelper.closeConneciton(conn);
				}catch(Exception e){
					e.getStackTrace();
				}	
		}
		return false;
	}
	public String deposit(String cardNumber,String amount) {
	
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		String balance=null;
		String sql="select balance from card where cardNumber='"+cardNumber+"' ";
		System.out.println(sql);
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
//			if(rs==null){
//				return "card number not found";
//			}
			while(rs.next()){
				balance=(rs).getString(1);
			}
			
			System.out.println(balance);
			
//			if(balance==null){
//				//balance=amount;
//				System.out.println("1"+balance);
//				return "card number not found";
//			}
			 if(balance.equals("0")){
				balance=amount;
				System.out.println("1"+balance);
			}
			else{
				double aa=Double.parseDouble(balance);
				double a2=Double.parseDouble(amount);
				aa+=a2;
				balance=""+aa;
				System.out.println("2"+balance);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "unknown";
		}
		
		
		String sql2 = " UPDATE card SET balance = '"+balance+"' WHERE cardNumber = '"+cardNumber+"' ";
		try {
			stat2 = con.prepareStatement(sql2);
			System.out.println("update ok");
			//stat.setString(1, name);
			//stat.setString(2, password);
			int i =stat2.executeUpdate();
			//ResultSet rs = stat.executeQuery();
			if(i!=0){
				return "update success";
			}
			
			else{
				return "unknown";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "unknown";
		}
		finally{
			DBhelper.closePreparedStatement(stat1);
			DBhelper.closePreparedStatement(stat2);
			DBhelper.closeConneciton(con);
		}
		
		
	}
	
	public void setTransaction(double amount,String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		String sql="insert into hsbc.transaction values ("+null+",'"+sdate+"','deposit','"+amount+"','"+cardNumber+"','"+cardNumber+"');";
		
		try{
			stmt=conn.prepareStatement(sql);
			int check=stmt.executeUpdate();
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			try{
			DBhelper.closePreparedStatement(stmt);
			}catch(Exception e){
				e.getStackTrace();
			}
			try{
				DBhelper.closeConneciton(conn);
				}catch(Exception e){
					e.getStackTrace();
				}	
		}
	}
}
