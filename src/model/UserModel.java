/*
 * 用户表数据模型，完成对用户表的各种操作
 */
package model;

import java.sql.ResultSet;
import java.sql.*;
import db.*;
public class UserModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * @param uid 用户编号
	 * @param p   用户密码
	 * @return    该用户的职位，若不存在则返回""
	 */
	public String checkUser(String uid,String p)
	{
		String zhiwei="";
		SqlHelper sp=null;
		try {
			//组织sql和参数列表
			String sql="select people.work from login,people where login.id=people.id and login.id=? and login.pass=?";
			String paras[]={uid,p};
			sp=new SqlHelper();
			ResultSet rs=sp.query(sql, paras);
			if(rs.next())
			{
				zhiwei=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sp.close();
		}
		return zhiwei;
	}
}
