<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content">
		<div class="content-panel">
			<button class="addRole color-green">
			添加
			</button>
			<button class="deleteAllRole color-red">选删</button>
		</div>
		<div class="content-form">
			<table>
				<thead>
					<th width="80px"><input type="checkbox" class="check-all-btn">序号</th>
					<th>名称</th>
					<th>描述</th>
					<th>创建时间</th>
					<th>操作</th>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listrole }" varStatus="status">
						<tr class="item" data-id="${item.id }">
							<!-- count获取当前序号，从1开始 -->
							<!-- index获取当前索引，从0开始 -->
							<td><input type="checkbox" name="checkbox" data-id="${item.id }">${status.count }</td>
							<td>${item.username }</td>
							<td>${item.description }</td>
							<td>${item.createDate}</td> 
							<td width="100px">
							<div class="container-menu">
								<button data-id="${item.id }" class="editRole color-green">编辑</button>
								<button data-id="${item.id }" class="deleteRole color-red">删除</button>
								</div>	
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>