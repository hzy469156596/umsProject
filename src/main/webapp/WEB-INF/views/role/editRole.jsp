<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

#bottomPanel {
	flex-grow: 1;
	display: flex;
	flex-direction: row;
}

#bottomPanel .content-form {
	flex-grow: 1;
	width: 1110px;
	height: 380px;
	padding: 10px 0 10px 40px;
}
#bottomPanel .content-panel {
	flex-grow: 1;
	width: 1110px;
	height: 25px;
	padding: 20px 0 20px 40px;
	line-height: 2px;
}


#bottomPanel .content-form-foot {
	flex-grow: 1;
	width: 1110px;
	height: 25px;
	padding: 20px 0 10px 10px;
}
</style>
</head>
<body>
	<div id="bottomPanel">
		<div class="content">
			<div class="content-panel">
				<h3>修改角色</h3>
			</div>
			<div class="content-form">
				<form id="from" method="post">
						名 称：
						<input class="input" type="text" name="role.username"
							placeholder="请输入角色名称" required value="${findEditRole.username }">
						<br>
						<br>
						<br>
						<label>描 述：</label>
						<textarea name="role.description" rows="10" cols="50"
							style="vertical-align: top" style="width:70%; height:50%;"
							placeholder="请输入角色描述" required
							>${findEditRole.description }</textarea>
						<br>
						<br>
						<br> 权 限:
						<c:forEach var="list" items="${listjurisdiction }">
						   <c:if test="${list.flag == true }">
						   <input type="checkbox" name="role.jurisdiction.id"
						   value="${list.id }" checked>${list.username }
						   </c:if>
						   
						    <c:if test="${list.flag == false }">
						   <input type="checkbox" name="role.jurisdiction.id"
						   value="${list.id }" >${list.username }
						   </c:if>
					    </c:forEach>
						<div class="content-form-foot">
							<div class="content-form-foot-menu">
								<button type="button" class="submit color-green">保存</button>
								<button type="button" class="cancel color-red">取消</button>
								<button type="reset" class="color-yellow">重置</button>
							</div>
						</div>
						<div style="display: none;">
							<input type="text" name="role.id" value="${findEditRole.id }">
						</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			//取消
			$(".cancel").on("click", function() {
				$(".container").load("queryRole", function() {
					common();
					initRole();
				});
			});
			//提交表单
			$(".submit").on("click", function() {
				$.ajax({
					cache : true,
					type : "POST",
					url : "editRoleSubmit",
					data : $('#from').serialize(),// 你的form id
					success : function(data) {
						if (data.message == "success") {
							toastr.success("修改角色成功!");
							$(".container").load("queryRole", function() {
								common();
								initRole();
							});
						} else {
							toastr.error("修改角色失败,请重试!");
						}
					}
				});
			});
		});
	</script>
</body>
</html>