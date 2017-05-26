package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hsbc.study.entity.UserTermCard;

public class UserDepositDao {
	//
	public ArrayList timeDepositEnquiryByCard(String cardNumber){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		String sql1="select * from hsbc.userdeposit where cardNumberFrom='"+cardNumber+"';";
		ArrayList list=new ArrayList();
		try {
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			while(rs1.next()){
			    String depositNumber=rs1.getString("depositNumber");
				String cardNumberFrom=rs1.getString("cardNumberFrom");
				String userId=rs1.getString("userId");
				String term=rs1.getString("term");
				String InterestRate=rs1.getString("InterestRate");
				String amount=rs1.getString("amount");
				String createDate=rs1.getString("createDate");
				UserTermCard temp=new UserTermCard(depositNumber, cardNumberFrom,userId, term, InterestRate, amount, createDate);
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList timeDepositEnquiry(String userId){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		String sql1="select * from hsbc.userdeposit where userId='"+userId+"';";
		ArrayList list=new ArrayList();
		try {
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			while(rs1.next()){
			    String depositNumber=rs1.getString("depositNumber");
				String cardNumberFrom=rs1.getString("cardNumberFrom");
				
				String term=rs1.getString("term");
				String InterestRate=rs1.getString("InterestRate");
				String amount=rs1.getString("amount");
				String createDate=rs1.getString("createDate");
				UserTermCard temp=new UserTermCard(depositNumber, cardNumberFrom,userId, term, InterestRate, amount, createDate);
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	public String timeDeposit(String cardNumber,String userId,String term,String interestRate,String amount){
		Connection conn=DBhelper.connect();
		PreparedStatement stmt1=null;
		PreparedStatement stmt2=null;
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;

		double changeBalance1;

		Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
		String sql1="select * from hsbc.card where cardNumber='"+cardNumber+"';";
		
		try{
			stmt1=conn.prepareStatement(sql1);
			ResultSet rs1=stmt1.executeQuery();
			
			while(rs1.next()){
				double balance1=Double.parseDouble(rs1.getString("balance"));
			
				changeBalance1=balance1-Double.parseDouble(amount);	
				String newCardNum;
				newCardNum=sdate.trim().replace("-", "");
				newCardNum=newCardNum.trim().replace(":", "");
				newCardNum=newCardNum.trim().replace(" ", "");
				if(changeBalance1>=0){
					String sql2="insert into hsbc.userdeposit values ('"+newCardNum+"','"+cardNumber+"','"+userId+"','"+term+"','"+interestRate+"','"+amount+"','"+sdate +"');";
					System.out.println(sql2);
					String sql3="update hsbc.card set balance='"+changeBalance1+"' where cardNumber='"+cardNumber+"';";
					System.out.println(sql3);
					String sql4="insert into hsbc.transaction values ("+null+",'"+sdate+"','time deposit','"+amount+"','"+cardNumber+"','"+cardNumber+"');";
					//System.out.println(sql4);
					
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

			} catch(Exception e){
				e.getStackTrace();
			}
		}
		return "failed";
	//userdeposit
	//1、创建定期存款账号 2、活期余额取出 3、增加transaction 事务
}
	
	
	
}
