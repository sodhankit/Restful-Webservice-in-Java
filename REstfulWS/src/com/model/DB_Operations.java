package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Student;
import com.util.DB_Connection;

public class DB_Operations implements DB_Operations_Interface {

	Connection con;
	PreparedStatement ps;
	DB_Connection db = new DB_Connection();

	@Override
	public int insertData(Student student) {
		// TODO Auto-generated method stub
		int result = 0;
		con = db.getConnection();
		try {
			ps = con.prepareStatement("insert into student(fname,lname,emailid) values (?,?,?)");
			ps.setString(1, student.getFname());
			ps.setString(2, student.getLname());
			ps.setString(3, student.getEmailid());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int updateData(Student student) {
		// TODO Auto-generated method stub
		int result = 0;
		con = db.getConnection();
		String sql = "update student set fname=?,lname=?,emailid=? where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, student.getFname());
			ps.setString(2, student.getLname());
			ps.setString(3, student.getEmailid());
			ps.setInt(4, student.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteData(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		con = db.getConnection();
		String sql = "delete from student where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Student> viewData() {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		con = db.getConnection();
		String sql = "select * from student";
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setFname(rs.getString("fname"));
				student.setLname(rs.getString("lname"));
				student.setEmailid(rs.getString("emailid"));
				student.setId(rs.getInt("id"));
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
