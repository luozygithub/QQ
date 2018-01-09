package cn.edu.ldu;

import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.URL;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import cn.edu.ldu.util.LMessage;
public class ListMessage extends Thread{
    private DatagramSocket ListSocket; //会话套接字
    private ListUI parentUI; //父类
    private byte[] data=new byte[8096]; //8K字节数组
    
    private DefaultListModel listModel=new DefaultListModel(); //列表Model
    //构造函数
    public ListMessage(DatagramSocket socket,ListUI parentUI) {
        ListSocket=socket; //会话套接字
        this.parentUI=parentUI; //父类
        listModel=new DefaultListModel(); 
    }  

  
    @Override
    public void run() {
        while (true) { //无限循环，处理收到的各类消息
                parentUI.FriendList.setModel(listModel);
          try {
              System.out.println("kehuduan");
            DatagramPacket packet=new DatagramPacket(data,data.length); //构建接收报文
            ListSocket.receive(packet); //接收
             System.out.println("kehuduan");
            LMessage lmsg=(LMessage)Translate.ByteToObject(data);//还原消息对象
            String userId=lmsg.getUserId(); //当前用户id
            //根据消息类型分类处理
            if (lmsg.getType().equalsIgnoreCase("M_LOGIN")) { //是其他用户的登录消息
                playSound("/cn/edu/ldu/sound/fadeIn.wav");//上线提示音  
                //更新消息窗口
         
                //新上线用户加入列表
                listModel.add(listModel.getSize(), userId);
                parentUI.FriendList.setModel(listModel);
                
            }else if (lmsg.getType().equalsIgnoreCase("M_ACK")) { //是服务器确认消息
                //登录成功，将加入用户列表
                listModel.add(listModel.getSize(), userId);
                parentUI.FriendList.setModel(listModel);
            }else if (lmsg.getType().equalsIgnoreCase("M_QUIT")) { //是其他用户下线消息
                playSound("/cn/edu/ldu/sound/leave.wav");//消息提示音  
                //更新消息窗口
                listModel.remove(listModel.indexOf(userId));
                parentUI.FriendList.setModel(listModel);
            }//end if
          }catch (Exception ex) {
              System.out.println("ListMessage");
          }//end try
        } //end while
    }//end run
    /**
     * 播放声音文件
     * @param filename 声音文件路径和名称
     */
    private void playSound(String filename) {
        URL url = AudioClip.class.getResource(filename);
        AudioClip sound;
        sound = Applet.newAudioClip(url);
        sound.play();
    }

    void setUserId(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end class
