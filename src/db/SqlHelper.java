/*
 * 对数据库操作的类，crud
 * 调用存储过程
 */
package db;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SqlHelper {
	
	PreparedStatement ps=null;
	ResultSet  rs=null;
	Connection ct=null;
	String driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url="jdbc:sqlserver://localhost:1433; DatabaseName=RestaurantDB";
	String user="sa";
	String passwd="20070401";
	
	public void link()
	{
		try {
			Class.forName(driver); //加载驱动
			ct = DriverManager.getConnection(url, user, passwd); //得到连接
			if (!ct.isClosed()) {
				System.out.println("数据库连接成功");
			} else {
				System.out.println("数据库连接失败");
			} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(ct!=null)ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String sql,String []paras)
	{
		try {
			ps=ct.prepareStatement(sql);
//			System.out.println(sql);
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rs;
	}
	
	public void updExectue(String sql,String []paras)
	{
		boolean b=true;
		try {
			this.link();
			ps=ct.prepareStatement(sql);
			for (int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			if(ps.executeUpdate()!=1)b=false;
		} catch (Exception e2) {
			e2.printStackTrace();
		}finally{
			this.close();
		}
	}
	
	public SqlHelper()
	{
		this.link();
	}
}

