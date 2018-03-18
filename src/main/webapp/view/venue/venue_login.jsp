<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2018/2/6
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录、注册</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../../css/seat-chart.css">
    <link href="../../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/font-awesome.min.css">
    <link href="../../css/home.css" rel="stylesheet">
    <link href="../../css/button.css" rel="stylesheet">
</head>

<body>

<div class="banner-holder">
    <div class="banner-image-holder" style="z-index: 1">
        <div role="banner">
            <img alt="Background" src="../../img/home.jpg">
            <div class="fh5co-overlay"></div>
        </div>
    </div>


    <div class="jumbotron banner-desc col-md-4 col-md-offset-4 loginPanel" style="z-index: 10">
        <div class="container text-center">
            <h1>淘 票</h1>
            <div style="margin-top: 80px">
                <form class="form-horizontal" action="/venue/login" method="POST" role="form">
                    <div class="form-group" style="margin-top: 50px">
                        <label class="col-md-3 col-md-offset-1 control-label" for="login_id"
                               style="font-size: 18px;color:whitesmoke">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name='login_id' id="login_id" value=""
                                   placeholder="请输入账号">
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
                            <input type="checkbox" name="remember" id="remember" class="remember">记住密码
                        </label>
                    </div>

                    <button type="submit" class="btn btn-primary col-md-6 col-md-offset-3" style="margin-top: 30px"
                            onclick="">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                    </button>
                </form>
                <a class="regBt col-md-2">注 册</a>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
            <a class="loginBt" href="/member/" style="position: absolute;right:20px;bottom: 10px">会员登录</a>
        </div>

    </div>
    <div class="jumbotron banner-desc2 col-md-6 col-md-offset-3 registerPanel" style="z-index: 10" hidden>
        <div class="container text-center">
            <h2>淘 票</h2>
            <div>
                <div class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_venue"
                               style="font-size: 18px;color:whitesmoke">帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <div class="col-md-3 input-group" style="margin-left: 0px;left: 15px;">
                            <input type="text" class="form-control" name="reg_venue" id="reg_venue"
                                   placeholder="点击获取帐号" style="width: 210px;" disabled>
                            <span class="input-group-addon button-valid ">  <i class="icon-download-alt"></i></span>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_name"
                               style="font-size: 18px;color:whitesmoke">场馆名称</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="reg_name" id="reg_name"
                                   placeholder="输入场馆名称">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_password"
                               style="font-size: 18px;color:whitesmoke">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password" name="reg_password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_password2"
                               style="font-size: 18px;color:whitesmoke">确认密码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password2" name="reg_password2"
                                   placeholder="请再次输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label"
                               style="font-size: 18px;color:whitesmoke">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="reg_province" name="reg_province"
                                   placeholder="省份">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="reg_city" name="reg_city"
                                   placeholder="市">
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="reg_location" name="reg_location"
                                   placeholder="详细地址">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_password2"
                               style="font-size: 18px;color:whitesmoke">座位情况</label>
                        <div class="col-md-8" style="margin-left: 15px">
                            <div class='seat_set'>
                            </div>
                            <button class="button button-primary button-circle button-small" style="margin: 10px"
                                    id="add_seat_btn"><i class="icon-plus"></i></button>
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary col-md-6 col-md-offset-3 submit-btn"
                            style="margin-top: 30px">
                        申&nbsp;&nbsp;&nbsp;请&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;册
                    </button>
                </div>

                <a class="loginBt col-md-3">返回登录</a>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
        </div>
    </div>
</div>

<jsp:include page="venue_modify.jsp"></jsp:include>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../js/bootstrap.js"></script>
<script src="../../js/format-valid.js"></script>
<script src="../../js/icheck.js"></script>

<!--<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>-->
<script src="../../js/jquery.seat-charts.js"></script>
<script src="../../js/icheck.js"></script>
<script src="../../js/seat-create.js"></script>

<script>
    $(document).ready(function () {
        var message = "<%=session.getAttribute("errorMessage")%>";
        if(message!=null&&message!= "0"){
            $('.errorMessage').html(message);
            setTimeout(function () {
                $('.errorMessage').html(" ");
                <%session.setAttribute("errorMessage","0");%>
            }, 2000);
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

    $('.button-valid').bind("click", function valid() {
        $('.button-valid').html("<i class=\"icon-spinner icon-spin\"></i>");
        $('.button-valid').unbind("click")
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/valid",

            success: function (result) {
                $('#reg_venue').val(result);
                $('.button-valid').html("<i class=\"icon-download-alt\"></i>");
                $('.button-valid').bind("click", valid);
            },
            error: function (result) {
                $('.button-valid').html("<i class=\"icon-download-alt\"></i>");
                $('.button-valid').bind("click", valid);
                alert("发生了未知的错误，刷新后重试");
            }
        });
    });


    $(".submit-btn").bind("click", function () {
        var ss =JSON.stringify(seats);

        $.ajax({
            type: "post",
            async: true,
            url: "/venue/register_venue",
            data: {
                "venueid": $('#reg_venue').val(),
                "password": $('#reg_password').val(),
                "name": $('#reg_name').val(),
                "province": $('#reg_province').val(),
                "city": $('#reg_city').val(),
                "location": $('#reg_location').val(),
                "seat_info": JSON.stringify(seats)
            },

            success: function (result) {
                $(".registerPanel").hide();
                $(".loginPanel").show();
                $('.errorMessage').html("注册成功，账号"+  $('#reg_venue').val());
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 4000);
            },
            error: function (result) {
                $('.errorMessage').html("注册失败，请重新检查注册信息");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });
    })
</script>

</body>
</html>
