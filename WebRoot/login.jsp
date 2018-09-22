<%@ page language="java" import="java.util.*,java.text.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
	<meta charset="utf-8">
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
  </head>
  
  <body>
  		<form id="form">
  			<span>账号:</span><input type="text" id="username" name="username">
  			<span id="usernameSpan"></span><br/>
  			<span>密码:</span><input type="password" id="password" name="password">
  			<span id="passwordSpan">密码不能为空,长度不能于6大于20</span><br/>
  		</form>
  		<button id="button">登陆</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>register.jsp">没有账号?点击注册</a>
  		<script>
			$(document).ready(function(){
				$("#usernameSpan").hide();
				$("#passwordSpan").hide();
				
				$("#button").click(function(){
					if(f1() && f2()){
						//通过前端验证后发起登录请求
						var data = $("#form").serialize();
						$.post("<%=basePath%>login",data,function(rel){
							if(rel.flag){
								window.location.href='<%=basePath%>index.jsp';
							}else{
								alert("账号或密码错误");
								console.log(rel.flag);
							}
						},"json");
					}
				});
			});
			
			$("#username").blur(function(){
				f1()
			});
			
			$("#password").blur(function (){
				f2();
			});
			
			//账号验证
			function f1(){
				var buffer = $("#username").val();
				if(buffer == "" || buffer.length<6 || buffer.length>20){
					//验证长度
					$("#usernameSpan").text("账号不能为空,长度不能小于6大于20");
					$("#usernameSpan").show();
					return false;
				}
				$("#usernameSpan").hide();
				return true;
			}
			
			//密码验证
			function f2(){
				var buffer = $("#password").val();
				//验证长度
				if(buffer == "" || buffer.length<6 || buffer.length>20){
					$("#passwordSpan").show();
					return false;
				}
				$("#passwordSpan").hide();
				return true;
			}
  		</script>
  </body>
</html>
