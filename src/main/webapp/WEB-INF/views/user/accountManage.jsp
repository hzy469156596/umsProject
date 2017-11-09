<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content">
	<div class="content-panel">
		<button class="addUser color-green">添加</button>
		<button class="deleteUser color-red">选删</button>
	</div>
	<div class="content-form">
		<table>
			<thead>
				<th width="80px"><input class="check-all-btn" type="checkbox">序号</th>
				<th>用户名</th>
				<th>姓名</th>
				<th>部门</th>
				<th>角色</th>
				<th>状态</th>
				<th>创建时间</th>
				<th >操作</th>
			</thead>
			<tbody>
				<!-- varStatus获取当前索引或序号         varStatus="status" -->
				<c:forEach var="item" items="${listuser }" varStatus="status">
					<tr class="trId" data-id="${item.id }">
						<!-- count获取当前序号，从1开始 -->
						<!-- index获取当前索引，从0开始 -->
						<td><input type="checkbox" name="checkbox"
							data-id="${item.id }">${status.count }</td>
						<td>${item.username }</td>
						<td>${item.name }</td>
						<td>${item.department.username }</td>
						<td>${item.role.username }</td>
						<td>${item.status}</td>
						<td>${item.createDate}</td>
						<td width="100px">
						<div class="container-menu"	>
							<button data-id="${item.id }" class="edit color-green">编辑</button>
							<button data-id="${item.id }" class="delete color-red">删除</button>
						</div>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</div>