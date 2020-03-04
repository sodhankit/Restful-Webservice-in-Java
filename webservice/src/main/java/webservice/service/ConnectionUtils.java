package webservice.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String dbName="java_db";
    	String path="jdbc:mysql://localhost:3306/"+dbName;
    	String userName="root";
    	String password="";
    	Connection conn=null;
		try {
			 conn= DriverManager.getConnection(path, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}

