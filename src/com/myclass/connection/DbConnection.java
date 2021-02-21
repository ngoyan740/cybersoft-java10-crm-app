package com.myclass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static String url = "jdbc:mysql://localhost:3307/crm";
	private static String username = "root";
	private static String password = "1234";

	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Khong doc duoc driver JDBC");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Loi ket noi database. Kiem tra username, password");
		}
		
		return null;
	}
}
