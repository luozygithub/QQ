package cn.edu.ldu.game;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

//项目当中的第二个窗体，关于窗体
//此窗体继承于对话框，可以提供模式显示的功能
public class AboutFrame extends JDialog {

	// 要求将主窗体的引用作为参数传递进来
	public AboutFrame(Game_Project mf) {
		// 在子类的构造方法中，执行父类的某一个构造方法，使用super关键字
		// 此代码只能放在第一句。
		super(mf, true);
		this.setTitle("游戏规则");
		this.setSize(300, 200);
		this.setLocationRelativeTo(this);
		getContentPane().setLayout(null);

		JButton btnOk = new JButton("\u786E\u5B9A");
		btnOk.addActionListener(new ActionListener() {
			// 确定按钮的点击事件
			public void actionPerformed(ActionEvent e) {
				// 关闭当前窗体
				dispose(); // 关闭并销毁当前窗体

				// AboutFrame.this.dispose();
			}
		});
		btnOk.setBounds(209, 129, 65, 23);
		getContentPane().add(btnOk);

		JLabel labelrule1 = new JLabel("1.\u76F8\u540C\u7684\u6570\u5B57\u80FD\u76F8\u52A0");
		labelrule1.setBounds(10, 10, 264, 15);
		getContentPane().add(labelrule1);
		labelrule1.setFont(new Font("楷体", Font.PLAIN, 15));

		JLabel labelrule2 = new JLabel("2.\u6BCF\u6B21\u90FD\u4F1A\u4EA7\u751F\u4E00\u4E2A2\u6216\u80054");
		labelrule2.setBounds(10, 34, 264, 15);
		getContentPane().add(labelrule2);
		
		labelrule2.setFont(new Font("楷体", Font.PLAIN, 15));
		
		
		JLabel labelrule3 = new JLabel(
				"3.\u76F8\u90BB\u7684\u6570\u5B57\u4E0D\u7B49\u548C\u6CA1\u6709\u7A7A\u683C\u6E38\u620F\u5931\u8D25");
		labelrule3.setBounds(10, 58, 274, 15);
		getContentPane().add(labelrule3);
		labelrule3.setFont(new Font("楷体", Font.PLAIN, 15));

		JLabel labelrule4 = new JLabel("4.\u5F97\u5230\u4E00\u4E2A2048\u7684\u6570\u4E3A\u80DC\u5229");
		labelrule4.setBounds(10, 82, 264, 15);
		getContentPane().add(labelrule4);
		labelrule4.setFont(new Font("楷体", Font.PLAIN, 15));
	}
}
