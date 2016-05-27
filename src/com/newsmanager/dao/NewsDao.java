package com.newsmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newsmanager.db.DbConn;
import com.newsmanager.db.Dbclose;
import com.newsmanager.entity.News;

public class NewsDao {
    Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * @方法描述 TODO   根据newsid删除新闻
     * @param newsid  想要删除的新闻的id
     * @return row    数据库受影响的行数
     *  
     */

    public int deleteNewsBYId(Integer Did) {
	int row = 0;
	conn = DbConn.getconn();// 会抛出SQLException
	System.out.println("数据库连接成功，执行删除新闻操作 " + conn);
	String sql = "delete from news where newsid=?";
	try {
	    pst = conn.prepareStatement(sql);
	    pst.setInt(1, Did);
	    row = pst.executeUpdate();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    Dbclose.addClose(pst, conn);
	}
	return row;
    }

    /**
     * @方法描述 TODO  修改新闻是否审核的状态
     * @param Cid    想要审核的新闻id
     * @return row   数据库受影响的行数
     *  
     */
    public int changeNewsStatus(Integer Eid) {
	int row = 0;
	conn = DbConn.getconn();// 会抛出SQLException
	System.out.println("数据库连接成功，修改新闻是否审核的状态 " + conn);
	String sql = "update news set newsstatus='已审核' where newsid=?";
	try {
	    pst = conn.prepareStatement(sql);
	    pst.setInt(1, Eid);
	    row = pst.executeUpdate();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    Dbclose.addClose(pst, conn);
	}
	return row;
    }

    /**
     * @方法描述 TODO      添加新闻的方法
     * @param newstitle    新闻标题
     * @param newstype     新闻栏目
     * @param content      新闻内容
     * @return   row       数据库受影响的行数
     *  
     */

    public int addNews(String newstitle, String newstype, String newscontent) {
	int row = 0;
	conn = DbConn.getconn();// 会抛出SQLException
	System.out.println("数据库连接成功，执行添加新闻操作 " + conn);
	String sql = "insert into news(newstitle,newscontent,newsstatus,newstype,createtime) values(?,?,?,?,?)";
	try {
	    pst = conn.prepareStatement(sql);
	    pst.setObject(1, newstitle);
	    pst.setString(2, newscontent);
	    pst.setString(3, "待审核");
	    pst.setString(4, newstype);
	    pst.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
	    row = pst.executeUpdate();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    Dbclose.addClose(pst, conn);
	}
	return row;

    }

    /**
     * @方法描述 TODO        查询所有新闻集合
     * @param pageNumber   页码数
     * @param pageSize     每页的包含新闻条数
     * @return NewsList    所有的新闻集合
     *  
     */
    public List<News> getNewsList(Integer pageNumber, Integer pageSize) {
	List<News> NewsList = new ArrayList<News>();
	News news = null;
	conn = DbConn.getconn();// 会抛出SQLException
	System.out.println("数据库连接成功: " + conn);
	try {
	    int startSize = (pageNumber - 1) * pageSize;
	    String sql = "select * from news limit " + startSize + "," + pageSize;
	    st = conn.createStatement();
	    rs = st.executeQuery(sql);
	    // 5.操作结果集
	    while (rs.next()) {// 会抛出SQLException

		int newsId = rs.getInt("newsid");
		String newsTitle = rs.getString("newstitle");
		String newsContent = rs.getString("newsContent");
		String newsStatus = rs.getString("newsStatus");
		String newsType = rs.getString("newstype");
		Date createTime = rs.getDate("createtime");
		System.out.println();
		news = new News(newsId, newsTitle, newsContent, newsStatus, newsType, createTime);
		NewsList.add(news);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    Dbclose.dbClose(st, rs, conn);
	}
	return NewsList;
    }

    /**
     * @方法描述 TODO 查询新闻总条数
     * @return count 新闻总条数
     *  
     */
    public int getCount() {
	int count = 0;
	conn = DbConn.getconn();
	System.out.println("数据库连接成功，执行分页操作" + conn);
	String sql = "select count(*) from news";
	try {
	    pst = conn.prepareStatement(sql);// 会抛出SQLException
	    rs = pst.executeQuery();
	    if (rs.next()) {
		count = rs.getInt(1);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    Dbclose.queryClose(pst, rs, conn);
	}
	return count;
    }

    /**
     * @方法描述 TODO   根据新闻主键NewsId，查询单条新闻对象
     * @param newsid  单条新闻的id
     * @return  news  整条新闻对象
     *  
     */
    public News selectSingleNews(Integer Uid) {
	News news = null;
	conn = DbConn.getconn();// 会抛出SQLException
	System.out.println("数据库连接成功，根据新闻主键:NewsId=" + Uid + "，查询单条新闻对象 " + conn);
	String sql = "select * from news where newsid=?";
	try {
	    pst = conn.prepareStatement(sql);// 会抛出SQLException
	    pst.setInt(1, Uid);
	    rs = pst.executeQuery();
	    // 5.操作结果集
	    if (rs.next()) {// 会抛出SQLException

		int newsId = rs.getInt("newsid");
		String newsTitle = rs.getString("newsTitle");
		String newsContent = rs.getString("newsContent");
		String newsStatus = rs.getString("newsStatus");
		String newsType = rs.getString("newstype");
		Date createTime = rs.getDate("createtime");
		news = new News(newsId, newsTitle, newsContent, newsStatus, newsType, createTime);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    Dbclose.dbClose(st, rs, conn);
	}
	return news;
    }

    /**
     * @方法描述 TODO        根据新闻主键NewsId,修改新闻
     * @param Uid          想要修改新闻的id
     * @param newsTitle    新闻标题
     * @param newsType     新闻类型
     * @param newsContent  新闻内容
     * @return row         数据库受影响的行数 
     *  
     */
    public int updateNews(Integer Uid, String newsTitle, String newsType, String newsContent) {
	int row = 0;
	conn = DbConn.getconn();// 会抛出SQLException
	System.out.println("数据库连接成功，根据新闻主键:NewsId=" + Uid + "，修改新闻 " + conn);
	String sql = "update news set newstitle=?, newstype=?,newscontent=? where newsid=?";
	try {
	    pst = conn.prepareStatement(sql);
	    pst.setObject(1, newsTitle);
	    pst.setObject(2, newsType);
	    pst.setObject(3, newsContent);
	    pst.setInt(4, Uid);
	    row = pst.executeUpdate();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    Dbclose.addClose(pst, conn);
	}
	return row;
    }
}
