package cn.edu.ldu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 *
 * @author 罗中运
 */
public class group2Client {
	
	JFrame jf = new JFrame();
	JTextArea jtainput = new JTextArea();
	JScrollPane jsp = new JScrollPane(jtainput);
	JTextArea jtaoutput = new JTextArea();
	JScrollPane jsp1 = new JScrollPane(jtaoutput);
	JButton jb = new JButton("发送");
	JButton jb_self = new JButton("私聊");
	JPanel jp = new JPanel();
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	Socket sc;
	ObjectOutputStream oos;
	Message cm;
	DefaultListModel dlm = new DefaultListModel();
	JList jl = new JList(dlm);
	JLabel jl2 = new JLabel("好友在线列表：");
	JPanel jp_list = new JPanel();
	String[] str = {"在线","隐身"};
	JComboBox jcb = new JComboBox(str);
	private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {
            if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
                cm = new Message(jf.getTitle(),jtaoutput.getText());				
				
				try {
					
					oos.writeObject(cm);					
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				
				jtaoutput.setText("");
            }
        }
	public group2Client(String clientname){
			
                JLabel newlabel = new JLabel("1501罗中运20152203162");
                jp1.add(newlabel);
		jp.setLayout(new GridLayout(2,1,10,10));
		jp.add(jsp);
		jp.add(jsp1);
		jf.setSize(60,60);
		jf.setBounds(600, 600, 600, 600);
                 jf.setLocationRelativeTo(null); 
		jf.add(jp,BorderLayout.CENTER);
		jp1.add(jb);
		jp1.add(jb_self);
		jb_self.setEnabled(false);
		jf.add(jp1,BorderLayout.SOUTH);	
		jp2.setLayout(new BorderLayout());		
		jp2.add(jl2,BorderLayout.NORTH);
		jp2.add(jl,BorderLayout.CENTER);
		jp2.add(jcb,BorderLayout.SOUTH);		
		dlm.addElement(new String(clientname));		
		jf.add(jp2,BorderLayout.EAST);		
		jf.setVisible(true);
		jp.setBorder(javax.swing.BorderFactory.createTitledBorder("聊天窗口"));
                jp2.setBorder(javax.swing.BorderFactory.createTitledBorder("用户列表（地址和端口）"));
		//选择私聊对象，私聊按钮才可用
		jl.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成方法存根
				jb_self.setEnabled(true);				
			}
		});
		jtaoutput.addKeyListener(new java.awt.event.KeyAdapter() {
                         public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtPasswordKeyPressed(evt);
                     }
                  });
                
        
		//send按钮（给服务器发信息）
		jb.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根
				
				cm = new Message(jf.getTitle(),jtaoutput.getText());				
				
				try {
					
					oos.writeObject(cm);					
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				
				jtaoutput.setText("");
			}
		});
		
		//私聊按钮（带有自己的姓名，消息，还有私聊对象的信息，并以“only”开头，代表是私聊消息）
		jb_self.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根				
				
				cm = new Message( "only#" + jl.getSelectedValue() + "#" + sc.getLocalSocketAddress() + "#"+ jf.getTitle() + "#",jtaoutput.getText());
				try {
					oos.writeObject(cm);
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				jtainput.append("我说：\n" + cm.msg + "\n");
				jtaoutput.setText("");
				jl.clearSelection();
				jb_self.setEnabled(false);
			}
		});
		
		try {
			sc = new Socket("127.0.0.1",40000);
			oos = new ObjectOutputStream(sc.getOutputStream());
			
			
		} catch (UnknownHostException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		ClientThread ct = new ClientThread();
		ct.start();
		//关闭
		jf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				// TODO 自动生成方法存根
				cm = new Message("exit",sc.getLocalSocketAddress().toString());
				try {
					oos.writeObject(cm);
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					//e.printStackTrace();
				}
			}
		});
		
		//状态选择

		jcb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根
				
				//System.out.println(jcb.getSelectedItem()); //测试
				 if(jcb.getSelectedItem().equals("隐身")){
					
					cm = new Message("hide",sc.getLocalSocketAddress().toString());
					try {
						oos.writeObject(cm);
					} catch (IOException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}else{
					jb.setVisible(true);
					jb_self.setVisible(true);
					cm = new Message("again",sc.getLocalSocketAddress().toString());
					try {
						oos.writeObject(cm);
					} catch (IOException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
					
				}
			}
		});
		
	}
	
	class ClientThread extends Thread{
		
		public void run() {
			// TODO 自动生成方法存根
			
			Message m;
				try {
					ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
					while(true){
						m = (Message)ois.readObject();
						if(m.name.equals("##")){
							//jtainput.append(m.toString()); //test
							dlm.removeAllElements();
							//System.out.println("###########"); //测试
							String[] sss1 = m.toString().split("\n");
							String[] ss = sss1[1].toString().split("/");
							for(int i = 0;i < ss.length;i ++){
								//System.out.println(ss[i]);
								dlm.addElement(ss[i]);
							}
							
						}else{
							jtainput.append(m.toString());
						}											
					}
					
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					//e.printStackTrace();
					System.out.println("服务器端断开");
				} catch (ClassNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
		}		
	}
	
	public void addfriend(String name){
		dlm.addElement(name);
	}
}

