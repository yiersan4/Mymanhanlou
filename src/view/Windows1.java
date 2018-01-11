/*
 * 主界面，系统管理员，经理，主管
 */

package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
import tools.*; //自己定义的包
import view.UserLogin.BackImage;

public class Windows1 extends JFrame implements ActionListener, MouseListener {
	Image titleIcon, timebj;
	JToolBar jtb;
	JMenuBar jmb;
	JMenu jm1, jm2, jm3, jm4, jm5, jm6;
	JMenuItem jmi1, jmi2, jmi3, jmi4, jmi5; // 二级菜单
	ImageIcon jmi1_icon, jmi2_icon, jmi3_icon, jmi4_icon, jmi5_icon; // 图标
	JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9, jb10;
	JPanel p1, p2, p3, p4, p5;
	JLabel p1_lab[];
	JLabel p2_lab1, p2_lab2;
	ImagePanel p1_imgePanel;
	JLabel timeNow;
	javax.swing.Timer t; // 把包写上是避免用了其他包的Timer，作用：定时触发Action事件
	JSplitPane jsp;
	CardLayout cardp3,cardp2;
	
	public static void main(String[] args) {
		Windows1 w1 = new Windows1();

	}

	// 初始化工具栏
	public void initMenu() {
		// 创建一级菜单
		jm1 = new JMenu("系统管理");
		jm1.setFont(Mytools.f1);
		// 创建它的二级菜单
		jmi1_icon = new ImageIcon("image/qhyh.jpg");
		jmi1 = new JMenuItem("切换用户", jmi1_icon); // 后者为图标
		jmi1.setFont(Mytools.f2);
		jmi2_icon = new ImageIcon("image/qhsy.jpg");
		jmi2 = new JMenuItem("切换到收款界面", jmi2_icon);
		jmi2.setFont(Mytools.f2);
		jmi3_icon = new ImageIcon("image/login.jpg");
		jmi3 = new JMenuItem("登陆管理", jmi3_icon);
		jmi3.setFont(Mytools.f2);
		jmi4_icon = new ImageIcon("image/wnl.jpg");
		jmi4 = new JMenuItem("万年历", jmi4_icon);
		jmi4.setFont(Mytools.f2);
		jmi5_icon = new ImageIcon("image/exit.jpg");
		jmi5 = new JMenuItem("退出", jmi5_icon);
		jmi5.setFont(Mytools.f2);
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jm1.add(jmi5);

		jm2 = new JMenu("人事管理");
		jm2.setFont(Mytools.f1);
		jm3 = new JMenu("菜单服务");
		jm3.setFont(Mytools.f1);
		jm4 = new JMenu("报表统计");
		jm4.setFont(Mytools.f1);
		jm5 = new JMenu("成本及库房");
		jm5.setFont(Mytools.f1);
		jm6 = new JMenu("帮助");
		jm6.setFont(Mytools.f1);
		// 一级菜单加入到JMenuBar
		jmb = new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		// 把JMenuBar添加到JFrame
		this.setJMenuBar(jmb);
	}

	// 初始化菜单
	public void initToolBar() {
		// 工具栏
		jtb = new JToolBar();
		jtb.setFloatable(false); // 设置不可移动
		jb1 = new JButton(new ImageIcon("image/jt1.jpg"));
		jb2 = new JButton(new ImageIcon("image/jt2.jpg"));
		jb3 = new JButton(new ImageIcon("image/jt3.jpg"));
		jb4 = new JButton(new ImageIcon("image/jt4.jpg"));
		jb5 = new JButton(new ImageIcon("image/jt5.jpg"));
		jb6 = new JButton(new ImageIcon("image/jt6.jpg"));
		jb7 = new JButton(new ImageIcon("image/jt7.jpg"));
		jb8 = new JButton(new ImageIcon("image/jt8.jpg"));
		jb9 = new JButton(new ImageIcon("image/jt9.jpg"));
		jb10 = new JButton(new ImageIcon("image/jt10.jpg"));
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		jtb.add(jb7);
		jtb.add(jb8);
		jtb.add(jb9);
		jtb.add(jb10);
	}

	// 初始化中间四个JPanel
	public void initAllPanels() {
		// 中间面板
		p1 = new JPanel(new BorderLayout());
		Image p1_bg = null;
		try {
			p1_bg = ImageIO.read(new File("image/jp1_bg.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//设置光标
		p1_lab=new JLabel[8];
		this.p1_imgePanel = new ImagePanel(p1_bg);
		this.p1_imgePanel.setLayout(new GridLayout(8, 1));
		p1_lab[0] = new JLabel(new ImageIcon("image/p1_mhl.jpg"));
//		p1_lab[0].setFont(Mytools.f3);
//		p1_imgePanel.add(p1_lab[0]);
		p1_lab[1] = new JLabel("人 事 管 理", new ImageIcon("image/rs.jpg"), 0);
		p1_lab[2] = new JLabel("登 录 管 理", new ImageIcon("image/login.jpg"), 0);
		p1_lab[3] = new JLabel("菜 谱 价 格", new ImageIcon("image/cp.jpg"), 0);
		p1_lab[4] = new JLabel("报 表 统 计", new ImageIcon("image/bbtj.jpg"), 0);
		p1_lab[5] = new JLabel("成本及库房", new ImageIcon("image/cb.jpg"), 0);
		p1_lab[6] = new JLabel("系 统 设 置", new ImageIcon("image/xtsz.jpg"), 0);
		p1_lab[7] = new JLabel("动 画 帮 助", new ImageIcon("image/dhbz.jpg"), 0);
		for(int i=0;i<p1_lab.length;i++)
		{
			p1_lab[i].setFont(Mytools.f3);
			p1_lab[i].setCursor(myCursor);//设置手型
			p1_lab[i].setEnabled(false); // 让该Label不可用
			p1_lab[i].addMouseListener(this);//注册监听
			p1_imgePanel.add(p1_lab[i]);
		}
		p1_lab[0].setEnabled(true);
		p1.add(p1_imgePanel);

		// 处理p2,p3,p4
		p4 = new JPanel(new BorderLayout());
		cardp2=new CardLayout();
		p2 = new JPanel(this.cardp2); // 卡片布局
		p2_lab1 = new JLabel(new ImageIcon("image/p2_zuo.jpg"));
		p2_lab1.setCursor(myCursor);
		p2_lab1.addMouseListener(this);
		p2_lab2 = new JLabel(new ImageIcon("image/p2_you.jpg"));
		p2_lab2.setCursor(myCursor);
		p2_lab2.addMouseListener(this);
		p2.add(p2_lab1, "left");
		p2.add(p2_lab2, "right");
		cardp3=new CardLayout();
		p3 = new JPanel(this.cardp3);
		Image zhu_image = null; // p3主界面背景图
		try {
			zhu_image = ImageIO.read(new File("image/p3_bj.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImagePanel ip = new ImagePanel(zhu_image);
		p3.add(ip, "0");//ip卡片名字为“0”
		EmpInfo eInfo=new EmpInfo();
		p3.add(eInfo,"1");//eInfo卡片名字为“1”
		Chart mychart=new Chart();
		p3.add(mychart,"4");
		
		p4.add(p2, "West");
		p4.add(p3, "Center");
		// 拆分窗口分别存放p1,p4
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);
		jsp.setDividerLocation(120); // 指定左边面板占多大
		jsp.setDividerSize(0); // 把边界线设为0

	}

	// 初始化状态栏
	public void initzt() {
		// 状态栏
		p5 = new JPanel(new BorderLayout()); // JPanel默认的布局管理器为FlowLayout
		t = new Timer(1000, this); // 每隔1000毫秒触发ActionEvent
		t.start();
		timeNow = new JLabel("当前时间：" + Calendar.getInstance().getTime().toLocaleString());// 得到时间
		timeNow.setFont(Mytools.f1);
		try {
			timebj = ImageIO.read(new File("image/bj.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImagePanel ip1 = new ImagePanel(timebj);
		ip1.setLayout(new BorderLayout());
		ip1.add(timeNow, "East");
		p5.add(ip1);
	}

	public Windows1() {
		// 创建组件
		try {
			titleIcon = ImageIO.read(new File("image/jb.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用初始化菜单
		this.initMenu();
		// 调用初始化工具栏
		this.initToolBar();
		// 调用初始化中间面板
		this.initAllPanels();
		// 调用初始化状态栏
		this.initzt();

		Container ct = this.getContentPane(); // 第二种添加到JFrame方式
		ct.add(jtb, "North");
		ct.add(jsp);
		ct.add(p5, "South");

		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w, h - 30);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(titleIcon); // 设置图标
		this.setTitle("满汉楼餐饮系统"); // 设置标题
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("当前时间：" + Calendar.getInstance().getTime().toLocaleString());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.p1_lab[0])
		{
			this.cardp3.show(p3, "0");
		}else if(e.getSource()==this.p1_lab[1])
		{
			this.cardp3.show(p3, "1");
		}else if(e.getSource()==this.p1_lab[4])
		{
			this.cardp3.show(p3, "4");
		}else if(e.getSource()==this.p2_lab1)
		{
			this.cardp2.show(p2, "right");
			this.jsp.setDividerLocation(0);
		}else if(e.getSource()==this.p2_lab2)
		{
			this.cardp2.show(p2, "left");
			this.jsp.setDividerLocation(120);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=1;i<p1_lab.length;i++)
		{
			if(e.getSource()==this.p1_lab[i])
			{
				this.p1_lab[i].setEnabled(true);
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=1;i<p1_lab.length;i++)
		{
			if(e.getSource()==this.p1_lab[i])
			{
				this.p1_lab[i].setEnabled(false);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
