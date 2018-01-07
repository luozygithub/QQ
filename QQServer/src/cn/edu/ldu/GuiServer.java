package cn.edu.ldu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.*;


public class GuiServer {
	
	JFrame jf = new JFrame("群2服务器");
	JTextArea jtainput = new JTextArea();
	JScrollPane jsp = new JScrollPane(jtainput);
	JTextArea jtaoutput = new JTextArea();
	JScrollPane jsp1 = new JScrollPane(jtaoutput);
	JButton jb = new JButton("send");
	JPanel jp = new JPanel();
	JPanel jp1 = new JPanel();
	ObjectOutputStream oos;
	//第一次用的是List存放IP与ObjectOutputStream
//	List l = new ArrayList();
//	List l2 = new ArrayList();
	Map map = new HashMap();
	boolean flag = true;
	Message msg;
	ServerSocket ss;
	Socket s;

	
	public GuiServer(){
		
		jp.setLayout(new GridLayout(2,1,15,15));
		jp.add(jsp);
		jp.add(jsp1);
		//jf.setSize(300, 400);
		jf.setBounds(300, 300, 300, 400);
		jf.add(jp,BorderLayout.CENTER);
		jp1.add(jb);
		jf.add(jp1,BorderLayout.SOUTH);		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		//服务器端群发消息
		jb.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根
				
			    
			    try {
			    	//Iterator it = l.iterator();
			    	Set set = map.keySet();
				 	Iterator itset = set.iterator();
			    	msg = new Message("server",jtaoutput.getText());
			    	while(itset.hasNext()){
			    		
			    		oos = (ObjectOutputStream)map.get(itset.next());
						oos.writeObject(msg);
			    	}
			    	jtaoutput.setText("");
			    	jtainput.append(msg.toString());
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}					
			}
		});
		//开端口监听客户端是否有连接
		try {
			ss  = new ServerSocket(50000);  //9999端口
			
			ServerThread st = new ServerThread();
			st.start();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			//System.out.println("服务器已经开启");
		}
	}
	
	//服务器转发消息
	class MessageThread extends Thread{
		Socket sock;
		public MessageThread(Socket sock){
			this.sock = sock;
		}
		public void run() {
			// TODO 自动生成方法存根
			try {
				ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
				
				while(flag){
					
					msg = (Message)ois.readObject();
					jtainput.append(msg.toString());
					Set set1 = map.keySet();
				 	Iterator itset1 = set1.iterator();
					//判断是不是私聊，以“only”为标记
					if(msg.toString().startsWith("only")){
//						//System.out.println(msg.toString());
//						
						String[] sarr = msg.toString().split("\\n");
						String[] sarr2 = sarr[0].split("#");
						//测试得到的数组中的元素
//						for(int i = 0;i < sarr2.length;i ++){
//							System.out.println(sarr2[i]);
//						}
						
						String str ="/" + sarr2[1];
						//System.out.println(str);  //测试
						while(itset1.hasNext()){
							Object obj = itset1.next();
							if(str.equals(obj.toString())){
								oos = (ObjectOutputStream)map.get(obj);
								
							}
						}
						
						Message m = new Message(sarr2[3] + "对您",msg.msg);
						oos.writeObject(m);
						
						//判断是不是下线
					}else if(msg.toString().startsWith("exit")){
						//System.out.println(msg.toString());
						String ip = msg.msg;
					 	map.remove(ip);						
						Message mcm;
						
						jtaoutput.append("" + msg.msg + "下线啦!\n");	
						//System.out.println(map);
						//当有一个用户下线时，发消息通知别人有人下线啦
						 try {

							 	Set set = map.keySet();
							 	Iterator itset = set.iterator();
						    	
						    	String str = "";
						    	msg = new Message("server",jtaoutput.getText());
						    	
						    	while(itset.hasNext()){
						    		
						    		String sa = itset.next().toString();              //改
						    		str += sa;
						    	}						    	
						    	
						    	//System.out.println(str);  //测试要发送的list字符串
						    	mcm = new Message("##",str);
						    	Set set2 = map.keySet();
							 	Iterator itset2 = set2.iterator();
						    	while(itset2.hasNext()){
						    		//System.out.println(itset.next().toString());
						    		oos = (ObjectOutputStream)map.get(itset2.next());
						    		oos.writeObject(msg);
						    		oos.writeObject(mcm);
						    	}
						    	
						    	jtaoutput.setText("");
						    	jtainput.append(msg.toString());
							} catch (IOException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
							}
						
						//判断是不是隐身
					}else if(msg.toString().startsWith("hide")){
						
//						System.out.println(cm.toString());
						String ip = msg.msg;
						ObjectOutputStream os = (ObjectOutputStream)map.get(ip);
					 	map.remove(ip);						
						Message mcm;
						
						jtaoutput.append("" + msg.msg + "下线啦!\n");	
						//System.out.println(map);
						//当有一个用户下线时，发消息通知别人有人下线啦
						 try {

							 	Set set = map.keySet();
							 	Iterator itset = set.iterator();
						    	
						    	String str = "";
						    	msg = new Message("server",jtaoutput.getText());
						    	
						    	while(itset.hasNext()){
						    		
						    		String sa = itset.next().toString();              //改
						    		str += sa;
						    	}						    	
						    	
						    	//System.out.println(str);  //测试要发送的list字符串
						    	mcm = new Message("##",str);
						    	Set set2 = map.keySet();
							 	Iterator itset2 = set2.iterator();
						    	while(itset2.hasNext()){
						    		//System.out.println(itset.next().toString());
						    		oos = (ObjectOutputStream)map.get(itset2.next());
						    		oos.writeObject(msg);
						    		oos.writeObject(mcm);
						    	}
						    	
						    	jtaoutput.setText("");
						    	jtainput.append(msg.toString());
							} catch (IOException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
							}
							map.put(ip, os);
						//判断是不是隐身后又重新上线
					}else if(msg.toString().startsWith("again")){
						
						Message mcm;
						jtaoutput.append("有人上线啦!\n");	
						//当有一个用户重新上线时，发消息
						 try {
//						    	
							 	Set set = map.keySet();
							 	Iterator itset = set.iterator();
						    	
						    	String str = "";
						    	msg = new Message("server",jtaoutput.getText());
						 
						    	while(itset.hasNext()){
						    		
						    		String sa = itset.next().toString();    //改
						    		//System.out.println(sa);
						    		str += sa;
						    	}
						    	
						    	
						    	//System.out.println(str);  //测试要发送的list字符串
						    	mcm = new Message("##",str);
						    	Set set2 = map.keySet();
							 	Iterator itset2 = set2.iterator();
						    	while(itset2.hasNext()){
						    		//System.out.println(itset.next().toString());
						    		oos = (ObjectOutputStream)map.get(itset2.next());
						    		oos.writeObject(msg);
						    		oos.writeObject(mcm);
						    	}
						    	jtaoutput.setText("");
						    	jtainput.append(msg.toString());
							} catch (IOException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
							}
					}else{
						
						//Iterator itt = l.iterator();
						Set set = map.keySet();
					 	Iterator itset = set.iterator();
						while(itset.hasNext()){
							oos = (ObjectOutputStream)map.get(itset.next());
							//System.out.println(cm.toString());  //测试要发送的消息
							oos.writeObject(msg);
						}
					}					
				}

			} catch (IOException e) {
				// TODO 自动生成 catch 块
				//e.printStackTrace();
				System.out.println("客户端有断开");
			} catch (ClassNotFoundException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}
	
	//一个客户端开一个线程
	class ServerThread extends Thread{
		
		public Socket s;
		public void run() {
			// TODO 自动生成方法存根
			
				try {
					Message mcm;
					
					while(flag){
						s = ss.accept();  //监听并接受连接
						MessageThread mt = new MessageThread(s);
						mt.start();
						//System.out.println(l2.toString());  //测试啦l2的内容
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						//有个连接就map中保存一个客户信息
						map.put(s.getRemoteSocketAddress().toString(),oos);						
						
						jtaoutput.append("" + s.getRemoteSocketAddress() + "上线啦!\n");	
						//当有一个用户上线时，发消息
						 try {
//						    	Iterator it = l.iterator();
//						    	Iterator it2 = l2.iterator();
							 	Set set = map.keySet();
							 	Iterator itset = set.iterator();
						    	
						    	String str = "";
						    	msg = new Message("server",jtaoutput.getText());
						    
						    	while(itset.hasNext()){
						    		
						    		String sa = itset.next().toString();    //改
						    		//System.out.println(sa);
						    		str += sa;
						    	}
						    	
						    	//System.out.println(str);  //测试要发送的list字符串
						    	mcm = new Message("##",str);
						    	Set set2 = map.keySet();
							 	Iterator itset2 = set2.iterator();
						    	while(itset2.hasNext()){
						    		//System.out.println(itset.next().toString());
						    		oos = (ObjectOutputStream)map.get(itset2.next());
						    		oos.writeObject(msg);
						    		oos.writeObject(mcm);
						    	}
						    	jtaoutput.setText("");
						    	jtainput.append(msg.toString());
							} catch (IOException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
							}
					}					
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					//e.printStackTrace();
					System.out.println("服务器端断开");
				} 
		}			
	}

	
	public static void main(String[] args) {
		
		GuiServer server = new GuiServer();
	}
}

