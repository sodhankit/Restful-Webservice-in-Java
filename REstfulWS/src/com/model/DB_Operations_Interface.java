package com.model;

import java.util.List;

import com.bean.Student;

public interface DB_Operations_Interface {

	int insertData(Student student);
	int updateData(Student student);
	int deleteData(int id);
	List<Student> viewData();
}
