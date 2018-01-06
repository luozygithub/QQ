
package cn.edu.ldu.util;


import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;

import com.mysql.jdbc.Statement;

public class Dbutil {
	private static String dbUrl="jdbc:mysql://localhost:3307/qq?useUnicode=true&characterEncoding=UTF-8";
	private static String dbUseName="root";
	private static String dbPassword="123456";
	private static String jdbcName="com.mysql.jdbc.Driver";
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection connection=DriverManager.getConnection(dbUrl, dbUseName, dbPassword);
		return connection;
	}
	public  void closeCon(Connection c)throws Exception{
		
		if(c!=null){
			c.close();
		}
		
			
	}
	public  void closeCon(Statement statement, Connection c)throws Exception{
		if(statement!=null){
			statement.close();
			if(c!=null){
				c.close();
			}
		}
			
	}
	
	@SuppressWarnings("unused")
	private  void closeCon(PreparedStatement pStatement,Connection connection) throws Exception{
		if(pStatement!=null){
			pStatement.close();
			if(connection!=null){
				connection.close();
			}
		}
	}
}
