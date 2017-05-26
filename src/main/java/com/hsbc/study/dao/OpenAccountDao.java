package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenAccountDao {
	public String insertInfoNew(String userid,String username,String cardpin){
		String cardnumber="621785700000";
		int cardnopart2 = 0;
		
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		PreparedStatement stmt3=null;

		String sqlCount="SELECT count(*) as num FROM hsbc.accountopentimes;";
		try {
			stmt=conn.prepareStatement(sqlCount);
			ResultSet rs =stmt.executeQuery();
			if(rs.next()){
				cardnopart2=rs.getInt("num");
				cardnopart2+=1001;
				cardnumber=cardnumber+cardnopart2;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}
		String sql1="insert into user(`userId`,`userName`) values('"+userid+"','"+username+"');";
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		
		String sql2="insert into card(`cardNumber`,`userId`,`cardPin`,`balance`,`status`,`createDate`) values('"+cardnumber+"','"+userid+"','"+cardpin+"','0','Normal','"+sdate+"');";
		String sql3="insert into accountopentimes(`times`) values('"+sdate+"');";
		
		try{
			//想法：能不能用像银行转账那样的transaction来实现数据写进MySql但开卡不成功的情况？？？
			conn.setAutoCommit(false);
			stmt3=conn.prepareStatement(sql3);
			stmt3.executeUpdate();
			
			stmt2=conn.prepareStatement(sql2);	
			stmt2.executeUpdate();
			
			stmt1=conn.prepareStatement(sql1);					
			stmt1.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
			return cardnumber;
		} catch(SQLException e){
			e.getStackTrace();
			try{
				if(conn!=null){
					conn.rollback();
					conn.setAutoCommit(true);
					} 
				} catch(SQLException e1){
					e1.getStackTrace();
				}
			return "";
		} finally{
			try{
			DBhelper.closePreparedStatement(stmt1);
			}catch(Exception e){
				e.getStackTrace();
			}
			try{
				DBhelper.closePreparedStatement(stmt2);
				}catch(Exception e){
					e.getStackTrace();
				}
			try{
				DBhelper.closePreparedStatement(stmt3);
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
	
	public String insertInfoExist(String userid,String username,String cardpin){
		String cardnumber="621785700000";
		int cardnopart2 = 0;
		
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;

		String sqlCount="SELECT count(*) as num FROM hsbc.accountopentimes;";
		try {
			stmt=conn.prepareStatement(sqlCount);
			ResultSet rs =stmt.executeQuery();
			if(rs.next()){
				cardnopart2=rs.getInt("num");
				cardnopart2+=1001;
				cardnumber=cardnumber+cardnopart2;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}
		//String sql1="insert into user(`userId`,`userName`) values('"+userid+"','"+username+"');";
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		
		String sql1="insert into card(`cardNumber`,`userId`,`cardPin`,`balance`,`status`,`createDate`) values('"+cardnumber+"','"+userid+"','"+cardpin+"','0','Normal','"+sdate+"');";
		String sql2="insert into accountopentimes(`times`) values('"+sdate+"');";
		
		try{
			conn.setAutoCommit(false);
			stmt2=conn.prepareStatement(sql2);
			stmt2.executeUpdate();
			
			stmt1=conn.prepareStatement(sql1);					
			stmt1.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
			
			return cardnumber;
		} catch(SQLException e){
			e.getStackTrace();
			try{
				if(conn!=null){
					conn.rollback();
					conn.setAutoCommit(true);
					} 
				} catch(SQLException e1){
					e1.getStackTrace();
				}
			return "";
		} finally{
			try{
			DBhelper.closePreparedStatement(stmt1);
			}catch(Exception e){
				e.getStackTrace();
			}
			try{
				DBhelper.closePreparedStatement(stmt2);
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
	
	public String judgeUser(String userid,String username){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.user where userId='"+userid+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				if(rs.getString("userName").equals(username)){
					return "userid and username are matched";
				}
				else{
					return "userid and username are not matched";
				}
			}
			else{
				return "new user";
			}
		} catch (SQLException e){
			e.getStackTrace();
			return "error";
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
