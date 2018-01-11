package view;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import tools.*;
import model.*;

public class UserLogin extends JDialog implements ActionListener{

	JLabel jl1,jl2,jl3;
	JTextField jName;
	JPasswordField jpasswd;
	JButton jCon,jCancel;
	
	public static void main(String[] args) {

		UserLogin ul=new UserLogin();
		
	}
	
	
	public UserLogin()
	{
		//创建各个组件
		jl1=new JLabel("请输入用户名：");
		jl1.setBounds(60, 190, 150, 30);
		jl1.setFont(Mytools.f1);
		this.add(jl1);
		
		jl2=new JLabel("请输入密码：");
		jl2.setBounds(60, 240, 150, 30);
		jl2.setFont(Mytools.f1);
		this.add(jl2);
		
		jl3=new JLabel("（或员工号）");
		jl3.setBounds(100, 210, 100, 30);
		jl3.setFont(Mytools.f2);
		jl3.setForeground(Color.red);
		this.add(jl3);
		
		jName=new JTextField(20);
		jName.setBounds(180, 190, 120, 30);
		jName.setFont(Mytools.f1);
		jName.setBorder(BorderFactory.createLoweredBevelBorder());         //设置下凹
		this.add(jName);
		
		jpasswd=new JPasswordField(20);
		jpasswd.setBounds(180, 240, 120, 30);
		jpasswd.setFont(Mytools.f1);
		jpasswd.setBorder(BorderFactory.createLoweredBevelBorder());       //设置下凹
		this.add(jpasswd);
		
		jCon=new JButton("确定");
		jCon.setFont(Mytools.f1);
		jCon.setBounds(110,300,70,30);
		this.add(jCon);
		jCon.addActionListener(this);
		
		jCancel=new JButton("取消");
		jCancel.setFont(Mytools.f1);
		jCancel.setBounds(220,300,70,30);
		this.add(jCancel);
		jCancel.addActionListener(this);
		
		//空布局
		this.setLayout(null);
		//设置图片
		BackImage bi=new BackImage();
		bi.setBounds(0,0,360,360);                                         //位置
		this.add(bi);
		//不使用上下框
		this.setUndecorated(true);
		this.setSize(360,360);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int heigh=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200,heigh/2-150);
		this.setVisible(true);
		
	}
	//内部类
	class BackImage extends JPanel
	{
		Image im;
		public BackImage()
		{
			try {
				im=ImageIO.read(new File("image//login.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(im, 0, 0, 360, 360,this);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent ee) {
		// TODO Auto-generated method stub
		if(ee.getSource()==jCancel)
		{
			this.dispose();
			System.exit(0);
		}else if(ee.getSource()==jCon)
		{
			String uid=this.jName.getText().trim();
			String p=new String(this.jpasswd.getPassword());
			UserModel um=new UserModel();
			String res=um.checkUser(uid, p);
			if(res.equals("manager"))                                      //职位为manager的进入系统
			{
				this.dispose();
				new Windows1();
			}
		}
	}	
}
