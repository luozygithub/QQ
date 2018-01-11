package cn.edu.ldu.game;

public class Game_Strat {
        public static void start(){
            try {
			// 3.实例化出Game_project类
			Game_Project gp = new Game_Project();
			// 4.设置窗体为可见的
			gp.setVisible(true);
		} catch (Exception e) {
		}
        }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                //欢迎关注java联盟
		// 1.创建一个main方法启动程序
		// 2.创建一个Game_project类这个类写我们程序的主要功能代码
		start();
	}

}