package cn.edu.ldu.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Window.Type;

//=======================================1这两个接口是键盘监听器和鼠标监听器=======
public class Game_Project extends JFrame implements KeyListener, MouseMotionListener {
	private JLabel jifenlabel, Labelcount;
	private JButton btnwudi, btnagin, btnexit, btnup, btndown, btnright, btnleft;
	private JButton[][] btnary;// 二维数组
	private int sjs, sj, num, count = 0, a = 5;
	private String str, str1, fenshu;
	private JMenuBar menuBar;
	private JMenuItem jmitemwudi;
	private JMenuItem jmrule;
	private JMenu jmabout;
	Random ran = new Random();
	private JPanel jpcenter;
	private boolean fx;

	//
	public Game_Project() {
		addKeyListener(this);// 1键盘监听器必要代码，添加键盘监听器
		addMouseMotionListener(this);// 1鼠标监听器必要代码，添加鼠标监听器
		getContentPane().setForeground(SystemColor.inactiveCaption);

		this.setTitle("2048");// 设置标题
		this.setSize(400, 579);// 设置外部边框的大小
		this.setLocationRelativeTo(this);// 设置外部边框居中、
		this.setResizable(false);// 设置用户不可调外部边框的大小
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置窗口什么动作都不要执行
		// 为当前窗体更换图标
		// 1. 先根据需要的图标文件，创建ImageIcon类的对象
		ImageIcon im = new ImageIcon("img//128.jpg");
		Image img = im.getImage(); // 转化为Image类型的对象。
		this.setIconImage(img); // 设置当前窗体的图标

		Container con = this.getContentPane(); // 获取当前内容面板

		con.setLayout(null);// 设置内部面板为空
		con.setBackground(SystemColor.activeCaption);// 设置内容面板的背景颜色

		// ==========================================
		//
		JPanel jpnorth = new JPanel();// 实例化一个jpnroth面板
		jpnorth.setLayout(null);// 设置jpnroth面板的布局管理器为空

		jpcenter = new JPanel();// 实例化一个jpnroth面板
		GridLayout gl_jpcenter = new GridLayout(4, 4);// 实例化一个网格布局
		jpcenter.setLayout(gl_jpcenter);// 把网格布局给jpcenter面板
		btnary = new JButton[4][4];// 实例化btnary按钮数组
		for (int i = 0; i < 4; i++) { // 遍历数组
			for (int j = 0; j < 4; j++) {
				btnary[i][j] = new JButton(); // 实例化每个按钮

				btnary[i][j].setText("");

				jpcenter.add(btnary[i][j]); // 将创建按钮放在面板上
				btnary[i][j].setFont(new Font("楷体", Font.BOLD, 20));
				btnary[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));// 设置各个面板的边框

			}
		}
		SjsRule();// 随机数产生

		changecolor();

		jpnorth.setLocation(10, 10);// 设置jpnroth面板的坐标
		jpcenter.setLocation(40, 80);// 设置jpcenter面板的坐标
		jpcenter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));// 设置jpcenter的边框

		jpnorth.setSize(380, 50);// 设置jpnroth面板的大小
		jpcenter.setSize(320, 320);// 设置jpcenter面板的大小

		jpnorth.setBackground(SystemColor.activeCaptionBorder);// 设置jpnroth面板的颜色
		jpcenter.setBackground(SystemColor.textText);// 设置jpnroth面板的颜色

		// 计分的
		jifenlabel = new JLabel("\u8BA1\u5206:");// 实例化jifenlabel标签并给他加上
		jifenlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jifenlabel.setFont(new Font("楷体", Font.BOLD, 17));
		jifenlabel.setBounds(10, 0, 59, 50);// 设置jifenlabel的坐标和大小
		jpnorth.add(jifenlabel);// 把jifenlabel添加到jpnorth面板上
		// 统计的
		Labelcount = new JLabel(String.valueOf(count));// 实例化Labelcount标签并给他传一个字符串型的count
		Labelcount.setForeground(Color.RED);
		Labelcount.setFont(new Font("楷体", Font.PLAIN, 27));
		Labelcount.setBounds(79, -1, 114, 50);// 设置Labelcount标签的坐标，大小
		jpnorth.add(Labelcount);// 把Labelcount添加到jpnorth面板上

		btnwudi = new JButton("测试失败");// 实例化一个btn按钮
		btnwudi.setFont(new Font("楷体", Font.BOLD, 10));
		btnwudi.setBounds(291, 13, 80, 30);// 设置btnstop暂停按钮的坐标和大小
		jpnorth.add(btnwudi);// 把btnwudi暂停按钮添加到jpnorth面板上
		// btnwudi.setVisible(false);//无敌按钮的隐藏。

		// 功能键包括上下左右，重新开始、退出游戏
		// ===此处为上下左右的按钮实例化，以及设置按钮的位置和大小添加到con面板上===============
		btnup = new JButton("↑");
		btnup.setFont(new Font("楷体", Font.BOLD, 15));
		btnup.setBounds(249, 433, 60, 40);
		con.add(btnup);
		btndown = new JButton("↓");
		btndown.setFont(new Font("楷体", Font.BOLD, 15));
		btndown.setBounds(249, 483, 60, 40);
		con.add(btndown);
		btnleft = new JButton("←");
		btnleft.setFont(new Font("楷体", Font.BOLD, 15));
		btnleft.setBounds(178, 483, 60, 40);
		con.add(btnleft);
		btnright = new JButton("→");
		btnright.setFont(new Font("楷体", Font.BOLD, 15));
		btnright.setBounds(330, 483, 60, 40);
		con.add(btnright);

		btnagin = new JButton("重新开始");// 实例化一个btnagin重来按钮
		btnagin.setFont(new Font("楷体", Font.BOLD, 12));
		btnexit = new JButton("退出游戏");// 实例化一个btnexit退出按钮
		btnexit.setFont(new Font("楷体", Font.BOLD, 12));
		btnagin.setBounds(54, 433, 93, 40);// 设置btnagin重来按钮的坐标和大小
		btnexit.setBounds(54, 483, 93, 40);// 设置btnexit退出按钮的坐标和大小

		con.add(btnagin);// 把btnagin按钮添加con面板
		con.add(btnexit);// 把btnagin按钮添加con面板
		con.add(jpnorth);// 把jpnorth面板添加con面板
		con.add(jpcenter);// 把jpcenter面板添加con面板

		menuBar = new JMenuBar();// 实例化菜单条
		setJMenuBar(menuBar);// 将菜单条设置到当前窗体

		JMenu jmhelp = new JMenu("\u5E2E\u52A9");// 实例化各个菜单
		jmhelp.setFont(new Font("楷体", Font.PLAIN, 14));// 设置字体和大小
		menuBar.add(jmhelp);// 将菜单添加到菜单条

		jmitemwudi = new JMenuItem("\u4F5C\u5F0A");// 实例化各个子菜单
		jmitemwudi.setFont(new Font("楷体", Font.PLAIN, 12));// 设置字体和大小
		jmhelp.add(jmitemwudi);// 将子菜单添加到菜单

		jmabout = new JMenu("\u5173\u4E8E");
		jmabout.setFont(new Font("楷体", Font.PLAIN, 14));
		menuBar.add(jmabout);

		jmrule = new JMenuItem("\u6E38\u620F\u89C4\u5219");
		jmrule.setFont(new Font("楷体", Font.PLAIN, 12));
		jmabout.add(jmrule);
		// ======================================================================================

		// 实例化活动监听类，以及给上下左右无敌再来一遍和退出按钮添加监听器
		fxbtnAListener fbl = new fxbtnAListener();
		btnup.addActionListener(fbl);
		btndown.addActionListener(fbl);
		btnleft.addActionListener(fbl);
		btnright.addActionListener(fbl);
		btnwudi.addActionListener(fbl);
		btnagin.addActionListener(fbl);
		btnexit.addActionListener(fbl);

		// 帮助下的作弊按钮活动事件的实例化以及注册到监听器
		wudiAListener wda = new wudiAListener();
		jmitemwudi.addActionListener(wda);

		aboutAListener aba = new aboutAListener();
		jmrule.addActionListener(aba);

		// 实例化窗口事件的监听器，给前窗口添加监听器
		MyWindowListener mwl = new MyWindowListener();
		this.addWindowListener(mwl);

	}

	// ===游戏的规则=============================================
	// 变颜色的方法
	private void changecolor() {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {// for循环遍历16个按钮
				str = btnary[a][b].getText();
				if (str.equals("2")) {
					btnary[a][b].setBackground(Color.white);
				}
				if (str.equals("4")) {
					btnary[a][b].setBackground(Color.blue);
				}
				if (str.equals("8")) {
					btnary[a][b].setBackground(Color.green);
				}
				if (str.equals("16")) {
					btnary[a][b].setBackground(Color.PINK);
				}
				if (str.equals("32")) {
					btnary[a][b].setBackground(Color.white);
				}
				if (str.equals("64")) {
					btnary[a][b].setBackground(Color.blue);
				}
				if (str.equals("128")) {
					btnary[a][b].setBackground(Color.green);
				}
				if (str.equals("256")) {
					btnary[a][b].setBackground(Color.PINK);
				}
				if (str.equals("512")) {
					btnary[a][b].setBackground(Color.white);
				}
				if (str.equals("1024")) {
					btnary[a][b].setBackground(Color.blue);
				}
				if (str.equals("2048")) {
					btnary[a][b].setBackground(Color.green);
				} else if (str.equals("")) {
					btnary[a][b].setBackground(Color.white);
				}
			}
		}
	}

	// 输了的方法
	private boolean Isshu() {
		int c = 0;
		String qianyiwei = null, dangqianwei = null, shangyiwei;
		for (int a = 0; a < 4; a++) {

			for (int b = 1; b < 4; b++) {// for循环遍历16个按钮
				dangqianwei = btnary[a][b].getText();// 根据获得的ab的值来确定位置以及获取ab位置的字符串文本用str接收
				qianyiwei = btnary[a][b - 1].getText();

				// System.out.println("A"+a);
				// System.out.println("B"+b);
				if (dangqianwei.equals("") || qianyiwei.equals("")) {// 每接收一次就判断一次,判断接收的数字中str有没有等于2048的
					c++;
				}
				if (dangqianwei.equals(qianyiwei)) {
					c++;
				}
			}
		}
		for (int d = 1; d < 4; d++) {

			for (int e = 0; e < 4; e++) {// for循环遍历16个按钮
				dangqianwei = btnary[d][e].getText();// 根据获得的de的值来确定位置以及获取ab位置的字符串文本用str接收
				shangyiwei = btnary[d - 1][e].getText();

				// System.out.println("A"+a);
				// System.out.println("B"+b);
				if (dangqianwei.equals("") || shangyiwei.equals("")) {// 每接收一次就判断一次,判断接收的数字中str有没有等于2048的
					c++;
				}
				if (dangqianwei.equals(shangyiwei)) {
					c++;
				}
			}
		}

		// System.out.println(c);
		if (c == 0) {

			return true;
		}

		return false;
	}

	// 是否赢了。
	private boolean IsWin() {

		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {// for循环遍历16个按钮
				str = btnary[a][b].getText();// 根据获得的ab的值来确定位置以及获取ab位置的字符串文本用str接收
				// System.out.println("A"+a);
				// System.out.println("B"+b);
				if (str.equals("2048")) {// 每接收一次就判断一次,判断接收的数字中str有没有等于2048的
					return true;// 如果有就返回true
				}

			}
		}

		return false;
	}

	// 随机数产生的方法
	private void SjsRule() {

		sjs = ran.nextInt(4);// 产生一个随机数
		sj = ran.nextInt(4);// 产生一个随机数

		// System.out.println("y:" + sjs);
		// System.out.println("x:" + sj);

		if (btnary[sjs][sj].getText().equals("") && sjs < 1 && sj > 2) {// 把产生的sjs、sj分别给二维数组，然后获取此位置的字符串文本
			btnary[sjs][sj].setText("4");// 判断是否为空，为空就给此位置添加字符串文本
		} else if (btnary[sjs][sj].getText().equals("")) {
			btnary[sjs][sj].setText("2");
		} else {

			SjsRule();// 否则就再执行一次随机数产生的方法。
		}

	}

	// ===移动的方法============================================================================
	// 从左边的第二位开始获取值，并获取左边-1的值然后再比较
	private void LeftRule() {
		for (int i = 0; i < 4; i++) {// 行
			a = 5;
			for (int k = 0; k < 3; k++) {
				for (int j = 1; j < 4; j++) { // 列
					str = btnary[i][j].getText(); // 获取当前方块按钮字符串
					str1 = btnary[i][j - 1].getText(); // 获取当前左1方块按钮字符
					// 检查每个按钮有没有被循环到
					// System.out.println("当前" + i + "+" + j);
					// System.out.println("str" + str);
					// System.out.println("前一个" + i + "+" + (j - 1));//
					// System.out.println("strA" + str1);
					if (str1.equals("")) { // 如果左1方块文本为空字符
						btnary[i][j - 1].setText(str); // 字符左移
						btnary[i][j].setText(""); // 当前方块字符置空
					} else if (str.equals(str1) && (j != a) && (j != a - 1)) {// 如果当前方块和左1方块文本字符相等
						num = Integer.parseInt(str);// 将字符串str转化成整形num
						count += num;// 记分count=count+num
						String fenshu = String.valueOf(count + count);// 将整形转化为字符串
						Labelcount.setText(fenshu);// 把字符串fenshu给Labelcount标签
						str = String.valueOf(2 * num);// str等于（把整2*num形—字符串）
						btnary[i][j - 1].setText(str); // 左1方块文本字符变为两方块之和
						btnary[i][j].setText(""); // 当前方块字符置空
						a = j;
						// System.out.println("A"+a);
						// System.out.println(j);
					}
				}
			}
		}
		// 防止所有按钮都有数字还会产生随机数的方法
		// 循环判断还有没有空，如果有就会执行随机数的方法
		int f = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (btnary[a][b].getText().equals("")) {
					f++;
				}

			}
		}
		if (f > 0) {
			SjsRule();
		}
		changecolor();
	}

	// 右与左相反从右边第二个开始比较循环比较每次比较右边的一位+1；
	private void RightRule() {

		for (int i = 0; i < 4; i++) {
			a = 5;
			for (int k = 0; k < 3; k++) {
				for (int j = 2; j >= 0; j--) {
					str = btnary[i][j].getText();
					str1 = btnary[i][j + 1].getText();
					// System.out.println("当前" + i + "+" + j);
					// System.out.println("str" + str);
					// System.out.println("前一个" + i + "+" + (j + 1));//

					// System.out.println("strA" + str1);

					if (str1.equals("")) {
						btnary[i][j + 1].setText(str);

						btnary[i][j].setText("");

					} else if (str.equals(str1) && j != a && j != a + 1) {
						num = Integer.parseInt(str);
						count += num;
						String fenshu = String.valueOf(count + count);
						Labelcount.setText(fenshu);
						str = String.valueOf(2 * num);
						btnary[i][j + 1].setText(str);

						btnary[i][j].setText("");
						a = j;
					}
				}
			}
		} // 防止所有按钮都有数字还会产生随机数的方法
			// 循环判断还有没有空，如果有就会执行随机数的方法
		int f = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (btnary[a][b].getText().equals("")) {
					f++;
				}

			}
		}
		if (f > 0) {
			SjsRule();
		}
		changecolor();
	}

	// 获取上面第二个位置的值，然后在获取的位置上向上-1
	// 然后再判断上一个按钮为不为空如果为空就把位置-1的值给上一个按钮并设置原按钮为空
	// 最后再比较相等就相加，并把获取的位置设置为空
	private void UpRule() {
		for (int j = 0; j < 4; j++) {
			a = 5;
			for (int k = 0; k < 3; k++) {
				for (int i = 1; i < 4; i++) {
					str = btnary[i][j].getText();
					str1 = btnary[i - 1][j].getText();
					// 检查每个按钮有没有被循环到
					// System.out.println("当前" + i + "+" + j);
					// System.out.println("str" + str);
					// System.out.println("前一个" + (i-1)+ "+" + j);//

					// System.out.println("strA" + str1);

					if (str1.equals("")) {
						btnary[i - 1][j].setText(str);

						btnary[i][j].setText("");

					} else if (str.equals(str1) && i != a && i != a - 1) {
						num = Integer.parseInt(str);
						count += num;
						String fenshu = String.valueOf(count + count);
						Labelcount.setText(fenshu);
						str = String.valueOf(2 * num);
						btnary[i - 1][j].setText(str);

						btnary[i][j].setText("");
						a = i;
					}
				}
			}
		}
		// 防止所有按钮都有数字还会产生随机数的方法
		// 循环判断还有没有空，如果有就会执行随机数的方法
		int f = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (btnary[a][b].getText().equals("")) {
					f++;
				}

			}
		}
		if (f > 0) {
			SjsRule();
		}
		changecolor();
	}

	// 获取下面第二个位置的值，然后再获取位置向下+1的值，
	// 如果向下位置+1的值为空就把第二个位置的值给向下+1位置的值，设置第二个位置的值为空
	// 如果不为空并且相等就相加
	private void DownRule() {

		for (int j = 0; j < 4; j++) {
			a = 5;
			for (int k = 0; k < 5; k++) {
				for (int i = 2; i >= 0; i--) {
					str = btnary[i][j].getText();
					str1 = btnary[i + 1][j].getText();
					// System.out.println("当前" + i + "+" + j);
					// System.out.println("str" + str);
					// System.out.println("前一个" + (i+1) + "+" + j );

					// System.out.println("strA" + str1);

					if (str1.equals("")) {
						btnary[i + 1][j].setText(str);

						btnary[i][j].setText("");

					} else if (str.equals(str1) && i != a && i != a + 1) {
						num = Integer.parseInt(str);
						count += num;
						String fenshu = String.valueOf(count + count);
						Labelcount.setText(fenshu);
						str = String.valueOf(2 * num);
						btnary[i + 1][j].setText(str);

						btnary[i][j].setText("");
						a = i;
					}
				}
			}
		}
		// 防止所有按钮都有数字还会产生随机数的方法
		// 循环判断还有没有空，如果有就会执行随机数的方法
		int f = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (btnary[a][b].getText().equals("")) {
					f++;
				}

			}
		}
		if (f > 0) {
			SjsRule();
		}
		changecolor();
	}

	// =======监听器类
	// ======================================================================================
	// 此处为帮助下的作弊事件的活动接口监听器类
	private class wudiAListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae1) {

			btnary[3][3].setText("1024");
			btnary[2][3].setText("1024");
			SjsRule();
			changecolor();
		}
	}

	// 此处为关于下的规则事件的活动接口监听器类
	private class aboutAListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae1) {
			AboutFrame af = new AboutFrame(Game_Project.this);
			af.setVisible(true);
		}
	}

	// 此处为方向自定义接口活动监听类
	private class fxbtnAListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			// TODO Auto-generated method stub

			JButton btn = (JButton) (ae.getSource());
			if (btn == btnup) {
				UpRule();
				// IsWin();
			}
			if (btn == btndown) {
				DownRule();
				// IsWin();
			}
			if (btn == btnleft) {
				LeftRule();
				// IsWin();

			}
			if (btn == btnright) {
				RightRule();
				// IsWin();
			}
			if (btn == btnwudi) {// 作弊按钮
				btnary[0][0].setText("2");
				btnary[0][1].setText("4");
				btnary[0][2].setText("2");
				btnary[0][3].setText("4");
				btnary[1][0].setText("8");
				btnary[1][1].setText("2");
				btnary[1][2].setText("8");
				btnary[1][3].setText("2");
				btnary[2][0].setText("16");
				btnary[2][1].setText("4");
				btnary[2][2].setText("16");
				btnary[2][3].setText("4");
				btnary[3][0].setText("32");
				btnary[3][1].setText("2");
				btnary[3][2].setText("32");
				// btnary[3][3].setText("2");
				changecolor();

			}

			if (btn == btnagin) {
				count = 0;
				for (int i = 0; i < 4; i++) {// 利用嵌套循环清空所有的text
					for (int j = 0; j < 4; j++) {
						btnary[i][j].setText("");
					}
				}
				SjsRule();// 再执行随机数的方法
				Labelcount.setText("0");
				changecolor();

			}
			if (btn == btnexit) {
				if (JOptionPane.showConfirmDialog(null, "您真的打算退出吗？", "退出程序", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
			if (IsWin()) {
				JOptionPane.showMessageDialog(null, "恭喜你，胜利了！", "YOU WIN", JOptionPane.INFORMATION_MESSAGE);
				count = 0;
				for (int i = 0; i < 4; i++) {// 利用嵌套循环清空所有的text
					for (int j = 0; j < 4; j++) {
						btnary[i][j].setText("");
					}
				}
				SjsRule();// 再执行随机数的方法
				Labelcount.setText("0");
				changecolor();
			}
			if (Isshu()) {
				JOptionPane.showMessageDialog(null, "很遗憾,游戏结束！", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
				count = 0;
				for (int i = 0; i < 4; i++) {// 利用嵌套循环清空所有的text
					for (int j = 0; j < 4; j++) {
						btnary[i][j].setText("");
					}
				}
				SjsRule();// 再执行随机数的方法
				Labelcount.setText("0");
				changecolor();
			}
		}

	}

	// 采用的继承适配器类的方式。
	private class MyWindowListener extends WindowAdapter {
		// 窗体正在关闭时，需要关注
		public void windowClosing(WindowEvent we) {
			
		}
	}

	// 1此处为键盘监听器处理事件
	public void keyTyped(KeyEvent ke) {
		fx = true;
		char ch = ke.getKeyChar();
		if (ch == 97) {
			LeftRule();

			// System.out.println("aaaaa");
		}
		if (ke.getKeyChar() == 100) {
			RightRule();
			// System.out.println("aaaaa");
		}
		if (ke.getKeyChar() == 119) {
			UpRule();
			// System.out.println("aaaaa");
		}
		if (ke.getKeyChar() == 115) {
			DownRule();
			// System.out.println("aaaaa");
		}
		if (IsWin()) {
			JOptionPane.showMessageDialog(null, "恭喜你，胜利了！", "YOU WIN", JOptionPane.INFORMATION_MESSAGE);
			count = 0;
			for (int i = 0; i < 4; i++) {// 利用嵌套循环清空所有的text
				for (int j = 0; j < 4; j++) {
					btnary[i][j].setText("");
				}
			}
			SjsRule();// 再执行随机数的方法
			Labelcount.setText("0");
			changecolor();
		}
		if (Isshu()) {
			JOptionPane.showMessageDialog(null, "很遗憾,游戏结束！", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
			count = 0;
			for (int i = 0; i < 4; i++) {// 利用嵌套循环清空所有的text
				for (int j = 0; j < 4; j++) {
					btnary[i][j].setText("");
				}
			}
			SjsRule();// 再执行随机数的方法
			Labelcount.setText("0");
			changecolor();
		}
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		boolean status = this.requestFocusInWindow();// 1
	}

}