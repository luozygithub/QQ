/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.method;
import cn.edu.ldu.cls.User;
import cn.edu.ldu.util.Dbutil;
import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import com.mysql.jdbc.Statement;
import java.net.DatagramPacket;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author 罗中运
 */
public class GetPassword {
    
    public String GetPassword(User user){
        boolean flag=false;
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
                passwordString=rs.getString("password");
                System.out.println(rs.getString("password"));
                String sql2 = "select * from user where Tel='" + Tel + "'";
                rs = statement.executeQuery(sql2);
                if(rs.next()){
                 
                    flag=true;
                    System.out.println("cn.edu.ldu.method.GetPassword.GetPassword()");
                }
            }
       
        } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "出错了");
        }
        if(flag){
            return "您的密码是"+passwordString;
        }else{
            return "id或手机号错误";
        }
    }
}
