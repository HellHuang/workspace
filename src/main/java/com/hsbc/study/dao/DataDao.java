package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.study.entity.Transaction;

public class DataDao {
	public String getIncome(String cardNumber){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="SELECT * FROM `transaction` WHERE cardFrom='"+cardNumber+"'";
		System.out.println(sql);
		double transferSum=0;
		double DepositSum=0;
		double fundSum=0;
		String result="";
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			while(rs.next()){
				String date=rs.getString("transactionDate");
				String type=rs.getString("transactionType");
				String amount=rs.getString("transactionAmount");
				double b=Double.parseDouble(amount);
				if(type.equals("transfer in")){
					transferSum+=b;
				}
				else if(type.equals("deposit")){
					DepositSum+=b;
				}
				else if(type.equals("fund redeem")){
					fundSum+=b;
				}
				else{
					
				}
				
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return "t:"+transferSum+",d:"+DepositSum+",f:"+fundSum;
	}

	public String getExpense(String cardNumber){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="SELECT * FROM `transaction` WHERE cardFrom='"+cardNumber+"'";
		System.out.println(sql);
		double transferSum=0;
		double billSum=0;
		double waterSum=0;
		double gasSum=0;
		double eleSum=0;
		double fundSum=0;
		double timeSum=0;
		String result="";
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			while(rs.next()){
				String date=rs.getString("transactionDate");
				String type=rs.getString("transactionType");
				String amount=rs.getString("transactionAmount");
				double b=Double.parseDouble(amount);
				if(type.equals("transfer out")){
					transferSum+=b;
				}
				else if(type.matches(".*bill payment.*")){
					billSum+=b;
					if(type.equals("water bill payment")){
						waterSum=+b;
					}
					else if(type.equals("gas bill payment")){
						gasSum+=b;
					}
					else {
						eleSum+=b;
					}
				}
				else if(type.equals("fund purchase")){
					fundSum+=b;
				}
				else if(type.equals("time deposit")){
					timeSum+=b;
				}
				else{
					
				}
				
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return "t:"+transferSum+",b:"+billSum+",f:"+fundSum+",w:"+waterSum+",g:"+gasSum+",e:"+eleSum+",ti:"+timeSum;
	}
	public ArrayList detailData(String cardNumber){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		
		String sql="SELECT * FROM `transaction` WHERE cardFrom='"+cardNumber+"'";
		System.out.println(sql);

		String month[]={"2016-01","2016-02","2016-03","2016-04","2016-05","2016-06",};
		double transferList[]={0,0,0,0,0,0};
		double withList[]={0,0,0,0,0,0};
		double fundList[]={0,0,0,0,0,0};
		double gasList[]={0,0,0,0,0,0};
		double eleList[]={0,0,0,0,0,0};
		double waterList[]={0,0,0,0,0,0};
		ArrayList result=new ArrayList();
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			while(rs.next()){
				String date=rs.getString("transactionDate");
				String type=rs.getString("transactionType");
				String amount=rs.getString("transactionAmount");
				double b=Double.parseDouble(amount);
				for(int i=0;i<month.length;i++){
					if(type.equals("transfer out")&& date.matches(month[i]+".*")){
						transferList[i]+=b;
					}
					else if(type.equals("withdrawal")&& date.matches(month[i]+".*")){
						withList[i]+=b;
					}
					else if(type.equals("fund purchase")&& date.matches(month[i]+".*")){
						fundList[i]+=b;
					}
					else if(type.equals("gas bill payment")&& date.matches(month[i]+".*")){
						gasList[i]+=b;
					}
					else if(type.equals("electricity bill payment")&& date.matches(month[i]+".*")){
						eleList[i]+=b;
					}
					else if(type.matches("water.*")&& date.matches(month[i]+".*")){
						waterList[i]+=b;
					}
					else {
						
						
					}
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<transferList.length;i++){
			result.add(transferList[i]);
			
		}
		for(int i=0;i<withList.length;i++){
			result.add(withList[i]);
			
		}
		for(int i=0;i<fundList.length;i++){
			result.add(fundList[i]);
		}
		for(int i=0;i<gasList.length;i++){
			result.add(gasList[i]);
		}
		for(int i=0;i<eleList.length;i++){
			result.add(eleList[i]);
		}
		for(int i=0;i<waterList.length;i++){
			result.add(waterList[i]);
		}
		
		return result;
		
	}
}
