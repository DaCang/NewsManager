<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>添加新闻</title>
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
		<input type="hidden" name="oprate" value="addnews" /> 
		<input type="text" name="newstitle" placeholder="请输入标题" /> <br /> 
		新闻栏目 <select name="newstype">
			<option value="HTML">HTML</option>
			<option value="JavaScript">JavaScript</option>
			<option value="Oracle">Oracle</option>
		</select> <br />
		<textarea id="content" name="newscontent" rows="8" cols="70"></textarea>
		<br /> <input type="submit" value="添加" />
	</form>
</body>
</html>