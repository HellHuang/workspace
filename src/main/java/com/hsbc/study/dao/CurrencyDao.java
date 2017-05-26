package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrencyDao {
	public String currencyBuy(String cardNumber,String currencyType,String requiredMoney,String Samount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;
		PreparedStatement stmt5=null;
		double cost=Double.parseDouble(requiredMoney);
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		String sql1="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		String sql2="select * from hsbc.currency where cardNumber='"+cardNumber+"' and type='"+currencyType+"';";
		
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			if(rs1.next()){
				String Sbalance=rs1.getString("balance");
				double balance=Double.parseDouble(Sbalance);
				String Snbalance=String.valueOf(balance-Double.parseDouble(requiredMoney));
						
				stmt2=conn.prepareStatement(sql2);
				ResultSet rs2=stmt2.executeQuery();
				if(rs2.next()){
					String SoldAmount=rs2.getString("amount");
					double namount=Double.parseDouble(SoldAmount)+Double.parseDouble(Samount);
					String SnewAmount=String.valueOf(namount);
					String SRMB=rs2.getString("RMB");
					double nRMB=Double.parseDouble(SRMB)+Double.parseDouble(requiredMoney);
					String SnRMB=String.valueOf(nRMB);
					
					String sql3="update hsbc.currency set amount='"+SnewAmount+"',RMB='"+SnRMB+"' where cardNumber='"+cardNumber+"' and type='"+currencyType+"';";
					String sql4="update hsbc.card set balance='"+Snbalance+"' where cardNumber='"+cardNumber+"';";
					String sql5="insert into hsbc.transaction values ("+null+",'"+sdate+"','foreign currency buy','"+requiredMoney+"','"+cardNumber+"','"+cardNumber+"');";
					
					try{
						conn.setAutoCommit(false);
						
						stmt3=conn.prepareStatement(sql3);
						stmt3.executeUpdate();
						
						stmt4=conn.prepareStatement(sql4);
						stmt4.executeUpdate();
						
						stmt5=conn.prepareStatement(sql5);
						stmt5.executeUpdate();
						
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
				else{
					String sql3="insert into hsbc.currency values('"+cardNumber+"','"+currencyType+"','"+Samount+"','"+requiredMoney+"');";
					String sql4="update hsbc.card set balance='"+Snbalance+"' where cardNumber='"+cardNumber+"';";
					String sql5="insert into hsbc.transaction values ("+null+",'"+sdate+"','foreign currency buy','"+requiredMoney+"','"+cardNumber+"','"+cardNumber+"');";
					
					try{
						conn.setAutoCommit(false);
						
						stmt3=conn.prepareStatement(sql3);
						stmt3.executeUpdate();
						
						stmt4=conn.prepareStatement(sql4);
						stmt4.executeUpdate();
						
						stmt5=conn.prepareStatement(sql5);
						stmt5.executeUpdate();
						
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
			else{
				return "wrong";
			}
		} catch(SQLException e){
			e.getStackTrace();
		} finally{
			if(stmt1!=null){
				DBhelper.closePreparedStatement(stmt1);
			}
			if(stmt2!=null){
				DBhelper.closePreparedStatement(stmt2);
			}
			if(stmt3!=null){
				DBhelper.closePreparedStatement(stmt3);
			}
			if(stmt4!=null){
				DBhelper.closePreparedStatement(stmt4);
			}
			if(stmt5!=null){
				DBhelper.closePreparedStatement(stmt5);
			}
			if(conn!=null){
				DBhelper.closeConneciton(conn);
			}
		}
		return null;
	}
	
	//º∆À„income
	public String income(String cardNumber,String type,String amount,String price){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from hsbc.currency where cardNumber='"+cardNumber+"' and type='"+type+"';";
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String SOldamount=rs.getString("amount");
				String SRMB=rs.getString("RMB");
				double Oldamount=Double.parseDouble(SOldamount);
				double RMB=Double.parseDouble(SRMB);
				double income=(Double.parseDouble(price)-RMB/Oldamount)*Double.parseDouble(amount);
				String Sincome=new DecimalFormat(" ,##0.0000").format(income);
				return Sincome;
			}
		}  catch(SQLException e){
			e.getStackTrace();
		} finally{
			if(stmt!=null){
				DBhelper.closePreparedStatement(stmt);
			}
			if(conn!=null){
				DBhelper.closeConneciton(conn);
			}
		}
		return null;
	}
	
	public String currencySell(String cardNumber,String type,String amount,String price){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;
		PreparedStatement stmt5=null;
		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate);
		double Damount=Double.parseDouble(amount);
		double Dprice=Double.parseDouble(price);
		double sellAmount=Damount*Dprice;
		String SsellAmount=String.valueOf(sellAmount);
		String sql1="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		String sql2="select * from hsbc.currency where cardNumber='"+cardNumber+"' and type='"+type+"';";
		
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			if(rs1.next()){
				String Sbalance=rs1.getString("balance");
				double Dbalance=Double.parseDouble(Sbalance);
				double nbalance=Dbalance+sellAmount;
				String balance=String.valueOf(nbalance);
				
				stmt2=conn.prepareStatement(sql2);
				ResultSet rs2=stmt2.executeQuery();
				if(rs2.next()){
					String haveAmount=rs2.getString("amount");
					double DhaveAmount=Double.parseDouble(haveAmount);
					if((DhaveAmount-Damount)<0){
						return "nobalance";
					}
					else if((DhaveAmount-Damount)==0){
						String sql3="update hsbc.card set balance='"+balance+"' where cardNumber='"+cardNumber+"';";
						String sql4="delete from hsbc.currency where cardNumber='"+cardNumber+"' and type='"+type+"';";
						String sql5="insert into hsbc.transaction values ("+null+",'"+sdate+"','foreign currency sell','"+SsellAmount+"','"+cardNumber+"','"+cardNumber+"');";
						
						try{
							conn.setAutoCommit(false);
							
							stmt3=conn.prepareStatement(sql3);
							stmt3.executeUpdate();
							
							stmt4=conn.prepareStatement(sql4);
							stmt4.executeUpdate();
							
							stmt5=conn.prepareStatement(sql5);
							stmt5.executeUpdate();
							
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
					else{
						String Snamount=String.valueOf((DhaveAmount-Damount));
						String SRMB=rs2.getString("RMB");
						double DRMB=Double.parseDouble(SRMB);
						double DnRMB=DRMB*(1-Damount/DhaveAmount);
						String SnRMB=String.valueOf(DnRMB);
						String sql3="update hsbc.card set balance='"+balance+"' where cardNumber='"+cardNumber+"';";
						String sql4="update hsbc.currency set amount='"+Snamount+"',RMB='"+SnRMB+"' where cardNumber='"+cardNumber+"' and type='"+type+"';";
						String sql5="insert into hsbc.transaction values ("+null+",'"+sdate+"','foreign currency sell','"+SsellAmount+"','"+cardNumber+"','"+cardNumber+"');";
						
						try{
							conn.setAutoCommit(false);
							
							stmt3=conn.prepareStatement(sql3);
							stmt3.executeUpdate();
							
							stmt4=conn.prepareStatement(sql4);
							stmt4.executeUpdate();
							
							stmt5=conn.prepareStatement(sql5);
							stmt5.executeUpdate();
							
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
				else{
					return "notype";
				}
			}
			else{
				return "wrong";
			}
		} catch(SQLException e){
			e.getStackTrace();
		} finally{
			if(stmt1!=null){
				DBhelper.closePreparedStatement(stmt1);
			}
			if(stmt2!=null){
				DBhelper.closePreparedStatement(stmt2);
			}
			if(stmt3!=null){
				DBhelper.closePreparedStatement(stmt3);
			}
			if(stmt4!=null){
				DBhelper.closePreparedStatement(stmt4);
			}
			if(stmt5!=null){
				DBhelper.closePreparedStatement(stmt5);
			}
			if(conn!=null){
				DBhelper.closeConneciton(conn);
			}
		}
		return null;
	}
}
