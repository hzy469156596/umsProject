<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加权限</title>
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	margin: 0;
}

body {
	display: flex;
	flex-direction: column;
}

.content {
	width: 1000px;
	height: 500px;
	margin: 10px;
	border: 1px solid white;
}

.content .content-panel {
	flex-grow: 1;
	width: 1000px;
	height: 25px;
	border-bottom: 1px solid white;
	padding: 10px 0 10px 10px;
	line-height: 2px;
}


.content .content-form {
	flex-grow: 1;
	width: 1110px;
	height: 420px;
	padding: 10px 0 10px 10px;
}
</style>
</head>
<body>
	<div class="content">
		<div class="content-panel">
			<h3>添加权限</h3>
		</div>
		<form id="from" method="post">
			<div class="content-form">
				<table>
					<label>名 称：</label>
					<input class="input" type="text" name="jurisdiction.username"
						placeholder="请输入权限名称" minlength="4" maxlength="25" required>
					<br>
					<br>
					<br>
					<label>描 述：</label>
					<textarea name="jurisdiction.description" rows="10" cols="50"
						style="vertical-align: top" style="width:40%; height:50%;"
						placeholder="请输入权限描述" required></textarea>
					<div class="content-form-foot">
						<div class="content-form-foot-menu">
							<button type="button" class="submit color-green">保存</button>
							<button type="button" class="cancel color-red">取消</button>
						</div>
					</div>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			//取消
			$(".cancel").on("click", function() {
				$(".container").load("queryJurisdiction", function() {
					common();
					initJurisdiction();
				});
			});
			//提交表单
			$(".submit").on("click", function() {
				$.ajax({
					type : "POST",
					url : "form",
					data : $('#from').serialize(),// 你的form id
					success : function(data) {
						if(data.message == "success"){
							$(".container").load("queryJurisdiction", function() {
								toastr.success("添加角色成功!")
								common();
								initJurisdiction();
							});
						}else{
							toastr.error("添加角色失败!")
						}
						
					}
				});
			});
		});
	</script>
</body>
</html>