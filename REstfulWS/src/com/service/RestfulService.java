package com.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bean.Student;
import com.google.gson.Gson;
import com.model.DB_Operations;
import com.model.DB_Operations_Interface;

@Path("student")
public class RestfulService implements RestfulService_Interface{

	Gson gson=new Gson();
	DB_Operations_Interface dao=new DB_Operations();
	
	@Produces(MediaType.TEXT_PLAIN)
	@Path("add")
	@POST
	@Override
	public String addStudent(String student) {
		// TODO Auto-generated method stub
		Student s=gson.fromJson(student, Student.class);
		int result=dao.insertData(s);
		if(result>0)
			return "Data inserted successfully";
		else
			return "Error in Adding data into table";
	}

	@Produces(MediaType.TEXT_PLAIN)
	@Path("update")
	@POST
	@Override
	public String updateStudent(String student) {
		// TODO Auto-generated method stub
		Student s=gson.fromJson(student, Student.class);
		int result=dao.updateData(s);
		if(result>0)
			return "Data updated successfully";
		else
			return "Error in updating data into table";
	}

	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete")
	@GET
	@Override
	public String deleteStudent(@QueryParam("id")int id) {
		// TODO Auto-generated method stub
		int result=dao.deleteData(id);
		if(result>0)
			return "Data deleted successfully";
		else
			return "Error in deleting data into table";
	}

	@Produces(MediaType.APPLICATION_JSON)
	@Path("viewall")
	@GET
	@Override
	public String viewAllStudents() {
		// TODO Auto-generated method stub
		List<Student> studentList=dao.viewData();
		String students=gson.toJson(studentList);
		return students;
	}

}
