$("#signbtn").click(function () {
    if (checkPassword()) {
    $.ajax({
        url: "signup",
        type: "post",
        data:{"username":$("#user").val(),"password":$("#password1").val()},
        error: function () {
            alert("注册失败");
        },
        success: function (data) {
            alert("注册成功");
            window.location.href = "login";
        }
    })}
    else {

        alert("注册失败");
    }
})
function  checkPassword() {
    let password = document.getElementById("password1").value;
    let password2 = document.getElementById("password2").value;
   //非空判断
    if (password === "" || password2 === "") {
        alert("密码不能为空");
        return false;
    }
    //密码长度判断
    if (password.length < 6 || password2.length < 6) {
        alert("密码长度不能小于6位");
        return false;
    }
    //密码是否一致
    if (password !== password2) {
        alert("两次密码不一致");
        return false;
    }
    return true;
}