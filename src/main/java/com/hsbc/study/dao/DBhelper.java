package com.hsbc.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class DBhelper {
    public static Connection connect() {
    	 String dbDriver="com.mysql.jdbc.Driver";
         String dbUrl="jdbc:mysql://localhost:3306/hsbc?useUnicode=true&characterEncoding=utf8";;
         String dbUser="root";
         String dbPassword="";
         try {           
             Class.forName(dbDriver).newInstance();
             Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
             System.out.println("Success");
             return con;
         } catch (Exception ex) {
         	System.out.println("“Ï≥£"+ex.toString());
             return null;
         }
    }
    public static void closeResult(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
        }
    }
    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException e) {
        }
    }
    public static void closeConneciton(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
        }
    }
}
