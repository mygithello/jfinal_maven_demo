<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <a href="<%=basePath%>blog/form">新增</a>
	<br><br>
	<table width=600>
	<c:forEach items="${blogs}" var="b" varStatus="s">
		<tr><td>${s.index+1}</td><td>${b.title}</td><td>${b.pubtime}</td>
		<td align=center>
		<a href="<%=basePath%>blog/form?id=${b.id}">编辑</a>
		<a href="<%=basePath%>blog/delete?id=${b.id}">删除</a>
		</td></tr>
	</c:forEach>
	</table>
  
  
  
    hello jfinal !!!!!!!!!!!!! <br>
  </body>
</html>
