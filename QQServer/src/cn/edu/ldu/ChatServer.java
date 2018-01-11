package cn.edu.ldu;

import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import cn.edu.ldu.util.User;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

import java.net.SocketException;

public class ChatServer extends Thread {
    private DatagramSocket serverSocket; //服务器套接字
    private DatagramPacket packet;  //报文
    private List<User> userList=new ArrayList<User>(); //用户列表
    private byte[] data=new byte[8096]; //8K字节数组
   
    
    /**
     * 构造函数
     * @param socket 会话套接字
     * @param parentUI 父类
     */
    public ChatServer() throws SocketException {
        serverSocket=new DatagramSocket(20000);
    }
    @Override
    public void run() {  
        while (true) { //循环处理收到的各种消息
            try {
            packet=new DatagramPacket(data,data.length);//构建接收报文
            serverSocket.receive(packet);//接收客户机数据receive
            //收到的数据转为消息对象
            Message msg=(Message)Translate.ByteToObject(packet.getData());
            String userId=msg.getUserId();//当前消息来自用户的id      
             System.out.println("1");
  
            String touserString=msg.getTargetUser();
             System.out.println("4");
            if (msg.getType().equalsIgnoreCase("M_LOGIN")) { //是M_LOGIN消息 
                    Message backMsg=new Message();
                
                    backMsg.setType("M_SUCCESS");
                    byte[] buf=Translate.ObjectToByte(backMsg);
                    DatagramPacket backPacket=new DatagramPacket(buf,buf.length,packet.getAddress(),packet.getPort());//向登录用户发送的报文
                    serverSocket.send(backPacket); //发送   
                    
                    User user=new User();
                    user.setUserId(userId); //用户名
                    user.setPacket(packet); //保存收到的报文
                    userList.add(user); //将新用户加入用户列表

            }else if (msg.getType().equalsIgnoreCase("M_QUIT")) { //是M_QUIT消息
                //删除用户
                for(int i=0;i<userList.size();i++) {
                    if (userList.get(i).getUserId().equals(userId)) {
                        userList.remove(i);
                        break;
                    }
                }//end for
            }else if(msg.getType().equalsIgnoreCase("M_USER")){
                 System.out.println("5");
                int portString=0;

                for (int i=0;i<userList.size();i++) { 
                       System.out.println(userList.get(i).getUserId()+"port"+userList.get(i).getPacket().getPort());
                      if (touserString.equals(userList.get(i).getUserId())){
                         portString=userList.get(i).getPacket().getPort();
                      }
                }
                System.out.println("6");
                Message m=new Message();
                m.setTargetPort(portString);
                byte[] buffer=Translate.ObjectToByte(m);
      
                System.out.println(msg.getToPort()+1);
                System.out.println(portString);
                DatagramPacket newPacket=new DatagramPacket(buffer,buffer.length,packet.getAddress(),msg.getToPort()+1);
                serverSocket.send(newPacket);
                System.out.println("7");
                
            }//end if
            } catch (Exception e) {
                System.out.println("ChatServer");
            }
        }//end while
    }//end run
}//end class
