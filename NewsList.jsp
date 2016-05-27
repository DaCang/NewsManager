<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
		"http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
				<title>新闻列表</title>
				<script type="text/javascript">
					function allChecked(allCheck) {
					var deleteId = document.getElementsByName("deleteId");
					for (var i = 0; i < deleteId.length; i++) {
							deleteId[i].checked = allCheck.checked;
							}
							}
					function changeStatus() {
						document.getElementById("oprate").value = "editStatus";
						document.forms[0].submit();
					}
				</script>
					</head>
					<body>
					    <a href="index.jsp" >＜＜返回首页</a>
						<h1 align="center">
							<strong>欢迎访问</strong>
						</h1>
						<form action="NewsServlet" method="post">
							<input type="submit" value="批量删除" />
							<input type="hidden" id="oprate" name="oprate" value="allDelete" />
							<input type="button" value="批量审核" onclick="changeStatus()"/>
                            <a href="addNews.jsp" >添加新闻</a>
							<table width="1000px" cellpadding="0px" cellspacing="0px" border="1px" style="border: 1px solid gray; border-collaspe: collaspe;" >
								<tr>
									<!--表头-->
									<th align="right"><input type="checkbox" name="allCheck" onclick="allChecked(this)" /></th>
									<th>文章序号</th>
									<th>文章标题</th>
									<th>所属栏目</th>
									<th>创建时间</th>
									<th>是否审核</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${NewsList}" var="news">
									<tr>
										<!-- 表格 -->
										<td align="center">
											<input type="checkbox" name="deleteId" value="${news.newsId}" /></td>
										<td align="center">${news.newsId}</td>
										<td align="center">${news.newsTitle}</td>
										<td align="center">${news.newsType}</td>
										<td align="center">${news.createTime}</td>
										<td align="center">${news.newsStatus}</td>
										<td align="center">
											<a href="NewsServlet?oprate=toupdate&newsid=${news.newsId}">编辑 </a>
											| 
											<a href="NewsServlet?oprate=delete&newsid=${news.newsId}">删除</a></td>
									</tr>
								</c:forEach>
								<tr >
									<!-- 分页 -->
									<td colspan="6" align="right">
										<a href="NewsServlet?pageNumber=1">首页</a> 
										<a href="NewsServlet?pageNumber=${pageNumber-1 }">上一页</a> 
										<a href="NewsServlet?pageNumber=${pageNumber+1 }">下一页</a> 
										<a href="NewsServlet?pageNumber=${pageCount }">尾页</a>
										第 页  总共count 页

									</td>
								</tr>
							</table>
						</form>
						<p align="center">
							<img src="learn.jpg" border="1" usemap="#planetmap" alt="Learn" />
							<map name="planetmap" id="planetmap">
								<area shape="rect" coords="0,0,555,51" href="Learn_jpg.html" target="_blank" alt="Learn" />
							</map>
						</p>
						
						<p align="right">
							<button type="button" onclick="alert('<%out.print("JSP+Servlet+JDBC" + (new java.util.Date()).toLocaleString());%>' )">
										<b>关于我们</b>
									</button>
								</p>
								<p align="right">
									<%   Date date=new Date();
											out.print("现在时间:" + date.toLocaleString());
											%>
									</p>
								</body>
							</html>