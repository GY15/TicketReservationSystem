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
    <title>经理登录</title>

    <link href="../../css/bootstrap.css" rel="stylesheet">
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
                <form class="form-horizontal" action="/manage/login" method="POST" role="form">
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

                    <button type="submit" class="btn btn-primary col-md-6 col-md-offset-3" style="margin-top: 30px"
                            onclick="">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                    </button>
                </form>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
            <a class="loginBt" href="/member/" style="position: absolute;right:20px;bottom: 10px">会员登录</a>
        </div>

    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../js/bootstrap.js"></script>

<!--<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>-->

<script>
    $(document).ready(function () {
        var message = "<%=session.getAttribute("errorMessage")%>";
        if(message=="1"){
            $('.errorMessage').html("账号或者密码错误");
            setTimeout(function () {
                $('.errorMessage').html(" ")
            }, 2000);
        }
    });
</script>

</body>
</html>
