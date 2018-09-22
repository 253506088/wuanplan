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
  			<span>原密码:</span><input type="password" id="oldPassword" name="oldPassword">
  			<span id="oldPasswordSpan"></span><br/>
  			<span>密码:</span><input type="password" id="password" name="password">
  			<span id="passwordSpan">密码不能为空,长度不能于6大于20</span><br/>
  			<span>确认密码:</span><input type="password" id="password2">
  			<span id="passwordSpan2">两次密码不相同</span><br/>
  		</form>
  		<button id="button">修改密码</button>
  		<script>
			$(document).ready(function(){
				$("#oldPasswordSpan").hide();
				$("#passwordSpan").hide();
				$("#passwordSpan2").hide();
				
				$("#button").click(function(){
					if(f1() && f2()){
						//通过前端验证后发起修改密码请求
						var data = $("#form").serialize();
						$.post("<%=basePath%>modifyPassword",data,function(rel){
							if(rel.flag){
								window.location.href='<%=basePath%>index.jsp';
							}else{
								alert("原密码错误!");
								console.log(rel.flag);
							}
						},"json");
					}
				});
			});
			
			$("#oldPassword").blur(function(){
				f1()
			});
			
			$("#password").blur(function (){
				f2();
			});
			
			$("#password2").blur(function (){
				f2();
			});
			
			//原密码验证
			function f1(){
				var buffer = $("#oldPassword").val();
				if(buffer == "" || buffer.length<6 || buffer.length>20){
					//验证长度
					$("#oldPasswordSpan").text("原密码不能为空,长度不能小于6大于20");
					$("#oldPasswordSpan").show();
					return false;
				}
				$("#oldPasswordSpan").hide();
				return true;
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
