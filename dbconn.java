package com.newsmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbConn {
	public static Connection getconn() {
		final String url = "jdbc:mysql://localhost:3306/mysql";
		final String user = "root";
		final String passwd = "10ASDasd";
		Connection conn = null;

		// 已加载完驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
