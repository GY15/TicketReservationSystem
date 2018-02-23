<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看计划</title>

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
    <div class="row">
        <c:choose>
            <c:when test="${plans!=null}">
                <c:forEach items="${plans}" var="plan" varStatus="vs">
                    <div class="planBlock">
                        <form action="/member/open_plan_detail" method="post">
                            <div class="row">
                                <input type="text" class="planid" name="planid" hidden value="${plan.planid}"/>
                                <h5 class="col-md-offset-1 col-md-1" style="font-size: 130%">${plan.type}</h5>
                                <h5 class="col-md-3 plan_name" style="font-size: 90%;margin-top: 16px">${plan.description}</h5>
                                <h5 class="col-md-4"  style="font-size: 90%;margin-top: 16px">${plan.province} ${plan.city} ${plan.location}</h5>
                                <h5 style="font-size: 90%;margin-top: 16px">${plan.seatMaps[0].type[0].value}左右</h5>
                            </div>
                            <div class="row">
                                <div class="row col-md-4 col-md-offset-1">
                                    <div class="row">
                                        <span class="col-md-3 col-md-offset-2 plan_font">开始时间</span><span
                                            class="col-md-7 small">${plan.startTime}</span>
                                    </div>
                                    <div class="row">
                                        <span class="col-md-3 col-md-offset-2 plan_font">结束时间</span><span
                                            class="col-md-7 small">${plan.endTime}</span>
                                    </div>
                                </div>
                                <div class="row col-md-7">
                                    <div class="col-md-4">
                                        <span class="col-md-4" style="margin-top: 10px;margin-right: -20px">区域 :</span>
                                        <select data-actions-box="true" data-size="10" style="width: 300px"
                                                class="selectpicker show-tick col-md-8" name="selectpicker">
                                            <c:forEach items="${plan.seatMaps}" var="seat" varStatus="vs">
                                                <option>${seat.block}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <input type="submit" class=" btn btn-sm btn-info buy_ticket col-md-2" value="选座购票"
                                           style="margin-left: -25px;margin-top: 5px">
                                    <div class="col-md-4 col-md-offset-1 form-group">
                                        <span class="col-md-7" style="margin-top: 10px;margin-right: -20px">立即购票 :</span>
                                        <input type="text" class="form-control col-md-offset-2" style="width: 50px;margin-top: 5px" name="ticket_num" id="ticket_num">
                                    </div>
                                    <button class=" btn btn-sm btn-info col-md-1 buy_ticket"
                                            style="margin-top: 8px;margin-left: -45px">确认
                                    </button>
                                </div>
                            </div>
                        </form>


                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div> 你可以去船舰一个任务</div>
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
        $('#checkTicketModal').modal();
        planid = $(this).parent().parent().parent().find(".planid").eq(0).val();
        block =$(this).parent().find(".selectpicker").eq(0).val();
        $(".block").html(block);
        $(".description").html($(this).parent().parent().parent().find(".plan_name").eq(0).html());
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
