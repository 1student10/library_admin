package com.neusoft.dao;

import java.util.List;

import com.neusoft.entity.Administrator;

public interface AdministratorDao {

	void administratorAdd(Administrator administrator);
	
	void administratorDel(int  id);
	
	void administratorUpdate(Administrator administrator);
	
	List<Administrator> administratorQuery();
	
}

