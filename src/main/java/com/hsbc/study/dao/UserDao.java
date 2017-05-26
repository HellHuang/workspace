package com.hsbc.study.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hsbc.study.entity.User;
import com.hsbc.study.entity.userCard;

public class UserDao {
	public boolean select(String userId){

		Connection con = DBhelper.connect();
		PreparedStatement stat = null;
		String sql = "select * from user where userId="+"'"+userId+"'";
		try{
			stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		} catch(SQLException e){			
			e.getStackTrace();
			return false;
		} finally{
			try{
				DBhelper.closePreparedStatement(stat);
				DBhelper.closeConneciton(con);
			} catch(Exception e){
				e.getStackTrace();
			}
		}
	}

	public User findUser(String name,String password) throws SQLException{
		User User = null;
		Connection con = DBhelper.connect();
		PreparedStatement stat = null;
		
		
		String sql = "select * from user where userId="+"'"+name+"'"+" and password="+"'"+password+"'";
		try {
			stat = con.prepareStatement(sql);
			System.out.println("connet ok");
			//stat.setString(1, name);
			//stat.setString(2, password);
			stat.execute(sql);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				return new User(name,password);
			}
			
			else{
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBhelper.closePreparedStatement(stat);
			DBhelper.closeConneciton(con);
		}
		
		return User;
	}
	public ArrayList inquireUser(String userId){
		Connection con = DBhelper.connect();
		PreparedStatement stat = null;
		String sql="select * from (user,card) where user.userId=card.userId";
		try {
			stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();			
			ArrayList userCard=new ArrayList();
			int num=0;
				while(rs.next()){					
					if (rs.getString(1).equals(userId.trim())){
						num++;
						String cardNumber=rs.getString(3);
						NewUserDao nuser=new NewUserDao();
						String[] currency=nuser.cardCurrencyFu(cardNumber);
						String currencyInfo="";
						for(int i=0;i<currency.length;i++){
							currencyInfo=currencyInfo+currency[i];
						}
						userCard user=new userCard((String)rs.getString(3),(String)rs.getString(6),(String)rs.getString(7),(String)rs.getString(2),currencyInfo);
						userCard.add(user);
					}					
				}
				if(num==0){
					return null;
				}				
				return userCard;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String userIsExist(String userId){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		String sql1="select * from hsbc.user where userId='"+userId+"';";
		String sql2="select * from hsbc.card where userId='"+userId+"';";
		
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			if(rs1.next()){
				stmt2=conn.prepareStatement(sql2);
				ResultSet rs2=stmt2.executeQuery();
				if(rs2.next()){
					return "user&card";
				}
				else{
					return "user%nocard";
				}
			}
			else{
				return "nouser";
			}
		} catch(SQLException e){
			e.getStackTrace();
			return "error";
		} finally{
			try{
				DBhelper.closePreparedStatement(stmt1);
				DBhelper.closePreparedStatement(stmt2);
				DBhelper.closeConneciton(conn);
			} catch (Exception e){
				e.getStackTrace();
			}
		}
	}
	
	public boolean RhaveCard(String cardNumber){
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
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			try{
				DBhelper.closePreparedStatement(stmt);
				DBhelper.closeConneciton(conn);
			} catch(Exception e){
				e.getStackTrace();
			}
		}
	}
	
	public boolean Rmatch(String cardNumber,String Rname){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		String sql1="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			while(rs1.next()){
				String userId=rs1.getString("userId");
				String sql2="select * from hsbc.user where userId='"+userId+"';";
				stmt2=conn.prepareStatement(sql2);
				ResultSet rs2=stmt2.executeQuery();
				while(rs2.next()){
					String userName=rs2.getString("userName");
					if(userName.equals(Rname)){
						return true;
					}
					else{
						return false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			try{
				DBhelper.closePreparedStatement(stmt1);
				DBhelper.closeConneciton(conn);
			} catch(Exception e){
				e.getStackTrace();
			}
		}
		return false;
	}
	
	public boolean cardPin(String cardNumber,String cardPin){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.card where cardNumber='"+cardNumber+"' and cardPin='"+cardPin+"';";
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			try{
				DBhelper.closePreparedStatement(stmt);
				DBhelper.closeConneciton(conn);
			} catch(Exception e){
				e.getStackTrace();
			}
		}
	}
	
	//6.14²éÑ¯
}
