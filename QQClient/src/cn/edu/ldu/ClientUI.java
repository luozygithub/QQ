package cn.edu.ldu;

import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.JOptionPane;

/**
 * ClientUI，客户机聊天界面类
 * @author  董相志，版权所有2016--2018，upsunny2008@163.com
 */
public class ClientUI extends javax.swing.JFrame {
    private DatagramSocket clientSocket; //客户机套接字
    private Message msg; //消息对象
    private byte[] data=new byte[8096]; //8K字节数组
    /**
     * Creates new form ClientUI
     */
    public ClientUI() {
        initComponents();
        //设置窗体位置到屏幕中央
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height)/2;
        this.setLocation(x, y);
    }
    /**
     * 构造函数
     * @param socket 客户机会话套接字
     * @param msg 登录消息对象
     */
    public ClientUI(DatagramSocket socket,Message msg) {
        this(); //调用无参数构造函数，初始化界面
        clientSocket=socket; //初始化会话套接字
        this.msg=msg; //登录消息
        //创建客户机消息接收和处理线程
        Thread recvThread=new ReceiveMessage(clientSocket,this);
        recvThread.start();//启动消息线程    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        leftScrollPane2 = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        rightScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        leftScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "会话消息窗口", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("宋体", 1, 14))); // NOI18N

        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        txtArea.setLineWrap(true);
        txtArea.setRows(5);
        leftScrollPane1.setViewportView(txtArea);

        leftScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "发言窗口", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("宋体", 1, 14))); // NOI18N

        txtInput.setColumns(20);
        txtInput.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        txtInput.setLineWrap(true);
        txtInput.setRows(5);
        leftScrollPane2.setViewportView(txtInput);

        btnSend.setBackground(new java.awt.Color(153, 204, 255));
        btnSend.setText("发  送");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        rightScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "在线用户", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("宋体", 1, 14))); // NOI18N

        userList.setBackground(new java.awt.Color(255, 204, 255));
        userList.setFont(new java.awt.Font("宋体", 1, 14)); // NOI18N
        rightScrollPane.setViewportView(userList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSend))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                            .addComponent(leftScrollPane2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leftScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(rightScrollPane)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * 发送消息：“发送”按钮点击事件的处理逻辑
     * @param evt “发送”按钮点击事件
     */
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        try {
            msg.setText(txtInput.getText());//获取输入的文本
            msg.setType("M_MSG"); //普通会话消息
            data=Translate.ObjectToByte(msg);//消息对象序列化
            //构建发送报文
            DatagramPacket packet=new DatagramPacket(data,data.length,msg.getToAddr(),msg.getToPort());
            clientSocket.send(packet); //发送
            txtInput.setText(""); //清空输入框
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }       
    }//GEN-LAST:event_btnSendActionPerformed
    /**
     * 点击窗体关闭按钮，关闭窗体之前发送 "M_QUIT" 下线消息
     * @param evt 窗体事件
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            msg.setType("M_QUIT"); //消息类型
            msg.setText(null);
            data=Translate.ObjectToByte(msg); //消息对象序列化
            //构建发送
            DatagramPacket packet=new DatagramPacket(data,data.length,msg.getToAddr(),msg.getToPort());       
            clientSocket.send(packet); //发送
        } catch (IOException ex) { }
        clientSocket.close(); //关闭套接字
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane leftScrollPane1;
    private javax.swing.JScrollPane leftScrollPane2;
    private javax.swing.JScrollPane rightScrollPane;
    public javax.swing.JTextArea txtArea;
    private javax.swing.JTextArea txtInput;
    public javax.swing.JList<String> userList;
    // End of variables declaration//GEN-END:variables
}