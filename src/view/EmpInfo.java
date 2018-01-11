package view;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;
import javax.swing.*;
import tools.*;
import model.*;

public class EmpInfo extends JPanel implements ActionListener{
	
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_lab1,p3_lab1;
	JTextField p1_jtf1;
	JButton p1_jb1,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	JTable jtable;//显示人事信息
	JScrollPane jsp;
	EmpModel em;
	
	public EmpInfo()
	{
		//北部
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_lab1=new JLabel("请输入姓名（员工号或职位）");
		p1_lab1.setFont(Mytools.f2);
		p1_jtf1=new JTextField(20);
		p1_jb1=new JButton("查询");
		p1_jb1.setFont(Mytools.f2);
		p1_jb1.addActionListener(this);
		p1.add(p1_lab1);
		p1.add(p1_jtf1);
		p1.add(p1_jb1);
		//南部
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_lab1=new JLabel("总记录是**条");
		p3_lab1.setFont(Mytools.f2);
		p3.add(p3_lab1);
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("详细信息");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(Mytools.f2);
		p4_jb2=new JButton("添加");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(Mytools.f2);
		p4_jb3=new JButton("修改");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(Mytools.f2);
		p4_jb4=new JButton("删除");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(Mytools.f2);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5=new JPanel(new BorderLayout());
		p5.add(p3,"West");
		p5.add(p4,"East");
		//中部
		p2=new JPanel(new BorderLayout());
		this.em=new EmpModel();
		em.cshshow();
		jtable=new JTable(em);
		jsp=new JScrollPane(jtable);
		p2.add(jsp);
		
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.em=new EmpModel();
		if(e.getSource()==p1_jb1)
		{
			String []paras={this.p1_jtf1.getText()};
			System.out.println(this.p1_jtf1.getText());
			if(this.p1_jtf1.getText().equals("")){
				em.cshshow();
			}else{
				if(em.query("select id,name,sex,work from people where id=? ", paras)<=0)
				{
					if(em.query("select id,name,sex,work from people where name=? ", paras)<=0)
					{
						em.query("select id,name,sex,work from people where work=? ", paras);
					}
				}
			}
			jtable.setModel(em);
		}else if(e.getSource()==p4_jb1)
		{
			String []paras={this.p1_jtf1.getText()};
			if(this.p1_jtf1.getText().equals(""))
			{
				em.csh();
			}else{
				if(em.query("select * from people where id=? ", paras)<=0)
				{
					if(em.query("select * from people where name=? ", paras)<=0)
					{
						em.query("select * from people where work=? ", paras);
					}
				}
			}
			jtable.setModel(em);
		}else if(e.getSource()==p4_jb2)
		{
			em.add(SwingUtilities.getWindowAncestor(this),"添加员工",Dialog.ModalityType.valueOf("DOCUMENT_MODAL"));
			em.cshshow();
			jtable.setModel(em);
		}
		else if(e.getSource()==p4_jb3||e.getSource()==p4_jb4)
		{
			em.cshshow();
			int rowNum=this.jtable.getSelectedRow();                             //返回用户点中的行，若无则返回-1
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行");              //没选提示
			}else{
//				System.out.println(rowNum);
				if(e.getSource()==p4_jb4)
				{
					String uid=(String)em.getValueAt(rowNum,0);
					System.out.println(uid);
					em.delete(uid);
				}else if(e.getSource()==p4_jb3)
				{
					em.update(SwingUtilities.getWindowAncestor(this), "修改信息", Dialog.ModalityType.valueOf("DOCUMENT_MODAL"), em, rowNum);
				}
				em.cshshow();
				jtable.setModel(em);
			}
		}
	}
}
