package com.neusoft.entity;

public class User {
	private int  id;//用户编号
	private String account;  //用户名
	private String password;  //密码

	public User() {
		super();
	}
	
	public User(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public User(int id, String account, String password) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + "]";
	}
	
	
	

}
