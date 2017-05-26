package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.study.entity.Fund;
import com.hsbc.study.entity.NUser;
import com.hsbc.study.entity.userCard;

public class NewUserDao {
	public String select (String userIdNumber){
		
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="select * from  nuser where userIdNumber ='"+userIdNumber+"'";
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			while (rs.next()){
				if(rs.getString("userStatus").equals("Not Activated"))
					return "Not Activated";
				else
				{
					return "existed";
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Not existed";
		}
		finally{
			try{
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return "Not existed";
	}
	
	public boolean userAccount(String userName){
		Connection con = DBhelper.connect();
		PreparedStatement stmt = null;
		String sql="select * from  nuser where userName ='"+userName+"'";
		try {
			stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()){
				return true;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			try{
				DBhelper.closePreparedStatement(stmt);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return false;
	}
	
	public boolean match(String userName,String idNumber){
		Connection con = DBhelper.connect();
		PreparedStatement stmt = null;
		String sql="select * from  nuser where userName ='"+userName+"' and userIdNumber='"+idNumber+"';";
		
		try {
			stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()){
				return true;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			try{
				DBhelper.closePreparedStatement(stmt);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return false;
	}
	
	public boolean checkEmail(String userName,String idNumber,String email){
		Connection con = DBhelper.connect();
		PreparedStatement stmt = null;
		String sql="select * from  nuser where userName ='"+userName+"' and userIdNumber='"+idNumber+"'and email='"+email+"';";
		
		try {
			stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while (rs.next()){
				return true;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			try{
				DBhelper.closePreparedStatement(stmt);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return false;
	}
	
	public NUser notActived(String userName, String userPassword, String email, String userIdNumber) {
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="insert into nuser values('"+userIdNumber+"','"+userName+"','"+email+"','"+userPassword+"','Not Activated')";
		try {
			stat1=con.prepareStatement(sql);
			int i=stat1.executeUpdate();
			if(i>=1){
				return new NUser(userName, userPassword,  email, userIdNumber);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return null;
	}
	public String active(String userIdNumber) {
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="update nuser set userStatus= 'Normal' where userIdNumber='"+userIdNumber+"'";
		try {
			stat1=con.prepareStatement(sql);
			int i=stat1.executeUpdate();
			if(i>=1){
				return "actived";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "unknown";
		}
		finally{
			try{
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return null;
	}
	
	public String RetrievalPassword(String userId,String userAccount,String password){
		Connection con = DBhelper.connect();
		PreparedStatement stmt = null;
		String sql="update nuser set userPassword='"+password+"' where userIdNumber='"+userId+"' and userName='"+userAccount+"';";
		System.out.println(sql);
		try{
			stmt=con.prepareStatement(sql);
			int judge=stmt.executeUpdate();
			if(judge!=0){
				return "success";
			}
			else{
				return "failed";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "wrong";
		}
		finally{
			try{
				DBhelper.closePreparedStatement(stmt);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
	}
	
	public String userNameExisted(String userName) {
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="select * from  nuser where userName ='"+userName+"'";
		
			
			ResultSet rs;
			try {
				stat1=con.prepareStatement(sql);
				rs = stat1.executeQuery();
				if(rs.next()){
					return "userName existed";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					DBhelper.closePreparedStatement(stat1);
					DBhelper.closeConneciton(con);
					} catch(Exception e){
						e.getStackTrace();
					}
			}
			
		return "userName not existed";
	}
	
	public String userLogin(String userId, String userPassword){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="select * from  nuser where userIdNumber ='"+userId+"' and userPassword='"+userPassword+"'";
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			while (rs.next()){
				if(rs.getString("userStatus").equals("Not Activated")){
					return "Not Activated";
				}
				return "right";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return "wrong";
		
	}
	
	public boolean isHaveAccount(String userName,String userId){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="select * from  user where userId ='"+userId+"' and userName='"+userName+"'";
		
		try{
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return false;
	}
	
	public String getUserName(String userId){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		ResultSet rs=null;
		String sql="select * from  nuser where userIdNumber ='"+userId+"';";
		
		try{
			stat1=con.prepareStatement(sql);
			rs=stat1.executeQuery();
			if(rs.next()){
				return rs.getString("userName");
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return null;
	}
	
	public ArrayList getUserCard(String userId){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="select * from hsbc.card where userId='"+userId+"';";
		
		try{
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			ArrayList list=new ArrayList();
			while(rs.next()){
				userCard user=new userCard((String)rs.getString("cardNumber"),(String)rs.getString("balance"),(String)rs.getString("status"));
				list.add(user);
			}
			return list;
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
				} catch(Exception e){
					e.getStackTrace();
				}
		}
		return null;
	}
	
	//(5.30整合)
	public List selectUserCard(String userIdNumber){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="select cardNumber from  card where userId ='"+userIdNumber+"'";
		List cardList;
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			cardList=new ArrayList();
			while(rs.next()){
				cardList.add((String)rs.getString("cardNumber"));
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
		
				DBhelper.closePreparedStatement(stat1);
				DBhelper.closeConneciton(con);
			
					
				
		}
		return cardList;
		
	}
	public String getCardBalance(String cardNumber){
		Connection con = DBhelper.connect();
		PreparedStatement stat1 = null;
		String sql="select balance from  card where cardNumber ='"+cardNumber+"'";
		String balance=null;
		try {
			stat1=con.prepareStatement(sql);
			ResultSet rs=stat1.executeQuery();
			rs.next();
			balance=(String) rs.getString("balance");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;	
	}
	
	//2016.6.6 查询fund表中cardNumber下所拥有的基金
	public ArrayList getFund(String cardNumber){
		Connection conn = DBhelper.connect();
		PreparedStatement stmt = null;
		ArrayList list=new ArrayList();
		String sql="select * from hsbc.fund where cardNumber='"+cardNumber+"';";
		FundDao fundDao=new FundDao();
		
		try{
			stmt=conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			rs.last();
			int j=rs.getRow();
			rs.beforeFirst();
			
			while(rs.next()){
				//for(int i=0;i<j;i++){					
					String fundName=rs.getString("fundName");
					String fundCode=rs.getString("fundCode");
					String fundType=rs.getString("fundType");
					String rate=rs.getString("rate");
					String sum=rs.getString("sum");
					String amount=rs.getString("amount");
					amount=amount.replace(",", "");
					sum=sum.replace(",", "");
					
					String[] detail=fundDao.fundDetail(fundCode);
					String Snav=detail[3];
					Snav=Snav.replace(",", "");
					DecimalFormat df = new DecimalFormat("0.00");
					double SredeemTotal=Double.parseDouble(Snav)*Double.parseDouble(amount);
					double redeemTotal=Double.parseDouble(df.format(SredeemTotal));
					double income=Double.parseDouble(df.format(redeemTotal-1.015*Double.parseDouble(sum)));
					Fund fund=new Fund(fundName,fundCode,fundType,rate,income,redeemTotal,cardNumber,amount,sum);
					list.add(fund);
				//}
			}
			return list;
		} catch(SQLException e){
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
	
	//6.3 getonline account status
		public String getNUserStatus(String userIdNumber){
			Connection con = DBhelper.connect();
			PreparedStatement stat1 = null;
			String sql="select userStatus from  nuser where userIdNumber ='"+userIdNumber+"'";
			String userStatus=null;
			try {
				stat1=con.prepareStatement(sql);
				ResultSet rs=stat1.executeQuery();
				while(rs.next()){
					userStatus=(String) rs.getString("userStatus");
					if(userStatus.equals("Not Activated")){
						return "The account is not activated";
					}
					if(userStatus.equals("Normal")){
						return "The account is  activated";
					}
					
				}
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "The account is not created";
			}
			
			return "The account is not created";
			
			
		}
		
		//判断卡是否被冻结
		public boolean isFrozen(String cardNumber){
			Connection conn = DBhelper.connect();
			PreparedStatement stmt = null;
			String sql="select * from hsbc.card where cardNumber='"+cardNumber+"';";
			
			try{
				stmt=conn.prepareStatement(sql);
				ResultSet rs=stmt.executeQuery();
				while(rs.next()){
					String status=rs.getString("status");
					if(status.equals("Frozen")){
						return true;
					}
					else{
						return false;
					}
				}
			} catch(SQLException e){
				e.getStackTrace();
			} finally{
				if(stmt!=null){
					DBhelper.closePreparedStatement(stmt);
				}
				if(conn!=null){
					DBhelper.closeConneciton(conn);
				}
			}
			return false;
		}
		
		//6.11在currency表查询有什么外汇
		public String[] cardCurrency(String cardNumber){
			Connection conn = DBhelper.connect();
			PreparedStatement stmt = null;
			String sql="select * from hsbc.currency where cardNumber='"+cardNumber+"';";
			
			try{
				stmt=conn.prepareStatement(sql);
				ResultSet rs=stmt.executeQuery();
				rs.last();
				int i=rs.getRow();
				rs.beforeFirst();
				String[] currencyInfo=new String[i];
				while(rs.next()){
					int j=rs.getRow()-1;
					String type=rs.getString("type");
					double amount=Double.parseDouble(rs.getString("amount"));
					String Samount=new DecimalFormat(" ,##0.00").format(amount);
					String info="Foreign Currency Balance: "+type+" "+Samount;
					currencyInfo[j]=info;
				}	
				return currencyInfo;
			} catch(SQLException e){
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
		
		public String[] cardCurrencyFu(String cardNumber){
			Connection conn = DBhelper.connect();
			PreparedStatement stmt = null;
			String sql="select * from hsbc.currency where cardNumber='"+cardNumber+"';";
			
			try{
				stmt=conn.prepareStatement(sql);
				ResultSet rs=stmt.executeQuery();
				rs.last();
				int i=rs.getRow();
				rs.beforeFirst();
				String[] currencyInfo=new String[i];
				while(rs.next()){
					int j=rs.getRow()-1;
					String type=rs.getString("type");
					double amount=Double.parseDouble(rs.getString("amount"));
					String Samount=new DecimalFormat(" ,##0.00").format(amount);
					String info=type+" "+Samount+"  ";
					currencyInfo[j]=info;
				}	
				return currencyInfo;
			} catch(SQLException e){
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
		
		public String[] cardCurrencyType(String cardNumber){
			Connection conn = DBhelper.connect();
			PreparedStatement stmt = null;
			String sql="select * from hsbc.currency where cardNumber='"+cardNumber+"';";
			
			try{
				stmt=conn.prepareStatement(sql);
				ResultSet rs=stmt.executeQuery();
				rs.last();
				int i=rs.getRow();
				rs.beforeFirst();
				String[] currencyInfo=new String[i];
				while(rs.next()){
					int j=rs.getRow()-1;
					String type=rs.getString("type");
					String info=type;
					currencyInfo[j]=info;
				}	
				return currencyInfo;
			} catch(SQLException e){
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
		
		public boolean haveCurrency(String cardNumber){
			Connection conn = DBhelper.connect();
			PreparedStatement stmt = null;
			String sql="select * from hsbc.currency where cardNumber='"+cardNumber+"';";
			
			try{
				stmt=conn.prepareStatement(sql);
				ResultSet rs=stmt.executeQuery();
				if(rs.next()){
					return true;
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
			return false;
		}
		
		public String havefund(String cardNumber){
			Connection conn = DBhelper.connect();
			PreparedStatement stmt = null;
			String sql="select * from hsbc.fund where cardNumber='"+cardNumber+"';";
			
			try{
				stmt=conn.prepareStatement(sql);
				ResultSet rs=stmt.executeQuery();
				String fundInfo="";
				while(rs.next()){
					String code=rs.getString("fundCode");
					String amount=rs.getString("amount");
					fundInfo="Fund Code: "+code+", Shares: "+amount+"  ";
				}
				return fundInfo;
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
}
