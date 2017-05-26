package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hsbc.study.dao.DBhelper;
import com.hsbc.study.entity.Fund;

public class HttpGet{
	public static void main(String[] args) throws SQLException, IOException{
		String navInfo="";
		Connection con = DBhelper.connect();
		String code="";
		PreparedStatement stat2 = null;
		String sql2 = null;
		sql2="select code from funds limit 200";
		stat2 = con.prepareStatement(sql2);
	
		ResultSet rs = stat2.executeQuery();
		 File file = new File("d:", "add.txt"); 
		 FileOutputStream in = null;
		while (rs.next()){
//			PreparedStatement stat = null;
//			String sql = null;
			try {
				// Open connection to URL for reading.
				code=(String)rs.getString("code");
				System.out.println(code);
				URL pageUrl=new URL("http://fund.eastmoney.com/"+code+".html");
				BufferedReader reader =
						new BufferedReader(new InputStreamReader(pageUrl.openStream(),"utf-8"));

				// Read page into buffer.
				String line;
				StringBuffer pageBuffer = new StringBuffer();
				while ((line = reader.readLine()) != null) {
					pageBuffer.append(line);
				
				}
				String content=pageBuffer.toString();
				Pattern p=Pattern.compile((".*日期(.*)更多净值信息"));
				Matcher m=p.matcher(content);  
				System.out.println("2");
				String temp[] = null;
				while(m.find())
				{ 
					System.out.println("getttt"+m.group(1)); 
					temp=m.group(1).trim().split("tr>  <td");
					for(int i=1;i<8;i++){
						
						String[] data=temp[i].split("td>  <td");
						//System.out.println(data[0]);
						// System.out.println(data[1]);
						data[0]=data[0].trim().replace("\"", "");
						data[0]=data[0].trim().replace("class=alignLeft", "");
						data[0]=data[0].trim().replace(">", "");
						data[0]=data[0].trim().replace("</", "");

						data[1]=data[1].trim().replace("\"", "");
						data[1]=data[1].trim().replace("class=alignRight bold", "");
						data[1]=data[1].trim().replace(">", "");
						data[1]=data[1].trim().replace("</", "");
						temp[i]=data[0]+":"+data[1];
					}
				
				if (temp!=null){
					String info="";
					for(int i=1;i<8;i++){
						info+=temp[i]+",";
					}
					String s="insert into nav values('"+code+"','"+info+"');"+"\n";
					System.out.println(info);
					 byte bt[] = new byte[1024];  
				     bt = s.getBytes(); 
				     in = new FileOutputStream(file,true); 
				     in.write(bt, 0, bt.length); 
				    
//					sql = "insert into nav values('"+code+"','"+info+"')";
//					stat = con.prepareStatement(sql);
//					stat.execute(sql);
//					int result = stat.executeUpdate(); 
				}


			}
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rs.next();

			}
			

		}
	
		
		
	}
}