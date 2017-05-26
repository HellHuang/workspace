package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.hsbc.study.dao.DBhelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class updateZichan {
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
    public static void main(String[] args){
    	getRequest3();
    	System.out.println("资产配置数据库更新完成");
    }
    
    //连接接口
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
                sb.append("\r\n");
            }
            reader.close();
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
  //3.资产配置存进数据库,管理员操作
    public static void getRequest3(){
    	
	    	Connection conn=DBhelper.connect();
	    	PreparedStatement stmt=null;
	    	PreparedStatement stmt1=null;
	    	
	    	try{
	    		String Sql="Truncate table hsbc.zichan;";
	    		stmt1=conn.prepareStatement(Sql);
	    		stmt1.executeUpdate();
	    	} catch(SQLException e){
	    		e.getStackTrace();
	    	} finally{
	    		if(stmt1!=null){
	    			DBhelper.closePreparedStatement(stmt1);
	    		}
	    	}
	    	
	    	String APPKEY ="5cd122bc05b270eb8d068926722be0b4";
	        String result =null;
	        String url ="http://web.juhe.cn:8080/fund/findata/config";//请求接口地址
	        Map params = new HashMap();//请求参数
	            params.put("key",APPKEY);//APPKEY值
	 
	        try {
	            result =net(url, params, "GET");
	            JSONObject object = JSONObject.fromObject(result);
	            if(object.getInt("error_code")==0){
	            	for(int i=1;i<3500;i++){
	            		JSONArray resultArray=object.getJSONArray("result");
	                	JSONObject Result=resultArray.getJSONObject(0);
	                	String index=String.valueOf(i);
	                	JSONObject alone=Result.getJSONObject(index);
	                	String code=alone.getString("code");
	                	String info="总资产(元)"+alone.getString("totalass")+" 股票投资比例(%)"+alone.getString("stockrat")+" 债券和货币比例(%)"+alone.getString("bcrate");
	                	String sql="insert into hsbc.zichan values('"+code+"','"+info+"');";
	                	try{
	                		stmt=conn.prepareStatement(sql);
	                		stmt.executeUpdate();
	                	} catch(SQLException e){
	                		e.getStackTrace();
	                	} finally{
	                		if(stmt!=null){
	                			DBhelper.closePreparedStatement(stmt);
	                		}
	                	}
	            	}                
	            }else{
	                System.out.println(object.get("error_code")+":"+object.get("reason"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("失败2");
	        } finally{
	        	DBhelper.closeConneciton(conn);
	        } 	
    }
    
}
