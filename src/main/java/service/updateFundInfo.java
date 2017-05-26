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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import com.hsbc.study.dao.DBhelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class updateFundInfo {
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
    public static void main(String[] args){
    	getRequest4();
    	System.out.println("全部基金信息数据库更新完成");
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
    
  //4.全部开放基金存入数据库,每天更新一次。
    public static void getRequest4(){
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt1=null;
    	PreparedStatement stmt=null;
    	try{
    		String Sql="Truncate table hsbc.funds;";
    		stmt1=conn.prepareStatement(Sql);
    		stmt1.executeUpdate();
    	} catch(SQLException e){
    		e.getStackTrace();
    	} finally{
    		if(stmt1!=null){
    			DBhelper.closePreparedStatement(stmt1);
    		}
    	}
    	
    	String APPKEY ="a6f47d6941e9ba875b48c50958d0c356";
        String url ="http://japi.juhe.cn/jingzhi/query.from";//请求接口地址
        for(int j=1;j<190;j++){
	        Map params = new HashMap();//请求参数
	            params.put("key",APPKEY);//APPKEY值
	            params.put("pagesize", 20);
	            params.put("page",j);
	            params.put("type", "netvalue");
	 
	        try {
	            String result =net(url, params, "GET");
	            JSONObject object = JSONObject.fromObject(result);            
	            NumberFormat formatter = new DecimalFormat("0.00%");
	            if(object.getInt("error_code")==0){
	            	JSONArray resultArray=object.getJSONArray("result");
	                for(int i=0;i<20;i++){
	                	JSONObject results=resultArray.getJSONObject(i);
	                	String Name=results.getString("sname");
	                	String Type=results.getString("jjlx");
	                	String Code=results.getString("symbol");
	                	String Nav_change=results.getString("nav_a");
	                	String Nav_Y=results.getString("yesterday_nav");
	                	double navChange=Double.parseDouble(Nav_change);
	                	double navY=Double.parseDouble(Nav_Y);
	                	if(navY!=0&&navChange!=0){
		                	double rate=navChange/navY*14.6;
		                	String returnRate=formatter.format(rate);
		                	String sql="insert into hsbc.funds values('"+Name+"','"+Code+"','"+Type+"','"+returnRate+"');";
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
	                	else{
	                	}
	                }
	            }else{
	                System.out.println(object.get("error_code")+":"+object.get("reason"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
    }
}
