package com.newsmanager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newsmanager.dao.NewsDao;
import com.newsmanager.entity.News;

public class NewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsDao NewsDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    @Override
    public void init() throws ServletException {

	NewsDao = new NewsDao();//初始化，加载类
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	String oprate = request.getParameter("oprate");
	if ("addnews".equals(oprate)) {
	    String newstitle = request.getParameter("newstitle");
	    String newstype = request.getParameter("newstype");
	    String newscontent = request.getParameter("newscontent");
	    int row = NewsDao.addNews(newstitle, newstype, newscontent);
	    response.sendRedirect("NewsServlet");//重定向到NewsServlet页面 
	} else if ("delete".equals(oprate)) {
	    Integer Did = Integer.valueOf(request.getParameter("newsid"));
	    //getParameter:the parameter has only one value  
	    int row = NewsDao.deleteNewsBYId(Did);
	    response.sendRedirect("NewsServlet");
	} else if ("allDelete".equals(oprate)) {
	    String[] deleteId = request.getParameterValues("deleteId");
	    if (deleteId != null) {
		//the parameter might have more than one value, use getParameterValues(java.lang.String).
		//And getParameterValues returns a String[];
		for (String string : deleteId) {
		    Integer Did = Integer.valueOf(string);
		    NewsDao.deleteNewsBYId(Did);
		}
	    }
	    response.sendRedirect("NewsServlet");

	} else if ("editStatus".equals(oprate)) {
	    String[] editId = request.getParameterValues("deleteId");
	    for (String string : editId) {
		Integer Eid = Integer.valueOf(string);
		NewsDao.changeNewsStatus(Eid);
	    }
	    response.sendRedirect("NewsServlet");

	} else if ("toupdate".equals(oprate)) {
	    Integer Uid = Integer.valueOf(request.getParameter("newsid"));
	    News news = NewsDao.selectSingleNews(Uid);
	    request.setAttribute("news", news);//request对象的setAttribute()方法将数据news设置在request范围内存取
	    request.getRequestDispatcher("updateNews.jsp").forward(request, response);
	} else if ("updatenews".equals(oprate)) {
	    Integer Uid = Integer.valueOf(request.getParameter("newsId"));
	    String newsTitle = request.getParameter("newsTitle");
	    String newsType = request.getParameter("newsType");
	    String newsContent = request.getParameter("newsContent");
	    int row = NewsDao.updateNews(Uid, newsTitle, newsType, newsContent);
	    response.sendRedirect("NewsServlet");
	} else {
	    Integer pageNumber = 1;
	    String pn = request.getParameter("pageNumber");

	    if (pn != null) {
		pageNumber = Integer.valueOf(pn);
	    }
	    if (pageNumber <= 1) {
		pageNumber = 1;
	    }
	    Integer pageSize = 6;
	    int count = NewsDao.getCount();
	    int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
	    if (pageNumber > pageCount) {
		pageNumber = pageCount;
	    }
	    // 调用查询News集合的方法
	    List<News> NewsList = NewsDao.getNewsList(pageNumber, pageSize);
	    // 添加到request作用域中
	    request.setAttribute("NewsList", NewsList);
	    request.setAttribute("pageNumber", pageNumber);
	    request.setAttribute("pageCount", pageCount);
	    request.setAttribute("count", count);

	    // 转发到首页NewsList.jsp
	    request.getRequestDispatcher("NewsList.jsp").forward(request, response);
	}
    }

    @Override
    public void destroy() {
	// 什么也不做
    }

}
