package com.neusoft.dao;

import java.util.List;

import com.neusoft.entity.Record;

public interface RecordDao {

	void recordAdd(Record record);
	
	void recordDel(String  name);
	
	void recordUpdate(Record record);
	
	List<Record> recordQuery();
	
}
