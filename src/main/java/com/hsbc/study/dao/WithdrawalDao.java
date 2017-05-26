package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WithdrawalDao{
	public String checkCardnumber(String cardNumber,Double amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		double changeBalance=0;
		String sql="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				double balance=Double.parseDouble(rs.getString("balance"));
				changeBalance=balance-amount;
				if(changeBalance>=0){
					return "card and balance";
				}
				else{
					return "card no balance";
				}
			}
			else{
				return "no card";
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
		return "no card";
	}
	
	public String checkUsercard(String cardNumber,Double amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		double changeBalance=0;
		String sql="select * from hsbc.userdeposit where depositNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				double balance=Double.parseDouble(rs.getString("amount"));
				changeBalance=balance-amount;
				if(changeBalance>=0){
					return "card and balance";
				}
				else{
					return "card no balance";
				}
			}
			else{
				return "no card";
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
		return "no card";
	}
	
	public String Withdrawal(String cardNumber,double amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		double changeBalance=0;
		String sql1="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			while(rs1.next()){
				double balance=Double.parseDouble(rs1.getString("balance"));
				changeBalance=balance-amount;
				//if(changeBalance>=0){
				String sql2="update hsbc.card set balance='"+changeBalance+"' where cardNumber='"+cardNumber+"';";
				stmt2=conn.prepareStatement(sql2);
				int judge=stmt2.executeUpdate();
				if(judge!=0){
					return "successful";
				}
				else{
					return "unknown";
				}
				//}
				//else{
					//return "not enough";
				//}
			}
			} catch (SQLException e){
				e.getStackTrace();
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
			return "unknow";
	}
	
	//¸ù¾Ýtime deposit numberµÃ¿¨ºÅ
	public String getCardNumber(String number){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.userdeposit where depositNumber='"+number+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String cardNumber=rs.getString("cardNumberFrom");
				return cardNumber;
			}
		} catch (SQLException e){
			e.getStackTrace();
		} finally{
			if(conn!=null){
				DBhelper.closeConneciton(conn);
			}
			if(stmt!=null){
				DBhelper.closePreparedStatement(stmt);
			}
		}
		return null;
	}
	
	public String userWithdrawal(String cardNumber,double amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;
		String number=this.getCardNumber(cardNumber);
		double changeBalance=0;
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		String sql1="select * from hsbc.userdeposit where depositNumber='"+cardNumber+"';";
		String sql4="insert into hsbc.transaction values ("+null+",'"+sdate+"','time deposit withdrawal','"+amount+"','"+number+"','"+number+"');";
		
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			while(rs1.next()){
				double balance=Double.parseDouble(rs1.getString("amount"));
				changeBalance=balance-amount;
					if(changeBalance>0){
						String sql2="update hsbc.userdeposit set amount='"+changeBalance+"' where depositNumber='"+cardNumber+"';";
						
						conn.setAutoCommit(false);
						
						stmt2=conn.prepareStatement(sql2);
						stmt2.executeUpdate();
						
						stmt4=conn.prepareStatement(sql4);
						stmt4.executeUpdate();
						
						conn.commit();
						conn.setAutoCommit(true);
						return "successful";
					}
					else{
						String sql3="delete from hsbc.userdeposit where depositNumber='"+cardNumber+"';";
						
						conn.setAutoCommit(false);
						
						stmt3=conn.prepareStatement(sql3);
						stmt3.executeUpdate();
						
						stmt4=conn.prepareStatement(sql4);
						stmt4.executeUpdate();
						
						conn.commit();
						conn.setAutoCommit(true);
						return "successful";
					}
				}
			} catch (SQLException e){
				e.getStackTrace();
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
					DBhelper.closePreparedStatement(stmt4);
				}catch(Exception e){
					e.getStackTrace();
				}
				try{
					DBhelper.closeConneciton(conn);
					}catch(Exception e){
						e.getStackTrace();
					}	
			}
			return "unknow";
	}
	
	public void setTransaction(double amount,String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		String sql="insert into hsbc.transaction values ("+null+",'"+sdate+"','withdrawal','"+amount+"','"+cardNumber+"','"+cardNumber+"');";
		
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
	/*public boolean checkBalance(String cardNumber,double amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		double changeBalance=0;
		String sql="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				double balance=Double.parseDouble(rs.getString("balance"));
				changeBalance=balance-amount;
				if(changeBalance>=0){
					return true;
				}
				else{
					return false;
				}
			}
		}  catch (SQLException e){
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
	}*/
}