<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2018/2/6
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>会员登录</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/home.css" rel="stylesheet">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
</head>
<body>
<header>

    <div class="banner-holder">
        <div class="banner-image-holder" style="z-index: 1">
            <div role="banner">
                <img alt="Background" src="../img/home.jpg">
                <div class="fh5co-overlay"></div>
            </div>
        </div>


        <div class="jumbotron banner-desc col-md-4 col-md-offset-4" style="z-index: 10">
            <div class="container text-center">
                <h1>淘 票</h1>
                <div class="loginPanel" style="display:none;margin-top: 80px">
                    <form class="form-horizontal" action="/login" method="POST" role="form">
                        <div class="form-group" style="margin-top: 50px">
                            <label class="col-md-3 col-md-offset-1 control-label" for="login_email"
                                   style="font-size: 18px;color:whitesmoke">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name='login_email' id="login_email" value=""
                                       placeholder="请输入用户名">
                            </div>
                        </div>

                        <div class="form-group" style="margin-top: 40px">
                            <label class="col-md-3 col-md-offset-1 control-label" for="login_password"
                                   style="font-size: 18px;color:whitesmoke">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" name="login_password" id="login_password"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="col-md-4 col-md-offset-8" style="color: whitesmoke">
                            <label id="rem-password">
                                <input type="checkbox" class="remember">记住密码
                            </label>
                        </div>

                        <button type="submit" class="btn btn-primary col-md-6 col-md-offset-3" style="margin-top: 30px"
                                onclick="login()">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                        </button>
                    </form>
                    <a class="regBt col-md-2">注 册</a>
                </div>

                <div class="registerPanel" style="display: none">
                    <form class="form-horizontal" action="/member/registerMember" method="post" role="form">
                        <div class="form-group" style="margin-top: 30px">
                            <label class="col-md-3 col-md-offset-1 control-label" for="reg_email"
                                   style="font-size: 18px;color:whitesmoke">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
                            <div class="col-md-6 input-group" style="margin-left: 0px;left: 15px;">
                                <input type="text" class="form-control" name="reg_email" id="reg_email"
                                       placeholder="请输入邮箱" style="width: 210px;">
                                <span class="input-group-addon button-valid">  <i class="icon-envelope"></i></span>
                            </div>
                        </div>

                        <div class="form-group" style="margin-top: 20px">
                            <label class="col-md-3 col-md-offset-1 control-label" for="valid_word"
                                   style="font-size: 18px;color:whitesmoke">验&nbsp;&nbsp;证&nbsp;&nbsp;码</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="valid_word" id="valid_word"
                                       placeholder="点击从邮箱中获取验证码">
                            </div>
                        </div>

                        <div class="form-group" style="margin-top: 20px">
                            <label class="col-md-3 col-md-offset-1 control-label" for="login_password"
                                   style="font-size: 18px;color:whitesmoke">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="reg_nickname" id="reg_nickname"
                                       placeholder="请输入昵称">
                            </div>
                        </div>

                        <div class="form-group" style="margin-top: 20px">
                            <label class="col-md-3 col-md-offset-1 control-label" for="reg_password"
                                   style="font-size: 18px;color:whitesmoke">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" id="reg_password" name="reg_password"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 20px">
                            <label class="col-md-3 col-md-offset-1 control-label" for="reg_password2"
                                   style="font-size: 18px;color:whitesmoke">确认密码</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" id="reg_password2" name="reg_password2"
                                       placeholder="请再次输入密码">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary col-md-6 col-md-offset-3" style="margin-top: 30px">
                            注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册
                        </button>
                    </form>

                    <a class="loginBt col-md-3">返回登录</a>
                </div>
                <div class="col-md-12 errorMessage">
                </div>
                <a class="loginBt" style="position: absolute;right:20px;bottom: 10px">场馆登录</a>
            </div>
        </div>
    </div>
</header>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/format-valid.js"></script>
<script src="../js/icheck.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        var type = "<%=session.getAttribute("type")%>";
        if (type == "fail") {
            $(".registerPanel").show();
            $('.errorMessage').html("验证码错误或已经被注册");
            setTimeout(function () {
                $('.errorMessage').html(" ")
            }, 2000);
            <%session.setAttribute("type","init");%>
        } else {
            $(".loginPanel").show();
            if (type == "success") {
                $('.errorMessage').html("用户注册成功");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
                $('#login_email').val("<%=session.getAttribute("registerMember")%>")
            }
            <%session.setAttribute("type","init");%>
        }
    });
    $('.loginBt').click(function () {
        $('.loginPanel').slideDown();
        $('.registerPanel').slideUp("fast");
    });
    $('.regBt').click(function () {
        $('.loginPanel').slideUp();
        $('.registerPanel').slideDown("fast");
    });
    $('.remember').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
    });

    $('.button-valid').bind("click", valid_email);

    function valid_email() {
        if (!check_email($('#reg_email').val())) {
            $('.errorMessage').html("邮箱格式不对");
            setTimeout(function () {
                $('.errorMessage').html(" ")
            }, 2000);
            return 0;
        } else {
            $('.button-valid').html("<i class=\"icon-spinner icon-spin\"></i>");
            $('.button-valid').unbind("click")
            $.ajax({
                type: "post",
                async: true,
                url: "/member/valid",
                data: {
                    "email": $('#reg_email').val()
                },

                success: function (result) {
                    $('.errorMessage').html(result);
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                    }, 2000);
                    $('.button-valid').html("<i class=\"icon-envelope\n\"></i>");
                    $('.button-valid').bind("click",valid_email)
                },
                error: function (result) {
                    $('.button-valid').html("<i class=\"icon-envelope\n\"></i>");
                    alert("发生了未知的错误");
                }
            });

        }
    }
</script>

</body>
</html>