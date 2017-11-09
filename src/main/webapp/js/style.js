 $(function() {
        //全选 
        $(".check-all-btn").on("click", function() {
            $("input[name='checkbox']").prop("checked", $(".check-all-btn").prop("checked"));
        });
      
        //删除一行
        $(".delete").on("click", function() {
            $("input[name='checkbox']:checked").parent().parent().remove();
        });
    });