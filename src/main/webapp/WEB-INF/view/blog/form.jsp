<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	  <!--
			  当存在blog参数时，说明是修改，并且查询出来了展示在表单中，当表单中没有数据时，
			  会两种情况，一种是请求form的时候没有加？id=..参数，另外一种情况是，携带的id在数据库中是不存在的。
		-->
	<form method="post" action="<%=basePath%>blog/save">
		<input type="hidden" name="blog.id" value="${blog.id}" />
		<input type="text" name="blog.title" value="${blog.title}" /><Br>
		<textarea style="width:600px;height:200px" name="blog.content">${blog.content}</textarea>
		<br>
		<input type="submit" value=" 提 交 " />
	</form>
	
  </body>
</html>
