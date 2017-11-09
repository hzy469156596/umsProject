<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>阿尔泰用户管理系统</title>
<link href="./css/style.css" rel='stylesheet' type='text/css' />

<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./plugins/toastr.min.js"></script>
</head>
<body>
	<h1>Altai管理系统</h1>
	<div class="login-form">
		<div class="close"></div>
		<div class="head-info">
			<label class="lbl-1"> </label> <label class="lbl-2"> </label> <label
				class="lbl-3"> </label>
		</div>
		<div class="avtar">
			<img src="./images/avtar.png" />
		</div>
		
		<form onsubmit="return login()" action="login" method="post">
			<input type="text" class="text" name="user.name" placeholder="请输入你的用户名！" id="username">
			<div class="key">
				<input type="password" name="user.password" placeholder="请输入你的密码！" id="password">
			</div>
			<div class="a">
				<a href="" style="padding: 0 0 0 300px"
					onclick="window.open('forgetPassword.jsp')">忘记密码？</a>
			</div>
			<div class="signin">
			<div class="message">${message }</div>
				<input type="submit" value="Login">
			</div>
		</form>
	</div>
			
	<div class="copy-rights">
		<p>一組 &copy;2017用户管理后台</p>
	</div>
	
	<script>
		// 登陆验证函数
		function login() {
			//获取用户输入的用户名
			var username = document.getElementById("username").value;
			//获取用户输入的密码
			var password = document.getElementById("password").value;

			//验证用户输入项
			if (username == "") {
				alert("用户名不允许为空！");
				return false;
			} else if (password == "") {
				alert("密码不允许为空！");
				return false;
			}

		}
	</script>
</body>
</html>