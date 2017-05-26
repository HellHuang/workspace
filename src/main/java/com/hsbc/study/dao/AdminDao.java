package com.hsbc.study.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hsbc.study.entity.Admin;

public class AdminDao {

	public Admin findAdmin(String name,String password) throws SQLException{
		Admin Admin = null;
		Connection con = DBhelper.connect();
		PreparedStatement stat = null;
		
		String sql = "select * from admin where `adminName`="+"'"+name+"'"+" and `adminPassword`="+"'"+password+"'";
		try {
			stat = con.prepareStatement(sql);
			System.out.println("connet ok");
			//stat.setString(1, name);
			//stat.setString(2, password);
			stat.execute(sql);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				return new Admin(name,password);
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
		return Admin;
	}
	
	
	
	
}
