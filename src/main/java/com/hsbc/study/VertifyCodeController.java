package com.hsbc.study;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.study.dao.DBhelper;
@Controller
public class VertifyCodeController {
	@RequestMapping(value="/codeVertify",method = RequestMethod.GET)
	public void doDetermind(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Connection con = DBhelper.connect();
		Statement stat = null;
		String sql="select authcode from authcode";
		stat = (Statement) con.createStatement();
		ResultSet rs=stat.executeQuery(sql);
		String code=null;
		while(rs.next()){
			System.out.println("vertifycontorller get"+rs.getString("authcode"));
			code=rs.getString("authcode");
		}
		PrintWriter pw = response.getWriter();
		pw.print(code);
		pw.close();
	}
}
