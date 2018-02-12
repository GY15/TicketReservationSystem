<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>发布计划</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/venue_plan.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <div class="row navigation">
        <ul class="nav nav-pills">
            <li><a href="#">查看我的计划</a></li>
            <li><a href="#">发布一个计划</a></li>
            <li><a href="#">查看统计信息</a></li>
            <li><a href="#">查看场馆信息</a></li>
        </ul>
    </div>

    <div class="row jumbotron banner-desc2 ">
        <div class="text-center">

            <div>
                <div class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">开始时间</label>
                        <div class='input-group date col-md-7' id='datetimeStart' style="width:170px">
                            <input id="startDate" type='text' style="margin-left: 15px;width: 170px"
                                   class="form-control"/>
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">结束时间</label>
                        <div class='input-group date col-md-7' id='datetimeEnd' style="width:170px">
                            <input id="endDate" type='text' style="margin-left: 15px;width: 170px"
                                   class="form-control"/>
                            <span class="input-group-addon">
                                  <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="plan_type" name="plan_type"
                                   placeholder="输入类型">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="plan_description">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="plan_description" name="plan_description"
                                   placeholder="输入计划的描述">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">座位价格</label>
                        <div class="col-md-8" style="margin-left: 15px">
                            <div class='seat_set'>
                                <div class="form-group" style="margin-top:10px">
                                    <div class="seat-char" hidden>a</div>
                                    <label class="col-md-2  form-label-sm">普通座位</label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control seat_value"
                                               placeholder="输入座位价格">
                                    </div>
                                </div>
                            </div>
                            <div class='seat_set'>
                                <div class="form-group" style="margin-top:10px">
                                    <div class="seat-char" hidden>a</div>
                                    <label class="col-md-2  form-label-sm">普通座位</label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control seat_value"
                                               placeholder="输入座位价格">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary col-md-4 col-md-offset-4" id="publish_btn" style="margin-top: 30px">
                        发&nbsp;&nbsp;&nbsp;布&nbsp;&nbsp;&nbsp;计&nbsp;&nbsp;&nbsp;划
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
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/dbDatePicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script>
    $("#publish_btn").bind("click",function () {
        var startTime = $("#startDate").val();
        var endTime = $("#endDate").val();
        var type = $("#plan_type").val();
        var description  = $("#plan_description").val();
        var seat_value = [];
        alert(seat_value);
        $('.seat_set').each(function () {
            var seat = {};
            seat.type = $(this).find('.seat-char').eq(0).html();
            seat.name = $(this).find('label').eq(0).html();
            seat.value = $(this).find('.seat_value').eq(0).html();
            seat_value.push(seat);
        });
        alert(JSON.stringify(seat_value));
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/publish",
            data: {
                "email": $('#reg_email').val()
            },

            success: function (result) {
                $('.errorMessage').html(result);
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
                $('.button-valid').html("<i class=\"icon-envelope\n\"></i>");
                $('.button-valid').bind("click")
            },
            error: function (result) {
                $('.button-valid').html("<i class=\"icon-envelope\n\"></i>");
                alert("发生了未知的错误");
            }
        });

    })
</script>

</body>
</html>
