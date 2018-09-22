<%@ page language="java" import="java.util.*,java.text.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@page import="www.wuanplan.com.database.pojo.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<%
	User user = null;
	if(session.getAttribute("user") == null){
		%>
			<a href="<%=basePath%>login.jsp"><h1>请先去登陆!</h1></a>
		<%
		return ;
	}
	user = (User) session.getAttribute("user");
	if(user.getAdmin() == 1 && request.getAttribute("userList") != null && 
		request.getAttribute("countPageNumber") != null && 
		request.getAttribute("pageNumber") != null){
		List<User> users = (List<User>) request.getAttribute("userList");
		
		%>
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
			<html>
			  <head>
			    <title>查看用户列表</title>
			    <meta charset="utf-8">
			    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
			  </head>
			  	<table border="1">
			  		<tr>
			  			<td>id</td>
			  			<td>账号</td>
			  			<td>创建时间</td>
			  			<td>上次修改时间</td>
			  		</tr>
			  		<%
						SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			  			for(User buffer : users){
			  				%>
			  					<tr>
			  					<td><%=buffer.getId() %></td>
			  					<td><%=buffer.getUsername() %></td>
			  					<td><%=sdf.format(new Date(new Long(buffer.getGmtCreate()))) %></td>
			  					<td><%=sdf.format(new Date(new Long(buffer.getGmtModified()))) %></td>
			  					</tr>
			  				<%
			  			}
			  		%>
			  	</table>

			  		

				<%
					Integer countPageNumber = (Integer) request.getAttribute("countPageNumber");
					Integer pageNumber = (Integer) request.getAttribute("pageNumber");
					%><a href="<%=basePath%>userList?pageNumber=<%=pageNumber-1%>">上一页</a>&nbsp;&nbsp;<%
			  		if(pageNumber<=	5){
			  			int buffer = 10;
			  			if(countPageNumber < 10){
			  				buffer = countPageNumber;
			  			}
			  			for(int i = 1;i <= buffer;i++){
			  				%><a href="<%=basePath%>userList?pageNumber=<%=i%>"><%=i %></a>&nbsp;&nbsp;<%
			  			}
			  		}
			  		if(pageNumber > 5){
			  			for(int i = (pageNumber - 5);i <= pageNumber;i++){
			  				%><a href="<%=basePath%>userList?pageNumber=<%=i%>"><%=i %></a>&nbsp;&nbsp;<%
			  			}
			  			int buffer = 5;
			  			if((countPageNumber - pageNumber) < 5){
			  				buffer = (countPageNumber - pageNumber)+1;
			  			}
			  			for(int i = 1;i < buffer;i++){
			  				int p = pageNumber+i;
			  				%><a href="<%=basePath%>userList?pageNumber=<%=p%>"><%=p %></a>&nbsp;&nbsp;<%
			  			}
			  		}
			  		%><a href="<%=basePath%>userList?pageNumber=<%=pageNumber+1%>">下一页</a>&nbsp;&nbsp;<%
			  		%><h1>总共<%=countPageNumber%>页</h1><%
		  		%>
			  </body>
			</html>
		<%
	}
%>
