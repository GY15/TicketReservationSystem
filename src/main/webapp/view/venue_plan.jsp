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
    <link href="../css/venue_plan.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
    <link href="../css/bootstrap-select.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <jsp:include page="navigation.jsp"></jsp:include>
    <div class="row">
        <c:choose>
            <c:when test="${plans!=null}">
                <c:forEach items="${plans}" var="plan" varStatus="vs">
                    <div class="planBlock">
                        <form action="/venue/open_plan_detail" method="post">
                            <div class="row">
                                <input type="text" class="planid" name="planid" hidden value="${plan.planid}"/>
                                <h5 class="col-md-offset-1 col-md-1 plan_name" style="font-size: 130%">${plan.type}</h5>
                                <h5 class="col-md-5 plan_name"
                                    style="font-size: 90%;margin-top: 16px">${plan.description}</h5>
                                <h5 style="font-size: 90%;margin-top: 16px">${plan.seatMaps[0].type[0].value}左右</h5>
                            </div>
                            <div class="row">
                                <div class="row col-md-5 col-md-offset-1">
                                    <div class="row">
                                        <span class="col-md-2 col-md-offset-2 plan_font">开始时间</span><span
                                            class="col-md-8 small">${plan.startTime}</span>
                                    </div>
                                    <div class="row">
                                        <span class="col-md-2 col-md-offset-2 plan_font">结束时间</span><span
                                            class="col-md-8 small">${plan.endTime}</span>
                                    </div>
                                </div>
                                <div class="row col-md-6">
                                    <div class="col-md-5">
                                        <span class="col-md-4" style="margin-top: 10px;margin-right: -20px">区域 :</span>
                                        <select data-actions-box="true" data-size="10" style="width: 300px"
                                                class="selectpicker show-tick col-md-8" name="selectpicker">
                                            <c:forEach items="${plan.seatMaps}" var="seat" varStatus="vs">
                                                <option>${seat.block}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <input type="submit" class=" btn btn-sm btn-info buy_ticket" value="现场购票"
                                           style="margin-left: -25px;margin-top: 5px">
                                    <button class=" btn btn-sm btn-info col-md-offset-1 check_ticket"
                                            style="margin-top: 5px">检票登记
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

<div class="modal fade" id="buyTicketModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 30px;height:710px;width: 90%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                <h4 class="modal-title" style="margin-top: 0px">现场购票</h4>
            </div>
            <div class="modal-body" style="height: 580px">
                <div class="row">
                    <label class="col-md-1 col-md-offset-2 label-font" align="right">区域名称</label>
                    <div class="col-md-1">
                        <input type="text" id="localName" class="form-control input-style" disabled>
                    </div>
                    <label class="col-md-1 label-font" align="right">区域说明</label>
                    <div class="col-md-2">
                        <input type="text" id="localDescription" class="form-control input-style" disabled>
                    </div>
                    <div class="col-md-2 errorMessage"></div>
                    <div class="col-md-1" style="font-size: 130%;margin-top: 4px;margin-left:115px">选座详情</div>
                </div>
                <div class="seat-panel row" style="margin-top: 5px;">
                    <div class="col-md-8 seat-container col-md-offset-1">
                        <div id="seat-area">
                        </div>
                    </div>
                    <div class="col-md-3 booking-details">
                    </div>
                </div>
            </div>
            <%--<div class="modal-footer">--%>
            <%--<div class="login-btn-group">--%>
            <%--<button type="button" class="btn btn-primary" onclick="saveSeat()">保存</button>--%>
            <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
            <%--</div>--%>
            <%--</div>--%>
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
    var plans;
    $(document).ready(function () {
        $('.selectpicker').selectpicker();
        plans = JSON.parse('${plansJson}');
    });
    $(".check_ticket").bind("click", function () {
        alert(12);
        return false;
    });
</script>

</body>
</html>
