<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>阿尔泰管理系统</title>
<link href="./css/accountManage.css" rel='stylesheet' type='text/css' />
<link href="./css/toastr.min.css" rel='stylesheet' type='text/css' />
<!-- 字体 -->
<link rel="stylesheet" type="text/css" href="./css/iconfont.css">
</head>
<body>
	<div id="topPanel">
		<div class="logo-panel">
			<div class="logo-content-panel"></div>
		</div>
		<div class="esc"><span>${user.name}</span><div class="caret"></div><a href="exit" class="exit">[退出账户]</a></div>
		
	</div>
	<div id="bottomPanel">
		<div class="menu-panel">
			<div class="menu-content-panel">
				<ul class="nav">
					<li class="user"><i class="iconfont icon-27"></i> 账号管理</li>
					<li class="role"><i class="iconfont icon-jiaose"></i> 角色管理</li>
					<li class=Jurisdiction><i class="iconfont icon-quanxian-copy"></i>
						权限管理</li>
					<li class="Department"><i
						class="iconfont icon-plus-departments"></i> 部门管理</li>
				</ul>
			</div>
		</div>
		<div class="container"></div>
	</div>
	<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="./plugins/toastr.min.js"></script>
	<script>
		$(function() {
			//通知
			toastr.options = {
				"closeButton" : true,
				"debug" : false,
				"positionClass" : "toast-top-right",
				"onclick" : null,
				"showDuration" : "300",
				"hideDuration" : "1000",
				"timeOut" : "3000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			}
			//高亮显示
			$(".nav li").click(function() {
				$(this).addClass("active").siblings().removeClass("active");
			});
			
		
			
			//账户管理
			$(".user").on("click", function() {
				$(".container").load("queryUser", function() {
					common();
					initUser();
				});
			}).first().click();
			//角色管理
			$(".role").on("click", function() {
				$(".container").load("queryRole", function() {
					common();
					initRole();
				});
			});
			//权限管理
			$(".Jurisdiction").on("click", function() {
				$(".container").load("queryJurisdiction", function() {
					common();
					initJurisdiction();
				});
			});
			//部门管理
			$(".Department").on("click", function() {
				$(".container").load("queryDepartment", function() {
					common();
					initDepartment();
				});
			});
		});

		//公有jQuery
		function common() {
			var $contentform = $(".content-form");
			$table = $contentform.find("table");
			//全选
			$(".check-all-btn").on(
					"click",
					function() {
						$("input[name='checkbox']").prop("checked",
								$(".check-all-btn").prop("checked"));
					});
			// 选中指定行
			$table.find("tbody tr td:not(:first-child)").on("click",
					function() {
						var $this = $(this).parent();
						var nowItem = $this.find("input[name='checkbox']");
						// 获取当前行checkbox的状态值
						var isChecked = nowItem.prop("checked");
						// 最新的状态值只需要与当前状态值相反即可
						nowItem.prop("checked", !isChecked);
						$table.find("tbody tr").removeClass("active");
						$this.addClass("active");
					});
		}
		//账户管理
		function initUser() {
			var $contentform = $(".container");
			$table = $contentform.find("table");

			//账户管理点击添加
			$(".addUser").on("click", function() {
				$(".container").load("queryAdduser");
			});

			//账户管理选中编辑
			$(".edit").on("click", function() {
				var $this = $(this);
				// 获取商品id
				var editId = $this.data("id");
				$(".container").load("editUser", {
					editId : editId
				}, function() {
					common();
					initUser();
				});
			});

			//账户管理选中删除
			$(".delete").on(
					"click",
					function() {
						var $this = $(this);
						var checkedboxItems = $table
								.find("input[name='checkbox']:checked");
						// 获取商品id
						var deleteId = $this.data("id");
						if (confirm("确定要删除吗？")) {
							// 异步传参数到Dao
							$.ajax({
								//action的name
								//cache : true,只有使用get方法才有效
								url : "delete",
								type : "post",
								async : false,
								dataType : "json",
								data : {
									deleteId : deleteId
								},
								success : function(data) {
									if (data.message == "success") {
										toastr.success("删除用户成功！")
										$(".container").load("queryUser",
												function() {
													common();
													initUser();
												});
									} else {
										toastr.error("删除用户失败！")
									}
									/*  checkedboxItems.parent().parent()
										.remove();   */
								}
							})
						}
					});

			//账户管理全选删除
			$(".deleteUser").on("click", function() {
				var ids = new Array();
				if ($("input[type='checkbox']:checked").length <= 0) {
					toastr.info("请选择要删除的项！")
				} else if (confirm("确认删除？")) {
					$("input[name='checkbox']:checked").each(function(i) {
						var $this = $(this)
						ids[i] = $this.data("id");
					});
					console.log(ids);
					$.ajax({
						traditional : true,
						cache : true,
						url : "deleteAll",
						type : "post",
						dataType : "json",
						data : {
							ids : ids
						},
						success : function(data) {
							if (data.message == "success") {
								$(".container").load("queryUser", function() {
									toastr.success("删除用户成功！")
									common();
									initUser();
								});
							} else {
								toastr.error("删除用户失败！")
							}
						}
					});
				}
			});
		}

		//角色管理
		function initRole() {
			var $contentform = $(".content-form");
			$table = $contentform.find("table");

			//角色管理点击添加
			$(".addRole").on("click", function() {
				$(".container").load("addRole");
			});

			//角色管理选中编辑
			$(".editRole").on("click", function() {
				var $this = $(this);
				// 获取商品id
				var editId = $this.data("id");
				$(".container").load("editRole", {
					editId : editId
				}, function() {
					common();
					initRole();
				});
			});

			//角色管理选中删除
			$(".deleteRole").on(
					"click",
					function() {
						var $this = $(this);
						var checkedboxItems = $table
								.find("input[name='checkbox']:checked");
						// 获取商品id
						var deleteId = $this.data("id");
						if (confirm("确定要删除吗？")) {
							// 异步传参数到Dao
							$.ajax({
								//action的name
								//cache : true,只有使用get方法才有效
								url : "deleteRole",
								type : "post",
								async : false,
								dataType : "json",
								data : {
									deleteId : deleteId
								},
								success : function(data) {
									if (data.message == "success") {
										toastr.success("删除角色成功！")
										$(".container").load("queryRole",
												function() {
													common();
													initRole();
												});
									} else {
										toastr.error("删除角色失败!");
									}
									/*  checkedboxItems.parent().parent()
										.remove();   */
								}
							})
						}
					});

			//角色管理全选删除
			$(".deleteAllRole").on("click", function() {
				var ids = new Array();
				if ($("input[type='checkbox']:checked").length <= 0) {
					toastr.info("请选择要删除的项！")
				} else if (confirm("确认删除？")) {
					$("input[name='checkbox']:checked").each(function(i) {
						var $this = $(this)
						ids[i] = $this.data("id");
					});
					console.log(ids);
					$.ajax({
						traditional : true,
						cache : true,
						url : "deleteAllRole",
						type : "post",
						dataType : "json",
						data : {
							ids : ids
						},
						success : function(data) {
							if (data.message == "success") {
								$(".container").load("queryRole", function() {
									toastr.success("删除角色成功！");
									common();
									initRole();
								});
							} else {
								toastr.error("删除角色失败!");
							}
						}
					});
				}
			});
		}
		//权限管理
		function initJurisdiction() {
			var $contentform = $(".content-form");
			$table = $contentform.find("table");
			//添加权限
			$(".addJurisdiction").on("click", function() {
				$(".container").load("queryAddjurisdiction");
			});

			//权限选中编辑
			$(".editJurisdiction").on("click", function() {
				var $this = $(this);
				// 获取商品id
				var editJurisdictionId = $this.data("id");
				$(".container").load("editJurisdiction", {
					editJurisdictionId : editJurisdictionId
				}, function() {
					common();
					initJurisdiction();
				});
			});

			//权限选中删除
			$(".deleteJurisdiction").on(
					"click",
					function() {
						var $this = $(this);
						var checkedboxItems = $table
								.find("input[name='checkbox']:checked");
						// 获取商品id
						var deleteJurisdictionId = $this.data("id");
						if (alert("大哥这个不能删!")) {
							// 异步传参数到Dao
							$.ajax({
								//action的name
								//cache : true,只有使用get方法才有效
								url : "deleteJurisdiction",
								type : "post",
								data : {
									deleteJurisdictionId : deleteJurisdictionId
								},
								success : function(data) {
									if (data.message == "success") {
										$(".container").load(
												"queryJurisdiction",
												function() {
													toastr.success("删除权限成功")
													common();
													initJurisdiction();
												});
									} else {
										toastr.error("删除权限失败!")
									}

								}
							});
						}
					});

			//权限管理全选删除
			$(".deleteAllJurisdiction").on(
					"click",
					function() {
						var ids = new Array();
						if ($("input[type='checkbox']:checked").length <= 0) {
							toastr.info("请选择要删除的项！")
						} else if (confirm("确认删除？")) {
							$("input[name='checkbox']:checked").each(
									function(i) {
										var $this = $(this)
										ids[i] = $this.data("id");
									});
							console.log(ids);
							$.ajax({
								traditional : true,
								cache : true,
								url : "deleteAllJurisdiction",
								type : "post",
								dataType : "json",
								data : {
									ids : ids
								},
								success : function(data) {
									if (data.message == "success") {
										$(".container").load(
												"queryJurisdiction",
												function() {
													toastr.success("删除权限成功！");
													common();
													initJurisdiction();
												});
									} else {
										toastr.error("删除权限失败!");
									}
								}
							});
						}
					});

		}
		//部门管理
		function initDepartment() {
			var $contentform = $(".container");
			$table = $contentform.find("table");

			//添加部门
			$(".addDepartment").on("click", function() {
				$(".container").load("queryAdddepartment");
			});

			//部门选中编辑
			$(".editDepartment").on("click", function() {
				var $this = $(this);
				// 获取商品id
				var editDepartmentId = $this.data("id");
				$(".container").load("editDepartment", {
					editDepartmentId : editDepartmentId
				}, function() {
					common();
					initDepartment();
				});
			});

			//部门选中删除
			$(".deleteDepartment").on(
					"click",
					function() {
						var $this = $(this);
						var checkedboxItems = $table
								.find("input[name='checkbox']:checked");
						// 获取商品id
						var deleteDepartmentId = $this.data("id");
						if (alert("大哥这个不能删!")) {
							// 异步传参数到Dao
							$.ajax({
								//action的name
								//cache : true,只有使用get方法才有效
								url : "deleteDepartment",
								type : "post",
								data : {
									deleteDepartmentId : deleteDepartmentId
								},
								success : function(data) {
									if (data.message == "success") {
										$(".container").load("queryDepartment",
												function() {
													toastr.success("删除部门成功!")
													common();
													initDepartment();
												});
									} else {
										toastr.error("删除部门失败!")
									}

								}
							});
						}
					});

			//部门管理全选删除
			$(".deleteAllDepartment").on(
					"click",
					function() {
						var ids = new Array();
						if ($("input[type='checkbox']:checked").length <= 0) {
							toastr.info("请选择要删除的项！")
						} else if (confirm("确认删除？")) {
							$("input[name='checkbox']:checked").each(
									function(i) {
										var $this = $(this)
										ids[i] = $this.data("id");
									});
							console.log(ids);
							$.ajax({
								traditional : true,
								cache : true,
								url : "deleteAllDepartment",
								type : "post",
								dataType : "json",
								data : {
									ids : ids
								},
								success : function(data) {
									if (data.message == "success") {
										$(".container").load("queryDepartment",
												function() {
													toastr.success("删除部门成功！");
													common();
													initDepartment();
												});
									} else {
										toastr.error("删除部门失败!");
									}
								}
							});
						}
					});
		}
	</script>
</body>
</html>