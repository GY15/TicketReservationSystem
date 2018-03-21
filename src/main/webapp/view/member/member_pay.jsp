<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单支付</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../../css/seat-chart.css">
    <link href="../../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/font-awesome.min.css">
    <link href="../../css/member.css" rel="stylesheet">
    <link href="../../css/button.css" rel="stylesheet">
    <link href="../../css/bootstrap-select.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6" style="text-align: center"><h2>支付界面</h2></div>
        <div class="col-md-offset-1" style="margin-top: 30px">倒计时：<span id="time">${second}</span>s</div>
        <div class="col-md-7 col-md-offset-4">
            <div class="row">
                <h4 class="col-md-offset-1" style="">订单号: <b class="orderid">${order.orderid}</b></h4>
            </div>
            <div class="row">
                <h4 class="col-md-offset-1" style="">开始时间: <b>${order.startTime}</b></h4>
            </div>
            <div class="row">
                <h4 class="col-md-offset-1" style="">结束时间: <b>${order.endTime}</b></h4>
            </div>
            <div class="row">
                <h4 class="col-md-offset-1" style="">演出简介: <b>${order.planType}&nbsp;&nbsp;&nbsp;${order.planName}</b></h4>
            </div>
            <div class="row">
                <h4 class="col-md-offset-1" style="">座位: <b>${order.block}&nbsp;&nbsp;&nbsp;${order.tickets}</b></h4>
            </div>
            <div class="row">
                <h4 class="col-md-offset-1" style="">订单价格: <b>￥${order.value}</b></h4>
            </div>
            <div class="row">
                <h4 class="col-md-offset-1 col-md-2" style="">支付密码:</h4> <div class="col-md-4"> <input value="123" type="password" class="form-control"/></div>
            </div>
            <div class="col-md-offset-2" style="margin-top: 20px">
                <button class="btn col-md-2 btn-info pay_order">支付</button>
            </div>
            <div class="errorMessage col-md-offset-3"></div>
        </div>
    </div>
</div>

</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../js/bootstrap.js"></script>
<script src="../../js/format-valid.js"></script>
<script src="../../js/icheck.js"></script>


<script src="../../js/bootstrap-select.js"></script>
<script src="../../js/icheck.js"></script>

<script>
    function sum() {
        $("#time").html( $("#time").html()-1)
        setTimeout( "sum();", 1000 );
    }
    sum();

    $(".pay_order").bind("click", function () {
        $.ajax({
            type: "post",
            async: true,
            url: "/member/pay_order",
            data: {
                "orderid": $(".orderid").html(),
            },
            success: function (result) {
                if (result == "success") {
                    $('.errorMessage').html("支付成功");
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                        window.location.href="/member/plan_page";
                    }, 1000);
                } else {
                    $('.errorMessage').html(result);
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                    }, 3000);
                }
            },
            error: function (result) {
                $('.errorMessage').html("你的订单有误");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 3000);
            }
        });
    });
</script>

</body>
</html>
