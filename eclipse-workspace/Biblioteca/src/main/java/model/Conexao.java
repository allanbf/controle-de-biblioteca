package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/db_biblioteca?useTimezone=true&serverTimezone=UTC";
		String user = "admin";
		String password = "admin";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
