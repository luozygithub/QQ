package cn.edu.ldu;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Message implements Serializable{
	
	String name;
	String msg;
	Date date;
	
	public Message(){
		
	}
	
	public Message(String name,String msg){
		
		this.name = name;
		this.msg = msg;
		this.date = new Date();
	}
	
	@SuppressWarnings("deprecation")
	public String toString(){
		
		return "" + name + "在" + date.getHours() + ":" + date.getMinutes() + " 说：\n" + msg + "\n";
	}

}

