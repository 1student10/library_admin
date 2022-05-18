package com.neusoft.entity;

public class Record {
	private int id; //记录编号
	private String bookname; //书名编号
	private String user; //用户名

	public Record() {
		super();
	}
	public Record(String bookname, String user) {
		super();
		this.bookname = bookname;
		this.user = user;
	}
	public Record(int id, String bookname, String user) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", bookname=" + bookname + ", user=" + user + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	

}
