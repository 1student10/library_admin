package com.neusoft.dao.impl;
import com.neusoft.dao.*;
import com.neusoft.entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.util.BaseDao;

public class AdministratorDaoImpl implements AdministratorDao {
	Administrator administrator=new Administrator();
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	//添加管理员
	public void administratorAdd(Administrator administrator) {
		String sql="INSERT INTO administrator(id,account,password) VALUES(?,?,?)";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,administrator.getId(),administrator.getAccount(),administrator.getPassword());
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("插入成功！");
			}else {
				System.out.println("插入失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn,pst,rs);
		}
	}

	@Override
	public void administratorDel(int id) {
		String sql="delete from user where id=?";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,administrator.getId());
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("删除成功！");
			}else {
				System.out.println("删除失败！");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			BaseDao.close(conn,pst,rs);
		}

	}

	@Override
	public void administratorUpdate(Administrator administrator) {
		String sql="UPDATE administrator SET account=? ,password=? WHERE id=?";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,administrator.getAccount(),administrator.getPassword(),administrator.getId());
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("修改管理员"+administrator.getId()+"成功！");
			}else {
				System.out.println("修改管理员"+administrator.getId()+"失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn,pst,rs);
		}

	}

	@Override
	public List<Administrator> administratorQuery() {
		String sql="SELECT * from administrator";
		List<Administrator> list=new ArrayList<Administrator>();
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql);
			rs=pst.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String account=rs.getString("account");
				String password=rs.getString("password");
		
					list.add(new Administrator(id,account,password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn,pst,rs);
		}

		return list;
	}
}
