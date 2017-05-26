package com.hsbc.study.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.ParseException;

public class TransferDao {
	public String transfer(String FcardNumber,String RcardNumber,double amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;
		PreparedStatement stmt5=null;
		PreparedStatement stmt6=null;
		double changeBalance1;
		double changeBalance2;
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		String sql1="select * from hsbc.card where cardNumber='"+FcardNumber+"';";
		String sql2="select * from hsbc.card where cardNumber='"+RcardNumber+"';";
		
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			stmt2=conn.prepareStatement(sql2);
			ResultSet rs2=stmt2.executeQuery();
			while(rs1.next()&&rs2.next()){
				double balance1=Double.parseDouble(rs1.getString("balance"));
				double balance2=Double.parseDouble(rs2.getString("balance"));
				changeBalance1=balance1-amount;	
				changeBalance2=balance2+amount;
				if(changeBalance1>=0){
					String sql3="update hsbc.card set balance='"+changeBalance1+"' where cardNumber='"+FcardNumber+"';";
					String sql4="update hsbc.card set balance='"+changeBalance2+"' where cardNumber='"+RcardNumber+"';";
					String sql5="insert into hsbc.transaction values ("+null+",'"+sdate+"','transfer out','"+amount+"','"+FcardNumber+"','"+RcardNumber+"');";
					String sql6="insert into hsbc.transaction values ("+null+",'"+sdate+"','transfer in','"+amount+"','"+RcardNumber+"','"+FcardNumber+"');";
					
					try{
						conn.setAutoCommit(false);
						stmt3=conn.prepareStatement(sql3);
						stmt3.executeUpdate();
						
						stmt4=conn.prepareStatement(sql4);
						stmt4.executeUpdate();
						
						stmt5=conn.prepareStatement(sql5);
						stmt5.executeUpdate();
						
						stmt6=conn.prepareStatement(sql6);
						stmt6.executeUpdate();
						
						conn.commit();
						conn.setAutoCommit(true);
						return "success";
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
						return "failed";
					}
				}
			}
		} catch(SQLException e){
			e.getStackTrace();
		} finally{
			try{
				DBhelper.closeConneciton(conn);
				DBhelper.closePreparedStatement(stmt1);	
				DBhelper.closePreparedStatement(stmt2);
				DBhelper.closePreparedStatement(stmt3);
				DBhelper.closePreparedStatement(stmt4);
				DBhelper.closePreparedStatement(stmt5);
				DBhelper.closePreparedStatement(stmt6);
			} catch(Exception e){
				e.getStackTrace();
			}
		}
		return "failed";
	}
	
	public boolean haveBalance(String FcardNumber,double amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.card where cardNumber='"+FcardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				double balance=Double.parseDouble(rs.getString("balance"));
				double haveBalance=balance-amount;
				if(haveBalance>=0){
					return true;
				}
				else{
					return false;
				}
			}
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			try{
			DBhelper.closeConneciton(conn);
			DBhelper.closePreparedStatement(stmt);			
			}catch(Exception e){
				e.getStackTrace();
			}	
		}
		return false;
	}
	
	public void setJournal(String date,String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="insert into hsbc.journal values("+null+",'"+date+"','"+cardNumber+"');";
		
		try{
			stmt=conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			try{
			DBhelper.closeConneciton(conn);
			DBhelper.closePreparedStatement(stmt);			
			}catch(Exception e){
				e.getStackTrace();
			}	
		}
	}
	
	public boolean shouldLock(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		int rowCount;
		String sql="select * from hsbc.journal where cardNumber='"+cardNumber+"' order by journalId desc;";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			rs.last();
			rowCount=rs.getRow()+1;
			System.out.println(rowCount);
			rs.beforeFirst();
			
			if(rowCount<3){
				return false;
			}
			else{
				while(rs.next()){
					rs.first();
					String Sdate1=rs.getString("journalDate");
					System.out.println(Sdate1);
					rs.absolute(3);
					String Sdate2=rs.getString("journalDate");
					System.out.println(Sdate2);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 			
					try {
						Date date1=sdf.parse(Sdate1);
						Date date2=sdf.parse(Sdate2);
						long between=date1.getTime()-date2.getTime();
						
						if(between<3600000){
							return true;
						}
						else{
							return false;
						}
					} catch (java.text.ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e){
			System.err.println(e.getStackTrace());
		} finally{
			try{
			DBhelper.closeConneciton(conn);
			DBhelper.closePreparedStatement(stmt);			
			}catch(Exception e){
				e.getStackTrace();
			}	
		}
		return true;
	}
	
	public void deleteJournal(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="delete from hsbc.journal where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			try{
			DBhelper.closeConneciton(conn);
			DBhelper.closePreparedStatement(stmt);			
			}catch(Exception e){
				e.getStackTrace();
			}	
		}
	}
	
	public void lock(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="update hsbc.card set status='Locked' where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			try{
			DBhelper.closeConneciton(conn);
			DBhelper.closePreparedStatement(stmt);			
			}catch(Exception e){
				e.getStackTrace();
			}	
		}
	}
	
	public boolean isLocked(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String status=rs.getString("status");
				if(status.equals("Locked")){
					return true;
				}
				else{
					return false;
				}
			}
		} catch (SQLException e){
			System.err.println("SqlException:"+e.getMessage());
		} finally{
			try{
			DBhelper.closeConneciton(conn);
			DBhelper.closePreparedStatement(stmt);			
			}catch(Exception e){
				e.getStackTrace();
			}	
		}
		return false;
	}
	
}
