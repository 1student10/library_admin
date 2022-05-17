package com.neusoft.dao;

import java.util.List;

import com.neusoft.entity.User;

public interface UserDao {

	void userAdd(User user);
	
	void userDel(String  name);
	
	void userUpdate(User user);
	
	List<User> userQuery();
	
}
