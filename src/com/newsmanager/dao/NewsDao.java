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
     * @�������� TODO   ����newsidɾ������
     * @param newsid  ��Ҫɾ�������ŵ�id
     * @return row    ���ݿ���Ӱ�������
     *  
     */

    public int deleteNewsBYId(Integer Did) {
	int row = 0;
	conn = DbConn.getconn();// ���׳�SQLException
	System.out.println("���ݿ����ӳɹ���ִ��ɾ�����Ų��� " + conn);
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
     * @�������� TODO  �޸������Ƿ���˵�״̬
     * @param Cid    ��Ҫ��˵�����id
     * @return row   ���ݿ���Ӱ�������
     *  
     */
    public int changeNewsStatus(Integer Eid) {
	int row = 0;
	conn = DbConn.getconn();// ���׳�SQLException
	System.out.println("���ݿ����ӳɹ����޸������Ƿ���˵�״̬ " + conn);
	String sql = "update news set newsstatus='�����' where newsid=?";
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
     * @�������� TODO      ������ŵķ���
     * @param newstitle    ���ű���
     * @param newstype     ������Ŀ
     * @param content      ��������
     * @return   row       ���ݿ���Ӱ�������
     *  
     */

    public int addNews(String newstitle, String newstype, String newscontent) {
	int row = 0;
	conn = DbConn.getconn();// ���׳�SQLException
	System.out.println("���ݿ����ӳɹ���ִ��������Ų��� " + conn);
	String sql = "insert into news(newstitle,newscontent,newsstatus,newstype,createtime) values(?,?,?,?,?)";
	try {
	    pst = conn.prepareStatement(sql);
	    pst.setObject(1, newstitle);
	    pst.setString(2, newscontent);
	    pst.setString(3, "�����");
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
     * @�������� TODO        ��ѯ�������ż���
     * @param pageNumber   ҳ����
     * @param pageSize     ÿҳ�İ�����������
     * @return NewsList    ���е����ż���
     *  
     */
    public List<News> getNewsList(Integer pageNumber, Integer pageSize) {
	List<News> NewsList = new ArrayList<News>();
	News news = null;
	conn = DbConn.getconn();// ���׳�SQLException
	System.out.println("���ݿ����ӳɹ�: " + conn);
	try {
	    int startSize = (pageNumber - 1) * pageSize;
	    String sql = "select * from news limit " + startSize + "," + pageSize;
	    st = conn.createStatement();
	    rs = st.executeQuery(sql);
	    // 5.���������
	    while (rs.next()) {// ���׳�SQLException

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
     * @�������� TODO ��ѯ����������
     * @return count ����������
     *  
     */
    public int getCount() {
	int count = 0;
	conn = DbConn.getconn();
	System.out.println("���ݿ����ӳɹ���ִ�з�ҳ����" + conn);
	String sql = "select count(*) from news";
	try {
	    pst = conn.prepareStatement(sql);// ���׳�SQLException
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
     * @�������� TODO   ������������NewsId����ѯ�������Ŷ���
     * @param newsid  �������ŵ�id
     * @return  news  �������Ŷ���
     *  
     */
    public News selectSingleNews(Integer Uid) {
	News news = null;
	conn = DbConn.getconn();// ���׳�SQLException
	System.out.println("���ݿ����ӳɹ���������������:NewsId=" + Uid + "����ѯ�������Ŷ��� " + conn);
	String sql = "select * from news where newsid=?";
	try {
	    pst = conn.prepareStatement(sql);// ���׳�SQLException
	    pst.setInt(1, Uid);
	    rs = pst.executeQuery();
	    // 5.���������
	    if (rs.next()) {// ���׳�SQLException

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
     * @�������� TODO        ������������NewsId,�޸�����
     * @param Uid          ��Ҫ�޸����ŵ�id
     * @param newsTitle    ���ű���
     * @param newsType     ��������
     * @param newsContent  ��������
     * @return row         ���ݿ���Ӱ������� 
     *  
     */
    public int updateNews(Integer Uid, String newsTitle, String newsType, String newsContent) {
	int row = 0;
	conn = DbConn.getconn();// ���׳�SQLException
	System.out.println("���ݿ����ӳɹ���������������:NewsId=" + Uid + "���޸����� " + conn);
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
