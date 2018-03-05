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
                                <h4 class="col-md-offset-9"><b>${order.number}张&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><span style="font-size: 90%">${order.state}</span></h4>
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


<div class="modal fade" id="checkTicketModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 130px;height:710px;width: 40%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                <h4 class="modal-title" style="margin-top: 0px">现场购票</h4>
            </div>
            <div class="modal-body" style="margin-top: 70px;margin-bottom: 100px">
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-5 label-font description" align="right"></label>
                    <label class="col-md-3 label-font block" align="center"></label>
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-3 label-font" align="right">检票</label>
                    <div class="col-md-5">
                        <input type="text" id="check_order" class="form-control input-style">
                    </div>
                    <div class="col-md-1">
                        <button class="btn btn-primary check_btn">确认订单号</button>
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
    <%--$(document).ready(function () {--%>
    <%--$('.selectpicker').selectpicker();--%>
    <%--plans = JSON.parse('${plansJson}');--%>
    <%--});--%>
    var planid = "";
    var block ="";
    $(".check_ticket").click(function () {
        window.location.href = "/member/goto_pay?orderid="+$(this).parent().parent().find(".orderid").eq(0).html()
        return false;
    });
    $(".check_btn").bind("click", function () {
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/check_ticket",
            data: {
                "orderid": $("#check_order").val(),
                "planid": planid
            },
            success: function (result) {
                if(result!=""){
                    $('.errorMessage').html("正在出票  "+result);
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                    }, 5000);
                }else {
                    $('.errorMessage').html("你的订单号有误");
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                    }, 3000);
                }
            },
            error: function (result) {
                $('.errorMessage').html("你的订单号有误");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 3000);
            }
        });
    });
</script>

</body>
</html>
