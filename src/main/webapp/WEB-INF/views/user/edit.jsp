<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
#bottomPanel {
	flex-grow: 1;
	display: flex;
	flex-direction: row;
}
#bottomPanel .container {
	width: 100%;
	height: 530px;
}

#bottomPanel .content-panel {
	flex-grow: 1;
	width: 1110px;
	height: 25px;
	padding: 10px 0 10px 10px;
}

#bottomPanel .content-form {
	flex-grow: 1;
	width: 1110px;
	height: 50px;
	line-height: 60px;
	padding: 10px 0 10px 30px;
}

#bottomPanel .content-panel-foot {
	flex-grow: 1;
	width: 1110px;
	 border-top: 1px solid gray;
	height: 25px;
	padding: 10px 0 10px 30px;
}

#bottomPanel .content-panel h3{
padding-left:30px;
}
</style>
</head>
<body>
	<div id="bottomPanel">
		<div class="content">
			<div class="content-panel">
		    <h3>修改用户</h3>
			</div>
			<form id="from" method="post">
				<div class="content-form">
					用户名:<input type="text" name="user.username"
						value="${listEditUser.username }"> 　密 码:<input type="text"
						name="user.password" value="${listEditUser.password }">
				</div>
				<div class="content-form">
					姓　名:<input type="text" name="user.name"
						value="${listEditUser.name }">　 手 机: <input type="text"
						name="user.phone" value="${listEditUser.phone }">
				</div>
				<div class="content-form">
					性别:<input type="radio" name="user.gender" value="男"
						<c:if test="${listEditUser.gender eq '男' }">checked</c:if>>
					男 <input type="radio" name="user.gender" value="女"
						<c:if test="${listEditUser.gender eq '女' }">checked</c:if>>
					女　　　　　　　　部 门: <select name="user.department.id" style="width: 170px"
						data-id="${item.id }">
						<c:forEach var="item" items="${listdepartment }"
							varStatus="status">
							<c:choose>
	                           <c:when test="${item.username == listEditUser.department.username }">
	                           <option value="${item.id}" selected >${item.username}</option>
	                           </c:when>
	                           <c:otherwise>
	                           <option value="${item.id}" >${item.username}</option>
	                           </c:otherwise>						
							</c:choose>
						</c:forEach>
						
					</select>
				</div>
				<div class="content-form">
					状 态:<input type="radio" name="user.status" value="启用"
						<c:if test="${listEditUser.status eq '启用' }">checked</c:if>>启用
					<input type="radio" name="user.status" value="禁用"
						<c:if test="${listEditUser.status eq '禁用' }">checked</c:if>>禁用
					          　　　　　　角 色: <select name="user.role.id" style="width: 170px"
						data-id="${item.id }">
						<c:forEach var="item1" items="${listrole }" varStatus="status">
						<c:choose>
	                           <c:when test="${item1.username == listEditUser.role.username }">
	                           <option value="${item1.id}" selected >${item1.username}</option>
	                           </c:when>
	                           <c:otherwise>
	                           <option value="${item1.id}" >${item1.username}</option>
	                           </c:otherwise>						
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="content-panel-foot">
					<button type="button" class="submit color-green">更新</button>
					<button type="button" class="cancel color-red">取消</button>
					<button type="reset" class="color-yellow">重置</button>
				</div>
				<div style="display: none;">
					<input type="text" name="user.id" value="${listEditUser.id }">
				</div>

			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			//取消
			$(".cancel").on("click", function() {
				$(".container").load("queryUser", function() {
					common();
					initUser();
				});
			});
			//提交表单
			$(".submit").on("click", function() {
				$.ajax({
					url : "editUserSave",
					type : "POST",
					data : $('#from').serialize(),
					success : function(data) {
						if (data.message == "success") {
							$(".container").load("queryUser", function() {
								toastr.success("修改用户成功!")
								common();
								initUser();
							});
						}else{
							toastr.error("修改用户失败!")
						}
					}
				});
			});
		});
	</script>