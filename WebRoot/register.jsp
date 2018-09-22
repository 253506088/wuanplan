<%@ page language="java" import="java.util.*,java.text.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
	<meta charset="utf-8">
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
  </head>
  
  <body>
  		<form id="form">
  			<span>账号:</span><input type="text" id="username" name="username">
  			<span id="usernameSpan"></span><br/>
  			<span>密码:</span><input type="password" id="password" name="password">
  			<span id="passwordSpan">密码不能为空,长度不能于6大于20</span><br/>
  			<span>确认密码:</span><input type="password" id="password2">
  			<span id="passwordSpan2">两次密码不相同</span><br/>
  		</form>
  		<button id="button">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>login.jsp">已经有账号?点击登录</a>
  		<script>
			$(document).ready(function(){
				$("#usernameSpan").hide();
				$("#passwordSpan").hide();
				$("#passwordSpan2").hide();
				
				$("#button").click(function(){
					if(f1() && f2()){
						//通过前端验证后发起注册请求
						var data = $("#form").serialize();
						$.post("<%=basePath%>register",data,function(rel){
							if(rel.flag){
								window.location.href='<%=basePath%>login.jsp';
							}else{
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
			
			$("#password2").blur(function (){
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
				}else{
					//验证重名
					var data = "username="+buffer;
					var flag = true;
					$.post("<%=basePath%>register",data,function(rel){
						if(rel.flag){
							$("#usernameSpan").hide();
						}else{
							console.log(rel.flag);
							$("#usernameSpan").text("该账号已存在");
							$("#usernameSpan").show();
							flag =  false;
						}
					},"json");
					return flag;
				}
			}
			
			//密码验证
			function f2(){
				var buffer = $("#password").val();
				//验证长度
				if(buffer == "" || buffer.length<6 || buffer.length>20){
					$("#passwordSpan").show();
					return false;
				}else if(buffer!=$("#password2").val()){
					//验证两次密码是否相同
					$("#passwordSpan2").show();
					return false;
				}
				$("#passwordSpan").hide();
				$("#passwordSpan2").hide();
				return true;
			}
  		</script>
  </body>
</html>
