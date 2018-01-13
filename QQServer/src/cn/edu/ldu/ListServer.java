package cn.edu.ldu;

import cn.edu.ldu.util.Translate;
import cn.edu.ldu.util.User;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import java.net.SocketException;
import cn.edu.ldu.util.LMessage;

public class ListServer extends Thread {
    private DatagramSocket serverSocket; //服务器套接字
    private DatagramPacket packet;  //报文
    private List<User> userList=new ArrayList<User>(); //用户列表
    private byte[] data=new byte[8096]; //8K字节数组

    
    public ListServer() throws SocketException {
          serverSocket=new DatagramSocket(60000);
    }
    @Override
    public void run() {  
        while (true) { //循环处理收到的各种消息
           try{

                packet=new DatagramPacket(data,data.length);//构建接收报文
                serverSocket.receive(packet);//接收客户机数据
            //收到的数据转为消息对象

                LMessage lmsg=(LMessage)Translate.ByteToObject(packet.getData());
                String userId=lmsg.getUserId();//当前消息来自用户的id            

            if (lmsg.getType().equalsIgnoreCase("M_LOGIN")) { //是M_LOGIN消息 
                    LMessage backMsg=new LMessage();
                 
                    byte[] buf=Translate.ObjectToByte(backMsg);
                    DatagramPacket backPacket=new DatagramPacket(buf,buf.length,packet.getAddress(),packet.getPort());//向登录用户发送的报文
                    serverSocket.send(backPacket); //发送   
                    
                    User user=new User();
                    user.setUserId(userId); //用户名
                    user.setPacket(packet); //保存收到的报文
                    userList.add(user); //将新用户加入用户列表

                    
                    //向所有其他在线用户发送M_LOGIN消息，向新登录者发送整个用户列表
                    for (int i=0;i<userList.size();i++) { //遍历整个用户列表                                       
                        //向其他在线用户发送M_LOGIN消息
                        if (!userId.equalsIgnoreCase(userList.get(i).getUserId())){
                            DatagramPacket oldPacket=userList.get(i).getPacket(); 
                            DatagramPacket newPacket=new DatagramPacket(data,data.length,oldPacket.getAddress(),oldPacket.getPort());//向其他用户发送的报文
                            serverSocket.send(newPacket); //发送
                        }//end if
                        //向当前用户回送M_ACK消息，将第i个用户加入当前用户的用户列表
                        LMessage other=new LMessage();
                        other.setUserId(userList.get(i).getUserId());
                        other.setType("M_ACK");
                        byte[] buffer=Translate.ObjectToByte(other);
                        DatagramPacket newPacket=new DatagramPacket(buffer,buffer.length,packet.getAddress(),packet.getPort());
                        serverSocket.send(newPacket);
                    }//end for                  
                //end if                           
            }else if (lmsg.getType().equalsIgnoreCase("M_QUIT")) { //是M_QUIT消息
                //更新显示

                for(int i=0;i<userList.size();i++) {
                    if (userList.get(i).getUserId().equals(userId)) {
                        userList.remove(i);
                        break;
                    }
                }//end for
   
                for (int i=0;i<userList.size();i++) {
                    DatagramPacket oldPacket=userList.get(i).getPacket();
                    DatagramPacket newPacket=new DatagramPacket(data,data.length,oldPacket.getAddress(),oldPacket.getPort());
                    serverSocket.send(newPacket);
                }//end for 
            }else if(lmsg.getType().equalsIgnoreCase("M_USER")){
                int portString=0;
                String touserString=lmsg.getTargetUser();
                for (int i=0;i<userList.size();i++) { 
                       System.out.println(userList.get(i).getUserId()+"port"+userList.get(i).getPacket().getPort());
                      if (touserString.equals(userList.get(i).getUserId())){
                         portString=userList.get(i).getPacket().getPort();
                      }
                }
                lmsg.setType("siliao");
                lmsg.setText("say:"+lmsg.getText());
                lmsg.setTargetId(userId);
                byte[] buffer=Translate.ObjectToByte(lmsg);
                DatagramPacket newPacket=new DatagramPacket(buffer,buffer.length,packet.getAddress(),portString);
                serverSocket.send(newPacket);
            }else if(lmsg.getType().equalsIgnoreCase("xiaoxi")){
                    System.out.println("xiaoxi");
                 System.out.println("8");
                int portString=0;
                 String touserString=lmsg.getTargetUser();
                for (int i=0;i<userList.size();i++) { 
                       System.out.println(userList.get(i).getUserId()+"port"+userList.get(i).getPacket().getPort());
                      if (touserString.equals(userList.get(i).getUserId())){
                         portString=userList.get(i).getPacket().getPort();
                      }
                }
                System.out.println("9");
     
      
                lmsg.setType("xiaoxi");
                System.out.println(lmsg.getText());
                lmsg.setTargetId(userId);
                lmsg.setText("say:"+lmsg.getText());//把消息加上谁说的
                byte[] buffer=Translate.ObjectToByte(lmsg);
                DatagramPacket newPacket=new DatagramPacket(buffer,buffer.length,packet.getAddress(),portString);
                serverSocket.send(newPacket);
                System.out.println("10");
            }
            } catch (Exception e) { 
                System.out.println("ListSERVER");
            }
        }//end while
    }//end run
}//end class
