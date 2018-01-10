
package cn.edu.ldu;
import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import cn.edu.ldu.util.LMessage;
import java.awt.Toolkit;
/**
 *
 * @author 罗中运
 */
public class ListUI extends javax.swing.JFrame {
    InetAddress remoteAddr;
    String remoteName;
    int remotePort=60000;
    DatagramSocket LSocket;
    Message msg=new Message();
    LMessage listmsg=new LMessage();
    DatagramSocket clientSocket;
  DatagramSocket ChatSocket;
    private byte[] data=new byte[8096]; //8K字节数组
    /**
     * Creates new form ListUI
     */
    
    public ListUI(DatagramSocket clientSocket,DatagramSocket ChatSocket,Message msg) throws HeadlessException, SocketException {
        this();

        try {
            
            try {
                this.clientSocket = new DatagramSocket();
                
            } catch (SocketException ex) {
                Logger.getLogger(ListUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.ChatSocket=ChatSocket;
            this.clientSocket=clientSocket;
            this.msg=msg;
            //tianjianlist 
            remoteName="127.0.0.1";
            remoteAddr=InetAddress.getByName(remoteName);
           
          
            listmsg.setUserId(msg.getUserId());
            listmsg.setType("M_LOGIN"); //登录消息类型
            listmsg.setToAddr(remoteAddr); //目标地址
            listmsg.setToPort(remotePort); //目标端口
            byte[] data=Translate.ObjectToByte(listmsg); //消息对象序列化
            //定义登录报文
            DatagramPacket listpacket=new DatagramPacket(data,data.length,remoteAddr,remotePort);
            //发送登录报文
            LSocket.send(listpacket);
            ListMessage listThread;
            listThread = new ListMessage(LSocket,this);
            listThread.start();//启动消息线程
            
            ChatMessage chatMessage=new ChatMessage(ChatSocket);
            chatMessage.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ListUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public ListUI() {
        try {
            this.LSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ListUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width);
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height)/2;
        this.setLocation(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        FriendList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/bankground .jpg"))); // NOI18N

        jButton1.setText("群1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("群2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("在线好友列表"));

        FriendList.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        FriendList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "123", "000", "好友3", "好友4", "好友5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        FriendList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FriendListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FriendListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(FriendList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
            
            ClientUI client=new ClientUI(clientSocket,msg); //创建客户机界面
            client.setTitle(msg.getUserId()); //设置标题
            client.setVisible(true); //显示会话窗体 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
     
            group2Client client=new group2Client(msg.getUserId()); //创建群2界面
            client.jf.setTitle(msg.getUserId() + "(" + client.sc.getLocalSocketAddress() + ")");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
      try {
            msg.setType("M_QUIT"); //消息类型
            msg.setText(null);
            data=Translate.ObjectToByte(msg); //消息对象序列化
            //构建发送
            DatagramPacket packet=new DatagramPacket(data,data.length,msg.getToAddr(),msg.getToPort());       
            clientSocket.send(packet); //发送
           
            listmsg.setType("M_QUIT"); //登录消息类型
            byte[] data=Translate.ObjectToByte(listmsg); //消息对象序列化
            //定义登录报文
            DatagramPacket listpacket=new DatagramPacket(data,data.length,remoteAddr,remotePort);
            //发送登录报文
            LSocket.send(listpacket);
            
        } catch (IOException ex) { }
        clientSocket.close(); //关闭套接字
    }//GEN-LAST:event_formWindowClosing

    private void FriendListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FriendListMouseClicked
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_FriendListMouseClicked

    private void FriendListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FriendListMousePressed
        // TODO add your handling code here:
        System.out.println(FriendList.getSelectedValue());
        FriendList.getSelectedValuesList();
        ChatpageUI chatpage=new ChatpageUI(FriendList.getSelectedValue(),clientSocket); //创建客户机界面
        chatpage.setTitle(msg.getUserId()); //设置标题
        chatpage.setVisible(true); //显示会话窗体
    }//GEN-LAST:event_FriendListMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JList<String> FriendList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
