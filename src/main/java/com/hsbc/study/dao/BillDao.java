package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hsbc.study.entity.Bill;

public class BillDao {
	public boolean isMatch(String billNumber,String billUserName){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		String sql1="select * from bill where billNumber='"+billNumber+"' and billUserName='"+billUserName+"'";
		System.out.println(sql1);
		boolean isMatch=false;
		try {
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs=stmt1.executeQuery(sql1);
			while(rs.next()){
				Object temp=rs.getString(1);
				if (temp==null){
					return false;
				}
				else{
					return true;
				}
			}
			
			
			System.out.println(isMatch+"ism");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				DBhelper.closeConneciton(conn);
				DBhelper.closePreparedStatement(stmt1);	
			

			} catch(Exception e){
				e.getStackTrace();
			}
		}

		return isMatch;
	}
	public ArrayList BillByNumber(String billNumber){
		Connection conn=DBhelper.connect();


		PreparedStatement stmt=null;

		String sql="select * from bill where billNumber='"+billNumber+"' and billStatus='Not'";
		System.out.println(sql);
		ResultSet rs = null;
		ArrayList billList=new ArrayList();
		try{


			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			System.out.println("0");
			while(rs.next()){

				String billItem=rs.getString("billItem");

				String billPeriod=rs.getString("billPeriod");

				String billAmount=rs.getString("billAmount");

				String billStatus=rs.getString("billStatus");

				String billUserId=rs.getString("billUserId");

				String billUserName=rs.getString("billUserName");

				Bill bill=new Bill(billNumber, billItem, billPeriod, billAmount, billStatus, billUserId,billUserName);

				billList.add(bill);
			}
			System.out.println("billlsit"+billList.size());
			return billList;


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

		return billList;
	}

	public ArrayList selectBill(String userId){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt=null;
		String sql="select * from bill where billUserId='"+userId+"'and billStatus='Not'";
		ResultSet rs = null;
		ArrayList billList=new ArrayList();
		try{
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				String billNumber=rs.getString("billNumber");
				String billItem=rs.getString("billItem");
				String billPeriod=rs.getString("billPeriod");
				String billAmount=rs.getString("billAmount");
				String billStatus=rs.getString("billStatus");
				Bill bill=new Bill(billNumber, billItem, billPeriod, billAmount, billStatus, userId);
				billList.add(bill);
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
		return billList;
	}

	public String billPay(String cardNumber,String userId,String amount,String billNumber,String billType){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;

		double changeBalance1;

		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		String sql1="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		billType=billType+" payment";
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();

			while(rs1.next()){
				double balance1=Double.parseDouble(rs1.getString("balance"));
				amount=amount.replace(",", "");
				changeBalance1=balance1-Double.parseDouble(amount);	

				if(changeBalance1>=0){
					String sql2="update hsbc.bill set billStatus='Yes' where billNumber='"+billNumber+"';";
					System.out.println(sql2);
					String sql3="update hsbc.card set balance='"+changeBalance1+"' where cardNumber='"+cardNumber+"';";
					System.out.println(sql3);
					String sql4="insert into hsbc.transaction values ("+null+",'"+sdate+"','"+billType+"','"+amount+"','"+cardNumber+"','"+cardNumber+"');";
					System.out.println(sql4);

					try{
						conn.setAutoCommit(false);
						stmt2=conn.prepareStatement(sql2);
						stmt2.executeUpdate();

						stmt3=conn.prepareStatement(sql3);
						stmt3.executeUpdate();

						stmt4=conn.prepareStatement(sql4);
						stmt4.executeUpdate();

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

			} catch(Exception e){
				e.getStackTrace();
			}
		}
		return "failed";
	}
}
