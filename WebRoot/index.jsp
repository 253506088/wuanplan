<%@ page language="java" import="java.util.*,java.text.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@page import="www.wuanplan.com.database.pojo.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<%
	User user = null;
	if(session.getAttribute("user") != null){
		user = (User) session.getAttribute("user");
		%>
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
			<html>
			  <head>
			    <title>首页</title>
			    <meta charset="utf-8">
			    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
			  </head>
			  <body>
				<h3><%=user.getUsername() %>您好,欢迎回来。</h3>
				<a href="<%=basePath%>modifyPassword.jsp"><h3>修改密码</h3></a>
				<a href="<%=basePath%>logout"><h3>登出</h3></a>
		<%
		// 需要管理员权限
		if(user.getAdmin() == 1){
			%>
				<a href="<%=basePath%>userList"><h3>查看用户列表</h3></a>
			<%
		}
		%>
				</body>
			</html>
		<%
	}else{
		%>
			<a href="<%=basePath%>login.jsp"><h1>请先去登陆!</h1></a>
		<%
	}
%>
