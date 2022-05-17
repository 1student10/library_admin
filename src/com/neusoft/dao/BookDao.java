package com.neusoft.dao;

import java.util.List;

import com.neusoft.entity.Book;

public interface BookDao {

	void bookAdd(Book book);
	
	void bookDel(String  name);
	
	void bookUpdate(Book book);
	
	List<Book> bookQuery();
	
}
