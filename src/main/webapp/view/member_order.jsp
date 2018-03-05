<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看我的订单</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/member.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
    <link href="../css/bootstrap-select.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <jsp:include page="member_navigation.jsp"></jsp:include>
    <ul class="nav nav-tabs">
        <li><a href="/member/my_order?state=ALL">全部订单</a></li>
        <li><a href="/member/my_order?state=ARRIVE">已出票</a></li>
        <li><a href="/member/my_order?state=NOT_PAY">未支付</a></li>
        <li><a href="/member/my_order?state=PAY">已支付</a></li>
        <li><a href="/member/my_order?state=REFUND">已退票</a></li>
        <li><a href="/member/my_quick_order">快速订票查看</a></li>
    </ul>
    <div class="row">
        <c:choose>
            <c:when test="${orders!=null}">
                <c:forEach items="${orders}" var="order" varStatus="vs">
                    <div class="orderBlock">
                            <div class="row">
                                <h4 class="col-md-offset-1 col-md-3" style="">订单号: <b class="orderid">${order.orderid}</b></h4>
                                <h5 class=" plan_id" hidden>${order.planid}</h5><h5 class="venue_id" hidden>${order.venueid}</h5>
                                <h4 class="col-md-offset-9"><b>￥<span class="order_value">${order.value}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><span style="font-size: 90%">${order.state}</span></h4>
                            </div>
                            <div class="row" style="margin-bottom: 4px">
                                <span class="col-md-2" style="margin-left: 110px"><b class="order_font">场馆:</b>${order.venueName}</span>
                                <span class="col-md-4 col-md-offset-1" style="margin-left: 70px" ><b class="order_font">地址:&nbsp;&nbsp;&nbsp;&nbsp;</b>${order.province}&nbsp;&nbsp;&nbsp;${order.city}&nbsp;&nbsp;&nbsp;${order.location}</span>
                            </div>
                            <div class="row">
                                <div class="row col-md-4">
                                    <div class="row">
                                        <span class="col-md-4 col-md-offset-2 order_font">开始时间</span><span
                                            class="col-md-5">${order.startTime}</span>
                                    </div>
                                    <div class="row">
                                        <span class="col-md-4 col-md-offset-2 order_font">结束时间</span><span
                                            class="col-md-5">${order.endTime}</span>
                                    </div>
                                </div>
                                <div class="row col-md-5">
                                    <div class="row">
                                        <span class="col-md-2 order_font" >${order.planType}:</span>
                                        <span>${order.planName}&nbsp;</span>
                                    </div>
                                    <div class="row">
                                        <span class="col-md-2 order_font" >座位: </span>
                                        <span>${order.block}&nbsp;${order.tickets}</span>
                                    </div>
                                </div>
                                <button class="btn btn-sm btn-info detail">查看订单详情
                                </button>
                                <c:choose>
                                <c:when test="${order.state==('已支付')}">
                                    <button class=" btn btn-sm btn-info refund">退票
                                    </button>
                                </c:when>
                                <c:when test="${order.state==('未支付')}">
                                    <button class=" btn btn-sm btn-info goto_pay">去支付
                                    </button>
                                </c:when>
                                </c:choose>
                            </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div> 你可以去买张票</div>
            </c:otherwise>
        </c:choose>
    </div>

</div>


<div class="modal fade" id="refundModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 130px;height:710px;width: 40%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                <h4 class="modal-title" style="margin-top: 0px">退票</h4>
            </div>
            <div class="modal-body" style="margin-top: 70px;margin-bottom: 100px">
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-5 label-font description" align="right"></label>
                    <label class="col-md-3 label-font block" align="center"></label>
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-5 col-md-offset-1 label-font order_id" align="right"></label>
                    <div class="col-md-1">
                        <button class="btn btn-primary sure_refund">确认退票</button>
                    </div>
                </div>
                <div class="errorMessage col-md-offset-3"></div>
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
<script src="../js/bootstrap-select.js"></script>
<script src="../js/icheck.js"></script>

<script>

    $(".goto_pay").click(function () {
        window.location.href = "/member/goto_pay?orderid="+$(this).parent().parent().find(".orderid").eq(0).html()
        return false;
    });
    var orderid;
    $(".refund").bind("click", function () {
        $('#refundModal').modal();
        orderid = $(this).parent().parent().find(".orderid").eq(0).html();
        var value = $(this).parent().parent().find(".order_value").eq(0).html()
        $.ajax({
            type: "post",
            async: true,
            url: "/member/get_refund_rate",
            data: {
                "orderid": orderid,
            },
            success: function (result) {
                $('.order_id').html("订单可以退款 " + result*value+" 元");
            },
            error: function (result) {
                $('.errorMessage').html("刷新后重试");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 3000);
            }
        });
    });

    $(".sure_refund").bind("click", function () {
        $('.errorMessage').html("正在退票");
        alert(123);
        $.ajax({
            type: "post",
            async: true,
            url: "/member/refund",
            data: {
                "orderid":  orderid,
            },
            success: function (result) {
                    $('.errorMessage').html("退票成功,退款"+result+"元");
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                        window.location.reload();
                    }, 1000);
            },
            error: function (result) {
                $('.errorMessage').html("退票失败，刷新后重试");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 3000);
            }
        });
    });
</script>

</body>
</html>
