/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu;

import cn.edu.ldu.util.Translate;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 罗中运
 */
public class tset  extends Thread {
      private DatagramSocket serverSocket; //服务器套接字
      private DatagramPacket packet;  
      private byte[] data=new byte[8096]; //8K字节数组
      
    public void run() {  
          String hostName="127.0.0.1";
           int hostPort=Integer.parseInt("51110");
        
          try {
              serverSocket=new DatagramSocket(hostPort);
              System.out.println("cn.edu.ldu.tset.run()");
          } catch (SocketException ex) {
              Logger.getLogger(tset.class.getName()).log(Level.SEVERE, null, ex);
          }
        while (true) { //循环处理收到的各种消息
            
                  //获取服务器工作地址端口
      
            //创建UDP数据报套接字,在指定端口侦听
            System.out.println("cn.edu.ldu.tset.run()");
          //  serverSocket.bind(hostName);
                packet=new DatagramPacket(data,data.length);//构建接收报文
              try {
                  serverSocket.receive(packet);//接收客户机数据
              } catch (IOException ex) {
                  Logger.getLogger(tset.class.getName()).log(Level.SEVERE, null, ex);
              }
                              System.out.println("cn.edu.ldu.tset.run()");
                cn.edu.ldu.util.Message msg=(cn.edu.ldu.util.Message)Translate.ByteToObject(packet.getData());
                               System.out.println("cn.edu.ldu.tset.run()");
                String userId=msg.getUserId();//当前消息来自用户的id            
                String password=msg.getPassword();//当前消息来自用户的  
                System.err.println(userId+password);
            
            
          }
        
    }
    
                
}
