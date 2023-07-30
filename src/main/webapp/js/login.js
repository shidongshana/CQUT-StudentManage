$("#loginbtn").click(function () {

    $.ajax({
        url: "login",
        type: "post",
        data:{"username":$("#user").val(),"password":$("#password").val()},
        error: function () {
            alert("登录失败");
        },
        success: function (data) {
         if( data=== "success"){
                alert("登录成功");
                //跳转到table
                window.location.href = "table";
                window.location.replace("table")
         }
            else if (data==="fail1") {
                alert("密码错误");
                //清空密码
                $("#password").val("");
         }
            else if (data==="fail2") {
                alert("用户名不存在");
                //清空用户名
                $("#user").val("");
                $("#password").val("");
         }
        }
    })
});
function check(){
   let username = document.getElementById("user").value;
    let password = document.getElementById("password").value;
    //非空判断
    if (username === "" || password === "") {
        alert("用户名或密码不能为空");
        return false;
    }
    return true;
}