package com.newsmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Administrator
 * ���ݿ�������
 *
 */
public final class DbConn {

    /**
     * @�������� TODO   �������ݿ�
     * @return 
     *  
     */
    public static Connection getconn() {
	final String url = "jdbc:mysql://localhost:3306/mysql";
	final String user = "root";
	final String passwd = "10ASDasd";
	Connection conn = null;

	// �Ѽ���������
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
