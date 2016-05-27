package com.newsmanager.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbclose {

    /**
     * @方法描述 TODO  关闭添加新闻的资源
     * @param pstmt
     * @param conn 
     *  
     */
    public static void addClose(PreparedStatement pstmt, Connection conn) {
	/*
	 * 多个 try-catch 出发点：安全
	 */
	try {
	    if (pstmt != null) {
		pstmt.close();
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	try {
	    if (conn != null) {
		conn.close();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * @方法描述 TODO 关闭查询的资源
     * @param pst
     * @param rs
     * @param conn 
     *  
     */
    public static void queryClose(PreparedStatement pst, ResultSet rs, Connection conn) {
	try {
	    if (pst != null) {
		pst.close();
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	try {
	    if (rs != null) {
		rs.close();
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	try {
	    if (conn != null) {
		conn.close();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public static void dbClose(Statement st, ResultSet rs, Connection conn) {
	try {
	    if (st != null) {
		st.close();
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	try {
	    if (rs != null) {
		rs.close();
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	try {
	    if (conn != null) {
		conn.close();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
