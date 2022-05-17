package com.neusoft.dao.impl;

import com.neusoft.dao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.neusoft.util.BaseDao;
import com.neusoft.entity.Book;

public class BookDaoImpl implements BookDao {
	Book book = new Book();
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	//添加书籍
	@Override
	public void bookAdd(Book book) {
		String sql = "INSERT INTO book(name,author,price) VALUES(?,?,?)";
		try {
			conn = BaseDao.getConnection();
			pst = BaseDao.setParam(conn, sql, book.getName(), book.getAuthor(), book.getPrice());
			int result = BaseDao.exeUpdate(pst);
			if (result > 0) {
				System.out.println("添加书籍：《"+book.getName()+"》成功！");
			} else {
				System.out.println("添加书籍：《"+book.getName()+"》失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}

	}

	//删除书籍
	@Override
	public void bookDel(String name) {
		String sql = "delete from book where name=?";
		try {
			conn = BaseDao.getConnection();
			pst = BaseDao.setParam(conn, sql, name);
			int result = BaseDao.exeUpdate(pst);
			if (result > 0) {
				System.out.println("删除书籍成功！");
			} else {
				System.out.println("删除书籍失败！请正确输入书籍全称！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}

	}

	//更新书籍（未使用该功能）
	@Override
	public void bookUpdate(Book book) {
		String sql = "UPDATE book SET  name=? ,author=?,price=?	WHERE id=?";
		try {
			conn = BaseDao.getConnection();
			pst = BaseDao.setParam(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getId());
			int result = BaseDao.exeUpdate(pst);
			if (result > 0) {
				System.out.println("更新书籍：《"+book.getName()+"》成功！");
			} else {
				System.out.println("更新书籍：《"+book.getName()+"》失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}

	}

	//查询所有书籍
	@Override
	public List<Book> bookQuery() {
		String sql = "SELECT * from book";
		List<Book> list = new ArrayList<Book>();
		try {
			conn = BaseDao.getConnection();
			pst = BaseDao.setParam(conn, sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				int price = rs.getInt("price");
				list.add(new Book(id, name, author, price));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}

		return list;

	}

	//图书模糊查询
	public void bookSelect(String name) {
		String sql = "SELECT * FROM book WHERE name LIKE ? ";
		boolean flag=false;
		try {
			conn = BaseDao.getConnection();
			pst = BaseDao.setParam(conn, sql, "%" + name + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				flag=true;
				
				String bookname = rs.getString("name");
				System.out.println("书名：《" + bookname + "》");
			} 
		if(!flag)	{
				System.out.println("书库中暂无该书信息！");  //如果书库中没有要查找的是
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}

	}
}
