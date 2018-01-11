package cn.edu.ldu.game.lightning;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JFrame;
//import java.awt.event.*;
import cn.edu.ldu.game.lightning.*;

public class Game extends JFrame{
  int x,y;
  MainPanel p1;
  Game() {
    x=Toolkit.getDefaultToolkit().getScreenSize().width/2;
    y=Toolkit.getDefaultToolkit().getScreenSize().height;
    //System.out.println(""+x+"  "+y);
 
    this.setSize(x,y);
    this.setLocation(x/2,0);
    this.setResizable(false);
    this.setTitle("Game");
    this.setBackground(Color.BLACK);
    this.setCursor(Cursor.CROSSHAIR_CURSOR);
    Container cn=getContentPane();
    p1=new MainPanel(this);
    cn.add(p1,BorderLayout.CENTER);
  }
  public static void start(){
       Game w = new Game();
    w.show();
  }
  public static void main(String[] args) {
   start();
  }

}