package com.neusoft.dao.impl;
import com.neusoft.entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.neusoft.util.BaseDao;
import com.neusoft.dao.UserDao;

public class UserDaoImpl implements UserDao {
	User user=new User();
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	//用户注册
	@Override
	public void userAdd(User user) {
		String sql="INSERT INTO user(account,password) VALUES(?,?)";
		String sql1="SELECT * FROM  user WHERE account=?";
		PreparedStatement pst1=null;
		try {
			conn = BaseDao.getConnection();
			pst1=BaseDao.setParam(conn,sql1,user.getAccount()); //判断用户名是否已经存在
			rs=pst1.executeQuery();
			if(rs.next()) {
				System.out.println("对不起您要注册的用户已经存在!，请重新输入");
			} else {
				pst=BaseDao.setParam(conn,sql,user.getAccount(),user.getPassword());
				int result=BaseDao.exeUpdate(pst);
				if(result>0) {
					System.out.println("插入成功！");
				} else {
					System.out.println("插入失败！");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}
	}

	//管理员删除用户
	@Override
	public void userDel(String  name) {
		String sql="delete from user where account=?";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,name);
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("删除成功！");
			}else {
				System.out.println("删除失败！所删除的用户不存在！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}

	}

	//用户修改自己信息
	@Override
	public void userUpdate(User user) {
		String sql="UPDATE user SET  account=? ,password=? WHERE id=?";
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql,user.getAccount(),user.getPassword(),user.getId());
			int result=BaseDao.exeUpdate(pst);
			if(result>0) {
				System.out.println("修改成功！");
			}else {
				System.out.println("修改失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}

	}

	//查询所有用户（未使用该功能）
	@Override
	public List<User> userQuery() {
		String sql="SELECT * from user  ";
		List<User> list=new ArrayList<User>();
		try {
			conn = BaseDao.getConnection();
			pst=BaseDao.setParam(conn,sql);
			rs=pst.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String account=rs.getString("account");
				String password=rs.getString("password");
				list.add(new User(id,account,password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn,pst,rs);
		}

		return list;
	}

}