package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeCardPinDao{
	public String CardAndUser(String userId, String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		String sql1="select * from hsbc.user where userId='"+userId+"';";
		String sql2="select * from hsbc.card where userId='"+userId+"'and cardNumber='"+cardNumber+"';";
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			stmt2=conn.prepareStatement(sql2);
			ResultSet rs2=stmt2.executeQuery();
			
			if(rs1.next()){
				if(rs2.next()){
					return "existed";
				}
				else{
					return "card isn't existed";
				}
			}
			else{
				return "the user isn't existed";
			}
		} catch(SQLException e){
			e.getStackTrace();
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
	
	public String AuthCode(String authCode){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.authcode where authcode='"+authCode+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				return "correct";
			}
			else{
				return "wrong";
			}
		} catch(SQLException e){
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
	
	public String Change(String cardPin,String userId,String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="update hsbc.card set cardPin='"+cardPin+"' where userId='"+userId+"' and cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			int i=stmt.executeUpdate();
			
			if(i!=0){
				System.out.println(cardPin);
				return "changed";
			}
			else{
				return "failed";
			}
		} catch(SQLException e){
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
	
	/*public boolean match(String userId,String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.card where userId='"+userId+"'and cardNumber='"+cardNumber+"';";
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		}  catch(SQLException e){
			e.getStackTrace();
			return false;
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
	}*/
}
