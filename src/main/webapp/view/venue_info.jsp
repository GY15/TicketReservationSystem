<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSC Demo</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/venue_plan.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <jsp:include page="navigation.jsp"></jsp:include>
    <div class="row jumbotron banner-desc2 ">
        <div class="text-center">

            <div>
                <div class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_venue">帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <div class="col-md-3 input-group" style="margin-left: 0px;left: 15px;">
                            <input type="text" class="form-control" name="reg_venue" id="reg_venue" style="width: 210px;" disabled>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_name">场馆名称</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="reg_name" id="reg_name"
                                   placeholder="输入场馆名称">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_password">旧&nbsp;&nbsp密&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password" name="reg_password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password" name="reg_password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_password2">确认密码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password2" name="reg_password2"
                                   placeholder="请再次输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
                        <div class="col-md-1">
                            <input type="text" class="form-control" id="reg_province" name="reg_province"
                                   placeholder="省份">
                        </div>
                        <div class="col-md-1">
                            <input type="text" class="form-control" id="reg_city" name="reg_city"
                                   placeholder="市">
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="reg_location" name="reg_location"
                                   placeholder="详细地址">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_password2">座位情况</label>
                        <div class="col-md-8" style="margin-left: 15px">
                            <div class='seat_set'>

                            </div>
                            <button class="button button-primary button-circle button-small" style="margin: 10px" id="add_seat_btn"><i class="icon-plus"></i></button>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary col-md-4 col-md-offset-4" style="margin-top: 30px">
                        申&nbsp;&nbsp;&nbsp;请&nbsp;&nbsp;&nbsp;修&nbsp;&nbsp;&nbsp;改
                    </button>
                </div>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
        </div>
    </div>
</div>




<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/format-valid.js"></script>
<script src="../js/icheck.js"></script>

<!--<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>-->
<script src="../js/jquery.seat-charts.js"></script>
<script src="../js/icheck.js"></script>

<script>

</script>

</body>
</html>