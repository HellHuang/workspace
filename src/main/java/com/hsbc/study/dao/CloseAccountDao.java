package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CloseAccountDao{
	public String Close(String cardNumber,Double balance){
		if(balance>0){
			return "cannot be closed";
		}
		else{
			return "can be closed";
		}
	}
	
	public String CloseCard(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="delete from hsbc.card where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			int rs=stmt.executeUpdate(sql);
			if(rs!=0){
				return "close successfully";
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
}
