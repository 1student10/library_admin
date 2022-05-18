package com.neusoft.util;
 //将增删改查这4个操作的共有语句整理进来
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/library?useSSL=false&characterEncoding=utf-8";
	private static final String USERNAME="root";
	private static final String PASSWORD="xie1234";
		
	//获取连接对象
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//代表多个Object对象
	public static PreparedStatement setParam(Connection conn,String sql,Object...param) throws SQLException{
		PreparedStatement pst=null;
		pst=conn.prepareStatement(sql);
		for(int i=0;i<param.length;i++) {
			pst.setObject(i+1, param[i]);
		}
		return pst;
	}


	//增删改的通用方法
	public static int exeUpdate(PreparedStatement pst) throws SQLException  {
		int result=0;
		//增删改都是对数据库的记录进行更改，他们都是使用executeUpdate这个方法来完成，返回值都是受影响行数
		result=pst.executeUpdate();
		return result;
	}

	//	关闭资源
	public static void close(Connection conn,PreparedStatement pst,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pst = null;
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	
}
