/*
 * 人事数据模型类，完成对人事表的各种操作
 */

package model;

import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import db.*;
public class EmpModel extends AbstractTableModel{

	Vector<String> colums;
	Vector<Vector> rows;
	
	//初始化整个模型
	public void csh()
	{
		String []paras={"1"};
		this.query("select * from people where 1=?", paras);
	}
	//初始化默认显示
	public void cshshow()
	{
		String []paras={"1"};
		this.query("select id,name,sex,work from people where 1=?", paras);
	}
	
	//查询需要显示的人事信息
	public int query(String sql,String []paras)
	{
		//初始化列
		this.rows=new Vector<Vector>();
		this.colums=new Vector<String>();
		SqlHelper sh=new SqlHelper();
		ResultSet rs=sh.query(sql, paras);
		try {
			ResultSetMetaData rsmt=rs.getMetaData();//rsmt可以得到结果有多少列，且可以得到每列的名字
			for(int i=0;i<rsmt.getColumnCount();i++)
			{
//				this.colums.add(rsmt.getColumnName(i+1));//得到i列的名字，从1开始
				this.colums.add(rsmt.getColumnLabel(i+1));
			}
			while(rs.next())
			{
				Vector temp=new Vector<String>();
				for(int i=0;i<rsmt.getColumnCount();i++)
				{
					temp.add(rs.getString(i+1));
				}
				rows.add(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sh.close();
			return rows.size();
		}
	}
	
	public void add(Window owner,String title,Dialog.ModalityType modalityType)
	{
		UpDialog ad=new UpDialog(owner,title,modalityType,null,0);
		
	}
	
	public void delete(String id)
	{
		String sql="delete from people where id=?";
		String []paras={id};
		SqlHelper sqlh=new SqlHelper();
		sqlh.updExectue(sql, paras);
	}
	public void update(Window owner,String title,Dialog.ModalityType modalityType,EmpModel em,int rowNum)
	{
		em.csh();
		UpDialog ad=new UpDialog(owner,title,modalityType,em,rowNum);
	}
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colums.get(column).toString();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
//		System.out.println(arg0+","+arg1+","+((Vector)rows.get(arg0)).get(arg1));
		return ((Vector)rows.get(arg0)).get(arg1);
	}

	
}
