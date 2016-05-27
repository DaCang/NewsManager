<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>修改新闻</title>
    <script type="text/javascript" src="kindeditor-4.1.10/kindeditor.js"></script>
    <script type="text/javascript">
        KindEditor.ready(function(K) {
                K.create('#content', {
	                uploadJson : 'kindeditor-4.1.10/jsp/upload_json.jsp',
	                fileManagerJson : 'kindeditor-4.1.10/jsp/file_manager_json.jsp',
	                allowFileManager : true
        		});
        });
    </script>
  </head>
  <body>
    <form action="NewsServlet" method="post">
    	<input type="hidden" value="${news.newsId}" name="newsId"/>
    	<input type="hidden" name="oprate" value="updatenews"/>
    	<input type="text" name="newsTitle" value="${news.newsTitle}" placeholder="请输入标题"/><br/>
    	新闻栏目
    	<select name="newsType">
    		
    	   <option  <c:if test="${news.newsType==\"HTML\"}">selected</c:if> value="HTML">HTML</option>
    	   <option  <c:if test="${news.newsType==\"Javascript\"}">selected</c:if> value="JavaScript">JavaScript</option>
    	   <option  <c:if test="${news.newsType==\"Oracle\"}">selected</c:if> value="Oracle">Oracle</option>
    	</select>
    	<br/>
    	<textarea id="content" name="newsContent" rows="8" cols="70">${news.newsContent}</textarea><br/>
    	<input type="submit" value="确定修改"/>
    </form>
  </body>
</html>
