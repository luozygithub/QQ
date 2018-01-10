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

public class ChatMessage extends Thread{
    private DatagramSocket clientSocket; //会话套接字
    private ChatpageUI parentUI; //父类
    private byte[] data=new byte[8096]; //8K字节数组
    

    //构造函数
    public ChatMessage(DatagramSocket socket) {
        clientSocket=socket; //会话套接字
    }  
    @Override
    public void run() {
        boolean flag=false;
        while (true) { //无限循环，处理收到的各类消息
          try {
                  System.out.println("1");
            DatagramPacket packet=new DatagramPacket(data,data.length); //构建接收报文
            clientSocket.receive(packet); //接收  
            Message msg=(Message)Translate.ByteToObject(data);//还原消息对象
                  System.out.println("2"+msg.getType()+clientSocket.getLocalPort());
            if((!flag)&&(msg.getType().equalsIgnoreCase("siliao"))){
                parentUI=new ChatpageUI(); //创建客户机界面
                parentUI.setTitle(msg.getUserId()); //设置标题
                parentUI.setVisible(true);
                flag=true;
                  System.out.println("limian"+msg.getText());
            }
                System.out.println("3"+msg.getText());
     
            if(flag&&(msg.getType().equalsIgnoreCase("siliao")))
                       parentUI.jTextAreaPriChatPanel.append(msg.getText());
                System.out.println("4"+msg.getText());
            //playSound("/cn/edu/ldu/sound/msg.wav");
             
          }catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "?????","错误提示",JOptionPane.ERROR_MESSAGE);
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
}//end class
