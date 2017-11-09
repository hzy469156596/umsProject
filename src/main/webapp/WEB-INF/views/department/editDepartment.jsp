<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">

    .content {
        width: 100%;
        height: 500px;
        margin: 10px;
    }

    .content .content-panel {
        flex-grow: 1;
        width: 1290px;
        height: 25px;
        padding: 10px 0 10px 20px;
        line-height: 2px;
    }

    .content .content-form {
        flex-grow: 1;
        width: 1110px;
        height: 420px;
        padding: 10px 0 10px 20px;
    }

#bottomPanel .content-form-foot-menu {
	flex-grow: 1;
	width: 1110px;
	height: 25px;
	padding: 10px 0 10px 20px;
	
}
    </style>

</head>
<body>
  <div class="content">
            <div class="content-panel">
                <h3>更新部门</h3>
            </div>
            <form id="from" method="post">
            <div class="content-form">
                <table>
                    <label>名　称：</label>
                    <input class="input" type="text" value="${listEditDepartment.username } " name="department.username" placeholder="请输入部门名称" minlength="4" maxlength="25" required>
                    <br>
                    <br>
                    <br>
                    <label>描　述：</label>
                    <textarea name="department.description" rows="10" cols="50" style="vertical-align:top" style="width:40%; height:50%;" placeholder="请输入部门描述" required>${listEditDepartment.description }</textarea>
                    <div class="content-form-foot">                        
                        <div class="content-form-foot-menu">
                            <button type="button" class="submit color-green">保存</button>
                            <button type="button" class="cancel color-red">取消</button>
                            <button type="reset" class="color-yellow">重置</button>　
                        </div>
                    </div>
                    </tbody>
                </table>
            </div>
             <div style="display:none;">
            <input  type="text" name="department.id" value="${listEditDepartment.id }" >
            </div>
             </form> 
        </div>
    <script type="text/javascript">
     $(function(){
    	//取消
       $(".cancel").on("click",function(){
    	  $(".container").load("queryDepartment",function(){
    		     common();
				initDepartment();
    	  });
       });
    	//提交表单
    	 $(".submit").on("click",function(){
         	$.ajax({
                url: "editDepartmentSave",
                type: "POST",
                data:$('#from').serialize(),
                success:function(data){
                	if(data.message == "success"){
                		$(".container").load("queryDepartment",function(){
                			toastr.success("修改部门成功!")
                   		    common();
        					initDepartment();
                  	  });
                	}else{
                		toastr.error("修改部门失败!")
                	}
                }
    	});
    	 }); 
     });
     </script>
  