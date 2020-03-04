package webservice.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userdao 
{
	private Connection conn;
	private PreparedStatement ps;
	public userdao() {
		conn=ConnectionUtils.getConnection();
	}
	
	public List<User> getUsers(){
		try {
			String sql="select * from user";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<User> userList=new ArrayList<User>();			
			while(rs.next()) {
				int id=rs.getInt("id");
				String fullname=rs.getString("fullname");
				String email=rs.getString("email");
				User user=new User(id, fullname, email, "");
				userList.add(user);
			}
			
			return userList;
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return null;
	}
	
	public String registerUser(User user) {
		try {
		String sql="insert into user (fullname, email, password) values (?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, user.getFullName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		int count=ps.executeUpdate();
		if(count>0) {
			return "Operation Success!!!";
		}else {
			return "Operation Fail!!!";
		}
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return "Operation Fail!!!";
	}

}
