package com.saveo.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MedicineConnectionFactory 
{
	
	public static Connection connection=null;
	//using static creating the connection 
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql:///medicinedetails", "root", "Anil@0704");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//creating the method of getConnection
	public static  Connection getConnection() {
		return connection;
	}
	

}
