let deleBtn = document.getElementById('delete');
$(function () {
    $("#checkAll").click(function () {
        if($(this).prop("checked")){
            $("input[name='checkAll']").prop("checked",true);
        }else{
            $("input[name='checkAll']").prop("checked",false);
        }
    });
});
$("#tb").bootstrapTable({
     search: true,   //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
    pagination: true,   //是否显示分页条
    pageSize: 10,   //一页显示的行数
    paginationLoop: true,   //是否开启分页条无限循环，最后一页时点击下一页是否转到第一页

});

deleBtn.onclick = function () {
   //用getCheckedIds()方法获取所有选中的id
    let ids = getCheckedIds();
    if (ids.length === 0) {
        alert("请选择要删除的数据");
        return;

    }

    if (confirm("确定要删除吗？")) {
        let hd = JSON.stringify(ids);
        $.ajax({
            //将要删除的id传到后台，显示在Tes.html页面
            url: "delete",
            type: "post",
            data: {
                "ids": hd.toString()
            },
            error: function () {
                alert("删除失败");
            },
            success: function (data) {
                alert("删除成功");
              //刷新页面
                window.location.reload();
            }
        });

    }
}
//返回有on的复选框的id
function getCheckedIds() {
    let ids = [];
    let checks = document.getElementsByName("checkAll");
    for (let i = 0; i < checks.length; i++) {
        if (checks[i].checked) {
            ids.push(checks[i].getAttribute("id"));
        }
    }
    //对ids进行处理，去掉check只留下数学
    for (let i = 0; i < ids.length; i++) {
        ids[i] = ids[i].substring(5);
    }
    //如果ids中有all，就把all删除
    if (ids.indexOf("All") !== -1) {
        ids.splice(ids.indexOf("All"), 1);
    }
    return ids;


}