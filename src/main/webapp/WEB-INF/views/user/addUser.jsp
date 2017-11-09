<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
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
        height: 230px;
        line-height: 60px;
        padding: 10px 0 10px 10px;
    }
    #bottomPanel .content-panel-foot {
        flex-grow: 1;
        width: 1110px;
        height: 25px;
        border-top: 1px solid gray;
        padding: 10px 0 10px 10px;
    }
    #bottomPanel h3 {
        margin:20px 0px 20px 40px;
    }
    </style>
</head>
<body>
<div id="bottomPanel">
        <div class="content">
                    <h3>添加用户</h3> 
 <form id="from" method="post">
            <div class="content-form">　　 用　户　名:
                <input type="text" name="user.username"> 　　　　　　 密　码:
                <input type="password" name="user.password">
                <br> 　　 姓　　　名:
                <input type="text" name="user.name"> 　　　　　　 手　机:
                <input type="text" name="user.phone">
                <br>　　 性　　　别:
                <input type="radio" name="user.gender" value="男" checked> 男
                <input type="radio" name="user.gender" value="女" > 女　　　　　　　　　　　　部　门:
                <select name="user.department.id" style="width: 170px" data-id="${item.id }">
                    <c:forEach var="item" items="${listdepartment }" varStatus="status">
                    <option value="${item.id}">${item.username }</option>
                    </c:forEach>
                </select>
                <br>　　 状　　　态:
                <input type="radio" name="user.status" value="启用" checked>启用
                <input type="radio" name="user.status" value="禁用">禁用 　　　　　　　　　　 角　色:
                <select name="user.role.id" style="width: 170px" data-id="${item.id }">
                    <c:forEach var="item1" items="${listrole }" varStatus="status">
                    <option value="${item1.id }">${item1.username }</option>
                    </c:forEach>
                </select>
            </div>
            <div class="content-panel-foot">
                <button type="button" class="submit color-green">保存</button>
                <button type="button" class="cancel color-red">取消</button>
                <button type="reset" class="color-yellow">重置</button>
            </div>
    </form> 
        </div>
    </div>
     <script type="text/javascript">
     $(function(){
    	//取消
       $(".cancel").on("click",function(){
    	  $(".container").load("queryUser",function(){
    		  common();
    		  initUser();
    	  });
       });
    	//提交表单
    	 $(".submit").on("click",function(){
    	$.ajax({
                cache: true,
                type: "POST",
                url: "from",
                dataType : "json",
                data:$('#from').serialize(),// 你的form id
                success:function(data){
                	if(data.message == "success"){
                		 $(".container").load("queryUser",function(){
                			toastr.success("添加用户成功!")
                       	  common();
                       	  initUser();
                      	  });
                	}else{
                		toastr.error("添加用户失败!")
                	}
                	
                }
    	});
    	 }); 
     });
     </script>
</body>
</html>