package com.neusoft.dao.impl;
import  com.neusoft.dao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.neusoft.util.BaseDao;
import com.neusoft.entity.Record;


public class RecordDaoImpl implements RecordDao {
	Record record=new Record();
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	//添加借书记录
	@Override
	public void recordAdd(Record record) {
		String sql="INSERT INTO record(bookname,user) VALUES(?,?)";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,record.getBookname(),record.getUser());
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("借书成功！");
			}else {
				System.out.println("借书失败！请确认书籍名称是否正确！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}
	}

	//还书操作
	@Override
	public void recordDel(String  name) {
		String sql="delete from record where bookname=?";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,name);
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("还书成功！");
			}else {
				System.out.println("还书失败！请正确输入所还书籍全称");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}
	}

	//更新书籍（未使用该功能）
	@Override
	public void recordUpdate(Record record) {
		String sql="UPDATE record SET bookname=?  WHERE user=?";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,record.getBookname(),record.getUser());
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("更新成功！");
			}else {
				System.out.println("更新失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}
	}

	//查询所有借书记录（未使用该功能）
	@Override
	public List<Record> recordQuery() {
		String sql="SELECT * from record";
		List<Record> list=new ArrayList<Record>();
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql);
			rs=pst.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String bookname=rs.getString("bookname");
				String user=rs.getString("user");
				System.out.println("id:"+id+" bookname: "+bookname+" user: "+user);
				list.add(new Record(id,bookname,user));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}

		return list;
	}

}
