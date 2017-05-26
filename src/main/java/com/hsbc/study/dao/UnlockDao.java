package com.hsbc.study.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnlockDao {
	public String unlock(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		String sql1="update hsbc.card set status='Normal' where cardNumber='"+cardNumber+"';";
		String sql2="delete from hsbc.journal where cardNumber='"+cardNumber+"';";
		try{
			conn.setAutoCommit(false);
			stmt1=conn.prepareStatement(sql1);
			stmt1.executeUpdate();
			
			stmt2=conn.prepareStatement(sql2);			
			stmt2.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
			
			return "success";
		} catch (SQLException e){
			e.getStackTrace();
			try{
				if(conn!=null){
					conn.rollback();
					conn.setAutoCommit(true);
					} 
				} catch(SQLException e1){
					e1.getStackTrace();
				}
			return "failed";
		} finally{
			try{
			DBhelper.closePreparedStatement(stmt1);
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
	
	public String frozen(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		PreparedStatement stmt1=null;
		String sql="select status from hsbc.card  where cardNumber='"+cardNumber+"';";
		String sql1="update hsbc.card set status='Frozen' where cardNumber='"+cardNumber+"';";
		try {
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				System.out.println("Testastesae");
				if(rs.getString("status").equals("Frozen")){
					System.out.println("Te3333");
					return "has been Frozen";
				}
				else if(rs.getString("status").equals("Locked")){
					return "has Locked";
				}
				else{
					stmt1=conn.prepareStatement(sql1);
					int check1=stmt1.executeUpdate();
					if(check1>0){
						return "has Frozen";
					}
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
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
		 
		return "Unkown Error";
	}

	public String usfreeze(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="update hsbc.card set status='Normal' where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			int rs=stmt.executeUpdate();
			while(rs!=0){
				return "success";
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			if(stmt!=null){
				DBhelper.closePreparedStatement(stmt);
			}
			if(conn!=null){
				DBhelper.closeConneciton(conn);
			}
		}
		return "failed";
	}
}
