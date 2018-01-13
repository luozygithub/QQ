
package cn.edu.ldu.util;


import cn.edu.ldu.Cryptography;
import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;
import cn.edu.ldu.cls.User;
import com.mysql.jdbc.Statement;
import java.sql.*;
import javax.swing.JOptionPane;

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
        public void Register(User user){
            try{
                Dbutil dbutil = new Dbutil();
		String sql = "insert into user values('"+user.getId()+"','"+user.getPassword()+"','"+user.getTel()+"','"+user.getNickname()+"')";
		Connection connection = dbutil.getCon();
                Statement statement = (Statement) connection.createStatement();
                statement.executeUpdate(sql);
             }catch(Exception e){
                  JOptionPane.showMessageDialog(null, "用户已被注册", "错误提示", JOptionPane.ERROR_MESSAGE);
             }
        }
        public void ChangePw(User user){
           
               
                String passwordString="";
                try {

                    Dbutil dbutil = new Dbutil();
                    String userId=user.getId();//当前消息来自用户的id            
                    String Tel=user.getTel();//当前消息来自用户的mima         

                    String sql = "select * from user where id='" + userId + "'";
                    Connection connection = dbutil.getCon();
                    java.sql.Statement statement = (java.sql.Statement) connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    if (rs.next()){
                      
                    	String sql2="select * from user where Tel='"+Tel+"'";
				rs=statement.executeQuery(sql2);
				if(rs.next()){
                                    String encryptPassword=Cryptography.getHash(user.getPassword(), "SHA-256");
                                     JOptionPane.showMessageDialog(null, "更新密码成功");
                                    String sql3 ="UPDATE user SET password='"+encryptPassword+"' WHERE id='"+user.getId()+"'";
                                    statement.executeUpdate(sql3);
                                }else{
                                        JOptionPane.showMessageDialog(null, "手机号错误");
                                }
                     
                     
                    }else{
                                   JOptionPane.showMessageDialog(null, "用户名错误");
                    
                }
                }catch(Exception e){
                     JOptionPane.showMessageDialog(null, "更新密码失败", "错误提示", JOptionPane.ERROR_MESSAGE);
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
